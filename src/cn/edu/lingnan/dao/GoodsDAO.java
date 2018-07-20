package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import cn.edu.lingnan.dto.GoodsDTO;
import cn.edu.lingnan.dto.UserDTO;
import cn.edu.lingnan.util.DataAccess;

public class GoodsDAO {
	//查询goods整张表信息
	public Vector<GoodsDTO> findAllGoodsInfo(int stateNum){
		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from goods where gstate = '"+stateNum+"'";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				GoodsDTO g = new GoodsDTO();
				g.setGid(rs.getString("gid"));
				g.setGname(rs.getString("gname"));
				g.setPrice(rs.getInt("price"));
				g.setCount(rs.getInt("count"));
				v.add(g);
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return v;
	}
	
	////查询goods整张表信息(库存大于0)
	public Vector<GoodsDTO> findAllGoodsInfoAboutCount(int stateNum){
		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from goods where gstate = '"+stateNum+"' and count > 0";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				GoodsDTO g = new GoodsDTO();
				g.setGid(rs.getString("gid"));
				g.setGname(rs.getString("gname"));
				g.setPrice(rs.getInt("price"));
				g.setCount(rs.getInt("count"));
				v.add(g);
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return v;
	}
	
	//根据gid查询gname
	public String findGnameByGid(String gid){
		String gname = null;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from goods where gid = '"+gid+"' and gstate = 1";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			rs.next();
			gname = rs.getString(2);
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return gname;
	}
	
	//根据gid查询price
	public int findPriceByGid(String gid){
		int price = 0;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from goods where gid = '"+gid+"' and gstate = 1";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			rs.next();
			price = rs.getInt(3);
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return price;
	}
	
	//根据gid删除表中一条记录
	public boolean deleteGoodsById(String id){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		Statement stat1 = null;
		Statement stat2 = null;
		Statement stat3 = null;
		ResultSet rs =null;
		ResultSet rs1 =null;
		ResultSet rs2 =null;
		ResultSet rs3 =null;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();
			stat1 = conn.createStatement();
			stat2 = conn.createStatement();
			stat3 = conn.createStatement();
			//根据gid找出cartItem表中需要删除记录的cid
			String sql, sql1, sql2, sql3, cid;
			Integer price, totalPrice, fPrice, num;
			sql = "select * from cartItem where gid ='"+id+"' and istate = 1";
			rs = stat.executeQuery(sql);
			HashSet<String> hs = new HashSet<String>();
			while(rs.next()){
				cid = rs.getString(1);
				hs.add(cid);
				}
			//查找出商品价格
			sql1 = "select * from goods where gid ='"+id+"' and gstate = 1";
			rs1 = stat1.executeQuery(sql1);
			rs1.next();
			price = rs1.getInt(3);
			//将删除操作放到一个原子中
			conn.setAutoCommit(false);//如果出错，回滚
			Iterator<String> it = hs.iterator();//用迭代器取出元素
			String _cid = null;
			while(it.hasNext()){
				_cid = it.next();
				sql2 = "select * from cart where cid = '"+_cid+"' and cstate = 1";
				rs2 = stat2.executeQuery(sql2);
				rs2.next();
				totalPrice = rs2.getInt(3);			
				sql3 = "select * from cartItem where cid = '"+_cid+"' and istate = 1";//查出商品数量
				rs3 = stat3.executeQuery(sql3);
				rs3.next();
				num = rs3.getInt(3);			
				fPrice = totalPrice-price*num;//最终总价格
				stat.executeUpdate("update cart set totalPrice = '"+fPrice+"' where cid = '"+_cid+"' and cstate = 1");
			}
			stat1.executeUpdate("update cartItem set istate = 0 where gid = '"+id+"'");	
			stat2.executeUpdate("update goods set gstate = 0 where gid = '"+id+"'");
			stat3.executeUpdate("update saleStat set sstate = 0 where gid = '"+id+"'");  //删除销售统计表中的相关记录
			conn.commit();
			conn.setAutoCommit(true);
			flag = true;
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(stat3, rs3);
			DataAccess.closeconn(stat2, rs2);
			DataAccess.closeconn(stat1, rs1);
			DataAccess.closeconn(conn, stat, rs);				
		}
		return flag;
	}

	//根据goods表中的gid更改商品余量count（一条记录）
	public boolean updateGoodsCountById(String id, Integer count){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;				
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("update goods set count=? where gid=? and gstate = 1");	
			prep.setInt(1, count);
			prep.setString(2, id);
			prep.executeUpdate();//执行SQL语句并处理结果集
			flag = true;		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(conn, prep);
		}	
		return flag;
	}		
	
	//根据goods表中的id更改商品价格price（一条记录）
	public boolean updateGoodsPriceById(String id, Integer price){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;				
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("update goods set price=? where gid=? and gstate = 1");	
			prep.setInt(1, price);
			prep.setString(2, id);
			prep.executeUpdate();//执行SQL语句并处理结果集
			flag = true;		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(conn, prep);
		}	
		return flag;
	}	
	
	//向goods表中插入一条完整的记录
	public boolean insertGoodsInfo(GoodsDTO gdto){
		boolean flag = false;
		String gid = gdto.getGid();
		String gname = gdto.getGname();
		int price = gdto.getPrice();
		int count = gdto.getCount();
		Connection conn = null;
		PreparedStatement prep = null;	
		PreparedStatement prep1 = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("insert into goods(gid, gname, price, count) values(?, ?, ?, ?)");
			prep.setString(1, gid);	
			prep.setString(2, gname);	
			prep.setInt(3, price);
			prep.setInt(4, count);
			prep.executeUpdate();
			prep1 = conn.prepareStatement("insert into saleStat(gid) values(?)");
			prep1.setString(1, gid);
			prep1.executeUpdate();
			flag = true;		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(conn, prep);
		}		
		return flag;
	}			
	
	//根据gid更改goods表中一条完整的记录
	public boolean updateGoodsById(GoodsDTO gdto){
		boolean flag = false;
		String gid = gdto.getGid();
		String gname = gdto.getGname();
		int price = gdto.getPrice();
		int count = gdto.getCount();
		System.out.println("updateGoodsInfoByID----"+gid+" "+gname+" "+price+" "+count);
		Connection conn = null;
		PreparedStatement prep = null;					
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("update goods set gname=?, price=?, count=? where gid=? ");	
			prep.setString(1, gname);	
			prep.setInt(2, price);
			prep.setInt(3, count);
			prep.setString(4, gid);	
			prep.executeUpdate();
			flag = true;		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(conn, prep);
		}		
		return flag;	
	}
}

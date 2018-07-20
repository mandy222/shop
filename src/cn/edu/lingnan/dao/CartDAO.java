package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import cn.edu.lingnan.dto.CartDTO;
import cn.edu.lingnan.dto.UserDTO;
import cn.edu.lingnan.util.DataAccess;

public class CartDAO {
	//查询cart整张表信息
	public Vector<CartDTO> findAllCartInfo(int stateNum){
		Vector<CartDTO> v = new Vector<CartDTO>();
		Connection conn = null;
		Statement stat = null;  
		ResultSet rs = null;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from cart where cstate = '"+stateNum+"' ";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				CartDTO c = new CartDTO();
				c.setCid(rs.getInt("cid"));
				c.setUid(rs.getString("uid"));
				c.setTotalPrice(rs.getInt("totalPrice"));
				v.add(c);
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return v;
	}	
	
	//根据uid查询totalPrice
	public int findTotalPriceByCid(int cid){
		Connection conn = null;
		Statement stat = null;  
		ResultSet rs = null;
		int totalPrice = 0;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from cart where cid = '"+cid+"' and cstate = 1";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			rs.last();
			totalPrice = rs.getInt(3);
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return totalPrice;
	}
	
	//查找cart表的最后一条记录的uid
	public int findLastCartInfo(int stateNum){
		Connection conn = null;
		Statement stat = null;  
		ResultSet rs = null;
		int cid = 0;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from cart where cstate = '"+stateNum+"' ";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			rs.last();
			cid = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return cid;
	}	
	
	//根据cid删除表中一条记录(购物车)
	public boolean deleteCartById(int id){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		Statement stat1 = null;
		Statement stat2 = null;
		ResultSet rs =null;
		ResultSet rs1 =null;
		ResultSet rs2 =null;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();
			stat1 = conn.createStatement();
			stat2 = conn.createStatement();
			//根据cid找出cartItem表中需的gid
			String sql, gid, _gid, sql1, sql2;
			Integer num, count, fCount;
			sql = "select * from cartItem where cid ='"+id+"' and istate = 1";
			rs = stat.executeQuery(sql);
			gid = null;
			HashSet<String> hs = new HashSet<String>();
			while(rs.next()){
				gid = rs.getString(2);
				hs.add(gid);
				}		
			//将删除操作放到一个原子中
			conn.setAutoCommit(false);//如果出错，回滚
			Iterator<String> it = hs.iterator();//用迭代器取出元素
			while(it.hasNext()){
				_gid = it.next();
				sql1 = "select * from cartItem where gid = '"+_gid+"' and cid = '"+id+"' and istate = 1";
				rs1 = stat1.executeQuery(sql1);
				rs1.next();
				num = rs1.getInt(3);
				//修改goods表中相应的商品余量
				sql2 = "select * from goods where gid = '"+_gid+"' and gstate = 1";
				rs2 = stat2.executeQuery(sql2);
				rs2.next();
				count = rs2.getInt(4);
				fCount = count + num;
				stat.executeUpdate("update goods set count = '"+fCount+"' where gid = '"+_gid+"' and gstate = 1");
			}
			//删除cartItem表中的相关记录
			stat.executeUpdate("update cartItem set istate = 0 where cid = '"+id+"'");
			//删除cart表中的记录
			stat.executeUpdate("update cart set cstate = 0 where cid = '"+id+"'");	
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
			DataAccess.closeconn(stat2, rs2);
			DataAccess.closeconn(stat1, rs1);
			DataAccess.closeconn(conn, stat, rs);				
		}
		return flag;
	}
	
	//根据cid删除表中一条记录(用户管理)
	public boolean deleteCartById1(int id){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		Statement stat1 = null;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();
			stat1 = conn.createStatement();
			//将删除操作放到一个原子中
			conn.setAutoCommit(false);//如果出错，回滚
			//删除cartItem表中的相关记录
			stat.executeUpdate("update cartItem set istate = 0 where cid = '"+id+"'");
			//删除cart表中的记录
			stat1.executeUpdate("update cart set cstate = 0 where cid = '"+id+"'");	
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
			DataAccess.closeconn(conn, stat);							
			try {
				if(stat != null){
					stat1.close();
					stat1 = null;
				}
			} catch (SQLException e) {				
				e.printStackTrace();			
			}
		}
		return flag;
	}
	
	//根据cart表中的cid更改uid（一条记录）
	public boolean updateCartUidByCid(String cid, String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;				
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("update cart set uid=? where cid=? and cstate = 1");	
			prep.setString(1, uid);
			prep.setString(2, cid);
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

	//向cart表中插入一条完整的记录
	public boolean insertCartInfo(String uid){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;			
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate("insert into cart(cid, uid) values(null, '"+uid+"')");
			flag = true;		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(conn, stat);
		}		
		return flag;
	}
	
	//根据cart表中的cid更改一整条记录
	public boolean updateCartInfoByID(CartDTO cdto){
		boolean flag = false;
		int cid = cdto.getCid();
		String uid = cdto.getUid();
		int totalPrice = cdto.getTotalPrice();
			
		System.out.println("updateSaleInfoByID----"+cid+" "+uid+" "+totalPrice);
		Connection conn = null;
		PreparedStatement prep = null;					
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("update cart set uid=?, totalPrice=? where cid=? ");	
			prep.setString(1, uid);	
			prep.setInt(2, totalPrice);
			prep.setInt(3, cid);	
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

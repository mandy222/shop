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
import cn.edu.lingnan.dto.CartItemDTO;
import cn.edu.lingnan.util.DataAccess;

public class CartItemDAO {
	//查询cartItem整张表信息
	public Vector<CartItemDTO> findAllCartItemInfo(int stateNum){
		Vector<CartItemDTO> v = new Vector<CartItemDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from cartItem where istate = '"+stateNum+"'";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				CartItemDTO i= new CartItemDTO();
				i.setCid(rs.getInt("cid"));
				i.setGid(rs.getString("gid"));
				i.setNum(rs.getInt("num"));
				i.setIstate(rs.getInt("istate"));
				v.add(i);
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return v;
	}	
	
	//根据cid查询cartItem表信息
	public Vector<CartItemDTO> findCartItemInfoByCid(int cid){
		Vector<CartItemDTO> v = new Vector<CartItemDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from cartItem where cid = '"+cid+"' and istate = 1";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				CartItemDTO i= new CartItemDTO();
				i.setCid(rs.getInt("cid"));
				i.setGid(rs.getString("gid"));
				i.setNum(rs.getInt("num"));
				i.setIstate(rs.getInt("istate"));
				v.add(i);
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return v;
	}	
	
	//根据cid查询cartItem表信息
	public boolean findCartItemInfoByCidAndGidAndNum(int cid, String gid, int num){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from cartItem where cid = '"+cid+"' and istate = 1 and gid = '"+gid+"' and num = '"+num+"'";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				flag = true;
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return flag;
	}
	
	//根据cid查询cartItem表信息
	public boolean findDeletedCartItemInfoByCidAndGidAndNum(int cid, String gid, int num){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from cartItem where cid = '"+cid+"' and istate = 0 and gid = '"+gid+"'";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				flag = true;
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return flag;
	}
	
	//根据cid,gid删除表中一条记录
	public boolean deleteCartItemByCidAndGid(int cid, String gid){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs =null;
		ResultSet rs1 =null;
		ResultSet rs2 =null;
		ResultSet rs3 =null;
		ResultSet rs4 =null;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();
			//根据个gid找出goods表中商品价格price
			String sql = "select * from goods where gid ='"+gid+"' and gstate = 1";
			rs = stat.executeQuery(sql);
			rs.next();
			Integer price = rs.getInt(3);
			
			//根据个gid找出cartItem表中商品数量num
			String sql1 = "select * from cartItem where cid ='"+cid+"' and gid ='"+gid+"' and istate = 1";
			rs1 = stat.executeQuery(sql1);
			rs1.next();
			Integer num = rs1.getInt(3);
			
			//将删除操作放到一个原子中
			conn.setAutoCommit(false);//如果出错，回滚	
			//删除cartItem表中记录
			stat.executeUpdate("update cartItem set istate = 0 where cid = '"+cid+"' and gid = '"+gid+"'");
			//修改cart表中的商品总价格
			String sql2 = "select * from cart where cid = '"+cid+"' and cstate = 1";
			rs2 = stat.executeQuery(sql2);
			rs2.next();
			Integer totalPrice = rs2.getInt(3);	
			Integer fPrice = totalPrice-price*num;
			stat.executeUpdate("update cart set totalPrice = '"+fPrice+"' where cid = '"+cid+"' and cstate = 1");		
			//修改goods表的商品余量
			String sql3 = "select * from goods where gid = '"+gid+"' and gstate = 1";
			rs3 = stat.executeQuery(sql3);
			rs3.next();
			Integer count = rs3.getInt(4);
			Integer fCount = count + num;
			stat.executeUpdate("update goods set count = '"+fCount+"' where gid = '"+gid+"' and gstate = 1");	
			//修改saleStat表中对应商品的销售数量
			rs4 = stat.executeQuery("select * from saleStat where gid = '"+gid+"' and sstate = 1");
			rs4.next();
			Integer saleNum = rs4.getInt(2);
			Integer fSaleNum = saleNum - num;
			stat.executeUpdate("update saleStat set saleNum = '"+fSaleNum+"' where gid = '"+gid+"' and sstate = 1");
			
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
			DataAccess.closeconn(rs4);
			DataAccess.closeconn(rs3);
			DataAccess.closeconn(rs2);
			DataAccess.closeconn(rs1);
			DataAccess.closeconn(conn, stat, rs);				
		}
		return flag;
	}
	
	//根据gid删除表中的相关记录
	public boolean deleteCartItemByCid(String id){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();			
			stat.executeUpdate("update cartItem set istate = 0 where cid = '"+id+"'");
			flag = true;
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(conn, stat);				
		}
		return flag;
	}
	
	//根据cid, gid更改商品数量num
	public String UpdateCartItemNumByCidAndGid(int cid, String gid, int uNum){
//		boolean flag = false;	
		String flag = null;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs =null;
		ResultSet rs1 =null;
		ResultSet rs2 =null;
		ResultSet rs3 =null;
		ResultSet rs4 =null;
		CartItemDAO c = new CartItemDAO();
		if(uNum == 0)
		{
			c.deleteCartItemByCidAndGid(cid, gid);
			flag = "true";
		}
		else{
			try {				
				conn = DataAccess.getConnection();
				stat = conn.createStatement();
				//根据个gid找出goods表中商品价格price
				String sql = "select * from goods where gid ='"+gid+"' and gstate = 1";
				rs = stat.executeQuery(sql);
				rs.next();
				Integer price = rs.getInt(3);
				
				//根据个gid找出cartItem表中商品数量num
				String sql1 = "select * from cartItem where cid ='"+cid+"' and gid ='"+gid+"' and istate = 1";
				rs1 = stat.executeQuery(sql1);
				rs1.next();
				Integer num = rs1.getInt(3);
				
				conn.setAutoCommit(false);//如果出错，回滚		
				//确定cart表中的商品总价格
				String sql2 = "select * from cart where cid = '"+cid+"' and cstate = 1";
				rs2 = stat.executeQuery(sql2);
				rs2.next();
				Integer totalPrice = rs2.getInt(3);	
				Integer fPrice = totalPrice - price*num + price*uNum;					
				//确定goods表的商品余量
				String sql3 = "select * from goods where gid = '"+gid+"' and gstate = 1";
				rs3 = stat.executeQuery(sql3);				
				rs3.next();
				Integer count = rs3.getInt(4);
				Integer hCount = count + num;
				Integer fCount = count + num - uNum;			
				if(uNum > hCount){
					System.out.println("商品余量不足，请修改商品数量至" + hCount + "或以下");
					flag = "商品余量不足，请修改商品数量至" + hCount + "或以下";
				}					
				else
				{	//修改cart表中的商品总价格
					stat.executeUpdate("update cart set totalPrice = '"+fPrice+"' where cid = '"+cid+"' and cstate = 1");
					//修改goods表中的商品余量
					stat.executeUpdate("update goods set count = '"+fCount+"' where gid = '"+gid+"' and gstate = 1");		
					//修改cartItem表中的商品数量
					stat.executeUpdate("update cartItem set num = '"+uNum+"' where gid = '"+gid+"' and cid = '"+cid+"' and istate = 1");
					//修改saleStat表中相应商品的销售数量
					rs4 = stat.executeQuery("select * from saleStat where gid = '"+gid+"' and sstate = 1");				
					rs4.next();
					Integer saleNum = rs4.getInt(2);
					Integer fSaleNum = saleNum - num + uNum;
					stat.executeUpdate("update saleStat set saleNum = '"+fSaleNum+"' where gid = '"+gid+"' and sstate = 1");
					conn.commit();
					conn.setAutoCommit(true);
					flag = "true";
				}	
			} catch (SQLException e) {
				System.out.println("获取数据库连接时出现SQL语句错误");
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				DataAccess.closeconn(rs3);
				DataAccess.closeconn(rs2);
				DataAccess.closeconn(rs1);
				DataAccess.closeconn(conn, stat, rs);				
			}		
		}		
		return flag;
	}	
	
	//向cartItem表中插入一条完整的记录
	public int insertCartItemInfo(int cid, String gid, Integer iNum){
		int flag1 = 0;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs =null;
		ResultSet rs1 =null;
		ResultSet rs2 =null;
		ResultSet rs3 =null;
		CartItemDAO c = new CartItemDAO();
		if(iNum == 0)
			c.deleteCartItemByCidAndGid(cid, gid);
		
		else{
			try {				
				conn = DataAccess.getConnection();
				stat = conn.createStatement();
				//根据个gid找出goods表中商品价格price
				String sql = "select * from goods where gid ='"+gid+"' and gstate = 1";
				rs = stat.executeQuery(sql);
				rs.next();
				Integer price = rs.getInt(3);
				conn.setAutoCommit(false);//如果出错，回滚		
				//确定cart表中的商品总价格
				String sql2 = "select * from cart where cid = '"+cid+"' and cstate = 1";
				rs1 = stat.executeQuery(sql2);
				rs1.next();
				Integer totalPrice = rs1.getInt(3);	
				Integer fPrice = totalPrice + price*iNum;					
				//确定goods表的商品余量
				String sql3 = "select * from goods where gid = '"+gid+"' and gstate = 1";
				rs2 = stat.executeQuery(sql3);				
				rs2.next();
				Integer count = rs2.getInt(4);
				Integer fCount = count - iNum;			
				if(iNum > count){
					System.out.println("商品余量不足，请修改商品数量至" + count + "或以下");
					flag1 = 1;
				}
					
				else
				{															
					boolean flag2 = c.findDeletedCartItemInfoByCidAndGidAndNum(cid, gid, iNum);
					if(flag2){//向cartItem表中添加一条数据（该记录存在）
						System.out.println("记录存在");
						stat.executeUpdate("delete from cartItem where cid = '"+cid+"' and gid = '"+gid+"'");
						stat.executeUpdate("insert into cartItem(cid, gid, num) values('"+cid+"', '"+gid+"', '"+iNum+"')");
					}else{//向cartItem表中添加一条数据（该记录不存在）
						System.out.println("记录不存在");
						stat.executeUpdate("insert into cartItem(cid, gid, num) values('"+cid+"', '"+gid+"', '"+iNum+"')");	
					}
						
					
					//修改cart表中的商品总价格
					stat.executeUpdate("update cart set totalPrice = '"+fPrice+"' where cid = '"+cid+"' and cstate = 1");
					//修改goods表中的商品余量
					stat.executeUpdate("update goods set count = '"+fCount+"' where gid = '"+gid+"' and gstate = 1");
					//修改saleStat表中的商品销售数量
						//查找saleStat表中原本的销售数量			
					rs3 = stat.executeQuery("select * from saleStat where gid ='"+gid+"'");
					rs3.next();
					Integer sstate = rs3.getInt(3);
					System.out.println("sstate:"+sstate);
					//如果销售表中存在该商品销售记录，直接修改该商品的销售记录
					if(sstate == 1){
						Integer fSaleNum = rs3.getInt(2) + iNum;					
						System.out.println("fSaleNum:"+fSaleNum);
						stat.executeUpdate("update saleStat set saleNum = '"+fSaleNum+"' where gid = '"+gid+"'");
					}
					//如不存在，则恢复该记录，并刷新销售数量
					else{
						stat.executeUpdate("update saleStat set saleNum = '"+iNum+"' , sstate = 1 where gid = '"+gid+"'");
						System.out.println("iNum:"+iNum);
					}
					conn.commit();
					conn.setAutoCommit(true);
				}	
			} catch (SQLException e) {
				System.out.println("获取数据库连接时出现SQL语句错误");
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				DataAccess.closeconn(rs3);
				DataAccess.closeconn(rs2);
				DataAccess.closeconn(rs1);
				DataAccess.closeconn(conn, stat, rs);				
			}		
		}		
		return flag1;
	}
}

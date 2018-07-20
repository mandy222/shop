package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.print.DocFlavor.STRING;

import cn.edu.lingnan.dto.UserDTO;
import cn.edu.lingnan.util.DataAccess;

public class UserDAO {
	//查询user整张表信息(1为存在用户，0为删除的用户)
	public Vector<UserDTO> findAllUserInfo(int stateNum){
		Vector<UserDTO> v = new Vector<UserDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from user where ustate = '"+stateNum+"'";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				UserDTO u = new UserDTO();
				u.setUid(rs.getString("uid"));
				u.setUname(rs.getString("uname"));
				u.setPassword(rs.getString("password"));
				u.setSuperuser(rs.getInt("superuser"));
				v.add(u);
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return v;
	}
	
	//登陆时判断用户名和密码是否正确
	public int findUserByNameAndPassword(String _name, String _password){
		int superValue = 0;	
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;   		
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("select * from user where uname = ? and password = ? and ustate = 1");
			prep.setString(1, _name);
			prep.setString(2, _password);
			rs = prep.executeQuery();
			if(rs.first()){
				superValue = rs.getInt("superuser");
				System.out.println("1.---"+superValue);
			}
		} catch (SQLException e) {
				System.out.println("运行SQL语句时出现错误");
				e.printStackTrace();			
		}finally{
			DataAccess.closeconn(conn, prep, rs);
		}		
		return superValue;			
	}
	
	//查找登陆用户的uid
	public String findUserByName(String _name){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;   		
		String uid = null;
		try {
			conn = DataAccess.getConnection();			
			prep = conn.prepareStatement
					("select * from user where uname = ? and ustate = 1");
			prep.setString(1, _name);
			rs = prep.executeQuery();
			if(rs.first())
				uid = rs.getString(1);
		} catch (SQLException e) {
				System.out.println("运行SQL语句时出现错误");
				e.printStackTrace();			
		}finally{
			DataAccess.closeconn(conn, prep, rs);
		}		
		return uid;			
	}
	
	//判断用户登陆名是否存在
	public boolean findUserNameByName(String _name){
		Connection conn = null;
		PreparedStatement prep = null;		
		boolean flag = false;
		try {
			conn = DataAccess.getConnection();			
			prep = conn.prepareStatement
					("select * from user where uname = ? and ustate = 1");
			prep.setString(1, _name);
			prep.executeQuery();
			flag = true;
		} catch (SQLException e) {
				System.out.println("运行SQL语句时出现错误");
				e.printStackTrace();			
		}finally{
			DataAccess.closeconn(conn, prep);
		}		
		return flag;			
	}
	
	//根据uid删除表中一条记录（第一版）
	public boolean deleteUserById(String id){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs =null;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();
			//根据uid找出cart表中需要删除记录的cid
			String sql = "select * from cart where uid ='"+id+"' and cstate = 1";
			rs = stat.executeQuery(sql);
			String cid = null;
			HashSet<String> hs = new HashSet<String>();
			while(rs.next()){
				cid = rs.getString(1);
				hs.add(cid);
				}
			
			//将删除操作放到一个原子中
			conn.setAutoCommit(false);//如果出错，回滚
			Iterator<String> it = hs.iterator();//用迭代器取出元素
			String _cid = null;
			while(it.hasNext()){
				_cid = it.next();
				stat.executeUpdate("update cartItem set istate = 0 where cid = '"+_cid+"'");
				stat.executeUpdate("update cart set cstate = 0 where cid = '"+_cid+"'");
			}
			stat.executeUpdate("update user set ustate = 0 where uid = '"+id+"'");	
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
			DataAccess.closeconn(conn, stat, rs);				
		}
		return flag;
	}
	
	//根据user表中的uid更改password（一条记录）
	public boolean updateUserPasswordById(String id, String _password){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;				
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("update user set password=? where uid=? and ustate = 1");	
			prep.setString(1, _password);
			prep.setString(2, id);
			prep.executeUpdate();//ִ//执行SQL语句并处理结果集
			flag = true;		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(conn, prep);
		}	
		return flag;
	}	
	
	//根据user表中的uid更改name（一条记录）
	public boolean updateUserNameById(String id, String name){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;				
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("update user set uname=? where uid=? and ustate = 1");	
			prep.setString(1, name);
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
	
	//更改user表中一条完整的记录
	public boolean updateUserInfoByID(UserDTO udto){
		boolean flag = false;
		String uid = udto.getUid();
		String um = udto.getUname();
		String password = udto.getPassword();
		int superuser = udto.getSuperuser();
		System.out.println("updateUserInfoByID----"+uid+" "+um+" "+password+" "+superuser);
		Connection conn = null;
		PreparedStatement prep = null;					
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("update user set uname=?, password=?, superuser=? where uid=? ");	
			prep.setString(1, um);	
			prep.setString(2, password);
			prep.setInt(3, superuser);
			prep.setString(4, uid);	
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
	
	//向user表中插入一条完整的记录
	public boolean insertUserInfo(UserDTO udto){
		boolean flag = false;
		String uid = udto.getUid();
		String um = udto.getUname();
		String password = udto.getPassword();
		int superuser = udto.getSuperuser();
		Connection conn = null;
		PreparedStatement prep = null;					
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("insert into user(uid, uname, password, superuser) values(?, ?, ?, ?)");
			prep.setString(1, uid);	
			prep.setString(2, um);	
			prep.setString(3, password);
			prep.setInt(4, superuser);
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

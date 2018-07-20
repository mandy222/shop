package cn.edu.lingnan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccess {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/shopping";
	private static String username = "root";
	private static String password = "123";
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Class.forName(driver);	//注册驱动程序
			conn = DriverManager.getConnection//获取数据库
						(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL的驱动程序找不到啦，看一下是不是对应的jar包没加载啊");
			e.printStackTrace();
		}catch (SQLException e) {
				System.out.println("获取数据库连接时出现SQL语句错误");
				e.printStackTrace();
				
		}
//		System.out.println("数据库连接获取成功......");
		return conn;
	}
	
	public static void closeconn(Connection conn, Statement stat, PreparedStatement prep, ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				rs = null;
				}	
			if(stat != null){
				stat.close();
				stat = null;
			}
			if(prep != null){
				prep.close();
				prep = null;
			}		
			if(conn != null){
				conn.close();
				conn = null;
				}
		} catch (SQLException e) {
			System.out.println("关闭连接/语句及结果集出现错误");
			e.printStackTrace();
		}
	}
	
	public static void closeconn(Connection conn, Statement stat,  ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				rs = null;
				}	
			if(stat != null){
				stat.close();
				stat = null;
			}
	
			if(conn != null){
				conn.close();
				conn = null;
				}
		} catch (SQLException e) {
			System.out.println("关闭连接/语句及结果集出现错误");
			e.printStackTrace();
		}
	}
	
	public static void closeconn(Statement stat,  ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				rs = null;
				}	
			if(stat != null)
				stat.close();
				stat = null;
			
		} catch (SQLException e) {
			System.out.println("关闭连接/语句及结果集出现错误");
			e.printStackTrace();
		}
	}
	
	public static void closeconn(Connection conn, PreparedStatement prep, ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				rs = null;
				}	
			if(prep != null){
				prep.close();
				prep = null;
			}		
			if(conn != null){
				conn.close();
				conn = null;
				}
		} catch (SQLException e) {
			System.out.println("关闭连接/语句及结果集出现错误");
			e.printStackTrace();
		}
	}
	
	public static void closeconn(Connection conn, Statement stat){
		try {
			if(stat != null){
				stat.close();
				stat = null;
			}		
			if(conn != null){
				conn.close();
				conn = null;
				}
		} catch (SQLException e) {
			System.out.println("关闭连接/语句及结果集出现错误");
			e.printStackTrace();
		}
	}
		
		public static void closeconn(Connection conn, PreparedStatement prep){
			try {
				if(prep != null){
					prep.close();
					prep = null;
				}		
				if(conn != null){
					conn.close();
					conn = null;
					}
			} catch (SQLException e) {
				System.out.println("关闭连接/语句及结果集出现错误");
				e.printStackTrace();
			}		
	}
		
	public static void closeconn(Connection conn, Statement stat, PreparedStatement prep){
		try {
			if(stat != null){
				stat.close();
				stat = null;
			}		
			if(conn != null){
				conn.close();
				conn = null;
				}
			if(prep != null){
				prep.close();
				prep = null;
			}
		} catch (SQLException e) {
			System.out.println("关闭连接/语句及结果集出现错误");
			e.printStackTrace();
		}
	}
		
	public static void closeconn(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				rs = null;
				}	
		} catch (SQLException e) {
			System.out.println("关闭连接/语句及结果集出现错误");
			e.printStackTrace();
		}
	}
}

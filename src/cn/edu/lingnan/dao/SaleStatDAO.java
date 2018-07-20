package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import cn.edu.lingnan.dto.SaleStatDTO;
import cn.edu.lingnan.util.DataAccess;

public class SaleStatDAO {
	//查询 saleStat整张表信息
	public Vector<SaleStatDTO> findAllSaleStatInfo(int stateNum){
		Vector<SaleStatDTO> v = new Vector<SaleStatDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;						
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();//创建一个Statement用于执行SQL语句
			String sql = "select * from SaleStat where sstate = '"+stateNum+"'";		
			rs = stat.executeQuery(sql);//执行SQL语句并处理结果集
			while(rs.next()){
				SaleStatDTO s = new SaleStatDTO();
				s.setGid(rs.getString("gid"));
				s.setSaleNum(rs.getInt("saleNum"));
				v.add(s);
			}		
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
				DataAccess.closeconn(conn, stat, rs);
		}	
		return v;
	}
	
	//根据gid删除表中一条记录（第一版）
	public boolean deleteSaleStatById(String id){
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		try {
			conn = DataAccess.getConnection();
			stat = conn.createStatement();			
			stat.executeUpdate("update saleStat set sstate = 0 where gid = '"+id+"'");	
			flag = true;
		} catch (SQLException e) {
			System.out.println("获取数据库连接时出现SQL语句错误");
			e.printStackTrace();
		}finally{
			DataAccess.closeconn(conn, stat);				
		}
		return flag;
	}
}

package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.UserDTO;

public class UpdateUserInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");// 处理响应乱码		
		req.setCharacterEncoding("UTF-8"); // 处理请求乱码post提交方式
		String um = req.getParameter("uname");		
		String uname=new String(um.getBytes("iso-8859-1"),"utf-8");//以iso-8859-1编码方式获取用户名,转换为UTF-8编码	
		
		String uid = req.getParameter("uid");

		String password = req.getParameter("password");
		String superuser = req.getParameter("superuser");
		System.out.println("UpdateUserInfoServlet----"+uid+" "+uname+" "+password+" "+superuser);

		UserDAO u = new UserDAO();
		UserDTO ud = new UserDTO();
		ud.setUid(uid);
		ud.setUname(uname);
		ud.setPassword(password);
		ud.setSuperuser((Integer.parseInt(superuser)));
		System.out.println("UpdateUserInfoServlet2----"+uid+" "+uname+" "+password+" "+superuser);
		boolean flag = u.updateUserInfoByID(ud);
		
		//重新刷新
		Vector<UserDTO> v = new Vector<UserDTO>();
		v = u.findAllUserInfo(1); 
		HttpSession s = req.getSession();
		s.setAttribute("alluser", v);		
		resp.sendRedirect("showAllUserInfo.jsp");
		
	}
}

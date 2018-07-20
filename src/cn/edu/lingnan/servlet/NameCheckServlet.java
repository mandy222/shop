package cn.edu.lingnan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dao.UserDAO;

public class NameCheckServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uname = new String(req.getParameter("uname").getBytes("ISO-8859-1"),"utf-8");		
		System.out.println("username:"+uname);
		UserDAO u = new UserDAO();
		boolean flag = u.findUserNameByName("uname");
		if(flag)
			resp.getWriter().print("true");
	}
}

package cn.edu.lingnan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.UserDTO;

public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {	
		System.out.println("已经注销了");
		HttpSession s = req.getSession();	//注销session
		s.invalidate();
		resp.sendRedirect(req.getContextPath()+"/index.html");	//去到登陆界
	}
}

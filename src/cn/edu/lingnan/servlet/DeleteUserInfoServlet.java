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

public class DeleteUserInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uid = req.getParameter("uid");
		UserDAO u = new UserDAO();
		boolean flag = u.deleteUserById(uid);

		
		//重新刷新
		Vector<UserDTO> v = new Vector<UserDTO>();
		v = u.findAllUserInfo(1); 
		HttpSession s = req.getSession();
		s.setAttribute("alluser", v);
		
		resp.sendRedirect("showAllUserInfo.jsp");
	}
}

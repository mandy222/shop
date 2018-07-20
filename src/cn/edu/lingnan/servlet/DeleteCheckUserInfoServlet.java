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

public class DeleteCheckUserInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] arr = req.getParameterValues("arr");
		UserDAO u = new UserDAO();
		for(String a : arr){
			String[] b = a.split(",");
			for(String c : b)
				u.deleteUserById(c);			
		}
		Vector<UserDTO> v = new Vector<UserDTO>();
		v = u.findAllUserInfo(1); 
		HttpSession s = req.getSession();
		s.setAttribute("alluser", v);
		resp.sendRedirect("showAllUserInfo.jsp");
			
	}
}

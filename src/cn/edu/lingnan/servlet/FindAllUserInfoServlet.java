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

public class FindAllUserInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserDAO u = new UserDAO();
		Vector<UserDTO> v = new Vector<UserDTO>();
		v = u.findAllUserInfo(1); 
		
		System.out.println("User table:");
		for(UserDTO ud : v)
			System.out.println(ud.getUid()+"\t"+ud.getUname()+"\t"+ud.getPassword()+"\t"+ud.getSuperuser()+"\t"+ud.getUstate());
		
		HttpSession s = req.getSession();
		s.setAttribute("alluser", v);
		resp.sendRedirect("showAllUserInfo.jsp");
	}
}

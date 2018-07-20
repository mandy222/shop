package cn.edu.lingnan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.UserDTO;

public class SignUpServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.从页面中获取我们想要的数据，4个
		resp.setContentType("text/html;charset=UTF-8");// 处理响应乱码		
		req.setCharacterEncoding("UTF-8"); // 处理请求乱码post提交方式
		String uid = req.getParameter("uid");
		String um = req.getParameter("uname");		
		String uname=new String(um.getBytes("iso-8859-1"),"utf-8");//以iso-8859-1编码方式获取用户名,转换为UTF-8编码		
		String password = req.getParameter("password");
		int superuser = Integer.parseInt(req.getParameter("superuser"));
		System.out.println("1."+uid+"  "+uname+"   "+password+"  "+superuser);
		
		//2.调用后台的业务逻辑去注册用户
		UserDAO u =new UserDAO();
		UserDTO udto = new UserDTO();
		udto.setUid(uid);
		udto.setUname(uname);
		udto.setPassword(password);
		udto.setSuperuser(superuser);		
		boolean flag = u.insertUserInfo(udto);
		
		//3.根据结果去到相应的页面
		if(flag)
			resp.sendRedirect(req.getContextPath()+"/index.html");
		else 
			resp.sendRedirect(req.getContextPath()+"/sign-up.html");			
	}
	
	
}

package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.GoodsDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.GoodsDTO;
import cn.edu.lingnan.dto.UserDTO;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.从页面中获取我们想要的数据
		String uname = new String(req.getParameter("uname").getBytes("ISO-8859-1"), "utf-8");
		String password = req.getParameter("password");
		System.out.println("1."+uname+"   "+password);
		
		//2.调用后台的业务逻辑去判断这个用户名和密码是否存在对应
		UserDAO u =new UserDAO();
		int superValue = u.findUserByNameAndPassword(uname, password);
		System.out.println("2.------"+superValue);
		HttpSession s1 =req.getSession();  //查看当前请求的会话对象
		s1.setAttribute("superValue", superValue); //设定指定名字的属性的值，并将它添加到session会话范围内（如果这个属性是会话范围内存在，则更改该属性的值） 
		
		//根据结果去到相应的页面
		if(superValue == 1){//showAllUserInfo.jsp一定要传东西过去！！不然会出现空指针异常
			Vector<UserDTO> v = new Vector<UserDTO>();
			v = u.findAllUserInfo(1); 			
			HttpSession s = req.getSession();
			s.setAttribute("alluser", v);
			
			String uid = u.findUserByName(uname);			
			HttpSession ss = req.getSession();
			ss.setAttribute("userUid", uid);
			resp.sendRedirect(req.getContextPath()+"/admin/showAllUserInfo.jsp");
//			resp.sendRedirect(req.getContextPath()+"/admin/admin.html");
		}
		else if(superValue == 2){
			GoodsDAO g = new GoodsDAO();
			Vector<GoodsDTO> v = new Vector<GoodsDTO>();
			v = g.findAllGoodsInfoAboutCount(1);			
			HttpSession s = req.getSession();
			s.setAttribute("allGoods", v);	
			
			String uid = u.findUserByName(uname);			
			HttpSession ss = req.getSession();
			ss.setAttribute("userUid", uid);
			
			int flag = 1;
			HttpSession s3 = req.getSession();
			s3.setAttribute("flag", flag);
			
			int cid = 0;
			HttpSession s5 = req.getSession();
			s5.setAttribute("cid", cid);
			resp.sendRedirect(req.getContextPath()+"/customer.jsp");	
//			resp.sendRedirect(req.getContextPath()+"/ok.html");
		}
		else 
		{
			resp.sendRedirect("error.jsp?error=yes");
		}
		System.out.println("superValue == 1："+req.getContextPath()+"/admin/showAllUserInfo.jsp");
	}
}

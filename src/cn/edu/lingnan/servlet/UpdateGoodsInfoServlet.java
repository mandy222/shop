package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.GoodsDAO;
import cn.edu.lingnan.dto.GoodsDTO;

public class UpdateGoodsInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");// 处理响应乱码		
		req.setCharacterEncoding("UTF-8"); // 处理请求乱码post提交方式
		String gm = req.getParameter("gname");		
		String gname=new String(gm.getBytes("iso-8859-1"),"utf-8");//以iso-8859-1编码方式获取用户名,转换为UTF-8编码	
		
		String gid = req.getParameter("gid");
		String price =   req.getParameter("price");
		String count = req.getParameter("count");
		System.out.println("UpdateUserInfoServlet----"+gid+" "+gname+" "+price+" "+count);

		GoodsDAO g = new GoodsDAO();
		GoodsDTO gd = new GoodsDTO();
		gd.setGid(gid);
		gd.setGname(gname);
		gd.setPrice((Integer.parseInt(price)));
		gd.setCount((Integer.parseInt(count)));
		System.out.println("UpdateGoodsInfoServlet2----"+gid+" "+gname+" "+price+" "+count);
		boolean flag = g.updateGoodsById(gd);
		
		//重新刷新
		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
		v = g.findAllGoodsInfo(1); 
		HttpSession s = req.getSession();
		s.setAttribute("allGoods", v);
		
		resp.sendRedirect("showAllGoodsInfo.jsp");
		
	}
}

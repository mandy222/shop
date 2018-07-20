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

public class InsertGoodsInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		String gname = new String(req.getParameter("gname").getBytes("ISO-8859-1"), "utf-8");		
		String gid = req.getParameter("gid");
		String price =   req.getParameter("price");
		String count = req.getParameter("count");
		System.out.println("InsertUserInfoServlet----"+gid+" "+gname+" "+price+" "+count);

		GoodsDAO g = new GoodsDAO();
		GoodsDTO gd = new GoodsDTO();
		gd.setGid(gid);
		gd.setGname(gname);
		gd.setPrice((Integer.parseInt(price)));
		gd.setCount((Integer.parseInt(count)));
		System.out.println("InsertGoodsInfoServlet2----"+gid+" "+gname+" "+price+" "+count);
		boolean flag = g.insertGoodsInfo(gd);
		
		//重新刷新
		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
		v = g.findAllGoodsInfo(1); 
		HttpSession s = req.getSession();
		s.setAttribute("allGoods", v);		
		resp.sendRedirect("showAllGoodsInfo.jsp");
	}
}

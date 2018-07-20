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

public class FindAllGoodsInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
		GoodsDAO g = new GoodsDAO();
		v = g.findAllGoodsInfo(1);
		
		
		System.out.println("Goods table:");
		for(GoodsDTO gd : v)
			System.out.println(gd.getGid()+"\t"+gd.getGname()+"\t"+gd.getPrice()+"\t"+gd.getCount());
		
		
		HttpSession s = req.getSession();
		s.setAttribute("allGoods", v);
		resp.sendRedirect("showAllGoodsInfo.jsp");
	}

}

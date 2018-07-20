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

public class DeleteCheckGoodsInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] arr = req.getParameterValues("arr");
		GoodsDAO g = new GoodsDAO();
		for(String a : arr){
			String[] b = a.split(",");
			for(String c : b)
				g.deleteGoodsById(c);		
		}
		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
		v = g.findAllGoodsInfo(1);
		HttpSession s = req.getSession();
		s.setAttribute("allGoods", v);
		resp.sendRedirect("showAllGoodsInfo.jsp");
			
	}
}

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

public class DeleteGoodsInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String gid = req.getParameter("gid");
		GoodsDAO g = new GoodsDAO();
		boolean flag = g.deleteGoodsById(gid);
		
		//重新刷新
		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
		v = g.findAllGoodsInfo(1); 
		HttpSession s = req.getSession();
		s.setAttribute("allGoods", v);
		
		resp.sendRedirect("showAllGoodsInfo.jsp");
	}
}

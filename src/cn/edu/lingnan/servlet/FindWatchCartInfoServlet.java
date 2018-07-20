package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.CartDAO;
import cn.edu.lingnan.dao.CartItemDAO;
import cn.edu.lingnan.dao.GoodsDAO;
import cn.edu.lingnan.dto.CartItemDTO;
import cn.edu.lingnan.dto.GoodsDTO;

public class FindWatchCartInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int cid = Integer.parseInt(req.getParameter("cid"));
		System.out.println("FindWatchCartInfoServlet cid"+cid);
		
		CartDAO c = new CartDAO();
		int totalPrice = c.findTotalPriceByCid(cid);
		
		CartItemDAO i = new CartItemDAO();		
		Vector<CartItemDTO> v = new Vector<CartItemDTO>();
		v = i.findCartItemInfoByCid(cid);
		
		System.out.println("cartInfo table:");
		for(CartItemDTO idto : v)
			System.out.println(idto.getCid()+"\t"+idto.getGid()+"\t"+idto.getNum()+"\t"+idto.getIstate());
		
		HttpSession s = req.getSession();
		s.setAttribute("allWatchCartInfo", v);
		
		HttpSession s1 = req.getSession();
		s1.setAttribute("totalPrice", totalPrice);
		
//		GoodsDAO g = new GoodsDAO();
//		Vector<GoodsDTO> v1 = new Vector<GoodsDTO>();
//		v1 = g.findAllGoodsInfoAboutCount(1);			
//		HttpSession s2 = req.getSession();
//		s2.setAttribute("allGoods", v1);	
				
		resp.sendRedirect(req.getContextPath()+"/cart.jsp");
	}
}

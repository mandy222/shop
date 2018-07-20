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

public class DeleteWatchCartInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int cid = Integer.parseInt(req.getParameter("cid"));
		String gid = req.getParameter("gid");
		CartItemDAO i = new CartItemDAO();
		i.deleteCartItemByCidAndGid(cid, gid);
		
		//重新刷新
		CartDAO c = new CartDAO();
		int totalPrice = c.findTotalPriceByCid(cid);
			
		Vector<CartItemDTO> v = new Vector<CartItemDTO>();
		v = i.findCartItemInfoByCid(cid);
		
		HttpSession s = req.getSession();
		s.setAttribute("allWatchCartInfo", v);
		
		HttpSession s1 = req.getSession();
		s1.setAttribute("totalPrice", totalPrice);
		resp.sendRedirect(req.getContextPath()+"/cart.jsp");
	}
}

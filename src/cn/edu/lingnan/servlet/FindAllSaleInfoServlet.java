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
import cn.edu.lingnan.dto.CartDTO;
import cn.edu.lingnan.dto.CartItemDTO;

public class FindAllSaleInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CartDAO c = new CartDAO();
		Vector<CartDTO> v = new Vector<CartDTO>();
		v = c.findAllCartInfo(1);
		
		System.out.println("Cart table:");
		for(CartDTO cd : v)
			System.out.println(cd.getCid()+"\t"+cd.getUid()+"\t"+cd.getTotalPrice()+"\t"+cd.getCstate());
		
		HttpSession s = req.getSession();
		s.setAttribute("allCart", v);
		
		CartItemDAO i = new CartItemDAO();
		Vector<CartItemDTO> v1 = new Vector<CartItemDTO>();
		v1 = i.findAllCartItemInfo(1);
		
		System.out.println("CartItem table:");
		for(CartItemDTO idto : v1)
			System.out.println(idto.getCid()+"\t"+idto.getGid()+"\t"+idto.getNum()+"\t"+idto.getIstate());
		
		HttpSession s1 = req.getSession();
		s.setAttribute("allCartItem", v1);
		
		resp.sendRedirect("showAllSaleInfo.jsp");
	}
}

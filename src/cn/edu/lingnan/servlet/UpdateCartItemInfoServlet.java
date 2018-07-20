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

public class UpdateCartItemInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		int cid = Integer.parseInt(req.getParameter("cid"));
		String gid =   req.getParameter("gid");
		int num = Integer.parseInt(req.getParameter("num"));
		System.out.println("UpdateCartItemInfoServlet----"+cid+" "+gid+" "+num);

		CartItemDAO i = new CartItemDAO();
		CartItemDTO idto = new CartItemDTO();
//		idto.setCid(cid);
//		idto.setGid(gid);
//		idto.setNum((Integer.parseInt(num)));
//		System.out.println("UpdateCartItemInfoServlet2----"+cid+" "+gid+" "+num);
		i.UpdateCartItemNumByCidAndGid(cid, gid, num);
		
		//重新刷新
		CartDAO c = new CartDAO();
		Vector<CartDTO> v = new Vector<CartDTO>();
		v = c.findAllCartInfo(1); 
		HttpSession s = req.getSession();
		s.setAttribute("allCart", v);
		
		Vector<CartItemDTO> v1 = new Vector<CartItemDTO>();
		v1 = i.findAllCartItemInfo(1); 
		HttpSession s1 = req.getSession();
		s1.setAttribute("allCartItem", v1);		
		resp.sendRedirect("showAllSaleInfo.jsp");		
	}
}

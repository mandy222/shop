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
import cn.edu.lingnan.dto.CartItemDTO;

public class DeleteCheckWatchCartInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CartDAO c = new CartDAO();
		Vector<CartItemDTO> v = new Vector<CartItemDTO>();
		int totalPrice = 0;
		
		String[] arr = req.getParameterValues("arr");
		CartItemDAO i = new CartItemDAO();
		int cid;
		String gid = null;
		for(String a : arr){
			String[] b = a.split(",");
			for(int k = 0; k<b.length; k=k+2){
				cid = Integer.parseInt(b[k]);
				gid = b[k+1];
				System.out.println("=="+cid+"   "+gid);
				i.deleteCartItemByCidAndGid(cid, gid);
				totalPrice = c.findTotalPriceByCid(cid);	//重新刷新					
				v = i.findCartItemInfoByCid(cid);
			}
		}
		//重新刷新
		HttpSession s = req.getSession();
		s.setAttribute("allWatchCartInfo", v);
		
		HttpSession s1 = req.getSession();
		s1.setAttribute("totalPrice", totalPrice);
		
		resp.sendRedirect(req.getContextPath()+"/cart.jsp");
	}
}

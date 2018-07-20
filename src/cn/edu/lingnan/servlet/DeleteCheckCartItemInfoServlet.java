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

public class DeleteCheckCartItemInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
			}
		}
						
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

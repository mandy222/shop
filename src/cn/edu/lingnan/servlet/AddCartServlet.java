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
import cn.edu.lingnan.dto.GoodsDTO;

public class AddCartServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int flag = Integer.parseInt(req.getParameter("flag")); 		
		String uid = req.getParameter("uid");
		CartDAO c = new CartDAO();
		System.out.println();
		if(flag == 1)//控制只向购物车表中插入一条记录
			c.insertCartInfo(uid);		
		flag++;
		System.out.println("AddCartServlet flag:"+flag);
		HttpSession s1 = req.getSession();
		s1.setAttribute("flag", flag);
		
		int cid = c.findLastCartInfo(1);//查询购物车表中刚插入的记录的购物车编号
		String gid = req.getParameter("gid");
		System.out.println("AddCartServlet:"+cid+" "+gid);

		CartItemDAO i = new CartItemDAO();
		i.insertCartItemInfo(cid, gid, 1); //向购物车清单表中插入商品记录
		
		//重新刷新
		GoodsDAO g = new GoodsDAO();
		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
		v = g.findAllGoodsInfoAboutCount(1);			
		HttpSession s = req.getSession();
		s.setAttribute("allGoods", v);	
		
		HttpSession ss = req.getSession();
		ss.setAttribute("userUid", uid);
				
		HttpSession s3 = req.getSession();
		s3.setAttribute("cid", cid);
		
		resp.sendRedirect(req.getContextPath()+"/customer.jsp");
	}
}

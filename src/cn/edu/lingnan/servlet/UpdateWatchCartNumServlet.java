package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import cn.edu.lingnan.dao.CartDAO;
import cn.edu.lingnan.dao.CartItemDAO;
import cn.edu.lingnan.dao.GoodsDAO;
import cn.edu.lingnan.dto.CartItemDTO;

public class UpdateWatchCartNumServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		CartDAO c = new CartDAO();
		Vector<CartItemDTO> v = new Vector<CartItemDTO>();
		int totalPrice = 0;		
		GoodsDAO g = new GoodsDAO();
		String gname;		
		String[] arr = req.getParameterValues("arr");
		CartItemDAO i = new CartItemDAO();
		String tipAboutCount;
		int cid;
		String gid = null;
		int num;
		boolean flag;
		for(String a : arr){
			String[] b = a.split(",");
			for(int k = 0; k<b.length; k=k+3){
				cid = Integer.parseInt(b[k]);
				gid = b[k+1];
				num = Integer.parseInt(b[k+2]);
				System.out.println("=="+cid+"   "+gid+"   "+num);
				flag = i.findCartItemInfoByCidAndGidAndNum(cid, gid, num);//判断表中是否有相同的记录
				if(flag == false){//记录不一样
					tipAboutCount = i.UpdateCartItemNumByCidAndGid(cid, gid, num);	//更新购物车商品数量
					if(!tipAboutCount.equals("true")){
						gname = g.findGnameByGid(gid);
						System.out.println("UpdateWatchCartNumServlet:"+gname+tipAboutCount);
						JOptionPane.showMessageDialog(null, gname+tipAboutCount);  //库存不足数量提示
					}
					totalPrice = c.findTotalPriceByCid(cid);	//重新刷新					
					v = i.findCartItemInfoByCid(cid);
				}							
			}
		}								
		HttpSession s = req.getSession();
		s.setAttribute("allWatchCartInfo", v);		
		HttpSession s1 = req.getSession();
		s1.setAttribute("totalPrice", totalPrice);		
		resp.sendRedirect(req.getContextPath()+"/cart.jsp");
	}
}

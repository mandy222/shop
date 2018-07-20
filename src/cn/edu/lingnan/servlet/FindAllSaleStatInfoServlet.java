package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.SaleStatDAO;
import cn.edu.lingnan.dto.SaleStatDTO;

public class FindAllSaleStatInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SaleStatDAO sdao = new SaleStatDAO();
		Vector<SaleStatDTO> v = new Vector<SaleStatDTO>();
		v = sdao.findAllSaleStatInfo(1);
		
		System.out.println("SaleStat table:");
		for(SaleStatDTO sd : v)
			System.out.println(sd.getGid()+"\t"+sd.getSaleNum());
		
		HttpSession s = req.getSession();
		s.setAttribute("allSaleStat", v);
		resp.sendRedirect("showAllSaleStatInfo.jsp");
	}
}

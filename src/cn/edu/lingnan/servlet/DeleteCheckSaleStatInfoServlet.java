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

public class DeleteCheckSaleStatInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] arr = req.getParameterValues("arr");
		SaleStatDAO sdao = new SaleStatDAO();
		for(String a : arr){
			String[] b = a.split(",");
			for(String c : b)
				sdao.deleteSaleStatById(c);	
		}
		Vector<SaleStatDTO> v = new Vector<SaleStatDTO>();
		v = sdao.findAllSaleStatInfo(1);
		HttpSession s = req.getSession();
		s.setAttribute("allSaleStat", v);
		resp.sendRedirect("showAllSaleStatInfo.jsp");			
	}
}

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

public class DeleteSaleStatInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String gid = req.getParameter("gid");
		SaleStatDAO sdao = new SaleStatDAO();
		boolean flag = sdao.deleteSaleStatById(gid);

		
		//重新刷新
		Vector<SaleStatDTO> v = new Vector<SaleStatDTO>();
		v = sdao.findAllSaleStatInfo(1); 
		HttpSession s = req.getSession();
		s.setAttribute("allSaleStat", v);
		
		resp.sendRedirect("showAllSaleStatInfo.jsp");
	}
}

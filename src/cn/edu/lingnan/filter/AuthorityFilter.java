package cn.edu.lingnan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorityFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//判断登陆用户的权限是什么，如果是1，可以去到admin目录下的页面，否则区权限错误的页面
		HttpServletRequest request = (HttpServletRequest) arg0;	//ServletRequest是HttpServletRequest的父类
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession s = request.getSession();
		Integer superValue = (Integer)s.getAttribute("superValue");  //在会话范围内获取指定名字的属性的值，返回值类型为object
		System.out.println("3.-----"+superValue);
		if(superValue != null){  
			if(superValue == 0)  //登陆错误
				response.sendRedirect(request.getContextPath()+"/index.html");
			else
				if(superValue == 1)  //超级用户
					arg2.doFilter(arg0, arg1);  //调用下一级或者直接调用资源
				else  //普通用户
					response.sendRedirect(request.getContextPath()+"/authority.html");
		}else //没有登陆
			response.sendRedirect(request.getContextPath()+"/index.html");					
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}

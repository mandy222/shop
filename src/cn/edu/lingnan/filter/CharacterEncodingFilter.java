package cn.edu.lingnan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	String encoding = null;
	public void destroy() {
		this.encoding = null;		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		System.out.println("字符集过滤器已经启用");
		arg0.setCharacterEncoding(this.encoding);
		arg1.setCharacterEncoding(this.encoding);
		arg2.doFilter(arg0, arg1);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.encoding = arg0.getInitParameter("encoding");
		
	}

}

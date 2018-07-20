package cn.edu.lingnan.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class TagExample1 extends TagSupport{  //标签出来器

	//标签开始做什么
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print("Hello 5.11");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;  //略过标签体，还有一个是执行标签体
	}
	
	//标签结束做什么
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;  //继续执行页面的东西，还有一种是略过
	}	
}

package com.cobble.takeaway.tag;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class BaseXTag extends TagSupport {

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		try {
			JspWriter out = pageContext.getOut();
			ServletRequest request = pageContext.getRequest();
			String base = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort()
					+ request.getServletContext().getContextPath();
			String baseX = "<base href=\""
					+ base + "/"
					+ "\" />";
			out.print(baseX);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
		/*return super.doStartTag();*/
	}

}

package com.cobble.takeaway.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobble.takeaway.util.UserUtil;

public class PrivilegeTag extends ConditionalTagSupport {
	private static final Logger logger = LoggerFactory.getLogger(PrivilegeTag.class);
	
	private String havePrivilege;
	

	public void setHavePrivilege(String havePrivilege) {
		this.havePrivilege = havePrivilege;
	}

	@Override
	protected boolean condition() throws JspTagException {
		Boolean ret = false;
		
		try {
			ret = UserUtil.havePrivilege(havePrivilege);
		} catch (Exception e) {
			logger.error("Check havePrivilege error: {}", e);
		}
		
		return ret;
	}

	@Override
	public int doStartTag() throws JspException {
		
		return super.doStartTag();
	}

	@Override
	public void release() {
		super.release();
	}

	@Override
	public void setScope(String scope) {
		super.setScope(scope);
	}

	@Override
	public void setVar(String var) {
		super.setVar(var);
	}
	
	

}

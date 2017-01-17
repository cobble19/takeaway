package com.cobble.takeaway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHttpRequestWrapper extends HttpServletRequestWrapper {
	private static final Logger logger = LoggerFactory.getLogger(MyHttpRequestWrapper.class);

	public MyHttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getQueryString() {
		String ret = super.getQueryString();
		logger.debug("Original QueryString: {}", ret);
		if (StringUtils.isNotBlank(ret)) {
			if (ret.contains("?10000skip=true")) {
				ret = ret.replace("?10000skip=true", "");
			}
			if (ret.contains("10000skip=true")) {
				ret = ret.replace("10000skip=true", "");
			}
		}
		logger.debug("Finally QueryString: {}", ret);
		return ret;
	}

	@Override
	public String getRequestURI() {
		String ret = super.getRequestURI();
		logger.debug("Original RequestURI: {}", ret);
		if (StringUtils.isNotBlank(ret)) {
			if (ret.contains("?10000skip=true")) {
				ret = ret.replace("?10000skip=true", "");
			}
			if (ret.contains("10000skip=true")) {
				ret = ret.replace("10000skip=true", "");
			}
		}
		logger.debug("Finally RequestURI: {}", ret);
		return ret;
	}

	@Override
	public StringBuffer getRequestURL() {
		StringBuffer sb = super.getRequestURL();
		if (sb != null) {
			String ret = sb.toString();
			logger.debug("Original RequestURL: {}", ret);
			if (StringUtils.isNotBlank(ret)) {
				if (ret.contains("?10000skip=true")) {
					ret = ret.replace("?10000skip=true", "");
				}
				if (ret.contains("10000skip=true")) {
					ret = ret.replace("10000skip=true", "");
				}
			}
			logger.debug("Finally RequestURL: {}", ret);
			sb = new StringBuffer(ret);
		}
		return sb;
	}

	@Override
	public String getPathInfo() {
		String ret = super.getPathInfo();
		logger.debug("Original PathInfo: {}", ret);
		if (StringUtils.isNotBlank(ret)) {
			if (ret.contains("?10000skip=true")) {
				ret = ret.replace("?10000skip=true", "");
			}
			if (ret.contains("10000skip=true")) {
				ret = ret.replace("10000skip=true", "");
			}
		}
		logger.debug("Finally PathInfo: {}", ret);
		return ret;
	}
	
	

}

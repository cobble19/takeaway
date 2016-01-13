package com.cobble.takeaway.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);
	
	public static String getBase(HttpServletRequest request) {
		String base = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort()
				+ request.getServletContext().getContextPath();
		
		return base;
	}
}

package com.cobble.takeaway.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class HttpRequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);
	
	public static String getBase(HttpServletRequest request) {
		String base = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort()
				+ request.getServletContext().getContextPath();
		
		return base;
	}
	
	public static Boolean isWeiXin(HttpServletRequest request) {
		Boolean ret = false;
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.toLowerCase().contains("micromessenger")) {
			return true;
		}
		return ret;
	}
	
	public static void saveRequest(HttpServletRequest request, HttpServletResponse response) {
		RequestCache requestCache = new HttpSessionRequestCache();
		requestCache.saveRequest(request, response);
	}
	
	public static SavedRequest getRequest(HttpServletRequest request, HttpServletResponse response) {
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		return savedRequest;
	}
	
}

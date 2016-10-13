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
			ret = true;
		}
		logger.info("isWeiXin: {}, userAgent: {}", ret, userAgent);
		return ret;
	}
	
	public static Boolean isNotWeiXin(HttpServletRequest request) {
		Boolean ret = !isWeiXin(request);
		return ret;
	}
	
	public static void saveRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Save request: {}", request);
		RequestCache requestCache = new MyHttpSessionRequestCache();
		requestCache.saveRequest(request, response);
	}
	
	public static SavedRequest getRequest(HttpServletRequest request, HttpServletResponse response) {
		RequestCache requestCache = new MyHttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		logger.info("Get SavedRequest: {}", savedRequest);
		return savedRequest;
	}
	
}

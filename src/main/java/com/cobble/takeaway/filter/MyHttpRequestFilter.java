package com.cobble.takeaway.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHttpRequestFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(MyHttpRequestFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			logger.debug("MyHttpRequestFilter Start: {}", filterChain);
			
			HttpServletRequest request = (HttpServletRequest) req;
			filterChain.doFilter(new MyHttpRequestWrapper(request), resp);
			
			logger.debug("MyHttpRequestFilter End: {}", filterChain);
		} catch (Exception e) {
			logger.error("MyHttpRequestFilter Filter: {}", e);
			// 如果报错, 则继续下面的filter
			filterChain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {

	}

}

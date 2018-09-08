package com.cobble.takeaway.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPreFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(MyPreFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			String uri = request.getRequestURI();
			// /guestbook/en_addmsg.asp
			if (uri.contains("/guestbook") || uri.contains(".asp") || uri.contains(".php")) {
				logger.info("拦截无效的URI: {}", uri);
			    return ;
			}
			filterChain.doFilter(req, resp);
		} catch (Exception e) {
			logger.error("MyPreFilter: {}", e);
		}
	}

	@Override
	public void destroy() {

	}

}

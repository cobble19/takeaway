package com.cobble.takeaway.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			filterChain.doFilter(req, resp);
		} catch (Exception e) {
			logger.error("Test Filter: {}", e);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

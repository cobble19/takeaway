package com.cobble.takeaway.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
		if (req instanceof HttpServletRequest) {
			BufferedReader in = new BufferedReader(new InputStreamReader(((HttpServletRequest)req).getInputStream()));
			StringBuilder sb = new StringBuilder();
			String xmlHead = "";
			String xmlContent = "";
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			logger.info("request from InputStream: {}", sb);
		}
		filterChain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

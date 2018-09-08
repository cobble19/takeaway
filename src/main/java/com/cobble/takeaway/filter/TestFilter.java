package com.cobble.takeaway.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
			try {
				if (req instanceof HttpServletRequest) {
					HttpServletRequest request = (HttpServletRequest) req;
					String uri = request.getRequestURI();
					String queryString = request.getQueryString();
					String url = request.getRequestURL() + "";
					if (StringUtils.isNotBlank(queryString)) {
						queryString = queryString.split("#")[0];
						url += "?" + queryString;
					}
					logger.error("uri: {}, queryString: {}, url: {}", uri, queryString, url);
				}
			} catch (Exception e1) {
				logger.error("Show http request error: ", e1);
			}
			logger.error("Test Filter: {}", e);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

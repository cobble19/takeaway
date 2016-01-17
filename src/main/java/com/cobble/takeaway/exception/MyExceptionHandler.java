package com.cobble.takeaway.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandler implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.info("发生异常， 将跳转, ex: {}", ex);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		// 根据不同错误转向不同页面
		if (ex instanceof AccessDeniedException) {
			return new ModelAndView("/errorPage/403", model);
		} else {
			return new ModelAndView("/errorPage/error", model);
		}
	}

}

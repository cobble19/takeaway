package com.cobble.takeaway.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionHandlerAnnotationController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAnnotationController.class);
	
//	@ExceptionHandler(value={IOException.class,SQLException.class})
	/*@ExceptionHandler
	public String exp(Exception ex,HttpServletRequest request) {
		logger.error("系统发生异常: ", ex);
		request.setAttribute("ex", ex); 
		return "/error.jsp";
	}*/
}

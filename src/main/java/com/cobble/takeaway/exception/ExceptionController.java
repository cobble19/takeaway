package com.cobble.takeaway.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.controller.BaseController;

@Controller
public class ExceptionController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping(value = "/errors/{errorCode}")
	public ModelAndView query(@PathVariable("errorCode") String errorCode, Exception ex,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.error("Error: {}", ex);
			ret.addObject("ex", ex);
			ret.setViewName("/errorPage/" + errorCode);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw e;
		}
		
		return ret;
	}

}

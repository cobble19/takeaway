package com.cobble.takeaway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	
	@RequestMapping(value = "")
	public ModelAndView defaultPage() throws Exception {
		ModelAndView ret = new ModelAndView();
		ret.setViewName("index");
		return ret;
	}

	@RequestMapping(value = "/index")
	public ModelAndView index() throws Exception {
		ModelAndView ret = new ModelAndView();
		ret.setViewName("index");
		return ret;
	}
	
}

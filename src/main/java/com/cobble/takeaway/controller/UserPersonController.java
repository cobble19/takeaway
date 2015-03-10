package com.cobble.takeaway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.UserPersonPOJO;
import com.cobble.takeaway.service.UserPersonService;

@Controller
public class UserPersonController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserPersonController.class);
	
	@Autowired
	private UserPersonService userPersonService;
	

	@RequestMapping(value = "/web/userPerson/reg", method = {RequestMethod.POST})
	public ModelAndView findFoodSellersPure(UserPersonPOJO userPersonPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		int result = userPersonService.reg(userPersonPOJO);
		ret.setViewName("page/person/reg_success");
		return ret;
	}
	
}

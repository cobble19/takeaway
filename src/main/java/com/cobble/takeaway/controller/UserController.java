package com.cobble.takeaway.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.spring.security.MyUser;

@Controller
public class UserController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/web/user/person/reg", method = {RequestMethod.POST})
	public ModelAndView regPerson(UserPOJO userPOJO, Model model) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			userPOJO.setUserType(MyUser.PERSON);
			int result = userService.insert(userPOJO);
			ret.addObject("success", true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.addObject("success", false);
			throw e;
		}
		ret.setViewName("redirect:/web/user/person/reg_success");
		
		return ret;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/web/user/person/reg/success", method = {RequestMethod.GET})
	public ModelAndView regPersonSuccess(UserPOJO userPOJO, Model model, Principal principal) throws Exception {
		
		Object name = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView ret = new ModelAndView();
		ret.setViewName("/page/person/reg_success");
		
		return ret;
	}
	
	@RequestMapping(value = "/web/user/enterprise/reg", method = {RequestMethod.POST})
	public ModelAndView regEnterprise(UserPOJO userPOJO, Model model) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			userPOJO.setUserType(MyUser.ENTERPRISE);
			int result = userService.insert(userPOJO);
			ret.addObject("success", true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.addObject("success", false);
			throw e;
		}

		ret.setViewName("/page/person/reg_success");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/user", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<UserPOJO> findUser(UserSearchPOJO userSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<UserPOJO> ret = new ExtjsPOJO<UserPOJO>();
		List<UserPOJO> userPOJOList = new ArrayList<UserPOJO>();
		userPOJOList = userService.finds(userSearchPOJO);
		int total = userService.getCount(userSearchPOJO);
		
		ret.setGridModelList(userPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/user/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(UserPOJO userPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = userService.insert(userPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/user/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(UserPOJO userPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = userService.update(userPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/user/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = userService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

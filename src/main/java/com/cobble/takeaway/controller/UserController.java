package com.cobble.takeaway.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.LocationAreaPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.spring.security.MyUser;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class UserController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	public final static String URL_ADMIN = "/mgr/ta/index.html";
	public final static String URL_INDEX = "/index";
	public final static String URL_PERSION = URL_INDEX;
	public final static String URL_ENTERPRISE = URL_INDEX;
	
	@Autowired
	private UserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @RequestMapping(value = "/web/user/check", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO checkUser(UserPOJO userPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			UserPOJO user = userService.findUserByName(userPOJO.getUsername());
			if (user == null) {
				ret.setErrorCode("1");
				ret.setErrorMsg("没有发现用户: " + userPOJO.getUsername());
			} else {
				if (!user.getPassword().equals(userPOJO.getPassword())) {
					ret.setErrorCode("2");
					ret.setErrorMsg("密码不正确");
				} else {
					ret.setErrorCode("0");
					ret.setErrorMsg("用户存在");
				}
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("find user error: ", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
//			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/user/nickname/exist")
	@ResponseBody
	public StatusPOJO existUserNickname(@RequestParam(value="nickname", required=true) String nickname, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (StringUtils.isBlank(nickname)) {
				ret.setSuccess(false);
				ret.setDesc("昵称不能为空");
				return ret;
			}
			UserPOJO userPOJO2 = userService.findUserByNickname(nickname);
			if (userPOJO2 != null && nickname.equals(userPOJO2.getNickname())) {
				ret.setSuccess(true);
				ret.setDesc("存在此昵称： " + nickname);
			} else {
				ret.setSuccess(false);
				ret.setDesc("不存在此昵称： " + nickname);
			}
		} catch (Exception e) {
			LOGGER.error(" error.", e);
			ret.setSuccess(false);
			ret.setDesc("发生异常： " + e.getMessage());
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/user/exist")
	@ResponseBody
	public StatusPOJO existUser(@RequestParam(value="username", required=true) String username, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (StringUtils.isBlank(username)) {
				ret.setSuccess(false);
				ret.setDesc("用户名不能为空");
				return ret;
			}
			UserPOJO userPOJO2 = userService.findUserByName(username);
			if (userPOJO2 != null && userPOJO2.getUsername().equals(username)) {
				ret.setSuccess(true);
				ret.setDesc("存在此用户： " + username);
			} else {
				ret.setSuccess(false);
				ret.setDesc("不存在此用户： " + username);
			}
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc("发生异常： " + e.getMessage());
			throw e;
		}
		
		return ret;
	}
    
	@RequestMapping(value = "/web/user/updatePassword")
	@ResponseBody
	public StatusPOJO updatePassword(UserPOJO userPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			MyUser myUser = UserUtil.getCurrentUser();
			if (myUser.getUserId().longValue() == userPOJO.getUserId() && myUser.getPassword().equals(userPOJO.getPasswordOld())) {
				int result = userService.updatePassword(userPOJO);
				ret.setSuccess(true);
			} else {
				ret.setSuccess(false);
			}
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/user/updateInfo")
	@ResponseBody
	public StatusPOJO updateInfo(UserPOJO userPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			MyUser myUser = UserUtil.getCurrentUser();
			if (myUser.getUserId().longValue() == userPOJO.getUserId()) {
				int result = userService.updateInfo(userPOJO);
				ret.setSuccess(true);
				myUser.setEmail(userPOJO.getEmail());
				myUser.setNickname(userPOJO.getNickname());
				UsernamePasswordAuthenticationToken anAnthentication = new UsernamePasswordAuthenticationToken(myUser, myUser.getPassword(), myUser.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(anAnthentication);
				
			} else {
				ret.setSuccess(false);
			}
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

    @RequestMapping(value = "/web/media/usercenter", method = {RequestMethod.GET})
	public ModelAndView usercenter4Media(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView ret = new ModelAndView();
    	MyUser myUser = UserUtil.getCurrentUser();
    	myUser = new MyUser(myUser);
    	
    	ret.addObject("myUser", myUser);
    	ret.setViewName("/page/media/user_center");
		
		return ret;
	}
    
    @RequestMapping(value = "/web/enterprise/usercenter", method = {RequestMethod.GET})
	public ModelAndView usercenter(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView ret = new ModelAndView();
    	MyUser myUser = UserUtil.getCurrentUser();
    	myUser = new MyUser(myUser);
    	
    	ret.addObject("myUser", myUser);
    	ret.setViewName("/page/enterprise/user_center");
		
		return ret;
	}
    
    @RequestMapping(value = "/web/person/usercenter", method = {RequestMethod.GET})
	public ModelAndView usercenter4person(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView ret = new ModelAndView();
    	MyUser myUser = UserUtil.getCurrentUser();
    	myUser = new MyUser(myUser);
    	
    	ret.addObject("myUser", myUser);
    	ret.setViewName("/page/person/user_center");
		
		return ret;
	}
    
    @RequestMapping(value = "/web/currentUser", method = {RequestMethod.GET})
    @ResponseBody
	public MyUser currentUser(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
    	MyUser ret = UserUtil.getCurrentUser();
    	ret = new MyUser(ret);
		
		return ret;
	}
    
	@RequestMapping(value = "/web/login/success", method = {RequestMethod.GET})
	public ModelAndView loginSuccess(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();

		/*ret.setViewName("redirect:/web/user/enterprise/reg/success");*/
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MyUser myUser = null;
		if (principal instanceof MyUser) {
			myUser = (MyUser) principal;
		}
		
		if (myUser == null) {
			response.getWriter().write("Null user");
		} else {
			response.getWriter().write("User Type: " + myUser.getUserType());
		}

		String url = "/mgr/ta/index.html";
		if (MyUser.ADMIN.equals(myUser.getUserType())) {
			url = URL_ADMIN;
		} else {
			url = URL_INDEX;
		}
		
		redirectStrategy.sendRedirect(request, response, url);
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/web/regSuccess")
	public ModelAndView regSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		
		HttpSession session = request.getSession();
		UserPOJO userPOJO = (UserPOJO) session.getAttribute("regUserPOJO");
		if (userPOJO != null) {
			ret.addObject("j_username", userPOJO.getUsername());
			ret.addObject("j_password", userPOJO.getPassword());
		}
		ret.setViewName("/reg_success");
		return ret;
	}
	
	@RequestMapping(value = "/web/user/person/reg", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO regPerson(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			userPOJO.setUserType(MyUser.PERSON);
			int result = userService.insert(userPOJO);
			ret.setSuccess(true);
			
			HttpSession session = request.getSession();
			session.setAttribute("regUserPOJO", userPOJO);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
		}
		
		return ret;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/web/user/person/reg/success", method = {RequestMethod.GET})
	public ModelAndView regPersonSuccess(UserPOJO userPOJO, Model model, Principal principal) throws Exception {
		
		Object name = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView ret = new ModelAndView();
		/*ret.setViewName("/page/person/reg_success");*/
		ret.setViewName("redirect:/index");
		
		return ret;
	}
	
	@RequestMapping(value = "/web/user/enterprise/reg", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO regEnterprise(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			userPOJO.setUserType(MyUser.ENTERPRISE);
			int result = userService.insert(userPOJO);
			ret.setSuccess(true);
			
			HttpSession session = request.getSession();
			session.setAttribute("regUserPOJO", userPOJO);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
		}

		return ret;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/web/user/enterprise/reg/success", method = {RequestMethod.GET})
	public ModelAndView regEnterpriseSuccess(UserPOJO userPOJO, Model model, Principal principal) throws Exception {
		
		//Object name = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView ret = new ModelAndView();
		ret.setViewName("/page/enterprise/reg_success");
		
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

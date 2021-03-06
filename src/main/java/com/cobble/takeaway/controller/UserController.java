package com.cobble.takeaway.controller;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.RelWxIndexMapPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.service.RelWxIndexMapService;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.spring.security.MyUser;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.UserUtil;
import com.cobble.takeaway.util.WxUtil;

@Controller
public class UserController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	public final static String URL_ADMIN = "/mgr/ta/index.html";
	public final static String URL_INDEX = "/index";
	public final static String URL_PERSION = URL_INDEX;
	public final static String URL_ENTERPRISE = URL_INDEX;
	public final static String URL_LOGIN_PC = "/login.jsp";
	public final static String URL_LOGIN_WEIXIN = "/web/wx/oauth2/third/personUser/login";
	

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserService userService;
	@Autowired
	private WxAuthorizerInfoService wxAuthorizerInfoService;
	@Autowired
	private RelWxIndexMapService relWxIndexMapService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping(value = "/web/unified/profile", method = {RequestMethod.GET})
	public ModelAndView userProfile(Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		MyUser myUser = UserUtil.getCurrentUser();
		myUser = new MyUser(myUser);
		
		Long userId = myUser.getUserId();

		Map map = new HashMap();
		map.put("userId", userId);
		String wxComLoginUrl = WxUtil.getWxComLoginUrl(map);

		WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
		try {
			wxAuthorizerInfoPOJO = wxAuthorizerInfoService.findWxAuthorizerInfoByUserId(userId);
		} catch (Exception e) {
			logger.error("Can't get Authorizer: {}", e);
		}

		ret.addObject("myUser", myUser);
		ret.addObject("wxComLoginUrl", wxComLoginUrl);
		ret.addObject("wxAuthorizerInfoPOJO", wxAuthorizerInfoPOJO);
		ret.setViewName("/page/unified/profile_single");

		return ret;
	}
    
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
			logger.error("find user error: ", e);
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
			logger.error(" error.", e);
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
			logger.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc("发生异常： " + e.getMessage());
			throw e;
		}
		
		return ret;
	}
    
	@RequestMapping(value = "/api/user/updatePassword", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO updatePassword(UserPOJO userPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			MyUser myUser = UserUtil.getCurrentUser();
			if (myUser.getUserId().longValue() == userPOJO.getUserId() && myUser.getPassword().equals(userPOJO.getPasswordOld())) {
				int result = userService.updatePassword(userPOJO);
				ret.setSuccess(true);
			} else {
				if (!myUser.getPassword().equals(userPOJO.getPasswordOld())) {
					ret.setErrorCode("PaswordError");
					ret.setErrorMsg("原密码不正确！");
				}
				ret.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/user/updateInfo", method = {RequestMethod.POST})
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
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

    @RequestMapping(value = "/web/unified/usercenter", method = {RequestMethod.GET})
	public ModelAndView usercenter4Unified(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		MyUser myUser = UserUtil.getCurrentUser();
		myUser = new MyUser(myUser);

		Map map = new HashMap();
		map.put("userId", myUser.getUserId());
		String wxComLoginUrl = WxUtil.getWxComLoginUrl(map);

		WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
		try {
			wxAuthorizerInfoPOJO = wxAuthorizerInfoService.findWxAuthorizerInfoByUserId(myUser.getUserId());
		} catch (Exception e) {
			logger.error("Can't get Authorizer: {}", e);
		}

		ret.addObject("myUser", myUser);
		ret.addObject("wxComLoginUrl", wxComLoginUrl);
		ret.addObject("wxAuthorizerInfoPOJO", wxAuthorizerInfoPOJO);
		ret.setViewName("/page/unified/user_center");

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
    @RequestMapping(value = "/web/login/dispatcher", method = {RequestMethod.GET})
	public ModelAndView loginDispatcher(Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		logger.info("Visit uri: " + uri + ", qs: " + qs);
		
		ModelAndView ret = new ModelAndView();
		String url = URL_LOGIN_PC;
		
		if (HttpRequestUtil.isWeiXin(request)) {
			url = URL_LOGIN_WEIXIN;
		}
		
		redirectStrategy.sendRedirect(request, response, url);
//		return ret;
		return null;
	}
    
    @RequestMapping(value = "/web/login/success/dispatcher", method = {RequestMethod.GET})
	public ModelAndView loginSuccessDispatcher(Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		logger.info("Visit uri: " + uri + ", qs: " + qs);
		
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
		
		SavedRequest savedRequest = HttpRequestUtil.getRequest(request, response);
		/*DefaultSavedRequest defaultSavedRequest = null;
		if (savedRequest instanceof DefaultSavedRequest) {
			defaultSavedRequest = (DefaultSavedRequest) savedRequest;
		}*/
		if (savedRequest != null) {
			url = savedRequest.getRedirectUrl();
		} else {
			if (MyUser.ADMIN.equals(myUser.getUserType())) {
				url = URL_ADMIN;
			} else {
				url = URL_INDEX;
			}
		}
		
		redirectStrategy.sendRedirect(request, response, url);
//		return ret;
		return null;
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
	
	@RequestMapping(value = "/web/wxAutoLogin")
	public ModelAndView wxAutoLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		
		HttpSession session = request.getSession();
		UserPOJO userPOJO = (UserPOJO) session.getAttribute("regUserPOJO");
		if (userPOJO != null) {
			ret.addObject("j_username", userPOJO.getUsername());
			ret.addObject("j_password", userPOJO.getPassword());
		}
		ret.setViewName("/wx_auto_login");
		return ret;
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
			logger.error("insert error.", e);
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
	public ModelAndView regEnterprise(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		/// 请同时修改 /api/user/enterprise/reg
		///
		ModelAndView ret = new ModelAndView();
		try {
			userPOJO.setUserType(MyUser.ENTERPRISE);
			int result = userService.insert(userPOJO);
			ret.addObject("success", true);
			
			HttpSession session = request.getSession();
			session.setAttribute("regUserPOJO", userPOJO);
			// create MyUser
			MyUser myUser = userService.createPrincipalByName(userPOJO.getUsername(), session);
			
			session.setAttribute("userId", userPOJO.getUserId());
			
			try {
				// 创建微信首页超链接
				RelWxIndexMapPOJO relWxIndexMapPOJO = new RelWxIndexMapPOJO();
				relWxIndexMapPOJO.setUserId(userPOJO.getUserId());
				relWxIndexMapPOJO.setWxIndexCode(userPOJO.getWxIndexCode());
				relWxIndexMapService.insert(relWxIndexMapPOJO);
				
				// 创建文件夹, 用于保存文件/图片/等等
				// 上传文件的代码才需要, 这儿是多余的代码
				/*String fileDirPath = messageSource.getMessage("files.directory", null, null);
				File fileDir = new File(fileDirPath);
				FileUtils.forceMkdir(fileDir);
				if (!fileDirPath.endsWith(File.separator)) {
					fileDirPath += File.separator;
				}
				fileDir = new File(fileDirPath + relWxIndexMapPOJO.getWxIndexCode());
				FileUtils.forceMkdir(fileDir);*/
			} catch (Exception e) {
				logger.error("创建微信首页链接/文件夹失败, userId: {}, wxIndexCode: {}, Exception: {}"
						, userPOJO.getUserId(), userPOJO.getWxIndexCode(), e);
			}
			
			// add wxComLoginUrl
			
			Map map = new HashMap();
			map.put("userId", myUser.getUserId());
	    	String wxComLoginUrl = WxUtil.getWxComLoginUrl(map);
	    	
	    	ret.addObject("wxComLoginUrl", wxComLoginUrl);
	    	redirectStrategy.sendRedirect(request, response, wxComLoginUrl);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.addObject("success", false);
			ret.addObject("desc", e.getMessage());
		}

		return null;
	}
	
	@RequestMapping(value = "/api/user/enterprise/reg", method = {RequestMethod.POST})
	@ResponseBody
	public Map regEnterprise4API(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		/// 请同时修改 /web/user/enterprise/reg
		///
		Map ret = new HashMap();
		try {
			userPOJO.setUserType(MyUser.ENTERPRISE);
//			int result = userService.insert(userPOJO);
			
			HttpSession session = request.getSession();
			session.setAttribute("regUserPOJO", userPOJO);
			// create MyUser
//			MyUser myUser = userService.createPrincipalByName(userPOJO.getUsername(), session);
			
//			logger.info("userPOJO userId: {}, myUser userId: {}", userPOJO.getUserId(), myUser.getUserId());
			
			// 可以用session
//			session.setAttribute("userId", userPOJO.getUserId());
			
			try {
				// 创建微信首页超链接
//				RelWxIndexMapPOJO relWxIndexMapPOJO = new RelWxIndexMapPOJO();
//				relWxIndexMapPOJO.setUserId(userPOJO.getUserId());
//				relWxIndexMapPOJO.setWxIndexCode(userPOJO.getWxIndexCode());
//				relWxIndexMapService.insert(relWxIndexMapPOJO);
				
				// 创建文件夹, 用于保存文件/图片/等等
				// 上传文件的代码才需要, 这儿是多余的代码
				/*String fileDirPath = messageSource.getMessage("files.directory", null, null);
				File fileDir = new File(fileDirPath);
				FileUtils.forceMkdir(fileDir);
				if (!fileDirPath.endsWith(File.separator)) {
					fileDirPath += File.separator;
				}
				fileDir = new File(fileDirPath + relWxIndexMapPOJO.getWxIndexCode());
				FileUtils.forceMkdir(fileDir);*/
			} catch (Exception e) {
				logger.error("创建微信首页链接/文件夹失败, userId: {}, wxIndexCode: {}, Exception: {}"
						, userPOJO.getUserId(), userPOJO.getWxIndexCode(), e);
			}
			
			// add wxComLoginUrl
			
			Map map = new HashMap();
			map.put(CommonConstant.COMMON_PARAM, CommonConstant.REG_ENTERPRISE_USER);	// session.setAttribute("regUserPOJO", userPOJO);
//			map.put("userId", myUser.getUserId());
		    	String wxComLoginUrl = WxUtil.getWxComLoginUrl(map);
		    	
		    	ret.put("wxComLoginUrl", wxComLoginUrl);
			ret.put("success", true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			ret.put("desc", e.getMessage());
		}

		return ret;
	}
	
	/*@RequestMapping(value = "/web/user/enterprise/reg", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView regEnterprise(UserPOJO userPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			userPOJO.setUserType(MyUser.ENTERPRISE);
			int result = userService.insert(userPOJO);
			ret.addObject("success", true);
			
			HttpSession session = request.getSession();
			session.setAttribute("regUserPOJO", userPOJO);
			// create MyUser
			MyUser myUser = userService.createPrincipalByName(userPOJO.getUsername(), session);
			// add wxComLoginUrl

	    	String wxComLoginUrl = WxUtil.getWxComLoginUrl();
	    	
	    	ret.addObject("wxComLoginUrl", wxComLoginUrl);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.addObject("success", false);
			ret.addObject("desc", e.getMessage());
		}

		return ret;
	}*/
	
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
			logger.error("insert error.", e);
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
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/user/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = userService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

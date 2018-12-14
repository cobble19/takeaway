package com.cobble.takeaway.spring.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import com.cobble.takeaway.oauth2.MyRedirectStrategy;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.service.VoteService;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.util.BeanUtil;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.HttpRequestUtil;

public class MyAccessDecisionManager implements AccessDecisionManager {
	private static final Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);

	MyRedirectStrategy myRedirectStrategy = new MyRedirectStrategy();

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		FilterInvocation fi = (FilterInvocation) object;
		HttpServletResponse response = fi.getHttpResponse();
		HttpServletRequest request = fi.getRequest();
		HttpSession session = request.getSession();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MyUser myUser = null;
		
		if ("anonymousUser".equalsIgnoreCase(principal.toString())) {
			List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS");
			myUser = new MyUser("anonymousUser", "anonymousUser", authorities, "ANONYMOUS_USER");
			UsernamePasswordAuthenticationToken anAnthentication = new UsernamePasswordAuthenticationToken(myUser, "anonymousUser", authorities);
			SecurityContextHolder.getContext().setAuthentication(anAnthentication);
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		if (principal instanceof MyUser) {
			myUser = (MyUser) principal;
			session.setAttribute("username", myUser.getUsername());
			session.setAttribute("userType", myUser.getUserType());
			session.setAttribute("myUser", myUser);
			String proxyOpenId = (String) session.getAttribute(CommonConstant.PROXY_OPEN_ID);
			String openId = (String) session.getAttribute(CommonConstant.OPEN_ID);
			String unionId = (String) session.getAttribute(CommonConstant.UNION_ID);
			if (StringUtils.isNotBlank(proxyOpenId)) {
				myUser.setProxyOpenId(proxyOpenId);
			}
			if (StringUtils.isNotBlank(openId)) {
				myUser.setOpenId(openId);
			}
			if (StringUtils.isNotBlank(unionId)) {
				myUser.setUnionId(unionId);
			}

			WxAuthorizerInfoService wxAuthorizerInfoService = (WxAuthorizerInfoService) BeanUtil.get("wxAuthorizerInfoServiceImpl");
			WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
			try {
				if (!MyUser.PERSON.equalsIgnoreCase(myUser.getUserType()) && myUser.getUserId() != null) {

					wxAuthorizerInfoPOJO = wxAuthorizerInfoService.findWxAuthorizerInfoByUserId(myUser.getUserId());
					if (wxAuthorizerInfoPOJO != null) {
						session.setAttribute(CommonConstant.AUTHORIZER_APP_ID, wxAuthorizerInfoPOJO.getAuthorizerAppId());
					}
				}
			} catch (Exception e) {
				logger.error("get wxAuthorizerInfo Exception: {}", e);
			}
		}
		
		logger.info("Login success: {}", principal);
		
		String url = ((FilterInvocation) object).getRequestUrl();
		logger.debug("URL = {}", url);
		
//		if (url.startsWith("/wx/hfjt") 
//				&& ("anonymousUser".equals(myUser.getUsername()) || !checkSessionUrls(url, session))) {
//			throw new AccessDeniedException("需要登录系统" + ", session is null, user = " + authentication.getName()
//					+ ", url = " + url);
//			/*try {
//				request.getRequestDispatcher("/web/wx/oauth2/third/personUser/login").forward(request, response);
//				return;
//			} catch (ServletException e) {
//				logger.error("跳转到微信登陆页面异常ServletException:{}", e);
//			} catch (IOException e) {
//				logger.error("跳转到微信登陆页面异常IOException:{}", e);
//			}*/
//		}
		
		if (!checkSessionUrls(url, session, request, response)) {
			this.saveRequest(request, response);
			try {
				myRedirectStrategy.sendRedirect(request, response, this.getWxLoginUrl(request, response));
			} catch (Exception e) {
				logger.error("Redirect Exception: ", e);
			}
//			throw new InsufficientAuthenticationException("InsufficientAuthenticationException");
			throw new AccessDeniedException("需要登录系统" + ", session is null, user = " + authentication.getName()
					+ ", url = " + url);
		}
		
		if (url.equalsIgnoreCase("/") || url.equalsIgnoreCase("")
				|| url.startsWith("/index") ) {
			return;
		}
		
		/*if (url.equalsIgnoreCase("/wx/hfjt")) {
			String openId = (String) session.getAttribute("openId");
			if (StringUtils.isBlank(openId)) {
				throw new AccessDeniedException("需要微信登录系统" + ", openId is null, user = " + authentication.getName()
						+ ", url = " + url);
			}
		}*/
		
		if ("admin".equalsIgnoreCase(myUser.getUsername())) {
			return;
		}
		
		
		if (CollectionUtils.isEmpty(configAttributes)) {	// 资源需要的角色
			logger.debug("url= {},  权限需要分配角色。", url);
			this.saveRequest(request, response);
			try {
				myRedirectStrategy.sendRedirect(request, response, this.getWxLoginUrl(request, response));
			} catch (Exception e) {
				logger.error("Redirect Exception: ", e);
			}
			throw new AccessDeniedException("权限需要分配角色," + ", configAttributes is null, user = " + authentication.getName()
					+ ", url = " + url);
		}
		/*Iterator<ConfigAttribute> it = configAttributes.iterator();*/
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if (CollectionUtils.isEmpty(authorities)) {
			// 当前的用户和企业用户没有角色和权限， 只有后台管理员有权限
			if (MyUser.PERSON.equalsIgnoreCase(myUser.getUserType()) || MyUser.ENTERPRISE.equalsIgnoreCase(myUser.getUserType())
					|| MyUser.MEDIA.equalsIgnoreCase(myUser.getUserType())) {
				logger.info("username= {},  User Type = {}", authentication.getName(), myUser.getUserType());
				return;
			}
			logger.debug("username= {},  用户需要分配角色。", authentication.getName());

			this.saveRequest(request, response);
			try {
				myRedirectStrategy.sendRedirect(request, response, this.getWxLoginUrl(request, response));
			} catch (Exception e) {
				logger.error("Redirect Exception: ", e);
			}
			throw new AccessDeniedException("用户需要分配角色," + ", authorities is null, user = " + authentication.getName()
					+ ", url = " + url);
		}
		
		String temp = "";
		for (ConfigAttribute configAttribute : configAttributes) {
			temp += configAttribute.getAttribute() + ", ";
		}
		logger.info("需要的角色：{}", temp);
		temp = "";
		for (GrantedAuthority authority : authorities) {
			temp += authority.getAuthority() + ", ";
		}
		logger.info("用户的角色：{}", temp);
		
		Iterator<ConfigAttribute> it = configAttributes.iterator();
		while (it.hasNext()) {
			ConfigAttribute configAttribute = it.next();
			String needRole = configAttribute.getAttribute();
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (needRole.trim().equalsIgnoreCase(grantedAuthority.getAuthority().trim())) {
					return;
				}
			}
		}
		this.saveRequest(request, response);
		try {
			myRedirectStrategy.sendRedirect(request, response, this.getWxLoginUrl(request, response));
		} catch (Exception e) {
			logger.error("Redirect Exception: ", e);
		}
		throw new AccessDeniedException("没有权限, user = " + authentication.getName()
				+ ", url = " + url);
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
		SavedRequest savedRequest = HttpRequestUtil.getRequest(request, response);
		if (savedRequest == null) {
			HttpRequestUtil.saveRequest(request, response);
		}
	}
	
	private UserPOJO getUser4Publisher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserPOJO ret = null;
		
		try {
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			String url = request.getRequestURL().toString();
			HttpSession session = request.getSession();

			if (qs == null) {
				qs = "";
			}
			logger.info("uri: {}, qs: {}, url: {}", uri, qs, url);
			
			UserService userService = (UserService) BeanUtil.get("userServiceImpl");
			VoteService voteService = (VoteService) BeanUtil.get("voteServiceImpl");
			WxAuthorizerInfoService wxAuthorizerInfoService = (WxAuthorizerInfoService) BeanUtil.get("wxAuthorizerInfoServiceImpl");
			
			UserPOJO userPOJO = null;
			// indexCode、活动发布者， 不是普通个人用户
			// 1. 根据indexCode,得到user。 
			// 2. 根据activityid得到user
			// 3. 根据/web/wx/usercenter/{wxIndexCode}/person
			if (uri.startsWith("/wx/")) {
				int index = uri.indexOf("/wx/");
				String indexCode = uri.substring(index + 4);
				logger.info("Index Code: {}", indexCode);
				if (!indexCode.contains("/")) {
					session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
					userPOJO = userService.findUserByIndexCode(indexCode);
				}
			} else if (uri.startsWith("/web/wx/usercenter/")) {
				Pattern p = Pattern.compile("(/web/wx/usercenter/)(\\w+)(/person)");
				Matcher m = p.matcher(uri);
				if (m.find() && m.groupCount() == 3) {
					String s = m.group(2);
					logger.info("index code: {}", s);
					String indexCode = s;
					if (!indexCode.contains("/")) {
						session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
						userPOJO = userService.findUserByIndexCode(indexCode);
					}
				}
			} else if (uri.contains("/activity_detail.jsp")) {	// /page/enterprise(unified)/activity_detail.jsp?activityId=31
				Pattern p = Pattern.compile("(activityId=)(\\d+)");
				Matcher m = p.matcher(qs);
				if (m.find() && m.groupCount() == 2) {
					String s = m.group(2);
					logger.info("activityId: {}", s);
					Long activityId = Long.parseLong(s);
					userPOJO = userService.findUserByActivityId(activityId);
					
					if (userPOJO != null && userPOJO.getUserId() != null) {
						UserPOJO user4IndexCode = userService.findUser4IndexCodeByUserId(userPOJO.getUserId());
						if (user4IndexCode != null) {
							String indexCode = user4IndexCode.getRelWxIndexMapPOJO().getWxIndexCode();
							session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
						}
					}
				}
			} else if (uri.contains("interactive2Detail")) {	// /page/enterprise(unified)/activity_detail.jsp?activityId=31
				Pattern p = Pattern.compile("(interactiveId=)(\\d+)");
				Matcher m = p.matcher(qs);
				if (m.find() && m.groupCount() == 2) {
					String s = m.group(2);
					logger.info("interactiveId: {}", s);
					Long interactiveId = Long.parseLong(s);
					userPOJO = userService.findUserByInteractiveId(interactiveId);
					
					if (userPOJO != null && userPOJO.getUserId() != null) {
						UserPOJO user4IndexCode = userService.findUser4IndexCodeByUserId(userPOJO.getUserId());
						if (user4IndexCode != null) {
							String indexCode = user4IndexCode.getRelWxIndexMapPOJO().getWxIndexCode();
							session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
						}
					}
				}
			} else if (uri.contains("/vote/query/")) {	// /page/enterprise/activity_detail.jsp?activityId=31
				Pattern p = Pattern.compile("(/vote/query/)(\\d+)");
				Matcher m = p.matcher(uri);
				if (m.find() && m.groupCount() == 2) {
					String s = m.group(2);
					logger.info("voteId: {}", s);
					Long voteId = Long.parseLong(s);
					VotePOJO votePOJO = voteService.findById(voteId);
					
					userPOJO = userService.findById(votePOJO.getUserId());
					
					if (userPOJO != null && userPOJO.getUserId() != null) {
						UserPOJO user4IndexCode = userService.findUser4IndexCodeByUserId(userPOJO.getUserId());
						if (user4IndexCode != null) {
							String indexCode = user4IndexCode.getRelWxIndexMapPOJO().getWxIndexCode();
							session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
						}
					}
				}
			} else if (uri.contains("/vote/loadmore/query/")) {	// /page/enterprise/activity_detail.jsp?activityId=31
				Pattern p = Pattern.compile("(/vote/loadmore/query/)(\\d+)");
				Matcher m = p.matcher(uri);
				if (m.find() && m.groupCount() == 2) {
					String s = m.group(2);
					logger.info("voteId: {}", s);
					Long voteId = Long.parseLong(s);
					VotePOJO votePOJO = voteService.findById(voteId);
					
					userPOJO = userService.findById(votePOJO.getUserId());
					
					if (userPOJO != null && userPOJO.getUserId() != null) {
						UserPOJO user4IndexCode = userService.findUser4IndexCodeByUserId(userPOJO.getUserId());
						if (user4IndexCode != null) {
							String indexCode = user4IndexCode.getRelWxIndexMapPOJO().getWxIndexCode();
							session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
						}
					}
				}
			} else if (uri.contains("/web/wxpay") || uri.contains("/web/ecommerce/ecorder/ecproduct/")
						|| uri.contains("/web/weixin/wxmycard") || uri.contains("/web/ecommerce/ecorder/ecwxcardacquire")) {
				
				// 关注的时候发送url
				if (uri.contains("/web/ecommerce/ecorder/ecproduct/")) {
					session.setAttribute(CommonConstant.ECOMMERCE_URL_1, url + "?" + qs);
				}
				
				Pattern p = Pattern.compile("(authorizerAppId=)(\\w+)");
				Matcher m = p.matcher(qs);
				if (m.find() && m.groupCount() == 2) {
					String s = m.group(2);
					logger.info("authorizerAppId: {}", s);
					String authorizerAppId = s;

					WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
					wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(authorizerAppId);
					List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
					if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(wxAuthorizerInfoPOJOs)) {
						userPOJO = userService.findById(wxAuthorizerInfoPOJOs.get(0).getUserId());
					}
					
					if (userPOJO != null && userPOJO.getUserId() != null) {
						UserPOJO user4IndexCode = userService.findUser4IndexCodeByUserId(userPOJO.getUserId());
						if (user4IndexCode != null) {
							String indexCode = user4IndexCode.getRelWxIndexMapPOJO().getWxIndexCode();
							session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
						}
					}
				}
			}
			// if没有判断url, 则用默认的APPID
			if (userPOJO == null) {
			    logger.warn("没有通过URL来获取到公众号信息, 用缺省的公众号authorizerAppId: {}", CommonConstant.DWYZ_AUTHORIZER_APP_ID);
				String authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;

				WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
				wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(authorizerAppId);
				List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
				if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(wxAuthorizerInfoPOJOs)) {
					userPOJO = userService.findById(wxAuthorizerInfoPOJOs.get(0).getUserId());
				}

				if (userPOJO != null && userPOJO.getUserId() != null) {
					UserPOJO user4IndexCode = userService.findUser4IndexCodeByUserId(userPOJO.getUserId());
					if (user4IndexCode != null) {
						String indexCode = user4IndexCode.getRelWxIndexMapPOJO().getWxIndexCode();
						session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
					}
				}
			}
			
			ret = userPOJO;
		} catch (Exception e) {
			logger.error("get enterprise user exception: {}", e);
		}
		return ret;
	}
	
	private WxAuthorizerInfoPOJO getWxAuthorizerInfo(Long userId) {
		WxAuthorizerInfoPOJO ret = null;
		try {
			// 获取AuthorizerAppId
			WxAuthorizerInfoService wxAuthorizerInfoService = (WxAuthorizerInfoService) BeanUtil.get("wxAuthorizerInfoServiceImpl");
			WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
			if (userId == null) {
				logger.info("微官网、活动发布者为空, 使用默认的得味驿站公众号代理");
				WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
				wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(CommonConstant.DWYZ_AUTHORIZER_APP_ID);
				List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = null;
				try {
					wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
				} catch (Exception e) {
					logger.error("Got Authorizer Exception: {}", e);
				}
				if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
					wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(0);
				}
			} else {
				logger.info("微官网、活动发布者不为空");
				wxAuthorizerInfoPOJO = wxAuthorizerInfoService.findWxAuthorizerInfoByUserId(userId);
			}
			logger.info("wxAuthorizerInfoPOJO: {}", wxAuthorizerInfoPOJO);
			ret = wxAuthorizerInfoPOJO;
		} catch (Exception e) {
			logger.error("get WxAuthorizerInfo exception: {}", e);
		}
		
		return ret;
	}
	
	public String getWxLoginUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ret = "";
		if (!HttpRequestUtil.isWeiXin(request)) {
			return HttpRequestUtil.getBase(request) + "/login.jsp";
		}
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		String url = request.getRequestURL().toString();
		HttpSession session = request.getSession();
		
		logger.info("uri: {}, qs: {}, url: {}", uri, qs, url);
		
		
		WxAuthorizerInfoService wxAuthorizerInfoService = (WxAuthorizerInfoService) BeanUtil.get("wxAuthorizerInfoServiceImpl");
		MessageSource messageSource = (MessageSource) BeanUtil.get("messageSource");

		String wxThirdClientId = messageSource.getMessage("WX.third.clientId", null, null);
		// 第三方网页登陆
		String wxThirdWebAuthorizeUrl = messageSource.getMessage("WX.third.web.authorizeUrl", null, null);
		String wxThirdWebRedirectUrl = messageSource.getMessage("WX.third.web.redirectUrl", null, null);
		
		String scope = messageSource.getMessage("WX.scope", null, null);
		
		UserService userService = (UserService) BeanUtil.get("userServiceImpl");
		VoteService voteService = (VoteService) BeanUtil.get("voteServiceImpl");
		
		UserPOJO userPOJO = this.getUser4Publisher(request, response);
		
		// 获取AuthorizerAppId
		WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = this.getWxAuthorizerInfo(userPOJO.getUserId());
		
		// 个人微信用户登录系统, 通过DWYZ代理登录
		String wxWebLoginUrl = "";
		String wxThirdPersonUserLoginUrl = "";
		
		if (wxAuthorizerInfoPOJO != null) {
			String extraParam = "${AND}loginVice${EQ}loginVice"
					+ "${AND}authorizerUserNameVice${EQ}" + wxAuthorizerInfoPOJO.getUserName();

			wxWebLoginUrl = wxThirdWebAuthorizeUrl
			.replace("COMPONENT_APPID", wxThirdClientId)
			.replace("APPID", CommonConstant.PROXY_AUTHORIZER_APP_ID_VALUE)
			.replace("REDIRECT_URI", wxThirdWebRedirectUrl.contains("?") ? 
					wxThirdWebRedirectUrl + extraParam
					: wxThirdWebRedirectUrl + "?abc=1" + extraParam)
			.replace("SCOPE", scope)
			.replace("STATE", RandomStringUtils.randomAlphabetic(6))
			;
			
			wxThirdPersonUserLoginUrl = wxWebLoginUrl;
			//wxThirdPersonUserLoginUrl = myRedirectStrategy.encodeQueryParam(wxThirdPersonUserLoginUrl);
//			wxThirdPersonUserLoginUrl = wxThirdPersonUserLoginUrl.replace("&loginVice=", "%26loginVice%3D")
//										.replace("&authorizerUserNameVice=", "%26authorizerUserNameVice%3D");
		} else {
			throw new NullPointerException("AuthorizerInfo is null");
		}
		ret = wxThirdPersonUserLoginUrl;
		return ret;
	}
	
	public boolean checkSessionUrls(String url, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Boolean ret = true;
		try {
			PathMatcher pathMatcher = new AntPathMatcher();
			MessageSource ms = (MessageSource) BeanUtil.get("messageSource");
			String checkSessionUrlsBlacklist = ms.getMessage("check.session.urls.noneedlogin", null, null);
			String[] checkSessionUrlsBlacklistArr = StringUtils.split(checkSessionUrlsBlacklist, ",");
			// blacklist, then return true, not filter
			if (ArrayUtils.isNotEmpty(checkSessionUrlsBlacklistArr)) {
				for (String temp : checkSessionUrlsBlacklistArr) {
					boolean isMatcherBlacklist = pathMatcher.match(StringUtils.trim(temp), url);
					if (isMatcherBlacklist) {
						return true;
					}
				}
			}

			String checkSessionUrlsWhitelist = ms.getMessage("check.session.urls.needlogin", null, null);
			String[] checkSessionUrlsWhitelistArr = StringUtils.split(checkSessionUrlsWhitelist, ",");
			boolean isMatcher = false;
			if (ArrayUtils.isNotEmpty(checkSessionUrlsWhitelistArr)) {
				for (String temp : checkSessionUrlsWhitelistArr) {
					isMatcher = pathMatcher.match(StringUtils.trim(temp), url);
					if (isMatcher) {
						break;
					}
				}
			}
			
			MyUser myUser = (MyUser) session.getAttribute("myUser");
			if (isMatcher) {
				if (myUser == null || myUser.getUserId() == null) {
					logger.error("Not user been login, please check it.");
					ret = false;
				} else if ("anonymousUser".equalsIgnoreCase(myUser.getUsername())) {
					logger.error("anonymousUser user been login, please check it, it is not correct.");
					ret = false;
				} else {
					ret = true;
				}
				
				if (ret) {
					UserPOJO userPOJO = this.getUser4Publisher(request, response);
					WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
					if (userPOJO != null) {
						wxAuthorizerInfoPOJO = this.getWxAuthorizerInfo(userPOJO.getUserId());
					}
					if (wxAuthorizerInfoPOJO != null) {
						String authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
						if (StringUtils.isNotBlank(authorizerAppId) && StringUtils.isNotBlank(wxAuthorizerInfoPOJO.getAuthorizerAppId())) {
							if (!authorizerAppId.equalsIgnoreCase(wxAuthorizerInfoPOJO.getAuthorizerAppId())) {
								logger.info("当前session中的authorizerAppId({})和当前authorizerAppId({})不同, 清除当前session, 重新登录");
								ret = false;
								SecurityContextHolder.clearContext();
								if (session != null) {
									session.invalidate();
								}
							}
						}
					}
				}
				
			} else {
				ret = true;
			}
		} catch (Exception e) {
			logger.error("checkSessionUrl exception: {}", e);
		}
		return ret;
	}
	
	public static void main(String[] argv) {
		/*MyAccessDecisionManager madm = new MyAccessDecisionManager();
		madm.checkSessionUrls("/web/media/wxTemplate?wxTemplate=-2", null);*/
			Pattern p = Pattern.compile("(activityId=)(\\d+)");
			Matcher m = p.matcher("/page/enterprise/activity_detail.jsp?activityId=31&h=1");
			if (m.find()) {
				String s = m.group(2);
//				Long activityId = Long.parseLong(s);
				logger.info(s + "   " + m.groupCount());
			}
			p = Pattern.compile("(authorizerAppId=)(\\w+).*&");
			m = p.matcher("/web/wxpay/fee?authorizerAppId=ab2c&");
			if (m.find()) {
				String s = m.group(2);
//				Long activityId = Long.parseLong(s);
				logger.info(s + "   " + m.groupCount());
			}
		
	}

}

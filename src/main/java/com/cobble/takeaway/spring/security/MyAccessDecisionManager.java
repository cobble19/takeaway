package com.cobble.takeaway.spring.security;

import java.io.IOException;
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
import com.cobble.takeaway.util.UserUtil;

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
			String openId = (String) session.getAttribute("openId");
			String unionId = (String) session.getAttribute("unionId");
			if (StringUtils.isNotBlank(openId)) {
				myUser.setOpenId(openId);
			}
			if (StringUtils.isNotBlank(unionId)) {
				myUser.setUnionId(unionId);
			}

			WxAuthorizerInfoService wxAuthorizerInfoService = (WxAuthorizerInfoService) BeanUtil.get("wxAuthorizerInfoServiceImpl");
			WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
			try {
				wxAuthorizerInfoPOJO = wxAuthorizerInfoService.findWxAuthorizerInfoByUserId(myUser.getUserId());
				if (wxAuthorizerInfoPOJO != null) {
					session.setAttribute(CommonConstant.AUTHORIZER_APP_ID, wxAuthorizerInfoPOJO.getAuthorizerAppId());
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
		
		if (!checkSessionUrls(url, session)) {
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
		
		UserPOJO userPOJO = null;
		// indexCode、活动发布者， 不是普通个人用户
		// 1. 根据indexCode,得到user。 2. 根据activityid得到user
		if (uri.startsWith("/wx/")) {
			int index = uri.indexOf("/wx/");
			String indexCode = uri.substring(index + 4);
			logger.info("Index Code: {}", indexCode);
			if (!indexCode.contains("/")) {
				session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
				userPOJO = userService.findUserByIndexCode(indexCode);
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
				logger.info("activityId: {}", s);
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
		}

		// 获取AuthorizerAppId
		WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
		if (userPOJO == null) {
			logger.info("微官网、活动发布者为空");
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
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
			wxAuthorizerInfoPOJO = wxAuthorizerInfoService.findWxAuthorizerInfoByUserId(userPOJO.getUserId());
		}
		
		String wxWebLoginUrl = "";
		String wxThirdPersonUserLoginUrl = "";
		
		logger.info("wxAuthorizerInfoPOJO: {}", wxAuthorizerInfoPOJO);
		if (wxAuthorizerInfoPOJO != null) {
			if (UserUtil.haveWebAuth(wxAuthorizerInfoPOJO)) {
				wxWebLoginUrl = wxThirdWebAuthorizeUrl
						.replace("COMPONENT_APPID", wxThirdClientId)
						.replace("APPID", wxAuthorizerInfoPOJO.getAuthorizerAppId())
						.replace("REDIRECT_URI", wxThirdWebRedirectUrl)
						.replace("SCOPE", scope)
						.replace("STATE", RandomStringUtils.randomAlphabetic(6))
						;
						
						session.setAttribute(CommonConstant.AUTHORIZER_APP_ID, wxAuthorizerInfoPOJO.getAuthorizerAppId());
						/*wxEncodeUrl = wxWebLoginUrl;
						wxWebLoginUrl = myRedirectStrategy.encodeQueryParam(wxWebLoginUrl);
						wxEncodeUrl = myRedirectStrategy.encodeUrl(response, wxEncodeUrl);*/
						
						wxThirdPersonUserLoginUrl = wxWebLoginUrl;
//						wxThirdPersonUserLoginUrl = myRedirectStrategy.encodeQueryParam(wxThirdPersonUserLoginUrl);
			} else {
				String extraParam = "&loginVice=loginVice"
									+ "&authorizerUserNameVice=" + wxAuthorizerInfoPOJO.getUserName();
				
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
//				wxThirdPersonUserLoginUrl = myRedirectStrategy.encodeQueryParam(wxThirdPersonUserLoginUrl);
				wxThirdPersonUserLoginUrl = wxThirdPersonUserLoginUrl.replace("&openIdVice=", "%26openIdVice%3D")
											.replace("&authorizerUserNameVice=", "%26authorizerUserNameVice%3D");
			}
			
		}
		ret = wxThirdPersonUserLoginUrl;
		return ret;
	}
	
	public boolean checkSessionUrls(String url, HttpSession session) {
		Boolean ret = true;
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
			} else {
				ret = true;
			}
		} else {
			ret = true;
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
		
	}

}

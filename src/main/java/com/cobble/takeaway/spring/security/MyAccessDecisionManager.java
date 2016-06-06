package com.cobble.takeaway.spring.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
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
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import com.cobble.takeaway.util.BeanUtil;

public class MyAccessDecisionManager implements AccessDecisionManager {
	private static final Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		FilterInvocation fi = (FilterInvocation) object;
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
		} 
		
		logger.info("Login success: {}", principal);
		
		String url = ((FilterInvocation) object).getRequestUrl();
		logger.debug("URL = {}", url);
		
		if (!checkSessionUrls(url, session)) {
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
//			return;
			throw new AccessDeniedException("权限需要分配角色," + ", configAttributes is null, user = " + authentication.getName()
					+ ", url = " + url);
		}
		Iterator<ConfigAttribute> it = configAttributes.iterator();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if (CollectionUtils.isEmpty(authorities)) {
			// 当前的用户和企业用户没有角色和权限， 只有后台管理员有权限
			if (MyUser.PERSON.equalsIgnoreCase(myUser.getUserType()) || MyUser.ENTERPRISE.equalsIgnoreCase(myUser.getUserType())
					|| MyUser.MEDIA.equalsIgnoreCase(myUser.getUserType())) {
				logger.info("username= {},  User Type = {}", authentication.getName(), myUser.getUserType());
				return;
			}
			logger.debug("username= {},  用户需要分配角色。", authentication.getName());
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
		
		while (it.hasNext()) {
			ConfigAttribute configAttribute = it.next();
			String needRole = configAttribute.getAttribute();
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (needRole.trim().equalsIgnoreCase(grantedAuthority.getAuthority().trim())) {
					return;
				}
			}
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
		MyAccessDecisionManager madm = new MyAccessDecisionManager();
		madm.checkSessionUrls("/web/media/wxTemplate?wxTemplate=-2", null);
		
	}

}

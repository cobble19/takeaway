package com.cobble.takeaway.spring.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.util.CollectionUtils;

public class MyAccessDecisionManager implements AccessDecisionManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAccessDecisionManager.class);

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
		
		LOGGER.info("Login success: {}", principal);
		
		String url = ((FilterInvocation) object).getRequestUrl();
		LOGGER.debug("URL = {}", url);
		if (url.equalsIgnoreCase("/") || url.equalsIgnoreCase("")
				|| url.startsWith("/index") ) {
			return;
		}
		if (CollectionUtils.isEmpty(configAttributes)) {	// 资源需要的角色
			LOGGER.debug("url= {},  权限需要分配角色。", url);
//			return;
			throw new AccessDeniedException("权限需要分配角色," + ", configAttributes is null, user = " + authentication.getName()
					+ ", url = " + url);
		}
		Iterator<ConfigAttribute> it = configAttributes.iterator();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if (CollectionUtils.isEmpty(authorities)) {
			// 当前的用户和企业用户没有角色和权限， 只有后台管理员有权限
			if (MyUser.PERSON.equalsIgnoreCase(myUser.getUserType())|| MyUser.ENTERPRISE.equalsIgnoreCase(myUser.getUserType())) {
				LOGGER.info("username= {},  User Type = {}", authentication.getName(), myUser.getUserType());
				return;
			}
			LOGGER.debug("username= {},  用户需要分配角色。", authentication.getName());
			throw new AccessDeniedException("用户需要分配角色," + ", authorities is null, user = " + authentication.getName()
					+ ", url = " + url);
		}
		
		String temp = "";
		for (ConfigAttribute configAttribute : configAttributes) {
			temp += configAttribute.getAttribute() + ", ";
		}
		LOGGER.info("需要的角色：{}", temp);
		temp = "";
		for (GrantedAuthority authority : authorities) {
			temp += authority.getAuthority() + ", ";
		}
		LOGGER.info("用户的角色：{}", temp);
		
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

}

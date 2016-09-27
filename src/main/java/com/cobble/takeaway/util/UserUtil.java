package com.cobble.takeaway.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.service.PrivilegeService;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.spring.security.MyUser;

public class UserUtil {
	private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);
	
	public static Boolean havePrivilege(String url) {
		Boolean ret = false;
		try {
			String username = getCurrentUsername();
			UserService userService = (UserService) BeanUtil.get("userServiceImpl");
			PrivilegeService privilegeService = (PrivilegeService) BeanUtil.get("privilegeServiceImpl");
			MyUser myUser = userService.findMyUserByName(username);
			Collection<GrantedAuthority> authorities = myUser.getAuthorities();
			
			List<String> haveRoles = new ArrayList<String>();
			List<String> needRoles = new ArrayList<String>();
			
			if (!CollectionUtils.isEmpty(authorities)) {
				for (GrantedAuthority authority : authorities) {
					haveRoles.add(authority.getAuthority());
				}
			}
			
			needRoles = privilegeService.findRoles(url);
			
			ret = matchRoles(needRoles, haveRoles);
			
			logger.info("判断权限结果: {}, 资源: {}, 需要的角色: {}, 用户的角色: {}", ret, url, needRoles, haveRoles);
		} catch (Exception e) {
			logger.error("check privilege : {}", e);
		}
		
		return ret;
	}
	
	public static Boolean havePrivilege(String username, String url) {
		Boolean ret = false;
		try {
			UserService userService = (UserService) BeanUtil.get("userServiceImpl");
			PrivilegeService privilegeService = (PrivilegeService) BeanUtil.get("privilegeServiceImpl");
			MyUser myUser = userService.findMyUserByName(username);
			Collection<GrantedAuthority> authorities = myUser.getAuthorities();
			
			List<String> haveRoles = new ArrayList<String>();
			List<String> needRoles = new ArrayList<String>();
			
			if (!CollectionUtils.isEmpty(authorities)) {
				for (GrantedAuthority authority : authorities) {
					haveRoles.add(authority.getAuthority());
				}
			}
			
			needRoles = privilegeService.findRoles(url);
			
			ret = matchRoles(needRoles, haveRoles);
		} catch (Exception e) {
			logger.error("check privilege : {}", e);
		}
		
		return ret;
	}
	
	public static Boolean matchRoles(List<String> needRoles, List<String> haveRoles) {
		Boolean ret = false;
		
		if (CollectionUtils.isEmpty(needRoles)) {
			return true;
		}
		
		if (CollectionUtils.isEmpty(haveRoles)) {
			return false;
		}
		
		for (String needRole : needRoles) {
			needRole = StringUtils.trimToEmpty(needRole);
			for (String haveRole : haveRoles) {
				haveRole = StringUtils.trimToEmpty(haveRole);
				if (needRole.equalsIgnoreCase(haveRole)) {
					return true;
				}
			}
		}
		
		return ret;
	}
	
	public static MyUser getCurrentUser() {
		MyUser ret = null;
		try {
			ret = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("获取当前用户失败： {}", e);
		}
		return ret;
	}
	
	public static String getCurrentUsername() {
		String ret = "";
		MyUser myUser = getCurrentUser();
		
		if (myUser == null) {
			return ret;
		}
		ret = myUser.getUsername();
		return ret;
	}
	
	public static Long getCurrentUserId() {
		Long ret = null;
		MyUser myUser = getCurrentUser();
		
		if (myUser == null) {
			return ret;
		}
		ret = myUser.getUserId();
		return ret;
	}
	
	public static List<UserPOJO> removePassword(List<UserPOJO> userPOJOs) {
		List<UserPOJO> ret = userPOJOs;
		
		if (CollectionUtils.isEmpty(ret)) {
			return ret;
		}
		
		for (UserPOJO userPOJO : ret) {
			userPOJO.setPassword("");
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		logger.info("Test....");
		
		String str = "C:/abc/def/gh";
		logger.info(str);
		str = str.replaceAll("/", "\\\\");
		logger.info(str);
		str = "C:\\abc\\def\\gh";
		logger.info(str);
		str = str.replaceAll("\\\\", "/");
		logger.info(str);
		str = "<tr>center> ag<font color=\"red\">abcd</tr>";
		str = str.replaceAll("center>.*<font color=.*>", "<font color='green'>");
		logger.info(str);
		str = str.replace("bb", "GG");
		logger.info(str);
		
	}
}

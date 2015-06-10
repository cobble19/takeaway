package com.cobble.takeaway.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cobble.takeaway.spring.security.MyUser;

public class UserUtil {
	private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);
	
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
}

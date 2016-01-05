package com.cobble.takeaway.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.pojo.UserPOJO;
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
	}
}

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
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.service.PrivilegeService;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.spring.security.MyUser;

public class UserUtil {
	private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);
	

	public static boolean isProxyWeiXinAuthorizer(Long userId) {
		Boolean ret = false;
		if (CommonConstant.PROXY_USER_ID_VALUE.longValue() == userId) {
			ret = true;
		}
		return ret;
	}
	
	/**
	 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1445241432&token=&lang=zh_CN
	 * 微信网页授权
	 * service_type_info: 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
	 * verify_type_info: 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，
	 * 4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
	 * @param serviceTypeInfo
	 * @return
	 */
	public static boolean haveWebAuth(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) {
		Boolean ret = false;
		if (wxAuthorizerInfoPOJO == null) {
			return false;
		}
		if (CommonConstant.WX_ACCOUNT_AUTH_WEIXIN == wxAuthorizerInfoPOJO.getVerifyTypeInfo()
				&& (CommonConstant.WX_ACCOUNT_SUBSCRIBE == wxAuthorizerInfoPOJO.getServiceTypeInfo() 
						|| CommonConstant.WX_ACCOUNT_SERVICE == wxAuthorizerInfoPOJO.getServiceTypeInfo())
						) {
			ret = true;
		}
		return ret;
	}
	
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

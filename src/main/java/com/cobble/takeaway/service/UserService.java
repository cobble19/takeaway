package com.cobble.takeaway.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;
import com.cobble.takeaway.spring.security.MyUser;

public interface UserService {
	int insert(UserPOJO userPOJO) throws Exception;
	int update(UserPOJO userPOJO) throws Exception;
	int updateInfo(UserPOJO userPOJO) throws Exception;
	int updatePassword(UserPOJO userPOJO) throws Exception;
	List<UserPOJO> finds(UserSearchPOJO userSearchPOJO) throws Exception;
	int getCount(UserSearchPOJO userSearchPOJO) throws Exception;
	UserPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
	UserPOJO findUserByName(String username) throws Exception;
	UserPOJO findUserByNickname(String nickname) throws Exception;

	UserPOJO findUserByIndexCode(String wxIndexCode) throws Exception;
	UserPOJO findUserByActivityId(Long activityId) throws Exception;
	UserPOJO findUser4IndexCodeByUserId(Long userId) throws Exception;
	
	MyUser findMyUserByName(String username) throws Exception;
	MyUser createPrincipalByName(String username, HttpSession session) throws Exception;
}

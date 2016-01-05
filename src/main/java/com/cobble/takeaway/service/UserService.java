package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;

public interface UserService {
	int insert(UserPOJO userPOJO) throws Exception;
	int update(UserPOJO userPOJO) throws Exception;
	int updateInfo(UserPOJO userPOJO) throws Exception;
	int updatePassword(UserPOJO userPOJO) throws Exception;
	List<UserPOJO> finds(UserSearchPOJO userSearchPOJO) throws Exception;
	int getCount(UserSearchPOJO userSearchPOJO) throws Exception;
	UserPOJO findById(Integer id) throws Exception;
	int delete(Integer id) throws Exception;
	int delete(Integer[] ids) throws Exception;
	
	UserPOJO findUserByName(String username) throws Exception;
	UserPOJO findUserByNickname(String nickname) throws Exception;

	UserPOJO findUserByIndexCode(String wxIndexCode) throws Exception;
}

package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;


public interface UserMapper {
	int insert(UserPOJO userPOJO) throws Exception;
	int update(UserPOJO userPOJO) throws Exception;
	List<UserPOJO> finds(UserSearchPOJO userSearchPOJO) throws Exception;
	int getCount(UserSearchPOJO userSearchPOJO) throws Exception;
	UserPOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
	
	UserPOJO findUserByName(String username) throws Exception;
}
package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserSearchPOJO;


public interface WxPersonUserMapper {
	int insert(WxPersonUserPOJO wxPersonUserPOJO) throws Exception;
	int update(WxPersonUserPOJO wxPersonUserPOJO) throws Exception;
	List<WxPersonUserPOJO> finds(WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception;
	
	int getCount(WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception;
	WxPersonUserPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
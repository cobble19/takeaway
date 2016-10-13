package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserSearchPOJO;

public interface WxPersonUserService {

	int insert(WxPersonUserPOJO wxPersonUserPOJO) throws Exception;
	int update(WxPersonUserPOJO wxPersonUserPOJO) throws Exception;
	List<WxPersonUserPOJO> finds(WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception;
	
	int getCount(WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception;
	WxPersonUserPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

	List<WxPersonUserPOJO> findFulls(WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception;
	int getFullCount(WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception;
	
}

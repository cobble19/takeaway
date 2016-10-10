package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxPersonUserVicePOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserViceSearchPOJO;

public interface WxPersonUserViceService {

	int insert(WxPersonUserVicePOJO wxPersonUserVicePOJO) throws Exception;
	int update(WxPersonUserVicePOJO wxPersonUserVicePOJO) throws Exception;
	List<WxPersonUserVicePOJO> finds(WxPersonUserViceSearchPOJO wxPersonUserViceSearchPOJO) throws Exception;
	
	int getCount(WxPersonUserViceSearchPOJO wxPersonUserViceSearchPOJO) throws Exception;
	WxPersonUserVicePOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
	List<WxPersonUserVicePOJO> findFulls(WxPersonUserViceSearchPOJO wxPersonUserViceSearchPOJO) throws Exception;
	int getFullCount(WxPersonUserViceSearchPOJO wxPersonUserViceSearchPOJO) throws Exception;
	
}

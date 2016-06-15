package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;

public interface WxAuthorizerInfoService {

	int insert(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) throws Exception;
	int update(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) throws Exception;
	List<WxAuthorizerInfoPOJO> finds(WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception;
	
	int getCount(WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception;
	WxAuthorizerInfoPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
	WxAuthorizerInfoPOJO findWxAuthorizerInfoByIndexCode(String indexCode) throws Exception;
	WxAuthorizerInfoPOJO findWxAuthorizerInfoByUserId(Long userId) throws Exception;
	
}

package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;


public interface WxAuthorizerInfoMapper {
	int insert(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) throws Exception;
	int update(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) throws Exception;
	List<WxAuthorizerInfoPOJO> finds(WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception;
	
	int getCount(WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception;
	WxAuthorizerInfoPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
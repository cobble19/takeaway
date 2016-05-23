package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxAuthorizerAccessTokenPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerAccessTokenSearchPOJO;


public interface WxAuthorizerAccessTokenMapper {
	int insert(WxAuthorizerAccessTokenPOJO wxAuthorizerAccessTokenPOJO) throws Exception;
	int update(WxAuthorizerAccessTokenPOJO wxAuthorizerAccessTokenPOJO) throws Exception;
	List<WxAuthorizerAccessTokenPOJO> finds(WxAuthorizerAccessTokenSearchPOJO wxAuthorizerAccessTokenSearchPOJO) throws Exception;
	
	int getCount(WxAuthorizerAccessTokenSearchPOJO wxAuthorizerAccessTokenSearchPOJO) throws Exception;
	WxAuthorizerAccessTokenPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
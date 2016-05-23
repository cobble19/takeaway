package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxAuthorizerAccessTokenPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerAccessTokenSearchPOJO;

public interface WxAuthorizerAccessTokenService {

	int insert(WxAuthorizerAccessTokenPOJO wxAuthorizerAccessTokenPOJO) throws Exception;
	int update(WxAuthorizerAccessTokenPOJO wxAuthorizerAccessTokenPOJO) throws Exception;
	List<WxAuthorizerAccessTokenPOJO> finds(WxAuthorizerAccessTokenSearchPOJO wxAuthorizerAccessTokenSearchPOJO) throws Exception;
	
	int getCount(WxAuthorizerAccessTokenSearchPOJO wxAuthorizerAccessTokenSearchPOJO) throws Exception;
	WxAuthorizerAccessTokenPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxAuthorizerRefreshTokenPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerRefreshTokenSearchPOJO;


public interface WxAuthorizerRefreshTokenMapper {
	int insert(WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO) throws Exception;
	int update(WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO) throws Exception;
	List<WxAuthorizerRefreshTokenPOJO> finds(WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO) throws Exception;
	List<WxAuthorizerRefreshTokenPOJO> findAuthorizerAppIds(WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO) throws Exception;
	
	int getCount(WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO) throws Exception;
	WxAuthorizerRefreshTokenPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
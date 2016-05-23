package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenSearchPOJO;


public interface WxComAccessTokenMapper {
	int insert(WxComAccessTokenPOJO wxComAccessTokenPOJO) throws Exception;
	int update(WxComAccessTokenPOJO wxComAccessTokenPOJO) throws Exception;
	List<WxComAccessTokenPOJO> finds(WxComAccessTokenSearchPOJO wxComAccessTokenSearchPOJO) throws Exception;
	
	List<WxComAccessTokenPOJO> findsByIds(WxComAccessTokenSearchPOJO wxComAccessTokenSearchPOJO) throws Exception;
	
	int getCount(WxComAccessTokenSearchPOJO wxComAccessTokenSearchPOJO) throws Exception;
	WxComAccessTokenPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
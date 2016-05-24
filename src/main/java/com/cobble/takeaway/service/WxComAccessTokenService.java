package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenSearchApiPOJO;

public interface WxComAccessTokenService {

	int insert(WxComAccessTokenApiPOJO wxComAccessTokenPOJO) throws Exception;
	int update(WxComAccessTokenApiPOJO wxComAccessTokenPOJO) throws Exception;
	List<WxComAccessTokenApiPOJO> finds(WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchPOJO) throws Exception;
	
	int getCount(WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchPOJO) throws Exception;
	WxComAccessTokenApiPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

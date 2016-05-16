package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.WxComAccessTokenPOJO;
import com.cobble.takeaway.pojo.WxComAccessTokenSearchPOJO;

public interface WxComAccessTokenService {

	int insert(WxComAccessTokenPOJO wxComAccessTokenPOJO) throws Exception;
	int update(WxComAccessTokenPOJO wxComAccessTokenPOJO) throws Exception;
	List<WxComAccessTokenPOJO> finds(WxComAccessTokenSearchPOJO wxComAccessTokenSearchPOJO) throws Exception;
	
	int getCount(WxComAccessTokenSearchPOJO wxComAccessTokenSearchPOJO) throws Exception;
	WxComAccessTokenPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

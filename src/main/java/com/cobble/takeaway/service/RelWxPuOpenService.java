package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.RelWxPuOpenPOJO;
import com.cobble.takeaway.pojo.weixin.RelWxPuOpenSearchPOJO;

public interface RelWxPuOpenService {

	int insert(RelWxPuOpenPOJO relWxPuOpenPOJO) throws Exception;
	int update(RelWxPuOpenPOJO relWxPuOpenPOJO) throws Exception;
	List<RelWxPuOpenPOJO> finds(RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO) throws Exception;
	
	int getCount(RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO) throws Exception;
	RelWxPuOpenPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.RelWxPuOpenPOJO;
import com.cobble.takeaway.pojo.weixin.RelWxPuOpenSearchPOJO;


public interface RelWxPuOpenMapper {
	int insert(RelWxPuOpenPOJO relWxPuOpenPOJO) throws Exception;
	int update(RelWxPuOpenPOJO relWxPuOpenPOJO) throws Exception;
	List<RelWxPuOpenPOJO> finds(RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO) throws Exception;
	List<RelWxPuOpenPOJO> findWithPu(RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO) throws Exception;
	
	int getCount(RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO) throws Exception;
	RelWxPuOpenPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
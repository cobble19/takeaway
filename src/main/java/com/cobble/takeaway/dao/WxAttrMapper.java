package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.RelWxAttrPOJO;
import com.cobble.takeaway.pojo.WxAttrPOJO;
import com.cobble.takeaway.pojo.WxAttrSearchPOJO;


public interface WxAttrMapper {
	int insert(WxAttrPOJO wxAttrPOJO) throws Exception;
	int update(WxAttrPOJO wxAttrPOJO) throws Exception;
	List<WxAttrPOJO> finds(WxAttrSearchPOJO wxAttrSearchPOJO) throws Exception;
	
	List<WxAttrPOJO> findsByIds(WxAttrSearchPOJO wxAttrSearchPOJO) throws Exception;
	
	int getCount(WxAttrSearchPOJO wxAttrSearchPOJO) throws Exception;
	WxAttrPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
	int insertRelWxAttr(RelWxAttrPOJO relWxAttrPOJO) throws Exception;
}
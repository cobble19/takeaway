package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.RelWxAttrPOJO;
import com.cobble.takeaway.pojo.WxAttrPOJO;
import com.cobble.takeaway.pojo.WxAttrSearchPOJO;
import com.cobble.takeaway.pojo.WxSecPOJO;

public interface WxAttrService {

	int insert(WxAttrPOJO wxAttrPOJO) throws Exception;
	int update(WxAttrPOJO wxAttrPOJO) throws Exception;
	List<WxAttrPOJO> finds(WxAttrSearchPOJO wxAttrSearchPOJO) throws Exception;
	
	List<WxAttrPOJO> findsByIds(Long userId, Long wxTemplateId) throws Exception;
	
	List<WxSecPOJO> findWxSecsByIds(Long userId, Long wxTemplateId) throws Exception;
	
	int getCount(WxAttrSearchPOJO wxAttrSearchPOJO) throws Exception;
	WxAttrPOJO findById(Long id) throws Exception;
	
	int insertRelWxAttr(RelWxAttrPOJO relWxAttrPOJO) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	List<WxAttrPOJO> findsByIds(Long userId, Long wxTemplateId, Integer orderNo, Integer wxSecOrderNo) throws Exception;
	
}

package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategorySearchPOJO;


public interface WxMenuMgrCategoryMapper {
	int insert(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) throws Exception;
	int update(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) throws Exception;
	List<WxMenuMgrCategoryPOJO> finds(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception;
	
	int getCount(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception;
	WxMenuMgrCategoryPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
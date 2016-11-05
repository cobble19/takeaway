package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategorySearchPOJO;

public interface WxMenuMgrCategoryService {
	int insert(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) throws Exception;
	int update(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) throws Exception;
	List<WxMenuMgrCategoryPOJO> finds(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception;
	
	List<WxMenuMgrCategoryPOJO> findFull(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception;
	
	int getCount(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception;
	WxMenuMgrCategoryPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

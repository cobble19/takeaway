package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonSearchPOJO;


public interface WxMenuMgrButtonMapper {
	int insert(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO) throws Exception;
	int update(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO) throws Exception;
	List<WxMenuMgrButtonPOJO> finds(WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO) throws Exception;
	
	int getCount(WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO) throws Exception;
	WxMenuMgrButtonPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
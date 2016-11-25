package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRulePOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRuleSearchPOJO;


public interface WxMenuMgrMatchRuleMapper {
	int insert(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO) throws Exception;
	int update(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO) throws Exception;
	List<WxMenuMgrMatchRulePOJO> finds(WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO) throws Exception;
	
	int getCount(WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO) throws Exception;
	WxMenuMgrMatchRulePOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
}
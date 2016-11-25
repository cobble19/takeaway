package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRulePOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRuleSearchPOJO;

public interface WxMenuMgrMatchRuleService {
	int insert(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO) throws Exception;
	int update(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO) throws Exception;
	List<WxMenuMgrMatchRulePOJO> finds(WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO) throws Exception;
	
	int getCount(WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO) throws Exception;
	WxMenuMgrMatchRulePOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

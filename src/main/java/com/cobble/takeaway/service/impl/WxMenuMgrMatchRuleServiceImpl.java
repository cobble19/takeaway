package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxMenuMgrButtonMapper;
import com.cobble.takeaway.dao.WxMenuMgrMatchRuleMapper;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRulePOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRuleSearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrMatchRuleService;

@Service
public class WxMenuMgrMatchRuleServiceImpl implements WxMenuMgrMatchRuleService {
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WxMenuMgrMatchRuleMapper wxMenuMgrMatchRuleMapper;
	@Autowired
	private WxMenuMgrButtonMapper wxMenuMgrButtonMapper;

	@Override
	public int insert(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrMatchRuleMapper.insert(wxMenuMgrMatchRulePOJO);
		return ret;
	}

	@Override
	public int update(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrMatchRuleMapper.update(wxMenuMgrMatchRulePOJO);
		return ret;
	}

	@Override
	public List<WxMenuMgrMatchRulePOJO> finds(
			WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO) throws Exception {
		List<WxMenuMgrMatchRulePOJO> ret = null;
		ret = wxMenuMgrMatchRuleMapper.finds(wxMenuMgrMatchRuleSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxMenuMgrMatchRuleMapper.getCount(wxMenuMgrMatchRuleSearchPOJO);
		return ret;
	}

	@Override
	public WxMenuMgrMatchRulePOJO findById(Long id) throws Exception {
		WxMenuMgrMatchRulePOJO ret = null;
		ret = wxMenuMgrMatchRuleMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Long id) throws Exception {
		int ret = 0;
		ret = wxMenuMgrMatchRuleMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxMenuMgrMatchRuleMapper.deleteById(id);
			}
		}
		return ret;
	}


}

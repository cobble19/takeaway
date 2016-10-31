package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxMenuMgrButtonMapper;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonSearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrButtonService;

@Service
public class WxMenuMgrButtonServiceImpl implements WxMenuMgrButtonService {
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WxMenuMgrButtonMapper wxMenuMgrButtonMapper;

	@Override
	public int insert(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrButtonMapper.insert(wxMenuMgrButtonPOJO);
		return ret;
	}

	@Override
	public int update(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrButtonMapper.update(wxMenuMgrButtonPOJO);
		return ret;
	}

	@Override
	public List<WxMenuMgrButtonPOJO> finds(
			WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO) throws Exception {
		List<WxMenuMgrButtonPOJO> ret = null;
		ret = wxMenuMgrButtonMapper.finds(wxMenuMgrButtonSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxMenuMgrButtonMapper.getCount(wxMenuMgrButtonSearchPOJO);
		return ret;
	}

	@Override
	public WxMenuMgrButtonPOJO findById(Long id) throws Exception {
		WxMenuMgrButtonPOJO ret = null;
		ret = wxMenuMgrButtonMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Long id) throws Exception {
		int ret = 0;
		ret = wxMenuMgrButtonMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxMenuMgrButtonMapper.deleteById(id);
			}
		}
		return ret;
	}


}

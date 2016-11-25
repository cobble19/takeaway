package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxMenuMgrFullMapper;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrFullPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrFullSearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrFullService;

@Service
public class WxMenuMgrFullServiceImpl implements WxMenuMgrFullService {
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WxMenuMgrFullMapper wxMenuMgrFullMapper;

	@Override
	public int insert(WxMenuMgrFullPOJO wxMenuMgrFullPOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrFullMapper.insert(wxMenuMgrFullPOJO);
		return ret;
	}

	@Override
	public int update(WxMenuMgrFullPOJO wxMenuMgrFullPOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrFullMapper.update(wxMenuMgrFullPOJO);
		return ret;
	}

	@Override
	public List<WxMenuMgrFullPOJO> finds(
			WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO) throws Exception {
		List<WxMenuMgrFullPOJO> ret = null;
		ret = wxMenuMgrFullMapper.finds(wxMenuMgrFullSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxMenuMgrFullMapper.getCount(wxMenuMgrFullSearchPOJO);
		return ret;
	}

	@Override
	public WxMenuMgrFullPOJO findById(Long id) throws Exception {
		WxMenuMgrFullPOJO ret = null;
		ret = wxMenuMgrFullMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Long id) throws Exception {
		int ret = 0;
		ret = wxMenuMgrFullMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxMenuMgrFullMapper.deleteById(id);
			}
		}
		return ret;
	}


}

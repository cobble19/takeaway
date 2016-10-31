package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxMenuMgrCategoryMapper;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategorySearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrCategoryService;

@Service
public class WxMenuMgrCategoryServiceImpl implements WxMenuMgrCategoryService {
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WxMenuMgrCategoryMapper wxMenuMgrCategoryMapper;

	@Override
	public int insert(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrCategoryMapper.insert(wxMenuMgrCategoryPOJO);
		return ret;
	}

	@Override
	public int update(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) throws Exception {
		int ret = 0;
		ret = wxMenuMgrCategoryMapper.update(wxMenuMgrCategoryPOJO);
		return ret;
	}

	@Override
	public List<WxMenuMgrCategoryPOJO> finds(
			WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception {
		List<WxMenuMgrCategoryPOJO> ret = null;
		ret = wxMenuMgrCategoryMapper.finds(wxMenuMgrCategorySearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxMenuMgrCategoryMapper.getCount(wxMenuMgrCategorySearchPOJO);
		return ret;
	}

	@Override
	public WxMenuMgrCategoryPOJO findById(Long id) throws Exception {
		WxMenuMgrCategoryPOJO ret = null;
		ret = wxMenuMgrCategoryMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Long id) throws Exception {
		int ret = 0;
		ret = wxMenuMgrCategoryMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxMenuMgrCategoryMapper.deleteById(id);
			}
		}
		return ret;
	}


}

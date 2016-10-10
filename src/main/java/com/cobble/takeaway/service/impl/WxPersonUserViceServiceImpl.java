package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxPersonUserViceMapper;
import com.cobble.takeaway.pojo.weixin.WxPersonUserVicePOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserViceSearchPOJO;
import com.cobble.takeaway.service.WxPersonUserViceService;

@Service
public class WxPersonUserViceServiceImpl implements WxPersonUserViceService {
	
	@Autowired
	private WxPersonUserViceMapper wxPersonUserViceMapper;

	@Override
	public int insert(WxPersonUserVicePOJO wxPersonUserVicePOJO) throws Exception {
		int ret = 0;
		ret = wxPersonUserViceMapper.insert(wxPersonUserVicePOJO);
		return ret;
	}

	@Override
	public int update(WxPersonUserVicePOJO wxPersonUserVicePOJO) throws Exception {
		int ret = 0;
		ret = wxPersonUserViceMapper.update(wxPersonUserVicePOJO);
		return ret;
	}

	@Override
	public List<WxPersonUserVicePOJO> finds(
			WxPersonUserViceSearchPOJO wxPersonUserViceSearchPOJO) throws Exception {
		List<WxPersonUserVicePOJO> ret = null;
		ret = wxPersonUserViceMapper.finds(wxPersonUserViceSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxPersonUserViceSearchPOJO wxPersonUserViceSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxPersonUserViceMapper.getCount(wxPersonUserViceSearchPOJO);
		return ret;
	}

	@Override
	public WxPersonUserVicePOJO findById(Long id) throws Exception {
		WxPersonUserVicePOJO ret = null;
		ret = wxPersonUserViceMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxPersonUserViceMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxPersonUserViceMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<WxPersonUserVicePOJO> findFulls(
			WxPersonUserViceSearchPOJO wxPersonUserViceSearchPOJO)
			throws Exception {
		List<WxPersonUserVicePOJO> ret = null;
		ret = wxPersonUserViceMapper.findFulls(wxPersonUserViceSearchPOJO);
		return ret;
	}

	@Override
	public int getFullCount(
			WxPersonUserViceSearchPOJO wxPersonUserViceSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxPersonUserViceMapper.getFullCount(wxPersonUserViceSearchPOJO);
		return ret;
	}

}

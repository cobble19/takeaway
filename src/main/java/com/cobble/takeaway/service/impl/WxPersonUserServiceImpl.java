package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxPersonUserMapper;
import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserSearchPOJO;
import com.cobble.takeaway.service.WxPersonUserService;

@Service
public class WxPersonUserServiceImpl implements WxPersonUserService {
	
	@Autowired
	private WxPersonUserMapper wxPersonUserMapper;

	@Override
	public int insert(WxPersonUserPOJO wxPersonUserPOJO) throws Exception {
		int ret = 0;
		ret = wxPersonUserMapper.insert(wxPersonUserPOJO);
		return ret;
	}

	@Override
	public int update(WxPersonUserPOJO wxPersonUserPOJO) throws Exception {
		int ret = 0;
		ret = wxPersonUserMapper.update(wxPersonUserPOJO);
		return ret;
	}

	@Override
	public List<WxPersonUserPOJO> finds(
			WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception {
		List<WxPersonUserPOJO> ret = null;
		ret = wxPersonUserMapper.finds(wxPersonUserSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxPersonUserSearchPOJO wxPersonUserSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxPersonUserMapper.getCount(wxPersonUserSearchPOJO);
		return ret;
	}

	@Override
	public WxPersonUserPOJO findById(Long id) throws Exception {
		WxPersonUserPOJO ret = null;
		ret = wxPersonUserMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxPersonUserMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxPersonUserMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<WxPersonUserPOJO> findFulls(
			WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception {
		List<WxPersonUserPOJO> ret = null;
		ret = wxPersonUserMapper.findFulls(wxPersonUserSearchPOJO);
		return ret;
	}

	@Override
	public int getFullCount(WxPersonUserSearchPOJO wxPersonUserSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxPersonUserMapper.getFullCount(wxPersonUserSearchPOJO);
		return ret;
	}

}

package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxComAccessTokenMapper;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenSearchApiPOJO;
import com.cobble.takeaway.service.WxComAccessTokenService;

@Service
public class WxComAccessTokenServiceImpl implements WxComAccessTokenService {
	
	@Autowired
	private WxComAccessTokenMapper wxComAccessTokenMapper;

	@Override
	public int insert(WxComAccessTokenApiPOJO wxComAccessTokenPOJO) throws Exception {
		int ret = 0;
		ret = wxComAccessTokenMapper.insert(wxComAccessTokenPOJO);
		return ret;
	}

	@Override
	public int update(WxComAccessTokenApiPOJO wxComAccessTokenPOJO) throws Exception {
		int ret = 0;
		ret = wxComAccessTokenMapper.update(wxComAccessTokenPOJO);
		return ret;
	}

	@Override
	public List<WxComAccessTokenApiPOJO> finds(
			WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchPOJO) throws Exception {
		List<WxComAccessTokenApiPOJO> ret = null;
		ret = wxComAccessTokenMapper.finds(wxComAccessTokenSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxComAccessTokenMapper.getCount(wxComAccessTokenSearchPOJO);
		return ret;
	}

	@Override
	public WxComAccessTokenApiPOJO findById(Long id) throws Exception {
		WxComAccessTokenApiPOJO ret = null;
		ret = wxComAccessTokenMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxComAccessTokenMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxComAccessTokenMapper.deleteById(id);
			}
		}
		return ret;
	}

}

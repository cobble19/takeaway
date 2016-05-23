package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxAuthorizerAccessTokenMapper;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerAccessTokenPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerAccessTokenSearchPOJO;
import com.cobble.takeaway.service.WxAuthorizerAccessTokenService;

@Service
public class WxAuthorizerAccessTokenServiceImpl implements WxAuthorizerAccessTokenService {
	
	@Autowired
	private WxAuthorizerAccessTokenMapper wxAuthorizerAccessTokenMapper;

	@Override
	public int insert(WxAuthorizerAccessTokenPOJO wxAuthorizerAccessTokenPOJO) throws Exception {
		int ret = 0;
		ret = wxAuthorizerAccessTokenMapper.insert(wxAuthorizerAccessTokenPOJO);
		return ret;
	}

	@Override
	public int update(WxAuthorizerAccessTokenPOJO wxAuthorizerAccessTokenPOJO) throws Exception {
		int ret = 0;
		ret = wxAuthorizerAccessTokenMapper.update(wxAuthorizerAccessTokenPOJO);
		return ret;
	}

	@Override
	public List<WxAuthorizerAccessTokenPOJO> finds(
			WxAuthorizerAccessTokenSearchPOJO wxAuthorizerAccessTokenSearchPOJO) throws Exception {
		List<WxAuthorizerAccessTokenPOJO> ret = null;
		ret = wxAuthorizerAccessTokenMapper.finds(wxAuthorizerAccessTokenSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxAuthorizerAccessTokenSearchPOJO wxAuthorizerAccessTokenSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxAuthorizerAccessTokenMapper.getCount(wxAuthorizerAccessTokenSearchPOJO);
		return ret;
	}

	@Override
	public WxAuthorizerAccessTokenPOJO findById(Long id) throws Exception {
		WxAuthorizerAccessTokenPOJO ret = null;
		ret = wxAuthorizerAccessTokenMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxAuthorizerAccessTokenMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxAuthorizerAccessTokenMapper.deleteById(id);
			}
		}
		return ret;
	}

}

package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.WxAuthorizerRefreshTokenMapper;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerRefreshTokenPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerRefreshTokenSearchPOJO;
import com.cobble.takeaway.service.WxAuthorizerRefreshTokenService;

@Service
public class WxAuthorizerRefreshTokenServiceImpl implements WxAuthorizerRefreshTokenService {
	
	@Autowired
	private WxAuthorizerRefreshTokenMapper wxAuthorizerRefreshTokenMapper;

	@Override
	public int insert(WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO) throws Exception {
		int ret = 0;
		ret = wxAuthorizerRefreshTokenMapper.insert(wxAuthorizerRefreshTokenPOJO);
		return ret;
	}

	@Override
	public int update(WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO) throws Exception {
		int ret = 0;
		ret = wxAuthorizerRefreshTokenMapper.update(wxAuthorizerRefreshTokenPOJO);
		return ret;
	}

	@Override
	public List<WxAuthorizerRefreshTokenPOJO> finds(
			WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO) throws Exception {
		List<WxAuthorizerRefreshTokenPOJO> ret = null;
		ret = wxAuthorizerRefreshTokenMapper.finds(wxAuthorizerRefreshTokenSearchPOJO);
		return ret;
	}

	@Override
	public List<WxAuthorizerRefreshTokenPOJO> findAuthorizerAppIds(
			WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO)
			throws Exception {
		List<WxAuthorizerRefreshTokenPOJO> ret = null;
		ret = wxAuthorizerRefreshTokenMapper.findAuthorizerAppIds(wxAuthorizerRefreshTokenSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxAuthorizerRefreshTokenMapper.getCount(wxAuthorizerRefreshTokenSearchPOJO);
		return ret;
	}

	@Override
	public WxAuthorizerRefreshTokenPOJO findById(Long id) throws Exception {
		WxAuthorizerRefreshTokenPOJO ret = null;
		ret = wxAuthorizerRefreshTokenMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxAuthorizerRefreshTokenMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxAuthorizerRefreshTokenMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public WxAuthorizerRefreshTokenPOJO findByAuthorizerAppId(String authorizerAppId)
			throws Exception {
		WxAuthorizerRefreshTokenPOJO ret = null;
		List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = null;
		WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
		wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(authorizerAppId);
		wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenMapper.finds(wxAuthorizerRefreshTokenSearchPOJO);
		if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
			ret = wxAuthorizerRefreshTokenPOJOs.get(0);
		}
		return ret;
	}

	@Override
	public String findTokenByAuthorizerAppId(String authorizerAppId) throws Exception {
		String ret = null;
		List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = null;
		WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
		wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(authorizerAppId);
		wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenMapper.finds(wxAuthorizerRefreshTokenSearchPOJO);
		if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
			ret = wxAuthorizerRefreshTokenPOJOs.get(0).getAuthorizerAccessToken();
		}
		return ret;
	}

}

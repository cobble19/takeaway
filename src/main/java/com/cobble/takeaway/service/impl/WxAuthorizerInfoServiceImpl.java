package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.WxAuthorizerInfoMapper;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;
import com.cobble.takeaway.service.WxAuthorizerInfoService;

@Service
public class WxAuthorizerInfoServiceImpl implements WxAuthorizerInfoService {
	
	@Autowired
	private WxAuthorizerInfoMapper wxAuthorizerInfoMapper;

	@Override
	public int insert(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) throws Exception {
		int ret = 0;
		ret = wxAuthorizerInfoMapper.insert(wxAuthorizerInfoPOJO);
		return ret;
	}

	@Override
	public int update(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) throws Exception {
		int ret = 0;
		ret = wxAuthorizerInfoMapper.update(wxAuthorizerInfoPOJO);
		return ret;
	}

	@Override
	public List<WxAuthorizerInfoPOJO> finds(
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception {
		List<WxAuthorizerInfoPOJO> ret = null;
		ret = wxAuthorizerInfoMapper.finds(wxAuthorizerInfoSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxAuthorizerInfoMapper.getCount(wxAuthorizerInfoSearchPOJO);
		return ret;
	}

	@Override
	public WxAuthorizerInfoPOJO findById(Long id) throws Exception {
		WxAuthorizerInfoPOJO ret = null;
		ret = wxAuthorizerInfoMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxAuthorizerInfoMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxAuthorizerInfoMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public WxAuthorizerInfoPOJO findWxAuthorizerInfoByIndexCode(String indexCode)
			throws Exception {
		WxAuthorizerInfoPOJO ret = null;
		ret = wxAuthorizerInfoMapper.findWxAuthorizerInfoByIndexCode(indexCode);
		return ret;
	}

	@Override
	public WxAuthorizerInfoPOJO findWxAuthorizerInfoByUserId(Long userId)
			throws Exception {
		WxAuthorizerInfoPOJO ret = null;
		List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = null;
		WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
		wxAuthorizerInfoSearchPOJO.setUserId(userId);
		wxAuthorizerInfoPOJOs = wxAuthorizerInfoMapper.finds(wxAuthorizerInfoSearchPOJO);
		if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
			ret = wxAuthorizerInfoPOJOs.get(0);
		}
		return ret;
	}

}

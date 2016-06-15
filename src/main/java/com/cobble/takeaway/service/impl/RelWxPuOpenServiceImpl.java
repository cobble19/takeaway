package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.RelWxPuOpenMapper;
import com.cobble.takeaway.pojo.weixin.RelWxPuOpenPOJO;
import com.cobble.takeaway.pojo.weixin.RelWxPuOpenSearchPOJO;
import com.cobble.takeaway.service.RelWxPuOpenService;

@Service
public class RelWxPuOpenServiceImpl implements RelWxPuOpenService {
	
	@Autowired
	private RelWxPuOpenMapper relWxPuOpenMapper;

	@Override
	public int insert(RelWxPuOpenPOJO relWxPuOpenPOJO) throws Exception {
		int ret = 0;
		ret = relWxPuOpenMapper.insert(relWxPuOpenPOJO);
		return ret;
	}

	@Override
	public int update(RelWxPuOpenPOJO relWxPuOpenPOJO) throws Exception {
		int ret = 0;
		ret = relWxPuOpenMapper.update(relWxPuOpenPOJO);
		return ret;
	}

	@Override
	public List<RelWxPuOpenPOJO> finds(
			RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO) throws Exception {
		List<RelWxPuOpenPOJO> ret = null;
		ret = relWxPuOpenMapper.finds(relWxPuOpenSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = relWxPuOpenMapper.getCount(relWxPuOpenSearchPOJO);
		return ret;
	}

	@Override
	public RelWxPuOpenPOJO findById(Long id) throws Exception {
		RelWxPuOpenPOJO ret = null;
		ret = relWxPuOpenMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = relWxPuOpenMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += relWxPuOpenMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<RelWxPuOpenPOJO> findWithPu(
			RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO) throws Exception {
		List<RelWxPuOpenPOJO> ret = null;
		ret = relWxPuOpenMapper.findWithPu(relWxPuOpenSearchPOJO);
		return ret;
	}

	@Override
	public RelWxPuOpenPOJO findWithPu(String unionId, String authorizerAppId)
			throws Exception {
		RelWxPuOpenPOJO ret = null;
		RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO = new RelWxPuOpenSearchPOJO();
		relWxPuOpenSearchPOJO.setUnionId(unionId);
		relWxPuOpenSearchPOJO.setAuthorizerAppId(authorizerAppId);
		List<RelWxPuOpenPOJO> relWxPuOpenPOJOs = null;
		relWxPuOpenPOJOs = relWxPuOpenMapper.findWithPu(relWxPuOpenSearchPOJO);
		if (!CollectionUtils.isEmpty(relWxPuOpenPOJOs)) {
			ret = relWxPuOpenPOJOs.get(0);
		}
		return ret;
	}

}

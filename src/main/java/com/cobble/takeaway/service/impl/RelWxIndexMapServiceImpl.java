package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.RelWxIndexMapMapper;
import com.cobble.takeaway.pojo.RelWxIndexMapPOJO;
import com.cobble.takeaway.pojo.RelWxIndexMapSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;
import com.cobble.takeaway.service.RelWxIndexMapService;
import com.cobble.takeaway.service.WxAuthorizerInfoService;

@Service
public class RelWxIndexMapServiceImpl implements RelWxIndexMapService {
	
	@Autowired
	private RelWxIndexMapMapper relWxIndexMapMapper;
	@Autowired
	private WxAuthorizerInfoService wxAuthorizerInfoService;

	@Override
	public int insert(RelWxIndexMapPOJO relWxIndexMapPOJO) throws Exception {
		int ret = 0;
		ret = relWxIndexMapMapper.insert(relWxIndexMapPOJO);
		return ret;
	}

	@Override
	public int update(RelWxIndexMapPOJO relWxIndexMapPOJO) throws Exception {
		int ret = 0;
		ret = relWxIndexMapMapper.update(relWxIndexMapPOJO);
		return ret;
	}

	@Override
	public List<RelWxIndexMapPOJO> finds(
			RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO) throws Exception {
		List<RelWxIndexMapPOJO> ret = null;
		ret = relWxIndexMapMapper.finds(relWxIndexMapSearchPOJO);
		return ret;
	}
	
	@Override
	public List<RelWxIndexMapPOJO> findsByAuthorizerInfo(
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception {
		List<RelWxIndexMapPOJO> ret = new ArrayList<RelWxIndexMapPOJO>();
		// 获取公众号的信息, 确定是哪个公众号收到信息
		List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
		WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
		
		if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
			for (int i = 0; i < wxAuthorizerInfoPOJOs.size(); i++) {
				wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(i);
				
				// 获取公众号的wxIndexCode
				RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO = new RelWxIndexMapSearchPOJO();
				relWxIndexMapSearchPOJO.setUserId(wxAuthorizerInfoPOJO.getUserId());
				List<RelWxIndexMapPOJO> relWxIndexMapPOJOs = this.finds(relWxIndexMapSearchPOJO );
				if (!CollectionUtils.isEmpty(relWxIndexMapPOJOs)) {
					ret.addAll(relWxIndexMapPOJOs);
				}
			}
		}
		return ret;
	}

	@Override
	public int getCount(RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = relWxIndexMapMapper.getCount(relWxIndexMapSearchPOJO);
		return ret;
	}

	@Override
	public RelWxIndexMapPOJO findById(Long id) throws Exception {
		RelWxIndexMapPOJO ret = null;
		ret = relWxIndexMapMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = relWxIndexMapMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += relWxIndexMapMapper.deleteById(id);
			}
		}
		return ret;
	}


}

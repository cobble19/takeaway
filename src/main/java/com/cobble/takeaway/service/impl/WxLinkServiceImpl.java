package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxLinkMapper;
import com.cobble.takeaway.pojo.RelWxLinkPOJO;
import com.cobble.takeaway.pojo.WxLinkPOJO;
import com.cobble.takeaway.pojo.WxLinkSearchPOJO;
import com.cobble.takeaway.service.WxLinkService;

@Service
public class WxLinkServiceImpl implements WxLinkService {
	
	@Autowired
	private WxLinkMapper wxLinkMapper;

	@Override
	public int insert(WxLinkPOJO wxLinkPOJO) throws Exception {
		int ret = 0;
		ret = wxLinkMapper.insert(wxLinkPOJO);
		return ret;
	}

	@Override
	public int update(WxLinkPOJO wxLinkPOJO) throws Exception {
		int ret = 0;
		ret = wxLinkMapper.update(wxLinkPOJO);
		return ret;
	}

	@Override
	public List<WxLinkPOJO> finds(
			WxLinkSearchPOJO wxLinkSearchPOJO) throws Exception {
		List<WxLinkPOJO> ret = null;
		ret = wxLinkMapper.finds(wxLinkSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxLinkSearchPOJO wxLinkSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxLinkMapper.getCount(wxLinkSearchPOJO);
		return ret;
	}

	@Override
	public WxLinkPOJO findById(Long id) throws Exception {
		WxLinkPOJO ret = null;
		ret = wxLinkMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxLinkMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxLinkMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public int updateByKey(WxLinkPOJO wxLinkPOJO) throws Exception {
		int ret = wxLinkMapper.updateByKey(wxLinkPOJO);
		return ret;
	}

	@Override
	public int getCountByKey(WxLinkPOJO wxLinkPOJO) throws Exception {
		int ret = 0;
		ret = wxLinkMapper.getCountByKey(wxLinkPOJO);
		return ret;
	}

	@Override
	public List<WxLinkPOJO> findsByIds(WxLinkPOJO wxLinkPOJO)
			throws Exception {
		List<WxLinkPOJO> ret = null;
		ret = wxLinkMapper.findsByIds(wxLinkPOJO);
		return ret;
	}

	@Override
	public int insertRelWxLink(RelWxLinkPOJO relWxLinkPOJO) throws Exception {
		int ret = 0;
		ret = wxLinkMapper.insertRelWxLink(relWxLinkPOJO);
		return ret;
	}

}

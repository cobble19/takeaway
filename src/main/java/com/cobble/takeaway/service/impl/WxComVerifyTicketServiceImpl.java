package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxComVerifyTicketMapper;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketSearchApiPOJO;
import com.cobble.takeaway.service.WxComVerifyTicketService;

@Service
public class WxComVerifyTicketServiceImpl implements WxComVerifyTicketService {
	
	@Autowired
	private WxComVerifyTicketMapper wxComVerifyTicketMapper;

	@Override
	public int insert(WxComVerifyTicketApiPOJO wxComVerifyTicketPOJO) throws Exception {
		int ret = 0;
		ret = wxComVerifyTicketMapper.insert(wxComVerifyTicketPOJO);
		return ret;
	}

	@Override
	public int update(WxComVerifyTicketApiPOJO wxComVerifyTicketPOJO) throws Exception {
		int ret = 0;
		ret = wxComVerifyTicketMapper.update(wxComVerifyTicketPOJO);
		return ret;
	}

	@Override
	public List<WxComVerifyTicketApiPOJO> finds(
			WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchPOJO) throws Exception {
		List<WxComVerifyTicketApiPOJO> ret = null;
		ret = wxComVerifyTicketMapper.finds(wxComVerifyTicketSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxComVerifyTicketMapper.getCount(wxComVerifyTicketSearchPOJO);
		return ret;
	}

	@Override
	public WxComVerifyTicketApiPOJO findById(Long id) throws Exception {
		WxComVerifyTicketApiPOJO ret = null;
		ret = wxComVerifyTicketMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxComVerifyTicketMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxComVerifyTicketMapper.deleteById(id);
			}
		}
		return ret;
	}

}

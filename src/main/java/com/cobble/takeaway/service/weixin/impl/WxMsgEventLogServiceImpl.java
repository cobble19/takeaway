package com.cobble.takeaway.service.weixin.impl;

import com.cobble.takeaway.dao.weixin.WxMsgEventLogMapper;
import com.cobble.takeaway.pojo.weixin.WxMsgEventLogPOJO;
import com.cobble.takeaway.pojo.weixin.WxMsgEventLogSearchPOJO;
import com.cobble.takeaway.service.weixin.WxMsgEventLogService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxMsgEventLogServiceImpl implements WxMsgEventLogService {
	
	@Autowired
	private WxMsgEventLogMapper wxMsgEventLogMapper;

	@Override
	public int insert(WxMsgEventLogPOJO wxMsgEventLogPOJO) throws Exception {
		int ret = 0;
		ret = wxMsgEventLogMapper.insert(wxMsgEventLogPOJO);
		return ret;
	}

	@Override
	public int update(WxMsgEventLogPOJO wxMsgEventLogPOJO) throws Exception {
		int ret = 0;
		ret = wxMsgEventLogMapper.update(wxMsgEventLogPOJO);
		return ret;
	}

	@Override
	public List<WxMsgEventLogPOJO> finds(
			WxMsgEventLogSearchPOJO wxMsgEventLogSearchPOJO) throws Exception {
		List<WxMsgEventLogPOJO> ret = null;
		ret = wxMsgEventLogMapper.finds(wxMsgEventLogSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxMsgEventLogSearchPOJO wxMsgEventLogSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxMsgEventLogMapper.getCount(wxMsgEventLogSearchPOJO);
		return ret;
	}

	@Override
	public WxMsgEventLogPOJO findById(Long id) throws Exception {
		WxMsgEventLogPOJO ret = null;
		ret = wxMsgEventLogMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxMsgEventLogMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxMsgEventLogMapper.deleteById(id);
			}
		}
		return ret;
	}

}

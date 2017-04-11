package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.WxRespMsgMapper;
import com.cobble.takeaway.pojo.weixin.WxRespMsgPOJO;
import com.cobble.takeaway.pojo.weixin.WxRespMsgSearchPOJO;
import com.cobble.takeaway.service.WxRespMsgService;

@Service
public class WxRespMsgServiceImpl implements WxRespMsgService {
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WxRespMsgMapper wxRespMsgMapper;

	@Override
	public int insert(WxRespMsgPOJO wxRespMsgPOJO) throws Exception {
		int ret = 0;
		/*WxRespMsgSearchPOJO temp = new WxRespMsgSearchPOJO();
		temp.setAuthorizerAppId(wxRespMsgPOJO.getAuthorizerAppId());
		temp.setMsgReceive(wxRespMsgPOJO.getMsgReceive());
		
		int count = wxRespMsgMapper.getCount(temp);
		if (count == 0) {
			ret = wxRespMsgMapper.insert(wxRespMsgPOJO);
		}*/
		
		ret = wxRespMsgMapper.insert(wxRespMsgPOJO);
		return ret;
	}

	@Override
	public int update(WxRespMsgPOJO wxRespMsgPOJO) throws Exception {
		int ret = 0;
		ret = wxRespMsgMapper.update(wxRespMsgPOJO);
		return ret;
	}

	@Override
	public List<WxRespMsgPOJO> finds(
			WxRespMsgSearchPOJO wxRespMsgSearchPOJO) throws Exception {
		List<WxRespMsgPOJO> ret = null;
		ret = wxRespMsgMapper.finds(wxRespMsgSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WxRespMsgSearchPOJO wxRespMsgSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxRespMsgMapper.getCount(wxRespMsgSearchPOJO);
		return ret;
	}

	@Override
	public WxRespMsgPOJO findById(Long id) throws Exception {
		WxRespMsgPOJO ret = null;
		ret = wxRespMsgMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Long id) throws Exception {
		int ret = 0;
		ret = wxRespMsgMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxRespMsgMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public int delete(WxRespMsgPOJO wxRespMsgPOJO) throws Exception {
		int ret = 0;
		ret = wxRespMsgMapper.delete(wxRespMsgPOJO);
		return ret;
	}

	@Override
	public int delete(List<WxRespMsgPOJO> wxRespMsgPOJOs) throws Exception {
		int ret = 0;
		if (CollectionUtils.isNotEmpty(wxRespMsgPOJOs)) {
			for (WxRespMsgPOJO wxRespMsgPOJO : wxRespMsgPOJOs) {
				ret += wxRespMsgMapper.delete(wxRespMsgPOJO);
			}
		}
		return ret;
	}


}

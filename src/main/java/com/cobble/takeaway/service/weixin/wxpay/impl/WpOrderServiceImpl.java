package com.cobble.takeaway.service.weixin.wxpay.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.weixin.wxpay.WpOrderMapper;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderSearchPOJO;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;

@Service
public class WpOrderServiceImpl implements WpOrderService {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WpOrderMapper wpOrderMapper;

	@Override
	public int insert(WpOrderPOJO wpOrderPOJO) throws Exception {
		int ret = 0;
		ret = wpOrderMapper.insert(wpOrderPOJO);
		return ret;
	}

	@Override
	public int update(WpOrderPOJO wpOrderPOJO) throws Exception {
		int ret = 0;
		ret = wpOrderMapper.update(wpOrderPOJO);
		return ret;
	}

	@Override
	public List<WpOrderPOJO> finds(
			WpOrderSearchPOJO wpOrderSearchPOJO) throws Exception {
		List<WpOrderPOJO> ret = null;
		ret = wpOrderMapper.finds(wpOrderSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WpOrderSearchPOJO wpOrderSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wpOrderMapper.getCount(wpOrderSearchPOJO);
		return ret;
	}

	@Override
	public WpOrderPOJO findById(Long id) throws Exception {
		WpOrderPOJO ret = null;
		ret = wpOrderMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wpOrderMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wpOrderMapper.deleteById(id);
			}
		}
		return ret;
	}
}

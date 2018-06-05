package com.cobble.takeaway.service.weixin.wxpay.impl;

import com.cobble.takeaway.dao.weixin.wxpay.WpOrderRespMapper;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderRespPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderRespSearchPOJO;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderRespService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WpOrderRespServiceImpl implements WpOrderRespService {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WpOrderRespMapper wpOrderRespMapper;

	@Override
	public int insert(WpOrderRespPOJO wpOrderRespPOJO) throws Exception {
		int ret = 0;
		ret = wpOrderRespMapper.insert(wpOrderRespPOJO);
		return ret;
	}

	@Override
	public int update(WpOrderRespPOJO wpOrderRespPOJO) throws Exception {
		int ret = 0;
		ret = wpOrderRespMapper.update(wpOrderRespPOJO);
		return ret;
	}

	@Override
	public List<WpOrderRespPOJO> finds(
			WpOrderRespSearchPOJO wpOrderRespSearchPOJO) throws Exception {
		List<WpOrderRespPOJO> ret = null;
		ret = wpOrderRespMapper.finds(wpOrderRespSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WpOrderRespSearchPOJO wpOrderRespSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wpOrderRespMapper.getCount(wpOrderRespSearchPOJO);
		return ret;
	}

	@Override
	public WpOrderRespPOJO findById(Long id) throws Exception {
		WpOrderRespPOJO ret = null;
		ret = wpOrderRespMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wpOrderRespMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wpOrderRespMapper.deleteById(id);
			}
		}
		return ret;
	}

}

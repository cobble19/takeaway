package com.cobble.takeaway.service.weixin.wxpay.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.weixin.wxpay.WpOrderCloseMapper;
import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderClosePOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderCloseSearchPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.service.ecommerce.EcOrderService;
import com.cobble.takeaway.service.ecommerce.EcProductService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderCloseService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;


@Service
public class WpOrderCloseServiceImpl implements WpOrderCloseService {
	private final static Logger logger = LoggerFactory.getLogger(WpOrderCloseServiceImpl.class);

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WpOrderCloseMapper wpOrderCloseMapper;
	@Autowired
	private WpOrderService wpOrderService;
	@Autowired
	private EcOrderService ecOrderService;
	@Autowired
	private EcProductService ecProductService;

	@Override
	public int insert(WpOrderClosePOJO wpOrderClosePOJO) throws Exception {
		int ret = 0;
		ret = wpOrderCloseMapper.insert(wpOrderClosePOJO);
		return ret;
	}

	@Override
	public int increaseEcProductStock(WpOrderClosePOJO wpOrderClosePOJO) throws Exception {
		int ret = 0;
		try {
			if (wpOrderClosePOJO == null 
					|| !"SUCCESS".equalsIgnoreCase(wpOrderClosePOJO.getRespReturnCode())
					|| !"SUCCESS".equalsIgnoreCase(wpOrderClosePOJO.getRespResultCode())
					|| StringUtils.isBlank(wpOrderClosePOJO.getOutTradeNo())) {
				return ret;
			}
			// get WpOrder
			WpOrderPOJO wpOrderPOJO = wpOrderService.findByOutTradeNo(wpOrderClosePOJO.getOutTradeNo());
			if (wpOrderPOJO == null) {
				return ret;
			}
			// get EcOrder
			EcOrderPOJO ecOrderPOJO = ecOrderService.findById(wpOrderPOJO.getEcOrderId());
			// get EcProduct, 貌似不需要这不查询
//			EcProductPOJO ecProductPOJO = ecProductService.findById(ecOrderPOJO.getProductId());
			ret = ecProductService.increaseStock(ecOrderPOJO.getProductId(), ecOrderPOJO.getQuantity());
		} catch (Exception e) {
			logger.error("increaseEcProductStock exception: ", e);
		}
		return ret;
	}

	@Override
	public int update(WpOrderClosePOJO wpOrderClosePOJO) throws Exception {
		int ret = 0;
		ret = wpOrderCloseMapper.update(wpOrderClosePOJO);
		return ret;
	}

	@Override
	public int updateByOutTradeNo(WpOrderClosePOJO wpOrderClosePOJO) throws Exception {
		int ret = 0;
		ret = wpOrderCloseMapper.updateByOutTradeNo(wpOrderClosePOJO);
		return ret;
	}
	
	@Override
	public List<WpOrderClosePOJO> finds(
			WpOrderCloseSearchPOJO wpOrderCloseSearchPOJO) throws Exception {
		List<WpOrderClosePOJO> ret = null;
		ret = wpOrderCloseMapper.finds(wpOrderCloseSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WpOrderCloseSearchPOJO wpOrderCloseSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wpOrderCloseMapper.getCount(wpOrderCloseSearchPOJO);
		return ret;
	}

	@Override
	public WpOrderClosePOJO findById(Long id) throws Exception {
		WpOrderClosePOJO ret = null;
		ret = wpOrderCloseMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wpOrderCloseMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wpOrderCloseMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public WpOrderClosePOJO findByOutTradeNo(String outTradeNo) throws Exception {
		WpOrderClosePOJO ret = null;
		WpOrderCloseSearchPOJO wpOrderCloseSearchPOJO = new WpOrderCloseSearchPOJO();
		wpOrderCloseSearchPOJO.setOutTradeNo(outTradeNo);
		List<WpOrderClosePOJO> wpOrderClosePOJOs = wpOrderCloseMapper.finds(wpOrderCloseSearchPOJO);
		if (CollectionUtils.isNotEmpty(wpOrderClosePOJOs)) {
			ret = wpOrderClosePOJOs.get(0);
		}
		return ret;
	}

}

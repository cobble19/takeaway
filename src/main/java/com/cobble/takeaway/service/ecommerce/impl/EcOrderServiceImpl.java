package com.cobble.takeaway.service.ecommerce.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderSearchPOJO;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ecommerce.EcOrderMapper;
import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderSearchPOJO;
import com.cobble.takeaway.service.ecommerce.EcOrderService;

@Service
public class EcOrderServiceImpl implements EcOrderService {
	
	@Autowired
	private EcOrderMapper ecOrderMapper;
	@Autowired
	private WpOrderService wpOrderService;

	@Override
	public int insert(EcOrderPOJO ecOrderPOJO) throws Exception {
		int ret = 0;
		ret = ecOrderMapper.insert(ecOrderPOJO);
		return ret;
	}

	@Override
	public int update(EcOrderPOJO ecOrderPOJO) throws Exception {
		int ret = 0;
		ret = ecOrderMapper.update(ecOrderPOJO);
		return ret;
	}

	@Override
	public List<EcOrderPOJO> finds(
			EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception {
		List<EcOrderPOJO> ret = null;
		ret = ecOrderMapper.finds(ecOrderSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(EcOrderSearchPOJO ecOrderSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = ecOrderMapper.getCount(ecOrderSearchPOJO);
		return ret;
	}

	@Override
	public EcOrderPOJO findById(Long id) throws Exception {
		EcOrderPOJO ret = null;
		ret = ecOrderMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = ecOrderMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += ecOrderMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public int getCountReally(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception {
		int ret = 0;
		WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
		Long productId = ecOrderSearchPOJO.getProductId();
		String openId = ecOrderSearchPOJO.getOpenId();

		wpOrderSearchPOJO.setEcProductId(productId);
		wpOrderSearchPOJO.setOpenId(openId);
		wpOrderSearchPOJO.setRespReturnCode("SUCCESS");
		wpOrderSearchPOJO.setRespResultCode("SUCCESS");
		wpOrderSearchPOJO.setPaginationFlage(false);
		// 已成功购买商品(卡券)个数
//		int orderCount = wpOrderService.getCount(wpOrderSearchPOJO);
		List<WpOrderPOJO> wpOrderPOJOs = wpOrderService.finds(wpOrderSearchPOJO);

		List<Long> orderIds = new ArrayList<Long>();
		if (CollectionUtils.isNotEmpty(wpOrderPOJOs)) {
			for (WpOrderPOJO wpOrderPOJO : wpOrderPOJOs) {
				orderIds.add(wpOrderPOJO.getEcOrderId());
			}
			EcOrderSearchPOJO temp = new EcOrderSearchPOJO();
			temp.setOrderIds(orderIds);
			List<EcOrderPOJO> ecOrderPOJOs = ecOrderMapper.finds(temp);
			if (CollectionUtils.isNotEmpty(ecOrderPOJOs)) {
				for (EcOrderPOJO ecOrderPOJO : ecOrderPOJOs) {
					ret += ecOrderPOJO.getQuantity();
				}
			}
		}

		return ret;
	}

	@Override
	public int getCountTodayTotal(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception {
		int ret = 0;

		Date curDateTime = new Date();
		Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
		Date endDateTime = DateUtils.addMilliseconds(startDateTime, 1 * 24 * 60 * 60 * 1000 - 1);
        Long productId = ecOrderSearchPOJO.getProductId();

        WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
		wpOrderSearchPOJO.setEcProductId(productId);
		wpOrderSearchPOJO.setPaginationFlage(false);
		wpOrderSearchPOJO.setStartDateTime(startDateTime);
		wpOrderSearchPOJO.setEndDateTime(endDateTime);

		List<WpOrderPOJO> wpOrderPOJOs = wpOrderService.finds(wpOrderSearchPOJO);

		List<Long> orderIds = new ArrayList<Long>();
		if (CollectionUtils.isNotEmpty(wpOrderPOJOs)) {
			for (WpOrderPOJO wpOrderPOJO : wpOrderPOJOs) {
				orderIds.add(wpOrderPOJO.getEcOrderId());
			}
			EcOrderSearchPOJO temp = new EcOrderSearchPOJO();
			temp.setOrderIds(orderIds);
			List<EcOrderPOJO> ecOrderPOJOs = ecOrderMapper.finds(temp);
			if (CollectionUtils.isNotEmpty(ecOrderPOJOs)) {
				for (EcOrderPOJO ecOrderPOJO : ecOrderPOJOs) {
					ret += ecOrderPOJO.getQuantity();
				}
			}
		}

		return ret;
	}

	@Override
	public int getCountTodayClose(EcOrderSearchPOJO ecOrderSearchPOJO) throws Exception {
		int ret = 0;

		Date curDateTime = new Date();
		Date startDateTime = DateUtils.truncate(curDateTime, Calendar.DATE);
		Date endDateTime = DateUtils.addMilliseconds(startDateTime, 1 * 24 * 60 * 60 * 1000 - 1);
        Long productId = ecOrderSearchPOJO.getProductId();

        WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
		wpOrderSearchPOJO.setEcProductId(productId);
		wpOrderSearchPOJO.setPaginationFlage(false);
		wpOrderSearchPOJO.setStartDateTime(startDateTime);
		wpOrderSearchPOJO.setEndDateTime(endDateTime);

		List<WpOrderPOJO> wpOrderPOJOs = wpOrderService.findsWithClose(wpOrderSearchPOJO);

		List<Long> orderIds = new ArrayList<Long>();
		if (CollectionUtils.isNotEmpty(wpOrderPOJOs)) {
			for (WpOrderPOJO wpOrderPOJO : wpOrderPOJOs) {
				orderIds.add(wpOrderPOJO.getEcOrderId());
			}
			EcOrderSearchPOJO temp = new EcOrderSearchPOJO();
			temp.setOrderIds(orderIds);
			List<EcOrderPOJO> ecOrderPOJOs = ecOrderMapper.finds(temp);
			if (CollectionUtils.isNotEmpty(ecOrderPOJOs)) {
				for (EcOrderPOJO ecOrderPOJO : ecOrderPOJOs) {
					ret += ecOrderPOJO.getQuantity();
				}
			}
		}

		return ret;
	}


}

package com.cobble.takeaway.service.weixin.wxpay.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.weixin.wxpay.WpResultNotifyMapper;
import com.cobble.takeaway.pojo.weixin.wxpay.WpResultNotifyPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpResultNotifySearchPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayResultNotifyApiPOJO;
import com.cobble.takeaway.service.SequenceService;
import com.cobble.takeaway.service.weixin.wxpay.WpResultNotifyService;
import com.cobble.takeaway.util.XmlUtils;
import com.github.wxpay.sdk.WXPayUtil;

@Service
public class WpResultNotifyServiceImpl implements WpResultNotifyService {
	private static final Logger logger = LoggerFactory.getLogger(WpResultNotifyServiceImpl.class);

	@Autowired
	private WpResultNotifyMapper wpResultNotifyMapper;

	@Override
	public int insert(WpResultNotifyPOJO wpResultNotifyPOJO) throws Exception {
		int ret = 0;
		ret = wpResultNotifyMapper.insert(wpResultNotifyPOJO);
		return ret;
	}

	@Override
	public int update(WpResultNotifyPOJO wpResultNotifyPOJO) throws Exception {
		int ret = 0;
		ret = wpResultNotifyMapper.update(wpResultNotifyPOJO);
		return ret;
	}

	@Override
	public List<WpResultNotifyPOJO> finds(
			WpResultNotifySearchPOJO wpResultNotifySearchPOJO) throws Exception {
		List<WpResultNotifyPOJO> ret = null;
		ret = wpResultNotifyMapper.finds(wpResultNotifySearchPOJO);
		return ret;
	}

	@Override
	public int getCount(WpResultNotifySearchPOJO wpResultNotifySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wpResultNotifyMapper.getCount(wpResultNotifySearchPOJO);
		return ret;
	}

	@Override
	public WpResultNotifyPOJO findById(Long id) throws Exception {
		WpResultNotifyPOJO ret = null;
		ret = wpResultNotifyMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wpResultNotifyMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wpResultNotifyMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public int insert(String resultNofity) throws Exception {
		int ret = 0;
		try {
	        logger.info("insert微信支付成功通知start: {}", resultNofity);
//	        Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
	        WpResultNotifyPOJO wpResultNotifyPOJO = new WpResultNotifyPOJO();
	        WxPayResultNotifyApiPOJO wxPayResultNotifyApiPOJO = XmlUtils.convertToJavaBean(resultNofity, WxPayResultNotifyApiPOJO.class);
	        BeanUtils.copyProperties(wpResultNotifyPOJO, wxPayResultNotifyApiPOJO);
	        wpResultNotifyPOJO.setRawData(resultNofity);
			ret = wpResultNotifyMapper.insert(wpResultNotifyPOJO);
	        logger.info("insert微信支付成功通知finish");
		} catch (Exception e) {
			logger.error("insert微信支付成功通知发生异常", e);
		}
		return ret;
	}

}

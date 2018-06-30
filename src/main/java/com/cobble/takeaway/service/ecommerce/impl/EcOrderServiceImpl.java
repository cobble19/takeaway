package com.cobble.takeaway.service.ecommerce.impl;

import com.cobble.takeaway.dao.ecommerce.EcOrderMapper;
import com.cobble.takeaway.pojo.ecommerce.EcOrderCallWxPayParamPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderSearchPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderSearchPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderQueryReqApiPOJO;
import com.cobble.takeaway.service.WxPayService;
import com.cobble.takeaway.service.ecommerce.EcOrderService;
import com.cobble.takeaway.service.ecommerce.EcProductService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderRespService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.ConfigurationUtil;
import com.github.wxpay.sdk.MyWXPayConfigImpl;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EcOrderServiceImpl implements EcOrderService {
	private static final Logger logger = LoggerFactory.getLogger(EcOrderServiceImpl.class);
	
	@Autowired
	private EcOrderMapper ecOrderMapper;
	@Autowired
	private WpOrderService wpOrderService;
	@Autowired
	private WpOrderRespService wpOrderRespService;
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private EcProductService ecProductService;

	@Override
	public int insert(EcOrderPOJO ecOrderPOJO) throws Exception {
		int ret = 0;
		ecOrderPOJO.setOrigQuantity(ecOrderPOJO.getQuantity());
		ret = ecOrderMapper.insert(ecOrderPOJO);
		return ret;
	}

	@Override
	public int update(EcOrderPOJO ecOrderPOJO) throws Exception {
		int ret = 0;
		if (ecOrderPOJO.getQuantity() != 0) {
			ecOrderPOJO.setOrigQuantity(ecOrderPOJO.getQuantity());
		}
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
	public int updatePayResult(EcOrderPOJO ecOrderPOJO) throws Exception {
		int ret = 0;
		ret = ecOrderMapper.updatePayResult(ecOrderPOJO);
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

	/**
	 *
	 * @param ecOrderCallWxPayParamPOJO
	 * @return
	 * @throws Exception
	 */
	@Override
	public EcOrderPOJO clearExistEcOrderQuantity(EcOrderCallWxPayParamPOJO ecOrderCallWxPayParamPOJO) throws Exception {
		logger.info("ecOrderCallWxPayParamPOJO: {}", ecOrderCallWxPayParamPOJO);

		EcOrderPOJO ret = null;

		String authorizerAppId = ecOrderCallWxPayParamPOJO.getAuthorizerAppId();
		Long productId = ecOrderCallWxPayParamPOJO.getProductId();
		Integer unitPrice = ecOrderCallWxPayParamPOJO.getUnitPrice();
		Integer quantity = ecOrderCallWxPayParamPOJO.getQuantity();
		String openId = ecOrderCallWxPayParamPOJO.getOpenId();

		try {
			if (StringUtils.isBlank(openId)) {
				logger.error("openId is null");
				return null;
			}

			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
			}

			// 获取最后一条没有支付成功, 并且没有被close的订单
			int orderTimeout = ConfigurationUtil.getPropertiesConfig().getInt("WXPAY.orderTimeout", 30 * 60);
			WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
			wpOrderSearchPOJO.setPaginationFlage(false);
			wpOrderSearchPOJO.setAppId(authorizerAppId);
			wpOrderSearchPOJO.setEcProductId(productId);
			wpOrderSearchPOJO.setOpenId(openId);

			Calendar cal = Calendar.getInstance();
			Date curDate = cal.getTime();
			cal.add(Calendar.SECOND, -2 * orderTimeout );
			Date startDateTime = cal.getTime();
			wpOrderSearchPOJO.setStartDateTime(startDateTime);
			List<WpOrderPOJO> wpOrderPOJOs = wpOrderService.finds4Close(wpOrderSearchPOJO);
			if (org.apache.commons.collections4.CollectionUtils.isEmpty(wpOrderPOJOs)) {
				logger.error("wpOrderPOJOs is null");
				return null;
			}
			WpOrderPOJO wpOrderPOJO = wpOrderPOJOs.get(0);

			String appId = authorizerAppId;
			String nonceStr = wpOrderPOJO.getNonceStr();

			// 调用wx unified order api获取prepay_id
//			Map unifiedOrderReqMap = new HashMap();
//			WxPayUnifiedOrderReqApiPOJO wxPayUnifiedOrderReqApiPOJO = new WxPayUnifiedOrderReqApiPOJO();
			String mchId = MyWXPayConfigImpl.getInstance().getMchID();
//			String body = ecProductPOJO.getProductName();
			String outTradeNo = wpOrderPOJO.getOutTradeNo();
//			String totalFee = (unitPrice * quantity) + "";
//			String spbillCreateIp = HttpRequestUtil.getIpAddr(request);
//			String notifyUrl = ConfigurationUtil.getPropertiesConfig().getString("WXPAY.notifyUrl", "http://www.deweiyizhan.com/api/wxpay/notify");
//			String tradeType = "JSAPI";
//			wxPayUnifiedOrderReqApiPOJO.setAppId(appId);
//			wxPayUnifiedOrderReqApiPOJO.setMchId(mchId);
//			wxPayUnifiedOrderReqApiPOJO.setNonceStr(nonceStr);
//			wxPayUnifiedOrderReqApiPOJO.setDeviceInfo("WEB");
//			wxPayUnifiedOrderReqApiPOJO.setBody(body);
//			wxPayUnifiedOrderReqApiPOJO.setAttach("attach");
//			wxPayUnifiedOrderReqApiPOJO.setOutTradeNo(outTradeNo);
//			wxPayUnifiedOrderReqApiPOJO.setTotalFee(totalFee);
//			wxPayUnifiedOrderReqApiPOJO.setSpbillCreateIp(spbillCreateIp);
//			wxPayUnifiedOrderReqApiPOJO.setNotifyUrl(notifyUrl);
//			wxPayUnifiedOrderReqApiPOJO.setTradeType(tradeType);
//			wxPayUnifiedOrderReqApiPOJO.setOpenId(openId);
//
//			wxPayUnifiedOrderReqApiPOJO.setSignType(WXPayConstants.MD5);
//
//			Map unifiedOrderRespMap = wxPayService.unifiedOrder(wxPayUnifiedOrderReqApiPOJO);

//			WpOrderRespPOJO wpOrderRespPOJO = null;
//			try {
//				WpOrderRespSearchPOJO wpOrderRespSearchPOJO = new WpOrderRespSearchPOJO();
//				wpOrderRespSearchPOJO.setAppId(authorizerAppId);
//				wpOrderRespSearchPOJO.setOutTradeNo(outTradeNo);
//				wpOrderRespSearchPOJO.setOpenId(openId);
//				wpOrderRespSearchPOJO.setMchId(mchId);	// 可以不用
//				wpOrderRespSearchPOJO.setEcOrderId(wpOrderPOJO.getEcOrderId()); // 可以不用
//				wpOrderRespSearchPOJO.setEcProductId(productId);	// 可以不用
//				wpOrderRespSearchPOJO.setStartDateTime(startDateTime);
//
//				List<WpOrderRespPOJO> wpOrderRespPOJOs = wpOrderRespService.finds(wpOrderRespSearchPOJO);
//				if (org.apache.commons.collections4.CollectionUtils.isEmpty(wpOrderRespPOJOs)) {
//					logger.error("wpOrderRespPOJOs is null");
//					return null;
//				}
//				wpOrderRespPOJO = wpOrderRespPOJOs.get(0);
//			} catch (Exception e) {
//				logger.error("wpOrderService insert exception: ", e);
//			}

			// orderquery 查询微信支付订单
			WxPayOrderQueryReqApiPOJO wxPayOrderQueryReqApiPOJO = new WxPayOrderQueryReqApiPOJO();
			wxPayOrderQueryReqApiPOJO.setAppId(appId);
			wxPayOrderQueryReqApiPOJO.setMchId(mchId);
			wxPayOrderQueryReqApiPOJO.setOutTradeNo(outTradeNo);
			wxPayOrderQueryReqApiPOJO.setNonceStr(nonceStr + 1);
			wxPayOrderQueryReqApiPOJO.setSignType(WXPayConstants.MD5);
			Map orderQueryRespMap = wxPayService.orderQuery(wxPayOrderQueryReqApiPOJO);
			// end orderquery
			String returnCode = (String) orderQueryRespMap.get("return_code");
			String resultCode = (String) orderQueryRespMap.get("result_code");
			String tradeState = (String) orderQueryRespMap.get("trade_state");

			if (CommonConstant.WXPAY_SUCCESS.equals(returnCode)
					&& CommonConstant.WXPAY_SUCCESS.equals(resultCode)
					&& CommonConstant.WXPAY_ORDER_NOTPAY.equals(tradeState)) {
				ret = this.findById(wpOrderPOJO.getEcOrderId());
				logger.info("置0存在的ecOrder的quantity, ecOrder: {}", ret);
				// 获取ecOrder, 修改ecproduct的stock
				ecProductService.increaseStock(ret.getProductId(), ret.getQuantity());
				// 置ecOrder quantity=0
				EcOrderPOJO tempEcOrderPOJO = new EcOrderPOJO();
				tempEcOrderPOJO.setOrderId(ret.getOrderId());
				tempEcOrderPOJO.setQuantity(0);
				this.update(tempEcOrderPOJO);

				logger.info("clear existed wp order info, wpOrderPOJO: {}", wpOrderPOJO);
			} else {
				logger.error("订单不是未支付的: {}", WXPayUtil.mapToXml(orderQueryRespMap));
				return null;
			}

		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		return ret;
	}

}

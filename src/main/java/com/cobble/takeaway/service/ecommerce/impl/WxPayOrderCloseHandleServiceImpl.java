package com.cobble.takeaway.service.ecommerce.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderClosePOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderSearchPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderCloseReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderCloseRespApiPOJO;
import com.cobble.takeaway.service.WxPayService;
import com.cobble.takeaway.service.ecommerce.WpOrderCloseHandleService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderCloseService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;
import com.cobble.takeaway.util.ConfigurationUtil;
import com.cobble.takeaway.util.XmlUtils;
import com.github.wxpay.sdk.WXPayUtil;

@Service
public class WxPayOrderCloseHandleServiceImpl implements WpOrderCloseHandleService {
	private static final Logger logger = LoggerFactory.getLogger(WxPayOrderCloseHandleServiceImpl.class);
	
	@Autowired
	private WpOrderService wpOrderService;
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private WpOrderCloseService wpOrderCloseService;

	/**
	 * 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
	 * 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
	 * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
	 * ======
	 * 定时关闭微信支付订单
	 * 查询两天内的微信订单WP_ORDER
	 * 如果resp_return_code no
	 */
	@PostConstruct
	public void closeWxPayOrder() {
		try {
			logger.info("定期关闭无效的微信支付订单, 线程池 start...");
			ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
			CloseWxPayOrderThread closeWxPayOrderThread = new CloseWxPayOrderThread();
			long initialDelay = 5 * 60;
			long period = 1 * 60;
			TimeUnit unit = TimeUnit.SECONDS;
			executorService.scheduleAtFixedRate(closeWxPayOrderThread, initialDelay, period, unit );
		} catch (Exception e) {
			logger.error("closeWxPayOrder Exception: ", e);
		}
		logger.info("定期关闭无效的微信支付订单, 开启完成, 线程池 done...");
	}
	public class CloseWxPayOrderThread extends Thread {

		@Override
		public void run() {
			try {
				logger.info("定期关闭微信订单, thread start...");
				int orderTimeout = ConfigurationUtil.getPropertiesConfig().getInt("WXPAY.orderTimeout", 30 * 60);
				WpOrderSearchPOJO wpOrderSearchPOJO = new WpOrderSearchPOJO();
				Calendar cal = Calendar.getInstance();
				Date curDate = cal.getTime();
				cal.add(Calendar.HOUR_OF_DAY, -24);
				Date startDateTime = cal.getTime();
				wpOrderSearchPOJO.setStartDateTime(startDateTime);
				List<WpOrderPOJO> wpOrderPOJOs = wpOrderService.finds4Close(wpOrderSearchPOJO);
				if (CollectionUtils.isNotEmpty(wpOrderPOJOs)) {
					for (int i = 0; i < wpOrderPOJOs.size(); i++) {
						WpOrderPOJO wpOrderPOJO = wpOrderPOJOs.get(i);
						if (wpOrderPOJO.getWpOrderClosePOJO() == null && (curDate.getTime() - wpOrderPOJO.getCreateDateTime().getTime()) / 1000 > orderTimeout) {
							WxPayOrderCloseReqApiPOJO wxPayOrderCloseReqApiPOJO = new WxPayOrderCloseReqApiPOJO();
							wxPayOrderCloseReqApiPOJO.setAppId(wpOrderPOJO.getAppId());
							wxPayOrderCloseReqApiPOJO.setMchId(wpOrderPOJO.getMchId());
							wxPayOrderCloseReqApiPOJO.setNonceStr(RandomStringUtils.randomAlphanumeric(6));
							wxPayOrderCloseReqApiPOJO.setOutTradeNo(wpOrderPOJO.getOutTradeNo());
							wxPayOrderCloseReqApiPOJO.setSign(wpOrderPOJO.getSign());
							wxPayOrderCloseReqApiPOJO.setSignType(wpOrderPOJO.getSignType());
							
							Map orderCloseMap = wxPayService.orderClose(wxPayOrderCloseReqApiPOJO);
							String orderCloseResult = WXPayUtil.mapToXml(orderCloseMap);
							WxPayOrderCloseRespApiPOJO wxPayOrderCloseRespApiPOJO = XmlUtils.convertToJavaBean(orderCloseResult, WxPayOrderCloseRespApiPOJO.class);
							
							WpOrderClosePOJO wpOrderClosePOJO = new WpOrderClosePOJO();
							String appId = wxPayOrderCloseReqApiPOJO.getAppId();
							String mchId = wxPayOrderCloseReqApiPOJO.getMchId();
							String outTradeNo = wxPayOrderCloseReqApiPOJO.getOutTradeNo();
							String nonceStr = wxPayOrderCloseReqApiPOJO.getNonceStr();
							String sign = wxPayOrderCloseReqApiPOJO.getSign();
							String signType = wxPayOrderCloseReqApiPOJO.getSignType();
							String respReturnCode = wxPayOrderCloseRespApiPOJO.getReturnCode();
							String respReturnMsg = wxPayOrderCloseRespApiPOJO.getReturnMsg();
							String respAppId = wxPayOrderCloseRespApiPOJO.getAppId();
							String respMchId = wxPayOrderCloseRespApiPOJO.getMchId();
							String respNonceStr = wxPayOrderCloseRespApiPOJO.getNonceStr();
							String respSign = wxPayOrderCloseRespApiPOJO.getSign();
							String respResultCode = wxPayOrderCloseRespApiPOJO.getResultCode();
							String respResultMsg = wxPayOrderCloseRespApiPOJO.getResultMsg();
							String respErrCode = wxPayOrderCloseRespApiPOJO.getErrCode();
							String respErrCodeDes = wxPayOrderCloseRespApiPOJO.getErrCodeDes();
							Date createDateTime = new Date();
							Date lastModifiedDateTime = new Date();
							wpOrderClosePOJO.setAppId(appId);
							wpOrderClosePOJO.setMchId(mchId);
							wpOrderClosePOJO.setOutTradeNo(outTradeNo);
							wpOrderClosePOJO.setNonceStr(nonceStr);
							wpOrderClosePOJO.setSign(sign);
							wpOrderClosePOJO.setSignType(signType);
							wpOrderClosePOJO.setRespReturnCode(respReturnCode);
							wpOrderClosePOJO.setRespReturnMsg(respReturnMsg);
							wpOrderClosePOJO.setRespAppId(respAppId);
							wpOrderClosePOJO.setRespMchId(respMchId);
							wpOrderClosePOJO.setRespNonceStr(respNonceStr);
							wpOrderClosePOJO.setRespSign(respSign);
							wpOrderClosePOJO.setRespResultCode(respResultCode);
							wpOrderClosePOJO.setRespResultMsg(respResultMsg);
							wpOrderClosePOJO.setRespErrCode(respErrCode);
							wpOrderClosePOJO.setRespErrCodeDes(respErrCodeDes);
							wpOrderClosePOJO.setCreateDateTime(createDateTime);
							wpOrderClosePOJO.setLastModifiedDateTime(lastModifiedDateTime);
							wpOrderCloseService.insert(wpOrderClosePOJO);
						}
					}
				}
				logger.info("定期关闭微信订单, thread finish...");
			} catch (Exception e) {
				logger.error("closeWxPayOrder Exception: ", e);
			}
		}
		
	}
}

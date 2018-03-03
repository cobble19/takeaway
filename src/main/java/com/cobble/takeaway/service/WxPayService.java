package com.cobble.takeaway.service;

import java.util.Map;

import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayDownloadBillReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderCloseReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderQueryReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderReverseReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayRefundQueryReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayRefundReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPaySandboxSignKeyReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayShortUrlReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayUnifiedOrderReqApiPOJO;

public interface WxPayService {

	/**
	 * 扫码支付  下单
	 * @throws Exception 
	 */
	Map unifiedOrder(WxPayUnifiedOrderReqApiPOJO wxPayUnifiedOrderReqApiPOJO) throws Exception;

	/**
	 * 
	 * @param wxPayOrderCloseReqApiPOJO
	 * @throws Exception
	 */
	Map orderClose(WxPayOrderCloseReqApiPOJO wxPayOrderCloseReqApiPOJO) throws Exception;

	Map orderQuery(WxPayOrderQueryReqApiPOJO wxPayOrderQueryReqApiPOJO) throws Exception;

	Map orderReverse(WxPayOrderReverseReqApiPOJO wxPayOrderReverseReqApiPOJO) throws Exception;

	/**
	 * 长链接转短链接
	 * @throws Exception 
	 */
	Map shortUrl(WxPayShortUrlReqApiPOJO wxPayShortUrlReqApiPOJO) throws Exception;

	/**
	 * 退款
	 * @throws Exception 
	 */
	Map refund(WxPayRefundReqApiPOJO wxPayRefundReqApiPOJO) throws Exception;

	/**
	 * 查询退款
	 * @throws Exception 
	 */
	Map refundQuery(WxPayRefundQueryReqApiPOJO wxPayRefundQueryReqApiPOJO) throws Exception;

	/**
	 * 对账单下载
	 * 已测试
	 * @throws Exception 
	 */
	void downloadBill(WxPayDownloadBillReqApiPOJO wxPayDownloadBillReqApiPOJO) throws Exception;

	String getSandboxSignKey(WxPaySandboxSignKeyReqApiPOJO wxPaySandboxSignKeyReqApiPOJO) throws Exception;

}
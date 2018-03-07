package com.cobble.takeaway.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayDownloadBillReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderCloseReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderQueryReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderReverseReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayRefundQueryReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayRefundReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPaySandboxSignKeyReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayShortUrlReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayUnifiedOrderReqApiPOJO;
import com.cobble.takeaway.service.WxPayService;
import com.cobble.takeaway.util.XmlUtils;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.MyWXPayConfigImpl;
import com.github.wxpay.sdk.WXPayUtil;

@Service
public class WxPayServiceImpl implements WxPayService {
	private static final Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    private WXPay wxpay;
    private MyWXPayConfigImpl config;
//    private String out_trade_no;
//    private String total_fee;

    public WxPayServiceImpl() throws Exception {
    		init();
    }
    
    private void init() throws Exception {
        config = MyWXPayConfigImpl.getInstance();
        String notifyUrl = "http://www.deweiyizhan.com/api/wxpay/notify";
		wxpay = new WXPay(config, notifyUrl ,false, true);
//        total_fee = "1";
//        // out_trade_no = "201701017496748980290321";
//        out_trade_no = "201802032336010000003433-asd002";
    }

    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#unifiedOrder(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayUnifiedOrderReqApiPOJO)
	 */
    @Override
	public Map unifiedOrder(WxPayUnifiedOrderReqApiPOJO wxPayUnifiedOrderReqApiPOJO) throws Exception {
    		Map<String, String> ret = new HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPayUnifiedOrderReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
        data.put("trade_type", "NATIVE");
        data.put("product_id", "12");*/
        // data.put("time_expire", "20170112104120");

        try {
            ret = wxpay.unifiedOrder(data);
            ret.put("timestamp", System.currentTimeMillis() / 1000 + "");
            logger.info("unifiedOrder result: {}", ret);
        } catch (Exception e) {
            logger.error("unifiedOrder exception: ", e);
        }
        return ret;
    }

    public Map<String, String> appendSign(Map<String, String> reqData) throws Exception {
    		return wxpay.appendSign(reqData);
    }

    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#orderClose(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderCloseReqApiPOJO)
	 */
    @Override
	public Map orderClose(WxPayOrderCloseReqApiPOJO wxPayOrderCloseReqApiPOJO) throws Exception {
        logger.info("关闭订单");
        Map<String, String> ret = new HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPayOrderCloseReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("out_trade_no", out_trade_no);*/
        try {
            ret = wxpay.closeOrder(data);
            logger.info("orderClose result: {}", ret);
        } catch (Exception e) {
            logger.error("orderClose exception: ", e);
        }
        return ret;
    }

    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#orderQuery(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderQueryReqApiPOJO)
	 */
    @Override
	public Map orderQuery(WxPayOrderQueryReqApiPOJO wxPayOrderQueryReqApiPOJO) throws Exception {
        logger.info("查询订单");
        Map<String, String> ret = new HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPayOrderQueryReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("out_trade_no", out_trade_no);*/
//        data.put("transaction_id", "4008852001201608221962061594");
        try {
            ret = wxpay.orderQuery(data);
            logger.info("orderQuery result: {}", ret);
        } catch (Exception e) {
            logger.error("orderQuery exception: ", e);
        }
        return ret;
    }

    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#orderReverse(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderReverseReqApiPOJO)
	 */
    @Override
	public Map orderReverse(WxPayOrderReverseReqApiPOJO wxPayOrderReverseReqApiPOJO) throws Exception {
        System.out.println("撤销");
        Map<String, String> ret = new HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPayOrderReverseReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("out_trade_no", out_trade_no);*/
//        data.put("transaction_id", "4008852001201608221962061594");
        try {
            ret = wxpay.reverse(data);
            logger.info("orderReverse result: {}", ret);
        } catch (Exception e) {
            logger.error("orderReverse exception: ", e);
        }
        return ret;
    }



    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#shortUrl(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayShortUrlReqApiPOJO)
	 */
    @Override
	public Map shortUrl(WxPayShortUrlReqApiPOJO wxPayShortUrlReqApiPOJO) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
//        String long_url = "weixin://wxpay/bizpayurl?pr=etxB4DY";
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPayShortUrlReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("long_url", long_url);*/
        try {
            ret = wxpay.shortUrl(data);
            logger.info("shortUrl result: {}", ret);
        } catch (Exception e) {
            logger.error("shortUrl exception: ", e);
        }
        return ret;
    }

    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#refund(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayRefundReqApiPOJO)
	 */
    @Override
	public Map refund(WxPayRefundReqApiPOJO wxPayRefundReqApiPOJO) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPayRefundReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("refund_fee", total_fee);
        data.put("refund_fee_type", "CNY");
        data.put("op_user_id", config.getMchID());*/

        try {
            ret = wxpay.refund(data);
            logger.info("refund result: {}", ret);
        } catch (Exception e) {
            logger.error("refund exception: ", e);
        }
        return ret;
    }

    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#refundQuery(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayRefundQueryReqApiPOJO)
	 */
    @Override
	public Map refundQuery(WxPayRefundQueryReqApiPOJO wxPayRefundQueryReqApiPOJO) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPayRefundQueryReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("out_refund_no", out_trade_no);*/
        try {
            ret = wxpay.refundQuery(data);
            logger.info("refundQuery result: {}", ret);
        } catch (Exception e) {
            logger.error("refundQuery exception: ", e);
        }
        return ret;
    }

    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#downloadBill(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayDownloadBillReqApiPOJO)
	 */
    @Override
	public void downloadBill(WxPayDownloadBillReqApiPOJO wxPayDownloadBillReqApiPOJO) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPayDownloadBillReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("bill_date", "20161102");
        data.put("bill_type", "ALL");*/
        try {
            ret = wxpay.downloadBill(data);
            logger.info("downloadBill result: {}", ret);
        } catch (Exception e) {
            logger.error("downloadBill exception: ", e);
        }
    }

    /* (non-Javadoc)
	 * @see com.cobble.takeaway.service.wxpay.impl.WxPayService#getSandboxSignKey(com.cobble.takeaway.pojo.weixin.wxpay.api.WxPaySandboxSignKeyReqApiPOJO)
	 */
    @Override
	public String getSandboxSignKey(WxPaySandboxSignKeyReqApiPOJO wxPaySandboxSignKeyReqApiPOJO) throws Exception {
    		String ret = new String();
//    		MyWXPayConfigImpl config = MyWXPayConfigImpl.getInstance();
        HashMap<String, String> data = new HashMap<String, String>();
        String xml = XmlUtils.convertToXml(wxPaySandboxSignKeyReqApiPOJO);
        data = (HashMap<String, String>) WXPayUtil.xmlToMap(xml);
        /*data.put("mch_id", config.getMchID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());*/
        String sign = WXPayUtil.generateSignature(data, config.getKey());
        data.put("sign", sign);
//        WXPay wxPay = new WXPay(config);
        ret = wxpay.requestWithoutCert("https://api.mch.weixin.qq.com/sandbox/pay/getsignkey", data, 10000, 10000);
        logger.info("downloadBill result: {}", ret);
        return ret;
    }
}

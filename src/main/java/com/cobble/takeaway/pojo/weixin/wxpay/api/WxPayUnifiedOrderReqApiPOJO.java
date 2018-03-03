package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayUnifiedOrderReqApiPOJO extends BasePOJO {
	/*data.put("body", "腾讯充值中心-QQ会员充值");
    data.put("out_trade_no", out_trade_no);
    data.put("device_info", "");
    data.put("fee_type", "CNY");
    data.put("total_fee", "1");
    data.put("spbill_create_ip", "123.12.12.123");
    data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
    data.put("trade_type", "NATIVE");
    data.put("product_id", "12");*/
	@XmlElement(name="appid")
	private String appId;
	@XmlElement(name="mch_id")
	private String mchId;
	@XmlElement(name="device_info")
	private String deviceInfo;
	@XmlElement(name="nonce_str")
	private String nonceStr;
	@XmlElement(name="sign")
	private String sign;
	@XmlElement(name="sign_type")
	private String signType;
	@XmlElement(name="body")
	private String body;
	@XmlElement(name="detail")
	private String detail;
	@XmlElement(name="attach")
	private String attach;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="fee_type")
	private String feeType;
	@XmlElement(name="total_fee")
	private String totalFee;
	@XmlElement(name="spbill_create_ip")
	private String spbillCreateIp;
	@XmlElement(name="time_start")
	private String timeStart;
	@XmlElement(name="time_expire")
	private String timeExpire;
	@XmlElement(name="goods_tag")
	private String goodsTag;
	@XmlElement(name="notify_url")
	private String notifyUrl;
	@XmlElement(name="trade_type")
	private String tradeType;
	@XmlElement(name="product_id")
	private String productId;
	@XmlElement(name="limit_pay")
	private String limitPay;
	@XmlElement(name="openid")
	private String openId;
	@XmlElement(name="scene_info")
	private WxPaySceneInfoPOJO sceneInfo;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeExpire() {
		return timeExpire;
	}
	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}
	public String getGoodsTag() {
		return goodsTag;
	}
	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getLimitPay() {
		return limitPay;
	}
	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public WxPaySceneInfoPOJO getSceneInfo() {
		return sceneInfo;
	}
	public void setSceneInfo(WxPaySceneInfoPOJO sceneInfo) {
		this.sceneInfo = sceneInfo;
	}
	
}

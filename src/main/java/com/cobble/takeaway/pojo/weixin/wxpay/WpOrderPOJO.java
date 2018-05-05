package com.cobble.takeaway.pojo.weixin.wxpay;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class WpOrderPOJO extends BasePOJO {
	private Long wpOrderId;
	private String appId;
	private String mchId;
	private String deviceInfo;
	private String nonceStr;
	private String sign;
	private String signType;
	private String body;
	private String detail;
	private String attach;
	private String outTradeNo;
	private String feeType;
	private Integer totalFee;
	private String spbillCreateIp;
	// yyyyMMddHHmmssSSS
	private String timeStart;
	private String timeExpire;
	private String goodsTag;
	private String notifyUrl;
	private String tradeType;
	private String productId;
	private String limitPay;
	private String openId;
	private String sceneInfo;
	private String respReturnCode;
	private String respReturnMsg;
	private String respAppId;
	private String respMchId;
	private String respDeviceInfo;
	private String respNonceStr;
	private String respSign;
	private String respResultCode;
	private String respErrCode;
	private String respErrCodeDes;
	private String respTradeType;
	private String respPrepayId;
	private String respCodeUrl;
	private Date createDateTime;

	private String respBankType;
	private String respCashFee;
	private String respTotalFee;
	private String respFeeType;
	private String respIsSubscribe;
	private String respTimeEnd;
	private String respTransactionId;
	
	private Long ecOrderId;
	
	private WpOrderClosePOJO wpOrderClosePOJO;
	
	public WpOrderClosePOJO getWpOrderClosePOJO() {
		return wpOrderClosePOJO;
	}
	public void setWpOrderClosePOJO(WpOrderClosePOJO wpOrderClosePOJO) {
		this.wpOrderClosePOJO = wpOrderClosePOJO;
	}
	public Long getEcOrderId() {
		return ecOrderId;
	}
	public void setEcOrderId(Long ecOrderId) {
		this.ecOrderId = ecOrderId;
	}
	public String getRespBankType() {
		return respBankType;
	}
	public void setRespBankType(String respBankType) {
		this.respBankType = respBankType;
	}
	public String getRespCashFee() {
		return respCashFee;
	}
	public void setRespCashFee(String respCashFee) {
		this.respCashFee = respCashFee;
	}
	public String getRespTotalFee() {
		return respTotalFee;
	}
	public void setRespTotalFee(String respTotalFee) {
		this.respTotalFee = respTotalFee;
	}
	public String getRespFeeType() {
		return respFeeType;
	}
	public void setRespFeeType(String respFeeType) {
		this.respFeeType = respFeeType;
	}
	public String getRespIsSubscribe() {
		return respIsSubscribe;
	}
	public void setRespIsSubscribe(String respIsSubscribe) {
		this.respIsSubscribe = respIsSubscribe;
	}
	public String getRespTimeEnd() {
		return respTimeEnd;
	}
	public void setRespTimeEnd(String respTimeEnd) {
		this.respTimeEnd = respTimeEnd;
	}
	public String getRespTransactionId() {
		return respTransactionId;
	}
	public void setRespTransactionId(String respTransactionId) {
		this.respTransactionId = respTransactionId;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Long getWpOrderId() {
		return wpOrderId;
	}
	public void setWpOrderId(Long wpOrderId) {
		this.wpOrderId = wpOrderId;
	}
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
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
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
	public String getSceneInfo() {
		return sceneInfo;
	}
	public void setSceneInfo(String sceneInfo) {
		this.sceneInfo = sceneInfo;
	}
	public String getRespReturnCode() {
		return respReturnCode;
	}
	public void setRespReturnCode(String respReturnCode) {
		this.respReturnCode = respReturnCode;
	}
	public String getRespReturnMsg() {
		return respReturnMsg;
	}
	public void setRespReturnMsg(String respReturnMsg) {
		this.respReturnMsg = respReturnMsg;
	}
	public String getRespAppId() {
		return respAppId;
	}
	public void setRespAppId(String respAppId) {
		this.respAppId = respAppId;
	}
	public String getRespMchId() {
		return respMchId;
	}
	public void setRespMchId(String respMchId) {
		this.respMchId = respMchId;
	}
	public String getRespDeviceInfo() {
		return respDeviceInfo;
	}
	public void setRespDeviceInfo(String respDeviceInfo) {
		this.respDeviceInfo = respDeviceInfo;
	}
	public String getRespNonceStr() {
		return respNonceStr;
	}
	public void setRespNonceStr(String respNonceStr) {
		this.respNonceStr = respNonceStr;
	}
	public String getRespSign() {
		return respSign;
	}
	public void setRespSign(String respSign) {
		this.respSign = respSign;
	}
	public String getRespResultCode() {
		return respResultCode;
	}
	public void setRespResultCode(String respResultCode) {
		this.respResultCode = respResultCode;
	}
	public String getRespErrCode() {
		return respErrCode;
	}
	public void setRespErrCode(String respErrCode) {
		this.respErrCode = respErrCode;
	}
	public String getRespErrCodeDes() {
		return respErrCodeDes;
	}
	public void setRespErrCodeDes(String respErrCodeDes) {
		this.respErrCodeDes = respErrCodeDes;
	}
	public String getRespTradeType() {
		return respTradeType;
	}
	public void setRespTradeType(String respTradeType) {
		this.respTradeType = respTradeType;
	}
	public String getRespPrepayId() {
		return respPrepayId;
	}
	public void setRespPrepayId(String respPrepayId) {
		this.respPrepayId = respPrepayId;
	}
	public String getRespCodeUrl() {
		return respCodeUrl;
	}
	public void setRespCodeUrl(String respCodeUrl) {
		this.respCodeUrl = respCodeUrl;
	}
	
}

package com.cobble.takeaway.pojo.weixin.wxpay;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class WpResultNotifyPOJO extends BasePOJO {
	private String rawData;
	
	private String returnCode;
	private String returnMsg;

	private String appId;
	private String mchId;
	private String deviceInfo;
	private String nonceStr;
	private String sign;
	private String signType;
	private String resultCode;
	private String errCode;
	private String errCodeDes;
	private String openID;
	private String isSubscribe;
	private String tradeType;
	private String bankType;
	private Integer totalFee;
	private Integer settlementTotalFee;
	private String feeType;
	private Integer cashFee;
	private String cashFeeType;
	private Integer couponFee;
	private Integer couponCount;
	private String couponType0;
	private String couponId0;
	private Integer couponFee0;
	private String couponType1;
	private String couponId1;
	private Integer couponFee1;
	private String transactionId;
	private String outTradeNo;
	private String attach;
	private String timeEnd;
	private Date createDateTime;
	private Date lastModifiedDateTime;
	
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}
	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}
	public String getRawData() {
		return rawData;
	}
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
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
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	public Integer getSettlementTotalFee() {
		return settlementTotalFee;
	}
	public void setSettlementTotalFee(Integer settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Integer getCashFee() {
		return cashFee;
	}
	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	public Integer getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}
	public Integer getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}
	public String getCouponType0() {
		return couponType0;
	}
	public void setCouponType0(String couponType0) {
		this.couponType0 = couponType0;
	}
	public String getCouponId0() {
		return couponId0;
	}
	public void setCouponId0(String couponId0) {
		this.couponId0 = couponId0;
	}
	public Integer getCouponFee0() {
		return couponFee0;
	}
	public void setCouponFee0(Integer couponFee0) {
		this.couponFee0 = couponFee0;
	}
	public String getCouponType1() {
		return couponType1;
	}
	public void setCouponType1(String couponType1) {
		this.couponType1 = couponType1;
	}
	public String getCouponId1() {
		return couponId1;
	}
	public void setCouponId1(String couponId1) {
		this.couponId1 = couponId1;
	}
	public Integer getCouponFee1() {
		return couponFee1;
	}
	public void setCouponFee1(Integer couponFee1) {
		this.couponFee1 = couponFee1;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	
}

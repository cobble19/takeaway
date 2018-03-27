package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayResultNotifyApiPOJO extends BasePOJO {
	@XmlElement(name="return_code")
	private String returnCode;
	@XmlElement(name="return_msg")
	private String returnMsg;

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
	@XmlElement(name="result_code")
	private String resultCode;
	@XmlElement(name="err_code")
	private String errCode;
	@XmlElement(name="err_code_des")
	private String errCodeDes;
	@XmlElement(name="openid")
	private String openID;
	@XmlElement(name="is_subscribe")
	private String isSubscribe;
	@XmlElement(name="trade_type")
	private String tradeType;
	@XmlElement(name="bank_type")
	private String bankType;
	@XmlElement(name="total_fee")
	private String totalFee;
	@XmlElement(name="settlement_total_fee")
	private String settlementTotalFee;
	@XmlElement(name="fee_type")
	private String feeType;
	@XmlElement(name="cash_fee")
	private String cashFee;
	@XmlElement(name="cash_fee_type")
	private String cashFeeType;
	@XmlElement(name="coupon_fee")
	private String couponFee;
	@XmlElement(name="coupon_count")
	private String couponCount;
	@XmlElement(name="coupon_type_0")
	private String couponType0;
	@XmlElement(name="coupon_id_0")
	private String couponId0;
	@XmlElement(name="coupon_fee_0")
	private String couponFee0;
	@XmlElement(name="coupon_type_1")
	private String couponType1;
	@XmlElement(name="coupon_id_1")
	private String couponId1;
	@XmlElement(name="coupon_fee_1")
	private String couponFee1;
	@XmlElement(name="transaction_id")
	private String transactionId;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="attach")
	private String attach;
	@XmlElement(name="time_end")
	private String timeEnd;
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
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getSettlementTotalFee() {
		return settlementTotalFee;
	}
	public void setSettlementTotalFee(String settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getCashFee() {
		return cashFee;
	}
	public void setCashFee(String cashFee) {
		this.cashFee = cashFee;
	}
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	public String getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(String couponFee) {
		this.couponFee = couponFee;
	}
	public String getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(String couponCount) {
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
	public String getCouponFee0() {
		return couponFee0;
	}
	public void setCouponFee0(String couponFee0) {
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
	public String getCouponFee1() {
		return couponFee1;
	}
	public void setCouponFee1(String couponFee1) {
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

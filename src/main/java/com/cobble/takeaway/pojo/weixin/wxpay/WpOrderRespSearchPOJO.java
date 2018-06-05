package com.cobble.takeaway.pojo.weixin.wxpay;

import com.cobble.takeaway.pojo.BasePOJO;
import com.cobble.takeaway.pojo.BaseSearchPOJO;

import java.util.Date;

public class WpOrderRespSearchPOJO extends BaseSearchPOJO {
	private Long wpOrderRespId;

	private String returnCode;
	private String returnMsg;

	private String appId;
	private String mchId;
	private String deviceInfo;
	private String nonceStr;
	private String sign;
	private String resultCode;
	private String errCode;
	private String errCodeDes;
	private String tradeType;
	private String prepayId;
	private String codeUrl;

	private Long wpOrderId;
	private String outTradeNo;
	private String openId;
	private Long ecOrderId;
	private Long ecProductId;

	private Date createDateTime;
	private Date lastModifiedDateTime;

	public Long getWpOrderRespId() {
		return wpOrderRespId;
	}

	public void setWpOrderRespId(Long wpOrderRespId) {
		this.wpOrderRespId = wpOrderRespId;
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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public Long getWpOrderId() {
		return wpOrderId;
	}

	public void setWpOrderId(Long wpOrderId) {
		this.wpOrderId = wpOrderId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Long getEcOrderId() {
		return ecOrderId;
	}

	public void setEcOrderId(Long ecOrderId) {
		this.ecOrderId = ecOrderId;
	}

	public Long getEcProductId() {
		return ecProductId;
	}

	public void setEcProductId(Long ecProductId) {
		this.ecProductId = ecProductId;
	}

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
}

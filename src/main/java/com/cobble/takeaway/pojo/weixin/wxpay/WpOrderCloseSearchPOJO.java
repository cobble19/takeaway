package com.cobble.takeaway.pojo.weixin.wxpay;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;
import com.cobble.takeaway.pojo.BaseSearchPOJO;

public class WpOrderCloseSearchPOJO extends BaseSearchPOJO {
	private Long wpOrderCloseId;
	private String appId;
	private String mchId;
	private String outTradeNo;
	private String nonceStr;
	private String sign;
	private String signType;
	private String respReturnCode;
	private String respReturnMsg;
	private String respAppId;
	private String respMchId;
	private String respNonceStr;
	private String respSign;
	private String respResultCode;
	private String respResultMsg;
	private String respErrCode;
	private String respErrCodeDes;
	private Date createDateTime;
	private Date lastModifiedDateTime;
	
	public Long getWpOrderCloseId() {
		return wpOrderCloseId;
	}
	public void setWpOrderCloseId(Long wpOrderCloseId) {
		this.wpOrderCloseId = wpOrderCloseId;
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
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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
	public String getRespResultMsg() {
		return respResultMsg;
	}
	public void setRespResultMsg(String respResultMsg) {
		this.respResultMsg = respResultMsg;
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

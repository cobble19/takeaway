package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayOrderQueryReqApiPOJO extends BasePOJO {
	/*data.put("out_trade_no", out_trade_no);*/
	@XmlElement(name="appid")
	private String appId;
	@XmlElement(name="mch_id")
	private String mchId;
	@XmlElement(name="transaction_id")
	private String transactionId;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="nonce_str")
	private String nonceStr;
	@XmlElement(name="sign")
	private String sign;
	@XmlElement(name="sign_type")
	private String signType;
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
	
}

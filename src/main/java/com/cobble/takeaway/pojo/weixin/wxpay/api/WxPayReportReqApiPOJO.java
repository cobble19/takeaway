package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayReportReqApiPOJO extends BasePOJO {
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
	@XmlElement(name="interface_url")
	private String interfaceUrl;
	@XmlElement(name="execute_time")
	private String executeTime;
	@XmlElement(name="return_code")
	private String returnCode;
	@XmlElement(name="return_msg")
	private String returnMsg;
	@XmlElement(name="result_code")
	private String resultCode;
	@XmlElement(name="err_code")
	private String errCode;
	@XmlElement(name="err_code_des")
	private String errCodeDes;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="user_ip")
	private String userIp;
	@XmlElement(name="time")
	private String time;
	
}

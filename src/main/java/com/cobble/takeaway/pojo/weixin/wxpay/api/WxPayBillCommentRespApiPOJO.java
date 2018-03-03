package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayBillCommentRespApiPOJO extends BasePOJO {
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
}

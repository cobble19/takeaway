package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayBillCommentReqApiPOJO extends BasePOJO {
	/*data.put("bill_date", "20161102");
        data.put("bill_type", "ALL");*/
	@XmlElement(name="appid")
	private String appId;
	@XmlElement(name="mch_id")
	private String mchId;
	@XmlElement(name="nonce_str")
	private String nonceStr;
	@XmlElement(name="sign")
	private String sign;
	@XmlElement(name="sign_type")
	private String signType;
	@XmlElement(name="begin_time")
	private String beginTime;
	@XmlElement(name="end_time")
	private String endTime;
	@XmlElement(name="offset")
	private String offset;
	@XmlElement(name="limit")
	private String limt;
	
}

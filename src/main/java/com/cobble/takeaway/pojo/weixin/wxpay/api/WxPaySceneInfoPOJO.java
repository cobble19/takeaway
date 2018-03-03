package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlElement;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxPaySceneInfoPOJO extends BasePOJO {
	@XmlElement(name="id")
	private String id;
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="area_code")
	private String areaCode;
	@XmlElement(name="address")
	private String address;
}

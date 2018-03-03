package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayOrderReverseReqApiPOJO extends BasePOJO {
	/*data.put("out_trade_no", out_trade_no);*/
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	
}

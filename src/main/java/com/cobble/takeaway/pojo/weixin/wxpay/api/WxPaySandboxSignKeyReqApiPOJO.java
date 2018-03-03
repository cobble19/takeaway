package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;
import com.github.wxpay.sdk.WXPayUtil;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPaySandboxSignKeyReqApiPOJO extends BasePOJO {
	/*data.put("mch_id", config.getMchID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());*/
	@XmlElement(name="mch_id")
	private String mchId;
	@XmlElement(name="nonce_str")
	private String nonceStr;
	
}

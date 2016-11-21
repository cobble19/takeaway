package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxTagsMgrRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="tag")
    private WxTagsMgrTagRespApiPOJO tag = new WxTagsMgrTagRespApiPOJO();

	public WxTagsMgrTagRespApiPOJO getTag() {
		return tag;
	}

	public void setTag(WxTagsMgrTagRespApiPOJO tag) {
		this.tag = tag;
	}

	
}
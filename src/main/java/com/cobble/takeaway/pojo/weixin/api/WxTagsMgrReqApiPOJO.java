package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxTagsMgrReqApiPOJO extends BasePOJO {
	@JsonProperty(value="tag")
    private WxTagsMgrTagReqApiPOJO tag;

	public WxTagsMgrTagReqApiPOJO getTag() {
		return tag;
	}

	public void setTag(WxTagsMgrTagReqApiPOJO tag) {
		this.tag = tag;
	}


	
}
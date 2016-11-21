package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxTagsMgrUpateReqApiPOJO extends BasePOJO {
	@JsonProperty(value="tag")
    private WxTagsMgrTagUpdateReqApiPOJO tag;

	public WxTagsMgrTagUpdateReqApiPOJO getTag() {
		return tag;
	}

	public void setTag(WxTagsMgrTagUpdateReqApiPOJO tag) {
		this.tag = tag;
	}


	
}
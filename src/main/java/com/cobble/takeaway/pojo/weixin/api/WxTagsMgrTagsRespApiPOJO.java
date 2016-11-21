package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxTagsMgrTagsRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="tags")
    private List<WxTagsMgrTagRespApiPOJO> tags = new ArrayList<WxTagsMgrTagRespApiPOJO>();

	public List<WxTagsMgrTagRespApiPOJO> getTags() {
		return tags;
	}

	public void setTags(List<WxTagsMgrTagRespApiPOJO> tags) {
		this.tags = tags;
	}

	
}
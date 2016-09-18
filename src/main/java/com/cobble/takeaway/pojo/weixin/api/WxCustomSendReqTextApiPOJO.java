package com.cobble.takeaway.pojo.weixin.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WxCustomSendReqTextApiPOJO {
	@JsonProperty(value="content")
    private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}

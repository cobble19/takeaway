package com.cobble.takeaway.pojo.weixin.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WxUserInfoBatchGetReqApiPOJO {
	@JsonProperty(value="openid")
    private String openId;
	@JsonProperty(value="lang")
    private String lang;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
}

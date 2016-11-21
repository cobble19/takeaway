package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxTagsMgrUserOpenIdRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="openid")
    private List<String> openId = new ArrayList<String>();

	public List<String> getOpenId() {
		return openId;
	}

	public void setOpenId(List<String> openId) {
		this.openId = openId;
	}

	
}
package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxCardCodeDecryptRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="code")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
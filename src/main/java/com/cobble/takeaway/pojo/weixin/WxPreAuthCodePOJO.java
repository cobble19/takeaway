package com.cobble.takeaway.pojo.weixin;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxPreAuthCodePOJO extends BasePOJO {
	@JsonProperty(value="pre_auth_code")
    private String preAuthCode;
	@JsonProperty(value="expires_in")
    private Integer expiresIn;
	
	public String getPreAuthCode() {
		return preAuthCode;
	}
	public void setPreAuthCode(String preAuthCode) {
		this.preAuthCode = preAuthCode;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

}
package com.cobble.takeaway.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxComAccessTokenPOJO extends BasePOJO {
	@JsonProperty(value="component_access_token")
    private String componentAccessToken;
	@JsonProperty(value="expires_in")
    private Integer expiresIn;
	
	public String getComponentAccessToken() {
		return componentAccessToken;
	}
	public void setComponentAccessToken(String componentAccessToken) {
		this.componentAccessToken = componentAccessToken;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

}
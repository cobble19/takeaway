package com.cobble.takeaway.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerAccessTokenReqPOJO extends BasePOJO {
	@JsonProperty(value="component_appid")
    private String componentAppId;
	@JsonProperty(value="authorization_code")
    private String authorizationCode;
	
	public String getComponentAppId() {
		return componentAppId;
	}
	public void setComponentAppId(String componentAppId) {
		this.componentAppId = componentAppId;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
    

}
package com.cobble.takeaway.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerInfoReqPOJO extends BasePOJO {
	@JsonProperty(value="component_appid")
    private String componentAppId;
	@JsonProperty(value="authorizer_appid")
    private String authorizerAppId;
	
	public String getComponentAppId() {
		return componentAppId;
	}
	public void setComponentAppId(String componentAppId) {
		this.componentAppId = componentAppId;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}

}
package com.cobble.takeaway.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxComAccessTokenReqPOJO extends BasePOJO {
	@JsonProperty(value="component_appid")
    private String componentAppId;
	@JsonProperty(value="component_appsecret")
    private String componentAppSecret;
	@JsonProperty(value="component_verify_ticket")
    private String componentVerifyTicket;
	
	public String getComponentAppId() {
		return componentAppId;
	}
	public void setComponentAppId(String componentAppId) {
		this.componentAppId = componentAppId;
	}
	public String getComponentAppSecret() {
		return componentAppSecret;
	}
	public void setComponentAppSecret(String componentAppSecret) {
		this.componentAppSecret = componentAppSecret;
	}
	public String getComponentVerifyTicket() {
		return componentVerifyTicket;
	}
	public void setComponentVerifyTicket(String componentVerifyTicket) {
		this.componentVerifyTicket = componentVerifyTicket;
	}
    

}
package com.cobble.takeaway.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxPreAuthCodeReqPOJO extends BasePOJO {
	@JsonProperty(value="component_appid")
    private String componentAppId;
	
	public String getComponentAppId() {
		return componentAppId;
	}
	public void setComponentAppId(String componentAppId) {
		this.componentAppId = componentAppId;
	}
    

}
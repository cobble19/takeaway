package com.cobble.takeaway.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxComAccessTokenSearchPOJO extends BaseSearchPOJO {
	private Long wxComAccessTokenId;
	
	@JsonProperty(value="component_access_token")
    private String componentAccessToken;
	@JsonProperty(value="expires_in")
    private Integer expiresIn;
	
	private Date createDateTime;
	
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
	public Long getWxComAccessTokenId() {
		return wxComAccessTokenId;
	}
	public void setWxComAccessTokenId(Long wxComAccessTokenId) {
		this.wxComAccessTokenId = wxComAccessTokenId;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

}
package com.cobble.takeaway.pojo.weixin.api;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxComAccessTokenApiPOJO extends BasePOJO {
	private Long wxComAccessTokenId;
	
	@JsonProperty(value="component_access_token")
    private String componentAccessToken;
	@JsonProperty(value="expires_in")
    private Integer expiresIn;

	private Long wxComVerifyTicketId;
	private String appId;
	private Date createDateTime;
	private Date lastModifiedDateTime;

	public Long getWxComVerifyTicketId() {
		return wxComVerifyTicketId;
	}

	public void setWxComVerifyTicketId(Long wxComVerifyTicketId) {
		this.wxComVerifyTicketId = wxComVerifyTicketId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

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
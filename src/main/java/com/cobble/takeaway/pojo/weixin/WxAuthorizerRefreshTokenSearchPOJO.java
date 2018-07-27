package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BaseSearchPOJO;


public class WxAuthorizerRefreshTokenSearchPOJO extends BaseSearchPOJO {
	private Long wxAuthorizerRefreshTokenId;
    private String componentAppId;
    private String authorizerAppId;
	
    private String authorizerAccessToken;
    private Integer expiresIn;
    private String authorizerRefreshToken;
    private Date createDateTime;
	private Date lastModifiedDateTime;

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

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
	public String getAuthorizerAccessToken() {
		return authorizerAccessToken;
	}
	public void setAuthorizerAccessToken(String authorizerAccessToken) {
		this.authorizerAccessToken = authorizerAccessToken;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getAuthorizerRefreshToken() {
		return authorizerRefreshToken;
	}
	public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
		this.authorizerRefreshToken = authorizerRefreshToken;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Long getWxAuthorizerRefreshTokenId() {
		return wxAuthorizerRefreshTokenId;
	}
	public void setWxAuthorizerRefreshTokenId(Long wxAuthorizerRefreshTokenId) {
		this.wxAuthorizerRefreshTokenId = wxAuthorizerRefreshTokenId;
	}
	
}
package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BaseSearchPOJO;


public class WxAuthorizerAccessTokenSearchPOJO extends BaseSearchPOJO {
	private Long wxAuthorizerAccessTokenId;
    private String authorizerAppId;
    private String authorizerAccessToken;
    private Integer expiresIn;
    private String authorizerRefreshToken;
	private String funcInfo;
	private Date createDateTime;
	
	public Long getWxAuthorizerAccessTokenId() {
		return wxAuthorizerAccessTokenId;
	}
	public void setWxAuthorizerAccessTokenId(Long wxAuthorizerAccessTokenId) {
		this.wxAuthorizerAccessTokenId = wxAuthorizerAccessTokenId;
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
	public String getFuncInfo() {
		return funcInfo;
	}
	public void setFuncInfo(String funcInfo) {
		this.funcInfo = funcInfo;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
}
package com.cobble.takeaway.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerRefreshTokenPOJO extends BasePOJO {
	@JsonProperty(value="authorizer_access_token")
    private String authorizerAccessToken;
	@JsonProperty(value="expires_in")
    private Integer expiresIn;
	@JsonProperty(value="authorizer_refresh_token")
    private String authorizerRefreshToken;
	
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
	
}
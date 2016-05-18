package com.cobble.takeaway.pojo.weixin;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationInfoPOJO extends BasePOJO {
	@JsonProperty(value="authorizer_appid")
    private String authorizerAppId;
	@JsonProperty(value="authorizer_access_token")
    private String authorizerAccessToken;
	@JsonProperty(value="expires_in")
    private Integer expiresIn;
	@JsonProperty(value="authorizer_refresh_token")
    private String authorizerRefreshToken;
	@JsonProperty(value="func_info")
    private List<FuncInfoPOJO> funcInfoPOJOList = new ArrayList<FuncInfoPOJO>();
	
	public AuthorizationInfoPOJO() {
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
	public List<FuncInfoPOJO> getFuncInfoPOJOList() {
		return funcInfoPOJOList;
	}
	public void setFuncInfoPOJOList(List<FuncInfoPOJO> funcInfoPOJOList) {
		this.funcInfoPOJOList = funcInfoPOJOList;
	}
}
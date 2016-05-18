package com.cobble.takeaway.pojo;

import com.cobble.takeaway.pojo.weixin.AuthorizationInfoPOJO;
import com.cobble.takeaway.pojo.weixin.AuthorizerInfoPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerInfoPOJO extends BasePOJO {
	@JsonProperty(value="authorizer_info")
    private AuthorizerInfoPOJO authorizerInfoPOJO;
	@JsonProperty(value="qrcode_url")
    private String qrcodeUrl;
	@JsonProperty(value="authorization_info")
    private AuthorizationInfoPOJO authorizationInfo;
	

	public AuthorizerInfoPOJO getAuthorizerInfoPOJO() {
		return authorizerInfoPOJO;
	}

	public void setAuthorizerInfoPOJO(AuthorizerInfoPOJO authorizerInfoPOJO) {
		this.authorizerInfoPOJO = authorizerInfoPOJO;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	public AuthorizationInfoPOJO getAuthorizationInfo() {
		return authorizationInfo;
	}

	public void setAuthorizationInfo(AuthorizationInfoPOJO authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}

}
package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerInfoPOJO extends BasePOJO {
	@JsonProperty(value="authorizer_info")
    private AuthorizerInfoPOJO authorizerInfoPOJO;
	@JsonProperty(value="authorization_info")
    private AuthorizationInfo4AuthzerPOJO authorizationInfo4AuthzerPOJO;
	
	public AuthorizerInfoPOJO getAuthorizerInfoPOJO() {
		return authorizerInfoPOJO;
	}

	public void setAuthorizerInfoPOJO(AuthorizerInfoPOJO authorizerInfoPOJO) {
		this.authorizerInfoPOJO = authorizerInfoPOJO;
	}

	public AuthorizationInfo4AuthzerPOJO getAuthorizationInfo4AuthzerPOJO() {
		return authorizationInfo4AuthzerPOJO;
	}

	public void setAuthorizationInfo4AuthzerPOJO(
			AuthorizationInfo4AuthzerPOJO authorizationInfo4AuthzerPOJO) {
		this.authorizationInfo4AuthzerPOJO = authorizationInfo4AuthzerPOJO;
	}

}
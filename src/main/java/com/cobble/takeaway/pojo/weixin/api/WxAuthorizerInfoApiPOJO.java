package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerInfoApiPOJO extends BasePOJO {
	@JsonProperty(value="authorizer_info")
    private AuthorizerInfoApiPOJO authorizerInfoPOJO;
	@JsonProperty(value="authorization_info")
    private AuthorizationInfo4AuthzerApiPOJO authorizationInfo4AuthzerPOJO;
	
	public AuthorizerInfoApiPOJO getAuthorizerInfoPOJO() {
		return authorizerInfoPOJO;
	}

	public void setAuthorizerInfoPOJO(AuthorizerInfoApiPOJO authorizerInfoPOJO) {
		this.authorizerInfoPOJO = authorizerInfoPOJO;
	}

	public AuthorizationInfo4AuthzerApiPOJO getAuthorizationInfo4AuthzerPOJO() {
		return authorizationInfo4AuthzerPOJO;
	}

	public void setAuthorizationInfo4AuthzerPOJO(
			AuthorizationInfo4AuthzerApiPOJO authorizationInfo4AuthzerPOJO) {
		this.authorizationInfo4AuthzerPOJO = authorizationInfo4AuthzerPOJO;
	}

}
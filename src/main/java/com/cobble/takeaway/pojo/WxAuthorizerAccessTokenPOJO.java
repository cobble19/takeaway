package com.cobble.takeaway.pojo;

import com.cobble.takeaway.pojo.weixin.AuthorizationInfoPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerAccessTokenPOJO extends BasePOJO {
	@JsonProperty(value="authorization_info")
    private AuthorizationInfoPOJO authorizationInfoPOJO;

	public AuthorizationInfoPOJO getAuthorizationInfoPOJO() {
		return authorizationInfoPOJO;
	}

	public void setAuthorizationInfoPOJO(AuthorizationInfoPOJO authorizationInfoPOJO) {
		this.authorizationInfoPOJO = authorizationInfoPOJO;
	}

}
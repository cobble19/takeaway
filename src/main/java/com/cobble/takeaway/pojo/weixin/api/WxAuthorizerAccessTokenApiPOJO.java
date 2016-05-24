package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerAccessTokenApiPOJO extends BasePOJO {
	@JsonProperty(value="authorization_info")
    private AuthorizationInfoApiPOJO authorizationInfoPOJO;

	public AuthorizationInfoApiPOJO getAuthorizationInfoPOJO() {
		return authorizationInfoPOJO;
	}

	public void setAuthorizationInfoPOJO(AuthorizationInfoApiPOJO authorizationInfoPOJO) {
		this.authorizationInfoPOJO = authorizationInfoPOJO;
	}

}
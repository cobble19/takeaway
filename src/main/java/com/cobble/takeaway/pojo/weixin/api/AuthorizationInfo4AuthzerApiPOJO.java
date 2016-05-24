package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationInfo4AuthzerApiPOJO extends BasePOJO {
	@JsonProperty(value="authorizer_appid")
    private String authorizerAppId;
	@JsonProperty(value="func_info")
    private List<FuncInfoApiPOJO> funcInfoPOJOList;

	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	
	public List<FuncInfoApiPOJO> getFuncInfoPOJOList() {
		return funcInfoPOJOList;
	}
	public void setFuncInfoPOJOList(List<FuncInfoApiPOJO> funcInfoPOJOList) {
		this.funcInfoPOJOList = funcInfoPOJOList;
	}
}

package com.cobble.takeaway.pojo.weixin;

import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationInfo4AuthzerPOJO extends BasePOJO {
	@JsonProperty(value="authorizer_appid")
    private String authorizerAppId;
	@JsonProperty(value="func_info")
    private List<FuncInfoPOJO> funcInfoPOJOList;

	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	
	public List<FuncInfoPOJO> getFuncInfoPOJOList() {
		return funcInfoPOJOList;
	}
	public void setFuncInfoPOJOList(List<FuncInfoPOJO> funcInfoPOJOList) {
		this.funcInfoPOJOList = funcInfoPOJOList;
	}
}

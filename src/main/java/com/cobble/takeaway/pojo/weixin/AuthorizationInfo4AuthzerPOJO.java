package com.cobble.takeaway.pojo.weixin;

import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationInfo4AuthzerPOJO extends BasePOJO {
	@JsonProperty(value="appid")
    private String appId;
	@JsonProperty(value="func_info")
    private List<FuncInfoPOJO> funcInfoPOJOList;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public List<FuncInfoPOJO> getFuncInfoPOJOList() {
		return funcInfoPOJOList;
	}
	public void setFuncInfoPOJOList(List<FuncInfoPOJO> funcInfoPOJOList) {
		this.funcInfoPOJOList = funcInfoPOJOList;
	}
}

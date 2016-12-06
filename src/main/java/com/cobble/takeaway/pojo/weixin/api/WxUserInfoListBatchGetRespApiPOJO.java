package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WxUserInfoListBatchGetRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="user_info_list")
    private List<WxUserInfoApiPOJO> userInfoList;

	public List<WxUserInfoApiPOJO> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<WxUserInfoApiPOJO> userInfoList) {
		this.userInfoList = userInfoList;
	}
	
}
package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxUserInfoListBatchGetReqApiPOJO extends BasePOJO {
	@JsonProperty(value="user_list")
    private List<WxUserInfoBatchGetReqApiPOJO> userList;

	public List<WxUserInfoBatchGetReqApiPOJO> getUserList() {
		return userList;
	}

	public void setUserList(List<WxUserInfoBatchGetReqApiPOJO> userList) {
		this.userList = userList;
	}
	
}
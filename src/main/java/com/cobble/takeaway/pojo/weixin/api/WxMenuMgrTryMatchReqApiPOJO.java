package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrTryMatchReqApiPOJO extends BasePOJO {
	@JsonProperty(value="user_id")
	@JsonInclude(Include.NON_EMPTY)
	/**
	 * openId/微信号
	 */
    private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "WxMenuMgrTryMatchReqApiPOJO [userId=" + userId + "]";
	}
	
}
package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WxUserGetDataRespApiPOJO {
	@JsonProperty(value="openid")
    private List<String> openIds = new ArrayList<String>();

	public List<String> getOpenIds() {
		return openIds;
	}

	public void setOpenIds(List<String> openIds) {
		this.openIds = openIds;
	}
}

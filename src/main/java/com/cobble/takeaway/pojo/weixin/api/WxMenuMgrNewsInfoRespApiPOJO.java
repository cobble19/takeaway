package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WxMenuMgrNewsInfoRespApiPOJO {

	@JsonProperty(value="list")
    private List<WxMenuMgrNewsRespApiPOJO> list;

	public List<WxMenuMgrNewsRespApiPOJO> getList() {
		return list;
	}

	public void setList(List<WxMenuMgrNewsRespApiPOJO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "WxMenuMgrNewsInfoRespApiPOJO [list=" + list + "]";
	}
}

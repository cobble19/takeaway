package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrCreateReqApiPOJO extends BasePOJO {
	@JsonProperty(value="button")
    private List<WxMenuMgrCreateButtonReqApiPOJO> button = new ArrayList<WxMenuMgrCreateButtonReqApiPOJO>();

	public List<WxMenuMgrCreateButtonReqApiPOJO> getButton() {
		return button;
	}

	public void setButton(List<WxMenuMgrCreateButtonReqApiPOJO> button) {
		this.button = button;
	}

	@Override
	public String toString() {
		return "WxMenuMgrCreateReqApiPOJO [button=" + button + "]";
	}
	
}
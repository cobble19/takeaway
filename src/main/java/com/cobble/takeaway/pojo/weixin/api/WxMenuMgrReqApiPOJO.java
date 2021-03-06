package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrReqApiPOJO extends BasePOJO {
	@JsonProperty(value="button")
    private List<WxMenuMgrButtonReqApiPOJO> button = new ArrayList<WxMenuMgrButtonReqApiPOJO>();

	public List<WxMenuMgrButtonReqApiPOJO> getButton() {
		return button;
	}

	public void setButton(List<WxMenuMgrButtonReqApiPOJO> button) {
		this.button = button;
	}

	@Override
	public String toString() {
		return "WxMenuMgrCreateReqApiPOJO [button=" + button + "]";
	}
	
}
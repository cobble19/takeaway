package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrCreateReqApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="button")
    private List<WxMenuMgrCreateButtonReqApiPOJO> button = new ArrayList<WxMenuMgrCreateButtonReqApiPOJO>();

	public List<WxMenuMgrCreateButtonReqApiPOJO> getButton() {
		return button;
	}

	public void setButton(List<WxMenuMgrCreateButtonReqApiPOJO> button) {
		this.button = button;
	}
	
}
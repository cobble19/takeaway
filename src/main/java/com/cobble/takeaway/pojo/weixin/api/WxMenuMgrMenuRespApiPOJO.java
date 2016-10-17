package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrMenuRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="button")
    private List<WxMenuMgrCreateButtonReqApiPOJO> button = new ArrayList<WxMenuMgrCreateButtonReqApiPOJO>();
	@JsonProperty(value="menuid")
	private String menuId;

	public List<WxMenuMgrCreateButtonReqApiPOJO> getButton() {
		return button;
	}

	public void setButton(List<WxMenuMgrCreateButtonReqApiPOJO> button) {
		this.button = button;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="menu")
    private WxMenuMgrMenuRespApiPOJO menu = new WxMenuMgrMenuRespApiPOJO();
	@JsonProperty(value="conditionalmenu")
    private List<WxMenuMgrMenuCondRespApiPOJO> conditionalMenu = new ArrayList<WxMenuMgrMenuCondRespApiPOJO>();

	public List<WxMenuMgrMenuCondRespApiPOJO> getConditionalMenu() {
		return conditionalMenu;
	}

	public void setConditionalMenu(
			List<WxMenuMgrMenuCondRespApiPOJO> conditionalMenu) {
		this.conditionalMenu = conditionalMenu;
	}

	public WxMenuMgrMenuRespApiPOJO getMenu() {
		return menu;
	}

	public void setMenu(WxMenuMgrMenuRespApiPOJO menu) {
		this.menu = menu;
	}

	
}
package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="menu")
    private WxMenuMgrButtonsRespApiPOJO menu = new WxMenuMgrButtonsRespApiPOJO();
	@JsonProperty(value="conditionalmenu")
	@JsonInclude(Include.NON_EMPTY)
    private List<WxMenuMgrButtonsRespApiPOJO> conditionalMenu = new ArrayList<WxMenuMgrButtonsRespApiPOJO>();

	public WxMenuMgrButtonsRespApiPOJO getMenu() {
		return menu;
	}

	public void setMenu(WxMenuMgrButtonsRespApiPOJO menu) {
		this.menu = menu;
	}

	public List<WxMenuMgrButtonsRespApiPOJO> getConditionalMenu() {
		return conditionalMenu;
	}

	public void setConditionalMenu(List<WxMenuMgrButtonsRespApiPOJO> conditionalMenu) {
		this.conditionalMenu = conditionalMenu;
	}

	@Override
	public String toString() {
		return "WxMenuMgrRespApiPOJO [menu=" + menu + ", conditionalMenu="
				+ conditionalMenu + "]";
	}
	
}
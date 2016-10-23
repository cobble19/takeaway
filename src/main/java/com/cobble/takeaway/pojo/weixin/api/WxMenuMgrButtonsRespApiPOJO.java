package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrButtonsRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="button")
	private List<WxMenuMgrButtonRespApiPOJO> button;
	@JsonProperty(value="menuid")
	@JsonInclude(Include.NON_EMPTY)
	private String menuId;
	@JsonProperty(value="matchrule")
	@JsonInclude(Include.NON_EMPTY)
	private WxMenuMgrMenuCondMatchRuleRespApiPOJO wxMenuMgrMenuCondMatchRuleRespApiPOJO;
	public List<WxMenuMgrButtonRespApiPOJO> getButton() {
		return button;
	}
	public void setButton(List<WxMenuMgrButtonRespApiPOJO> button) {
		this.button = button;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public WxMenuMgrMenuCondMatchRuleRespApiPOJO getWxMenuMgrMenuCondMatchRuleRespApiPOJO() {
		return wxMenuMgrMenuCondMatchRuleRespApiPOJO;
	}
	public void setWxMenuMgrMenuCondMatchRuleRespApiPOJO(
			WxMenuMgrMenuCondMatchRuleRespApiPOJO wxMenuMgrMenuCondMatchRuleRespApiPOJO) {
		this.wxMenuMgrMenuCondMatchRuleRespApiPOJO = wxMenuMgrMenuCondMatchRuleRespApiPOJO;
	}
	@Override
	public String toString() {
		return "WxMenuMgrButtonsRespApiPOJO [button=" + button + ", menuId="
				+ menuId + ", wxMenuMgrMenuCondMatchRuleRespApiPOJO="
				+ wxMenuMgrMenuCondMatchRuleRespApiPOJO + "]";
	}
}
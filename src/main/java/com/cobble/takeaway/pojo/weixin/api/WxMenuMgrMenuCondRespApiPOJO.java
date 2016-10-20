package com.cobble.takeaway.pojo.weixin.api;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrMenuCondRespApiPOJO extends WxMenuMgrMenuRespApiPOJO {
	@JsonProperty(value="matchrule")
    private WxMenuMgrMenuCondMatchRuleRespApiPOJO wxMenuMgrMenuCondMatchRuleRespApiPOJO;

	public WxMenuMgrMenuCondMatchRuleRespApiPOJO getWxMenuMgrMenuCondMatchRuleRespApiPOJO() {
		return wxMenuMgrMenuCondMatchRuleRespApiPOJO;
	}

	public void setWxMenuMgrMenuCondMatchRuleRespApiPOJO(
			WxMenuMgrMenuCondMatchRuleRespApiPOJO wxMenuMgrMenuCondMatchRuleRespApiPOJO) {
		this.wxMenuMgrMenuCondMatchRuleRespApiPOJO = wxMenuMgrMenuCondMatchRuleRespApiPOJO;
	}

	@Override
	public String toString() {
		return "WxMenuMgrMenuCondRespApiPOJO [wxMenuMgrMenuCondMatchRuleRespApiPOJO="
				+ wxMenuMgrMenuCondMatchRuleRespApiPOJO + "]";
	}

}
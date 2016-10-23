package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrSelfMenuInfoRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="button")
	private List<WxMenuMgrSelfMenuInfoButtonRespApiPOJO> button;
	/*@JsonProperty(value="menuid")
	@JsonInclude(Include.NON_EMPTY)
	private String menuId;
	@JsonProperty(value="matchrule")
	@JsonInclude(Include.NON_EMPTY)
	private WxMenuMgrMenuCondMatchRuleRespApiPOJO wxMenuMgrMenuCondMatchRuleRespApiPOJO;*/
	public List<WxMenuMgrSelfMenuInfoButtonRespApiPOJO> getButton() {
		return button;
	}
	public void setButton(List<WxMenuMgrSelfMenuInfoButtonRespApiPOJO> button) {
		this.button = button;
	}
	@Override
	public String toString() {
		return "WxMenuMgrSelfMenuInfoRespApiPOJO [button=" + button + "]";
	}

}
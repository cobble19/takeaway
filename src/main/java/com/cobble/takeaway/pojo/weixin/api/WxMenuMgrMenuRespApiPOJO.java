package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrMenuRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="menu")
	private WxMenuMgrButtonsRespApiPOJO menu;
	@JsonProperty(value="conditionalmenu")
	@JsonInclude(Include.NON_EMPTY)
	private List<WxMenuMgrButtonsRespApiPOJO> conditionalMenu;

	
}
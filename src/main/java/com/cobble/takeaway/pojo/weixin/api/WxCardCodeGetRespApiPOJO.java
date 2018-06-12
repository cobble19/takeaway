package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxCardCodeGetRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="card")
	private WxCardCodeGetRespCardPOJO wxCardCodeGetRespCardPOJO;
	@JsonProperty(value="openid")
	private String openId;
	@JsonProperty(value="can_consume")
	private Boolean canConsume;
	@JsonProperty(value="outer_str")
	private String outerStr;
	@JsonProperty(value="user_card_status")
	private String userCardStatus;

	public WxCardCodeGetRespCardPOJO getWxCardCodeGetRespCardPOJO() {
		return wxCardCodeGetRespCardPOJO;
	}

	public void setWxCardCodeGetRespCardPOJO(WxCardCodeGetRespCardPOJO wxCardCodeGetRespCardPOJO) {
		this.wxCardCodeGetRespCardPOJO = wxCardCodeGetRespCardPOJO;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Boolean getCanConsume() {
		return canConsume;
	}

	public void setCanConsume(Boolean canConsume) {
		this.canConsume = canConsume;
	}

	public String getOuterStr() {
		return outerStr;
	}

	public void setOuterStr(String outerStr) {
		this.outerStr = outerStr;
	}

	public String getUserCardStatus() {
		return userCardStatus;
	}

	public void setUserCardStatus(String userCardStatus) {
		this.userCardStatus = userCardStatus;
	}
}
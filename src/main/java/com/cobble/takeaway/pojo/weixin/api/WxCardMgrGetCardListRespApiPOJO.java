package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class WxCardMgrGetCardListRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="card_list")
    private List<WxCardMgrCardApiPOJO> wxCardMgrCardApiPOJOs = new ArrayList<WxCardMgrCardApiPOJO>();
	@JsonProperty(value="has_share_card")
    private Boolean hasShareCard = false;

	public List<WxCardMgrCardApiPOJO> getWxCardMgrCardApiPOJOs() {
		return wxCardMgrCardApiPOJOs;
	}

	public void setWxCardMgrCardApiPOJOs(List<WxCardMgrCardApiPOJO> wxCardMgrCardApiPOJOs) {
		this.wxCardMgrCardApiPOJOs = wxCardMgrCardApiPOJOs;
	}

	public Boolean getHasShareCard() {
		return hasShareCard;
	}

	public void setHasShareCard(Boolean hasShareCard) {
		this.hasShareCard = hasShareCard;
	}
}
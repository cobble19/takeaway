package com.cobble.takeaway.pojo.weixin.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WxCustomSendReqWxCardApiPOJO {
	@JsonProperty(value="card_id")
    private String cardId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
}

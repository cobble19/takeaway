package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxCardCodeGetReqApiPOJO extends BasePOJO {
	@JsonProperty(value="card_id")
	private String cardId;
	@JsonProperty(value="code")
	private String code;
	@JsonProperty(value="check_consume")
	private Boolean checkConsume = false;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getCheckConsume() {
		return checkConsume;
	}

	public void setCheckConsume(Boolean checkConsume) {
		this.checkConsume = checkConsume;
	}
}
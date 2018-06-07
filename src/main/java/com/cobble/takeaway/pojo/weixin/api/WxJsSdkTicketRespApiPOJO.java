package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

// 此java类, 也适用于jssdkticket和卡券ticket
public class WxJsSdkTicketRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="ticket")
	private String ticket;
	// 有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket
	@JsonProperty(value="expires_in")
	@JsonInclude(Include.NON_EMPTY)
	private Integer expiresIn;
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
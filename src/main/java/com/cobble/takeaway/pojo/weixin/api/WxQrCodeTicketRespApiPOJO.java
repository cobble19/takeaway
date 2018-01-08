package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxQrCodeTicketRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="ticket")
	private String ticket;
	@JsonProperty(value="expire_seconds")
	@JsonInclude(Include.NON_EMPTY)
	private Integer expireSeconds;
	@JsonProperty(value="url")
	@JsonInclude(Include.NON_EMPTY)
	private String url;
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Integer getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
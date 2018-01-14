package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WxJsSdkConfigRespApiPOJO extends BasePOJO {
	@JsonProperty(value="appId")
	private String appId;
	@JsonProperty(value="timestamp")
	@JsonInclude(Include.NON_EMPTY)
	private Long timestamp;
	@JsonProperty(value="nonceStr")
	@JsonInclude(Include.NON_EMPTY)
	private String nonceStr;
	@JsonProperty(value="signature")
	@JsonInclude(Include.NON_EMPTY)
	private String signature;
	@JsonProperty(value="jsApiList")
	@JsonInclude(Include.NON_EMPTY)
	private List<String> jsApiList = new ArrayList<String>();

	private String url;
	private String ticket;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public List<String> getJsApiList() {
		return jsApiList;
	}
	public void setJsApiList(List<String> jsApiList) {
		this.jsApiList = jsApiList;
	}
	
	
}
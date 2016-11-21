package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxTagsMgrUserRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="count")
    private Integer count;
	@JsonProperty(value="data")
    private WxTagsMgrUserOpenIdRespApiPOJO data;
	@JsonProperty(value="next_openid")
    private String nextOpenId;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public WxTagsMgrUserOpenIdRespApiPOJO getData() {
		return data;
	}
	public void setData(WxTagsMgrUserOpenIdRespApiPOJO data) {
		this.data = data;
	}
	public String getNextOpenId() {
		return nextOpenId;
	}
	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}

	
}
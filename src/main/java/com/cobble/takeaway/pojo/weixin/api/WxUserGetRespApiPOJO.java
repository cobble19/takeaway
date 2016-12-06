package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WxUserGetRespApiPOJO extends BaseWxApiPOJO {
    private Integer total;
    private Integer count;
    private WxUserGetDataRespApiPOJO data;
	@JsonProperty(value="next_openid")
    private String nextOpenId;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public WxUserGetDataRespApiPOJO getData() {
		return data;
	}
	public void setData(WxUserGetDataRespApiPOJO data) {
		this.data = data;
	}
	public String getNextOpenId() {
		return nextOpenId;
	}
	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}

}
package com.cobble.takeaway.pojo.weixin;

import com.cobble.takeaway.pojo.BasePOJO;

public class RelWxPuOpenPOJO extends BasePOJO {
	private Long relWxPuOpenId;
	private Long wxPersonUserId;
    // 用户标识
    private String openId;
    
	public Long getRelWxPuOpenId() {
		return relWxPuOpenId;
	}
	public void setRelWxPuOpenId(Long relWxPuOpenId) {
		this.relWxPuOpenId = relWxPuOpenId;
	}
	public Long getWxPersonUserId() {
		return wxPersonUserId;
	}
	public void setWxPersonUserId(Long wxPersonUserId) {
		this.wxPersonUserId = wxPersonUserId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
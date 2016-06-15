package com.cobble.takeaway.pojo.weixin;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

public class RelWxPuOpenSearchPOJO extends BaseSearchPOJO {
	private Long relWxPuOpenId;
	private Long wxPersonUserId;
    // 用户标识
    private String openId;
    private String unionId;
    
    private String authorizerAppId;
    
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
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}

}
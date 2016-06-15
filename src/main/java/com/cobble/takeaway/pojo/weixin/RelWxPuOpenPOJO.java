package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class RelWxPuOpenPOJO extends BasePOJO {
	private Long relWxPuOpenId;
	private Long wxPersonUserId;
    private String openId;
    private String authorizerAppId;
    private Date createDateTime;
    
    private WxPersonUserPOJO wxPersonUserPOJO;
    
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
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public WxPersonUserPOJO getWxPersonUserPOJO() {
		return wxPersonUserPOJO;
	}
	public void setWxPersonUserPOJO(WxPersonUserPOJO wxPersonUserPOJO) {
		this.wxPersonUserPOJO = wxPersonUserPOJO;
	}

}
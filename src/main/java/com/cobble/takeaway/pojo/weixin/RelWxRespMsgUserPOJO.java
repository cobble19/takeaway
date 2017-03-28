package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class RelWxRespMsgUserPOJO extends BasePOJO {
	private Long relWxRespMsgUserId;
	private Long wxRespMsgId;
    private String authorizerAppId;
    private Long userId;
    private Date createDateTime;
	public Long getRelWxRespMsgUserId() {
		return relWxRespMsgUserId;
	}
	public void setRelWxRespMsgUserId(Long relWxRespMsgUserId) {
		this.relWxRespMsgUserId = relWxRespMsgUserId;
	}
	public Long getWxRespMsgId() {
		return wxRespMsgId;
	}
	public void setWxRespMsgId(Long wxRespMsgId) {
		this.wxRespMsgId = wxRespMsgId;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    
    
}

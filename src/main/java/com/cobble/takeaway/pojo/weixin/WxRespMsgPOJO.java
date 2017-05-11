package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxRespMsgPOJO extends BasePOJO {
	private Long wxRespMsgId;
    private String msgReceive;
    private String msgSend;
    private String msgType;
    private String msgUsage;
    private Long interactiveId;
    
    private Integer enableFlag;

    private Date createDateTime;
    
	private Long userId;
    private String authorizerAppId;
    
	public Long getWxRespMsgId() {
		return wxRespMsgId;
	}
	public void setWxRespMsgId(Long wxRespMsgId) {
		this.wxRespMsgId = wxRespMsgId;
	}
	public String getMsgReceive() {
		return msgReceive;
	}
	public void setMsgReceive(String msgReceive) {
		this.msgReceive = msgReceive;
	}
	public String getMsgSend() {
		return msgSend;
	}
	public void setMsgSend(String msgSend) {
		this.msgSend = msgSend;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "WxRespMsgPOJO [wxRespMsgId=" + wxRespMsgId + ", msgReceive="
				+ msgReceive + ", msgSend=" + msgSend + ", msgType=" + msgType
				+ ", createDateTime=" + createDateTime + ", userId=" + userId
				+ ", authorizerAppId=" + authorizerAppId + "]";
	}
	public Integer getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(Integer enableFlag) {
		this.enableFlag = enableFlag;
	}
	public String getMsgUsage() {
		return msgUsage;
	}
	public void setMsgUsage(String msgUsage) {
		this.msgUsage = msgUsage;
	}
	public Long getInteractiveId() {
		return interactiveId;
	}
	public void setInteractiveId(Long interactiveId) {
		this.interactiveId = interactiveId;
	}
    
}

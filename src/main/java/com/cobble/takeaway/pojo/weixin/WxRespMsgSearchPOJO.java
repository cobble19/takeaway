package com.cobble.takeaway.pojo.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

public class WxRespMsgSearchPOJO extends BaseSearchPOJO {
	private Long wxRespMsgId;
    private String msgReceive;
    private String msgSend;
    private String msgType;
    
    private Integer enableFlag;

    private Date createDateTime;
    
	private Long userId;
    private String authorizerAppId;
    
    private List<Long> interactiveIds = new ArrayList<Long>();
    
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
	public Integer getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(Integer enableFlag) {
		this.enableFlag = enableFlag;
	}
	public List<Long> getInteractiveIds() {
		return interactiveIds;
	}
	public void setInteractiveIds(List<Long> interactiveIds) {
		this.interactiveIds = interactiveIds;
	}
    
    
}

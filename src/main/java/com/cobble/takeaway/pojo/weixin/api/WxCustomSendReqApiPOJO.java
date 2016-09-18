package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxCustomSendReqApiPOJO extends BasePOJO {
	@JsonProperty(value="touser")
    private String touser;
	@JsonProperty(value="msgtype")
    private String msgtype;
	@JsonProperty(value="text")
    private WxCustomSendReqTextApiPOJO wxCustomSendReqTextApiPOJO;
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public WxCustomSendReqTextApiPOJO getWxCustomSendReqTextApiPOJO() {
		return wxCustomSendReqTextApiPOJO;
	}
	public void setWxCustomSendReqTextApiPOJO(
			WxCustomSendReqTextApiPOJO wxCustomSendReqTextApiPOJO) {
		this.wxCustomSendReqTextApiPOJO = wxCustomSendReqTextApiPOJO;
	}
	

}
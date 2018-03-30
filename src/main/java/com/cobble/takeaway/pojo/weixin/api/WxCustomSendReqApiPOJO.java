package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxCustomSendReqApiPOJO extends BasePOJO {
	@JsonProperty(value="touser")
    private String touser;
	@JsonProperty(value="msgtype")
    private String msgtype;
	@JsonProperty(value="text")
	@JsonInclude(Include.NON_EMPTY)
    private WxCustomSendReqTextApiPOJO wxCustomSendReqTextApiPOJO;
	@JsonProperty(value="wxcard")
	@JsonInclude(Include.NON_EMPTY)
	private WxCustomSendReqWxCardApiPOJO wxCustomSendReqWxCardApiPOJO;
	
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
	public WxCustomSendReqWxCardApiPOJO getWxCustomSendReqWxCardApiPOJO() {
		return wxCustomSendReqWxCardApiPOJO;
	}
	public void setWxCustomSendReqWxCardApiPOJO(WxCustomSendReqWxCardApiPOJO wxCustomSendReqWxCardApiPOJO) {
		this.wxCustomSendReqWxCardApiPOJO = wxCustomSendReqWxCardApiPOJO;
	}
	

}
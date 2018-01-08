package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
 * {"expire_seconds": 604800, "action_name": "QR_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
 * {"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
 * {"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
 * @author bange
 *
 */
public class WxQrCodeTicketReqApiPOJO extends BasePOJO {
	@JsonProperty(value="expire_seconds")
	@JsonInclude(Include.NON_EMPTY)
    private Integer expireSeconds;
	@JsonProperty(value="action_name")
	@JsonInclude(Include.NON_EMPTY)
    private String actionName;
	@JsonProperty(value="action_info")
	@JsonInclude(Include.NON_EMPTY)
    private WxQrCodeActionInfoReqApiPOJO wxQrCodeActionInfoReqApiPOJO;
	
	public Integer getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public WxQrCodeActionInfoReqApiPOJO getWxQrCodeActionInfoReqApiPOJO() {
		return wxQrCodeActionInfoReqApiPOJO;
	}
	public void setWxQrCodeActionInfoReqApiPOJO(WxQrCodeActionInfoReqApiPOJO wxQrCodeActionInfoReqApiPOJO) {
		this.wxQrCodeActionInfoReqApiPOJO = wxQrCodeActionInfoReqApiPOJO;
	}
	
}
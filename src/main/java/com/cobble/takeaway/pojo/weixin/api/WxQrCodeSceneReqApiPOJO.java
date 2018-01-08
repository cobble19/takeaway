package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class WxQrCodeSceneReqApiPOJO extends BasePOJO {
	@JsonProperty(value="scene_id")
	@JsonInclude(Include.NON_EMPTY)
    private Integer sceneId;
	@JsonProperty(value="scene_str")
	@JsonInclude(Include.NON_EMPTY)
    private String sceneStr;
	public Integer getSceneId() {
		return sceneId;
	}
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}
	public String getSceneStr() {
		return sceneStr;
	}
	public void setSceneStr(String sceneStr) {
		this.sceneStr = sceneStr;
	}
}

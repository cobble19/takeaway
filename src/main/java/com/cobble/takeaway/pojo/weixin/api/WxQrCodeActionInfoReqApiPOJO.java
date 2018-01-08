package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class WxQrCodeActionInfoReqApiPOJO extends BasePOJO {
	@JsonProperty(value="scene")
	@JsonInclude(Include.NON_EMPTY)
    private WxQrCodeSceneReqApiPOJO wxQrCodeSceneReqApiPOJO;

	public WxQrCodeSceneReqApiPOJO getWxQrCodeSceneReqApiPOJO() {
		return wxQrCodeSceneReqApiPOJO;
	}

	public void setWxQrCodeSceneReqApiPOJO(WxQrCodeSceneReqApiPOJO wxQrCodeSceneReqApiPOJO) {
		this.wxQrCodeSceneReqApiPOJO = wxQrCodeSceneReqApiPOJO;
	}
	
}

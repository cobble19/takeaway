package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxCardCodeDecryptReqApiPOJO extends BasePOJO {
	@JsonProperty(value="encrypt_code")
    private String encryptCode;

	public String getEncryptCode() {
		return encryptCode;
	}

	public void setEncryptCode(String encryptCode) {
		this.encryptCode = encryptCode;
	}
}
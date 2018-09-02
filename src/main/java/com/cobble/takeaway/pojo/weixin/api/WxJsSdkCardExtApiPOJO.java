package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WxJsSdkCardExtApiPOJO extends BasePOJO {
	private String code = "";
	@JsonProperty(value = "openid")
	@org.codehaus.jackson.annotate.JsonProperty(value = "openid")
	private String openId = "";

	private String timestamp = "";

	@JsonProperty(value = "nonce_str")
	@org.codehaus.jackson.annotate.JsonProperty(value = "nonce_str")
	private String nonceStr = "";

	@JsonProperty(value = "fixed_begintimestamp")
	@org.codehaus.jackson.annotate.JsonProperty(value = "fixed_begintimestamp")
	private String fixedBeginTimestamp = "";

	@JsonProperty(value = "outer_str")
	@org.codehaus.jackson.annotate.JsonProperty(value = "outer_str")
	private String outerStr = "";

	private String signature = "";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getFixedBeginTimestamp() {
		return fixedBeginTimestamp;
	}

	public void setFixedBeginTimestamp(String fixedBeginTimestamp) {
		this.fixedBeginTimestamp = fixedBeginTimestamp;
	}

	public String getOuterStr() {
		return outerStr;
	}

	public void setOuterStr(String outerStr) {
		this.outerStr = outerStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
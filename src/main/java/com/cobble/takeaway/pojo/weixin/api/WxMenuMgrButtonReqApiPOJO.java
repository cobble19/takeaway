package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrButtonReqApiPOJO extends BasePOJO {
	@JsonProperty(value="type")
	@JsonInclude(Include.NON_EMPTY)
    private String type;
	@JsonProperty(value="name")
	@JsonInclude(Include.NON_EMPTY)
    private String name;
	@JsonProperty(value="key")
	@JsonInclude(Include.NON_EMPTY)
    private String key;
	@JsonProperty(value="url")
	@JsonInclude(Include.NON_EMPTY)
    private String url;
	@JsonProperty(value="media_id")
	@JsonInclude(Include.NON_EMPTY)
    private String mediaId;
	@JsonProperty(value="sub_button")
	@JsonInclude(Include.NON_EMPTY)
    private List<WxMenuMgrButtonReqApiPOJO> subButton = new ArrayList<WxMenuMgrButtonReqApiPOJO>();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<WxMenuMgrButtonReqApiPOJO> getSubButton() {
		return subButton;
	}
	public void setSubButton(List<WxMenuMgrButtonReqApiPOJO> subButton) {
		this.subButton = subButton;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	@Override
	public String toString() {
		return "WxMenuMgrCreateButtonReqApiPOJO [type=" + type + ", name="
				+ name + ", key=" + key + ", url=" + url + ", media_id="
				+ mediaId + ", subButton=" + subButton + "]";
	}
	
}
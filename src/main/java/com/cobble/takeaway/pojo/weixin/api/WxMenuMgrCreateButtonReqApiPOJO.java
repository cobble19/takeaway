package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrCreateButtonReqApiPOJO extends BasePOJO {
	@JsonProperty(value="type")
    private String type;
	@JsonProperty(value="name")
    private String name;
	@JsonProperty(value="key")
    private String key;
	@JsonProperty(value="url")
    private String url;
	@JsonProperty(value="media_id")
    private String media_id;
	@JsonProperty(value="sub_button")
    private List<WxMenuMgrCreateButtonReqApiPOJO> subButton = new ArrayList<WxMenuMgrCreateButtonReqApiPOJO>();
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
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public List<WxMenuMgrCreateButtonReqApiPOJO> getSubButton() {
		return subButton;
	}
	public void setSubButton(List<WxMenuMgrCreateButtonReqApiPOJO> subButton) {
		this.subButton = subButton;
	}
	
}
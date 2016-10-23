package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class WxMenuMgrButtonRespApiPOJO extends BasePOJO {
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
	@JsonProperty(value="value")
	@JsonInclude(Include.NON_EMPTY)
    private String value;
	@JsonProperty(value="news_info")
	@JsonInclude(Include.NON_EMPTY)
    private WxMenuMgrNewsInfoRespApiPOJO newsInfo;
	@JsonProperty(value="sub_button")
	@JsonInclude(Include.NON_EMPTY)
    private List<WxMenuMgrButtonRespApiPOJO> subButton = new ArrayList<WxMenuMgrButtonRespApiPOJO>();
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
	public List<WxMenuMgrButtonRespApiPOJO> getSubButton() {
		return subButton;
	}
	public void setSubButton(List<WxMenuMgrButtonRespApiPOJO> subButton) {
		this.subButton = subButton;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public WxMenuMgrNewsInfoRespApiPOJO getNewsInfo() {
		return newsInfo;
	}
	public void setNewsInfo(WxMenuMgrNewsInfoRespApiPOJO newsInfo) {
		this.newsInfo = newsInfo;
	}
	@Override
	public String toString() {
		return "WxMenuMgrButtonRespApiPOJO [type=" + type + ", name=" + name
				+ ", key=" + key + ", url=" + url + ", mediaId=" + mediaId
				+ ", value=" + value + ", newsInfo=" + newsInfo
				+ ", subButton=" + subButton + "]";
	}
	
}
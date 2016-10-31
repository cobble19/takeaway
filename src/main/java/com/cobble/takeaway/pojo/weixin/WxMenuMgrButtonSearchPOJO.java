package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

public class WxMenuMgrButtonSearchPOJO extends BaseSearchPOJO {
	private Long wxMenuMgrButtonId;
	private Long parentButtonId;
    private String authorizerAppId;
    private String menuId;
    private Integer type;
    private Integer name;
    private String key;
    private String url;
    private String mediaId;
    private String value;
	private Integer newsInfo;
    private String level;
    private Date createDateTime;
    
	public Long getWxMenuMgrButtonId() {
		return wxMenuMgrButtonId;
	}
	public void setWxMenuMgrButtonId(Long wxMenuMgrButtonId) {
		this.wxMenuMgrButtonId = wxMenuMgrButtonId;
	}
	public Long getParentButtonId() {
		return parentButtonId;
	}
	public void setParentButtonId(Long parentButtonId) {
		this.parentButtonId = parentButtonId;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getName() {
		return name;
	}
	public void setName(Integer name) {
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
	public Integer getNewsInfo() {
		return newsInfo;
	}
	public void setNewsInfo(Integer newsInfo) {
		this.newsInfo = newsInfo;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    
}

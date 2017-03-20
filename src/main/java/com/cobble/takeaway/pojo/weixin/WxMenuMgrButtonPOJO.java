package com.cobble.takeaway.pojo.weixin;

import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxMenuMgrButtonPOJO extends BasePOJO {
	private Long wxMenuMgrButtonId;
	private Long parentButtonId;
    private String authorizerAppId;
    private Long wxMenuMgrCategoryId;
    private String menuId;
    private String type;
    private String name;
    private String btnKey;
    private String url;
    private String mediaId;
    private String value;
	private String newsInfo;
    private Integer level;

    private Integer orderNo;
    private Date createDateTime;
    
    private List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs;
    
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
	public String getNewsInfo() {
		return newsInfo;
	}
	public void setNewsInfo(String newsInfo) {
		this.newsInfo = newsInfo;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public List<WxMenuMgrButtonPOJO> getWxMenuMgrButtonPOJOs() {
		return wxMenuMgrButtonPOJOs;
	}
	public void setWxMenuMgrButtonPOJOs(
			List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs) {
		this.wxMenuMgrButtonPOJOs = wxMenuMgrButtonPOJOs;
	}
	public Long getWxMenuMgrCategoryId() {
		return wxMenuMgrCategoryId;
	}
	public void setWxMenuMgrCategoryId(Long wxMenuMgrCategoryId) {
		this.wxMenuMgrCategoryId = wxMenuMgrCategoryId;
	}
	public String getBtnKey() {
		return btnKey;
	}
	public void setBtnKey(String btnKey) {
		this.btnKey = btnKey;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
    
}

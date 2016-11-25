package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

public class WxMenuMgrFullSearchPOJO extends BaseSearchPOJO {
	private Long wxMenuMgrFullId;
    private String authorizerAppId;
    private String name;
    private String description;
    private Date createDateTime;
    
	public Long getWxMenuMgrFullId() {
		return wxMenuMgrFullId;
	}
	public void setWxMenuMgrFullId(Long wxMenuMgrFullId) {
		this.wxMenuMgrFullId = wxMenuMgrFullId;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    
}

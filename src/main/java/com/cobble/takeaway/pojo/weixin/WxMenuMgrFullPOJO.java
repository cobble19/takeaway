package com.cobble.takeaway.pojo.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxMenuMgrFullPOJO extends BasePOJO {
	private Long wxMenuMgrFullId;
    private String authorizerAppId;
    private String name;
    private String description;
    private Date createDateTime;
    
    private List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = new ArrayList<WxMenuMgrCategoryPOJO>();
    
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
	public List<WxMenuMgrCategoryPOJO> getWxMenuMgrCategoryPOJOs() {
		return wxMenuMgrCategoryPOJOs;
	}
	public void setWxMenuMgrCategoryPOJOs(
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs) {
		this.wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryPOJOs;
	}
    
}

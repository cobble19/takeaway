package com.cobble.takeaway.pojo.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxMenuMgrCategoryPOJO extends BasePOJO {
	private Long wxMenuMgrCategoryId;
    private String authorizerAppId;
    private String name;
    private String description;
    
    private String menuId;
    private Long wxMenuMgrFullId;
    private Long wxMenuMgrMatchRuleId;
    private Date createDateTime;
    private List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = new ArrayList<WxMenuMgrButtonPOJO>();
    
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
	public List<WxMenuMgrButtonPOJO> getWxMenuMgrButtonPOJOs() {
		return wxMenuMgrButtonPOJOs;
	}
	public void setWxMenuMgrButtonPOJOs(List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs) {
		this.wxMenuMgrButtonPOJOs = wxMenuMgrButtonPOJOs;
	}
	public Long getWxMenuMgrCategoryId() {
		return wxMenuMgrCategoryId;
	}
	public void setWxMenuMgrCategoryId(Long wxMenuMgrCategoryId) {
		this.wxMenuMgrCategoryId = wxMenuMgrCategoryId;
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
	public Long getWxMenuMgrFullId() {
		return wxMenuMgrFullId;
	}
	public void setWxMenuMgrFullId(Long wxMenuMgrFullId) {
		this.wxMenuMgrFullId = wxMenuMgrFullId;
	}
	public Long getWxMenuMgrMatchRuleId() {
		return wxMenuMgrMatchRuleId;
	}
	public void setWxMenuMgrMatchRuleId(Long wxMenuMgrMatchRuleId) {
		this.wxMenuMgrMatchRuleId = wxMenuMgrMatchRuleId;
	}
    
    
}

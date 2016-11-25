package com.cobble.takeaway.pojo.weixin;

import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

public class WxMenuMgrCategorySearchPOJO extends BaseSearchPOJO {
	private Long wxMenuMgrCategoryId;
    private String authorizerAppId;
    private String name;
    private String description;
    private String menuId;
    private Long wxMenuMgrFullId;
    private Long wxMenuMgrMatchRuleId;
    private List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJO;
    private Date createDateTime;
    
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
	public List<WxMenuMgrButtonPOJO> getWxMenuMgrButtonPOJO() {
		return wxMenuMgrButtonPOJO;
	}
	public void setWxMenuMgrButtonPOJO(List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJO) {
		this.wxMenuMgrButtonPOJO = wxMenuMgrButtonPOJO;
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

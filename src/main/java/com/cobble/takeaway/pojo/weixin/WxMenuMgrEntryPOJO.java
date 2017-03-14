package com.cobble.takeaway.pojo.weixin;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxMenuMgrEntryPOJO extends BasePOJO {
    private Long wxMenuMgrEntryId;
    
    private String authorizerAppId;
    private Long wxMenuMgrFullId;
    private Long wxMenuMgrMatchRuleId;
    
    private WxMenuMgrFullPOJO wxMenuMgrFullPOJO;
    private WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO;
    private WxMenuMgrButtonPOJO level1ButtonPOJO = new WxMenuMgrButtonPOJO();
    private WxMenuMgrButtonPOJO level2Button1POJO = new WxMenuMgrButtonPOJO();
    private WxMenuMgrButtonPOJO level2Button2POJO = new WxMenuMgrButtonPOJO();
    private WxMenuMgrButtonPOJO level2Button3POJO = new WxMenuMgrButtonPOJO();
    private WxMenuMgrButtonPOJO level2Button4POJO = new WxMenuMgrButtonPOJO();
    private WxMenuMgrButtonPOJO level2Button5POJO = new WxMenuMgrButtonPOJO();
    
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
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
	public WxMenuMgrFullPOJO getWxMenuMgrFullPOJO() {
		return wxMenuMgrFullPOJO;
	}
	public void setWxMenuMgrFullPOJO(WxMenuMgrFullPOJO wxMenuMgrFullPOJO) {
		this.wxMenuMgrFullPOJO = wxMenuMgrFullPOJO;
	}
	public WxMenuMgrCategoryPOJO getWxMenuMgrCategoryPOJO() {
		return wxMenuMgrCategoryPOJO;
	}
	public void setWxMenuMgrCategoryPOJO(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO) {
		this.wxMenuMgrCategoryPOJO = wxMenuMgrCategoryPOJO;
	}
	public WxMenuMgrButtonPOJO getLevel1ButtonPOJO() {
		return level1ButtonPOJO;
	}
	public void setLevel1ButtonPOJO(WxMenuMgrButtonPOJO level1ButtonPOJO) {
		this.level1ButtonPOJO = level1ButtonPOJO;
	}
	public WxMenuMgrButtonPOJO getLevel2Button1POJO() {
		return level2Button1POJO;
	}
	public void setLevel2Button1POJO(WxMenuMgrButtonPOJO level2Button1POJO) {
		this.level2Button1POJO = level2Button1POJO;
	}
	public WxMenuMgrButtonPOJO getLevel2Button2POJO() {
		return level2Button2POJO;
	}
	public void setLevel2Button2POJO(WxMenuMgrButtonPOJO level2Button2POJO) {
		this.level2Button2POJO = level2Button2POJO;
	}
	public WxMenuMgrButtonPOJO getLevel2Button3POJO() {
		return level2Button3POJO;
	}
	public void setLevel2Button3POJO(WxMenuMgrButtonPOJO level2Button3POJO) {
		this.level2Button3POJO = level2Button3POJO;
	}
	public WxMenuMgrButtonPOJO getLevel2Button4POJO() {
		return level2Button4POJO;
	}
	public void setLevel2Button4POJO(WxMenuMgrButtonPOJO level2Button4POJO) {
		this.level2Button4POJO = level2Button4POJO;
	}
	public WxMenuMgrButtonPOJO getLevel2Button5POJO() {
		return level2Button5POJO;
	}
	public void setLevel2Button5POJO(WxMenuMgrButtonPOJO level2Button5POJO) {
		this.level2Button5POJO = level2Button5POJO;
	}
	public Long getWxMenuMgrEntryId() {
		return wxMenuMgrEntryId;
	}
	public void setWxMenuMgrEntryId(Long wxMenuMgrEntryId) {
		this.wxMenuMgrEntryId = wxMenuMgrEntryId;
	}
    
    
}

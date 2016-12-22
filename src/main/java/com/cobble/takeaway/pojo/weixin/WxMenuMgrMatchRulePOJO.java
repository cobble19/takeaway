package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxMenuMgrMatchRulePOJO extends BasePOJO {
	private Long wxMenuMgrMatchRuleId;
	private Long wxMenuMgrCategoryId;
    private Integer groupId;
    private Integer sex;
    private String country;
    private String province;
    private String city;
    private Integer clientPlatformType;
    private String language;
    private Date createDateTime;
    
	public Long getWxMenuMgrMatchRuleId() {
		return wxMenuMgrMatchRuleId;
	}
	public void setWxMenuMgrMatchRuleId(Long wxMenuMgrMatchRuleId) {
		this.wxMenuMgrMatchRuleId = wxMenuMgrMatchRuleId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getClientPlatformType() {
		return clientPlatformType;
	}
	public void setClientPlatformType(Integer clientPlatformType) {
		this.clientPlatformType = clientPlatformType;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Long getWxMenuMgrCategoryId() {
		return wxMenuMgrCategoryId;
	}
	public void setWxMenuMgrCategoryId(Long wxMenuMgrCategoryId) {
		this.wxMenuMgrCategoryId = wxMenuMgrCategoryId;
	}
    
}

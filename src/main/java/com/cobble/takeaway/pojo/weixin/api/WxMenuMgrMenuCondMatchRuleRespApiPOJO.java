package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrMenuCondMatchRuleRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="group_id")
	private Integer groupId;
	@JsonProperty(value="sex")
	private Integer sex;
	@JsonProperty(value="country")
	private String country;
	@JsonProperty(value="province")
	private String province;
	@JsonProperty(value="city")
	private String city;
	@JsonProperty(value="clientPlatformType")
	private Integer client_platform_type;
	
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
	public Integer getClient_platform_type() {
		return client_platform_type;
	}
	public void setClient_platform_type(Integer client_platform_type) {
		this.client_platform_type = client_platform_type;
	}
	
}
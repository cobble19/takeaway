package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WxUserInfoApiPOJO extends BaseWxApiPOJO {
    // 用户标识
	@JsonProperty(value="openid")
    private String openId;
    // 用户昵称
    private String nickname;
    // 性别（1是男性，2是女性，0是未知）
    private int sex;
    // 语言
    private String language;
    // 国家
    private String country;
    // 省份
    private String province;
    // 城市
    private String city;
    // 用户头像链接
	@JsonProperty(value="headimgurl")
    private String headImgUrl;

    // 用户特权信息
	@JsonProperty("privilege")
    private List<String> privilegeList;
	@JsonProperty(value="unionid")
    private String unionId;
	@JsonProperty(value="subscribe")
    private Integer subscribe;
	@JsonProperty(value="subscribe_time")
    private Long subscribeTime;
    private String remark;
	@JsonProperty(value="groupid")
    private Integer groupId;
	@JsonProperty(value="tagid_list")
    private List<String> tagidList;
	@JsonProperty(value="subscribe_scene")
	private String subscribeScene;
	@JsonProperty(value="qr_scene")
	private Integer qrScene;
	@JsonProperty(value="qr_scene_str")
	private String qrSceneStr;
	
	public List<String> getPrivilegeList() {
		return privilegeList;
	}
	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}
	public String getSubscribeScene() {
		return subscribeScene;
	}
	public void setSubscribeScene(String subscribeScene) {
		this.subscribeScene = subscribeScene;
	}
	public Integer getQrScene() {
		return qrScene;
	}
	public void setQrScene(Integer qrScene) {
		this.qrScene = qrScene;
	}
	public String getQrSceneStr() {
		return qrSceneStr;
	}
	public void setQrSceneStr(String qrSceneStr) {
		this.qrSceneStr = qrSceneStr;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public Long getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public List<String> getTagidList() {
		return tagidList;
	}
	public void setTagidList(List<String> tagidList) {
		this.tagidList = tagidList;
	}
	@Override
	public String toString() {
		return "WxUserInfoApiPOJO [openId=" + openId + ", nickname=" + nickname
				+ ", sex=" + sex + ", language=" + language + ", country="
				+ country + ", province=" + province + ", city=" + city
				+ ", headImgUrl=" + headImgUrl + ", privilegeList="
				+ privilegeList + ", unionId=" + unionId + ", subscribe="
				+ subscribe + ", subscribeTime=" + subscribeTime + ", remark="
				+ remark + ", groupId=" + groupId + ", tagidList=" + tagidList
				+ "]";
	}
	
	

}
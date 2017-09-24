package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;


public class ActivityRegistrationPOJO extends BasePOJO {
    private Long activityRegistrationId;
    private String title;
    private String logoImg;
    private Date startDateTime;
    private Date endDateTime;
    private String content;
    private Date createDateTime;
    private Integer publishType;
    private Integer needSubscribe;
    private Long userId;
    private Long activityId;

    // check whether expired
    private Boolean expired;
    
    private UserPOJO userPOJO;
    
    private Long userIdEnterprise;
    private String usernameEnterprise;
    
    private WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserPOJO getUserPOJO() {
		return userPOJO;
	}
	public void setUserPOJO(UserPOJO userPOJO) {
		this.userPOJO = userPOJO;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public Boolean getExpired() {
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	public Long getUserIdEnterprise() {
		return userIdEnterprise;
	}
	public void setUserIdEnterprise(Long userIdEnterprise) {
		this.userIdEnterprise = userIdEnterprise;
	}
	public String getUsernameEnterprise() {
		return usernameEnterprise;
	}
	public void setUsernameEnterprise(String usernameEnterprise) {
		this.usernameEnterprise = usernameEnterprise;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public WxAuthorizerInfoPOJO getWxAuthorizerInfoPOJO() {
		return wxAuthorizerInfoPOJO;
	}
	public void setWxAuthorizerInfoPOJO(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) {
		this.wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJO;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Integer getNeedSubscribe() {
		return needSubscribe;
	}
	public void setNeedSubscribe(Integer needSubscribe) {
		this.needSubscribe = needSubscribe;
	}
	public Integer getPublishType() {
		return publishType;
	}
	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}
	public Long getActivityRegistrationId() {
		return activityRegistrationId;
	}
	public void setActivityRegistrationId(Long activityRegistrationId) {
		this.activityRegistrationId = activityRegistrationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
}
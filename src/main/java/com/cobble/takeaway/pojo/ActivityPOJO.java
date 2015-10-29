package com.cobble.takeaway.pojo;

import java.util.Date;


public class ActivityPOJO extends BasePOJO {
    private Long activityId;
    private String title;
    private Date startDateTime;
    private Date endDateTime;
    private String content;

    // check whether expired
    private Boolean expired;
    
    private UserPOJO userPOJO;
    
    private Long userIdEnterprise;
    
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
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
}
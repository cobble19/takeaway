package com.cobble.takeaway.pojo;


public class ActivityPOJO extends BasePOJO {
    private Long activityId;
    private String title;
    private String content;
    
    private UserPOJO userPOJO;
    
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
}
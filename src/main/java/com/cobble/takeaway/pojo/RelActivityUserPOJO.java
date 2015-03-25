package com.cobble.takeaway.pojo;


public class RelActivityUserPOJO extends BasePOJO {
    private Long activityUserId;
    private Long activityId;
    private Long userId;
	public Long getActivityUserId() {
		return activityUserId;
	}
	public void setActivityUserId(Long activityUserId) {
		this.activityUserId = activityUserId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
}
package com.cobble.takeaway.pojo;


public class ActivityRegistrationSearchPOJO extends BaseSearchPOJO {
    private Long activityRegistrationId;
    private String title;
    private String content;
    private Integer publishType;
    
    private String openId;
    private String unionId;
    private String wxIndexCode;
    
    private Long activityId;
    
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public String getWxIndexCode() {
		return wxIndexCode;
	}
	public void setWxIndexCode(String wxIndexCode) {
		this.wxIndexCode = wxIndexCode;
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
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
}
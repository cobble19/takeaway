package com.cobble.takeaway.pojo;

import java.util.Date;


public class PointEventSearchPOJO extends BaseSearchPOJO {
    private Long pointRecordId;
    private Long userId;
    private String authorizerAppId;
    private String eventName;
    private String pointNumPer;
    private String pointRate;
    private Date createDateTime;
	public Long getPointRecordId() {
		return pointRecordId;
	}
	public void setPointRecordId(Long pointRecordId) {
		this.pointRecordId = pointRecordId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getPointNumPer() {
		return pointNumPer;
	}
	public void setPointNumPer(String pointNumPer) {
		this.pointNumPer = pointNumPer;
	}
	public String getPointRate() {
		return pointRate;
	}
	public void setPointRate(String pointRate) {
		this.pointRate = pointRate;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    

}
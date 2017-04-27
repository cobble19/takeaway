package com.cobble.takeaway.pojo;

import java.util.Date;


public class PointEventPOJO extends BasePOJO {
    private Long pointEventId;
    private Long userId;
    private String authorizerAppId;
    private String eventName;
    private String pointNumPer;
    private String pointRate;
    private Date createDateTime;
	public Long getPointEventId() {
		return pointEventId;
	}
	public void setPointEventId(Long pointEventId) {
		this.pointEventId = pointEventId;
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
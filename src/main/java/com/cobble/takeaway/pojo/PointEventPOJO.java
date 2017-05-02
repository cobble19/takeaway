package com.cobble.takeaway.pojo;

import java.util.Date;


public class PointEventPOJO extends BasePOJO {
    private Long pointEventId;
    private Long userId;
    private String authorizerAppId;
    private String eventName;
    private Integer pointNumPer;
    private Integer pointRate;
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
	public Integer getPointNumPer() {
		return pointNumPer;
	}
	public void setPointNumPer(Integer pointNumPer) {
		this.pointNumPer = pointNumPer;
	}
	public Integer getPointRate() {
		return pointRate;
	}
	public void setPointRate(Integer pointRate) {
		this.pointRate = pointRate;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    

}
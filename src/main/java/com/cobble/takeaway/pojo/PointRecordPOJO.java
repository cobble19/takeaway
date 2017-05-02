package com.cobble.takeaway.pojo;

import java.util.Date;


public class PointRecordPOJO extends BasePOJO {
    private Long pointRecordId;
    private Long userId;
    private String openId;
    private String authorizerAppId;
    private Integer pointNum;
    private String pointReason;
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public Integer getPointNum() {
		return pointNum;
	}
	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}
	public String getPointReason() {
		return pointReason;
	}
	public void setPointReason(String pointReason) {
		this.pointReason = pointReason;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    

}
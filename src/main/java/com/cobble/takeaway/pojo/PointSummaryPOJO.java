package com.cobble.takeaway.pojo;

import java.util.Date;


public class PointSummaryPOJO extends BasePOJO {
    private Long pointSummaryId;
    private Long userId;
    private Integer pointTotal;
    private Integer pointUsed;
    private Integer pointRemainder;
    private Date createDateTime;
    
	public Long getPointSummaryId() {
		return pointSummaryId;
	}
	public void setPointSummaryId(Long pointSummaryId) {
		this.pointSummaryId = pointSummaryId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getPointTotal() {
		return pointTotal;
	}
	public void setPointTotal(Integer pointTotal) {
		this.pointTotal = pointTotal;
	}
	public Integer getPointUsed() {
		return pointUsed;
	}
	public void setPointUsed(Integer pointUsed) {
		this.pointUsed = pointUsed;
	}
	public Integer getPointRemainder() {
		return pointRemainder;
	}
	public void setPointRemainder(Integer pointRemainder) {
		this.pointRemainder = pointRemainder;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    

}
package com.cobble.takeaway.pojo;

import java.util.Date;


public class PointSummaryPOJO extends BasePOJO {
    private Long pointSummaryId;
    private Long userId;
    private String pointTotal;
    private String pointUsed;
    private String pointRemainder;
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
	public String getPointTotal() {
		return pointTotal;
	}
	public void setPointTotal(String pointTotal) {
		this.pointTotal = pointTotal;
	}
	public String getPointUsed() {
		return pointUsed;
	}
	public void setPointUsed(String pointUsed) {
		this.pointUsed = pointUsed;
	}
	public String getPointRemainder() {
		return pointRemainder;
	}
	public void setPointRemainder(String pointRemainder) {
		this.pointRemainder = pointRemainder;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    

}
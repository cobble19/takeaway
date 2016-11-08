package com.cobble.takeaway.pojo;

import java.util.Date;

public class AwardRecordSearchPOJO extends BaseSearchPOJO {
	private Long awardRecordId;
	private Long interactiveId;
	private Long awardId;
	private Long userId;
	private Date hitDateTime;
	private Date createDateTime;
	
	private String awardNameNot;
	
	public Long getAwardRecordId() {
		return awardRecordId;
	}
	public void setAwardRecordId(Long awardRecordId) {
		this.awardRecordId = awardRecordId;
	}
	public Long getInteractiveId() {
		return interactiveId;
	}
	public void setInteractiveId(Long interactiveId) {
		this.interactiveId = interactiveId;
	}
	public Long getAwardId() {
		return awardId;
	}
	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getHitDateTime() {
		return hitDateTime;
	}
	public void setHitDateTime(Date hitDateTime) {
		this.hitDateTime = hitDateTime;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getAwardNameNot() {
		return awardNameNot;
	}
	public void setAwardNameNot(String awardNameNot) {
		this.awardNameNot = awardNameNot;
	}
	
}

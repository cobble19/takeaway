package com.cobble.takeaway.pojo;

import java.util.Date;

import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;

public class AwardRecordPOJO extends BasePOJO {
	private Long awardRecordId;
	private Long interactiveId;
	private Long awardId;
	private Long userId;
	private Date hitDateTime;
	private Date createDateTime;

	private InteractivePOJO interactivePOJO;
	private UserPOJO userPOJO;
	private AwardPOJO awardPOJO;
	private WxPersonUserPOJO wxPersonUserPOJO;
	
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
	public UserPOJO getUserPOJO() {
		return userPOJO;
	}
	public void setUserPOJO(UserPOJO userPOJO) {
		this.userPOJO = userPOJO;
	}
	public AwardPOJO getAwardPOJO() {
		return awardPOJO;
	}
	public void setAwardPOJO(AwardPOJO awardPOJO) {
		this.awardPOJO = awardPOJO;
	}
	public WxPersonUserPOJO getWxPersonUserPOJO() {
		return wxPersonUserPOJO;
	}
	public void setWxPersonUserPOJO(WxPersonUserPOJO wxPersonUserPOJO) {
		this.wxPersonUserPOJO = wxPersonUserPOJO;
	}
	public InteractivePOJO getInteractivePOJO() {
		return interactivePOJO;
	}
	public void setInteractivePOJO(InteractivePOJO interactivePOJO) {
		this.interactivePOJO = interactivePOJO;
	}
	
}

package com.cobble.takeaway.pojo;

import java.util.Date;


public class RelInteractiveUserPOJO extends BasePOJO {
	private Long relInteractiveId;
    private Long interactiveId;
    private Long userId;
    private Integer answer;
    private Date createDateTime;
    
	public Long getRelInteractiveId() {
		return relInteractiveId;
	}
	public void setRelInteractiveId(Long relInteractiveId) {
		this.relInteractiveId = relInteractiveId;
	}
	public Long getInteractiveId() {
		return interactiveId;
	}
	public void setInteractiveId(Long interactiveId) {
		this.interactiveId = interactiveId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getAnswer() {
		return answer;
	}
	public void setAnswer(Integer answer) {
		this.answer = answer;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    
}
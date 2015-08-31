package com.cobble.takeaway.pojo;

import java.util.Date;


public class InteractiveApplySearchPOJO extends BaseSearchPOJO {
    private Long interactiveApplyId;
    private Long interactiveId;
    private Long userId;
    private Integer answer;
    private Date createDateTime;
    
	public Long getInteractiveApplyId() {
		return interactiveApplyId;
	}
	public void setInteractiveApplyId(Long interactiveApplyId) {
		this.interactiveApplyId = interactiveApplyId;
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
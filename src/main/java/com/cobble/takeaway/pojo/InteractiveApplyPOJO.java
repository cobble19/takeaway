package com.cobble.takeaway.pojo;

import java.util.Date;


public class InteractiveApplyPOJO extends BasePOJO {
    private Long interactiveApplyId;
    private Long interactiveId;
    private Long userId;
    private String username;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    
}
package com.cobble.takeaway.pojo;

import java.util.Date;


public class InteractiveApplySearchPOJO extends BaseSearchPOJO {
    private Long interactiveApplyId;
    private Long interactiveId;
    private Long userId;
    private String username;
    private Integer answer;
    private String verifyCode;
    private Integer isWinner;
    private Integer isVerified;
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
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public Integer getIsWinner() {
		return isWinner;
	}
	public void setIsWinner(Integer isWinner) {
		this.isWinner = isWinner;
	}
	public Integer getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Integer isVerified) {
		this.isVerified = isVerified;
	}
    
}
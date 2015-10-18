package com.cobble.takeaway.pojo;

import java.util.Date;


public class InteractivePOJO extends BasePOJO {
    private Long interactiveId;
    private String name;
    private String content;
    private String rule;
    private Date startDateTime;
    private Date endDateTime;
    private String prize;
    private Double answer;
    private Integer numOfWinner;
    
    private Long userId;
    private Date prizeEndDateTime;
    
    private UserPOJO userPOJO;
    
	public Long getInteractiveId() {
		return interactiveId;
	}
	public void setInteractiveId(Long interactiveId) {
		this.interactiveId = interactiveId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public Double getAnswer() {
		return answer;
	}
	public void setAnswer(Double answer) {
		this.answer = answer;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Integer getNumOfWinner() {
		return numOfWinner;
	}
	public void setNumOfWinner(Integer numOfWinner) {
		this.numOfWinner = numOfWinner;
	}
	public UserPOJO getUserPOJO() {
		return userPOJO;
	}
	public void setUserPOJO(UserPOJO userPOJO) {
		this.userPOJO = userPOJO;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getPrizeEndDateTime() {
		return prizeEndDateTime;
	}
	public void setPrizeEndDateTime(Date prizeEndDateTime) {
		this.prizeEndDateTime = prizeEndDateTime;
	}
    
}
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
    private Integer answer;
    private Integer numOfWinner;
    
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
	public Integer getAnswer() {
		return answer;
	}
	public void setAnswer(Integer answer) {
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
    
}
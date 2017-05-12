package com.cobble.takeaway.pojo;

import java.util.Date;


public class InteractiveSearchPOJO extends BaseSearchPOJO {
    private Long interactiveId;
    private String name;
    private String content;
    private Date startDateTime;
    private Date endDateTime;
    private String prize;
    private Double answer;
    
    private Integer numOfWinner;
    private Integer awardNumberPer;
    private String interactiveTYpe;
    
    private Integer status;
    
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getNumOfWinner() {
		return numOfWinner;
	}
	public void setNumOfWinner(Integer numOfWinner) {
		this.numOfWinner = numOfWinner;
	}
	public Integer getAwardNumberPer() {
		return awardNumberPer;
	}
	public void setAwardNumberPer(Integer awardNumberPer) {
		this.awardNumberPer = awardNumberPer;
	}
	public String getInteractiveTYpe() {
		return interactiveTYpe;
	}
	public void setInteractiveTYpe(String interactiveTYpe) {
		this.interactiveTYpe = interactiveTYpe;
	}
    
}
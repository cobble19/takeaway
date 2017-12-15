package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.weixin.WxRespMsgPOJO;


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
    private Long activityId;
    private Date prizeEndDateTime;
    
    private Integer status;
    private Integer awardNumberPer;
    private String interactiveType;
    private Date createDateTime;
    
    private UserPOJO userPOJO;
    
    private WxRespMsgPOJO wxRespMsgPOJO = new WxRespMsgPOJO();
    
    private List<AwardRecordPOJO> awardRecordPOJOs = new ArrayList<AwardRecordPOJO>();
    
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<AwardRecordPOJO> getAwardRecordPOJOs() {
		return awardRecordPOJOs;
	}
	public void setAwardRecordPOJOs(List<AwardRecordPOJO> awardRecordPOJOs) {
		this.awardRecordPOJOs = awardRecordPOJOs;
	}
	public Integer getAwardNumberPer() {
		return awardNumberPer;
	}
	public void setAwardNumberPer(Integer awardNumberPer) {
		this.awardNumberPer = awardNumberPer;
	}
	public String getInteractiveType() {
		return interactiveType;
	}
	public void setInteractiveType(String interactiveType) {
		this.interactiveType = interactiveType;
	}
	public WxRespMsgPOJO getWxRespMsgPOJO() {
		return wxRespMsgPOJO;
	}
	public void setWxRespMsgPOJO(WxRespMsgPOJO wxRespMsgPOJO) {
		this.wxRespMsgPOJO = wxRespMsgPOJO;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    
}
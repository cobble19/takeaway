package com.cobble.takeaway.pojo;

import java.util.Date;


public class VoteSearchPOJO extends BaseSearchPOJO {
    private Long voteId;
    private Long userId;
    private String title;
    private String content;
    private Integer voteType;
    private Integer publishType;
    private Date createDateTime;
    
    private Long activityId;
    private Integer period;
    private Integer numOfPeriod;
    
    private String activityTitle;
    private Long voteItemId;
    
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getVoteType() {
		return voteType;
	}
	public void setVoteType(Integer voteType) {
		this.voteType = voteType;
	}
	public Integer getPublishType() {
		return publishType;
	}
	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getNumOfPeriod() {
		return numOfPeriod;
	}
	public void setNumOfPeriod(Integer numOfPeriod) {
		this.numOfPeriod = numOfPeriod;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public Long getVoteItemId() {
		return voteItemId;
	}
	public void setVoteItemId(Long voteItemId) {
		this.voteItemId = voteItemId;
	}

}
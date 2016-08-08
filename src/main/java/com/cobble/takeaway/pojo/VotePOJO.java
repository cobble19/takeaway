package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class VotePOJO extends BasePOJO {
    private Long voteId;
    private Long userId;
    private String title;
    private String content;
    private Integer voteType;
    private Integer publishType;
    private Date createDateTime;
    
    private List<VoteItemPOJO> voteItemPOJOs = new ArrayList<VoteItemPOJO>();
    
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
	public List<VoteItemPOJO> getVoteItemPOJOs() {
		return voteItemPOJOs;
	}
	public void setVoteItemPOJOs(List<VoteItemPOJO> voteItemPOJOs) {
		this.voteItemPOJOs = voteItemPOJOs;
	}

}
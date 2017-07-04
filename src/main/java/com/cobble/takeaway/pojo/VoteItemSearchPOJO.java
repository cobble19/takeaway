package com.cobble.takeaway.pojo;

import java.util.Date;
import java.util.List;


public class VoteItemSearchPOJO extends BaseSearchPOJO {
    private Long voteItemId;
    private Long voteId;
    private String title;
    private String imgUrl;
    private String description;
    private Integer totalNum;
    private Date createDateTime;

    private Integer orderNo;
    private Integer approveFlag;
    
    private List<Long> apply2Ids;
    private Long apply2Id;
    
    private Long activityId;
    private List<Long> apply2AttrModelIds;
    
	public Long getVoteItemId() {
		return voteItemId;
	}
	public void setVoteItemId(Long voteItemId) {
		this.voteItemId = voteItemId;
	}
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public List<Long> getApply2Ids() {
		return apply2Ids;
	}
	public void setApply2Ids(List<Long> apply2Ids) {
		this.apply2Ids = apply2Ids;
	}
	public Long getApply2Id() {
		return apply2Id;
	}
	public void setApply2Id(Long apply2Id) {
		this.apply2Id = apply2Id;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public List<Long> getApply2AttrModelIds() {
		return apply2AttrModelIds;
	}
	public void setApply2AttrModelIds(List<Long> apply2AttrModelIds) {
		this.apply2AttrModelIds = apply2AttrModelIds;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getApproveFlag() {
		return approveFlag;
	}
	public void setApproveFlag(Integer approveFlag) {
		this.approveFlag = approveFlag;
	}

}
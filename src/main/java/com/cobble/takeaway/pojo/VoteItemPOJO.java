package com.cobble.takeaway.pojo;

import java.util.Date;

import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;


public class VoteItemPOJO extends BasePOJO {
    private Long voteItemId;
    private Long voteId;
    private String title;
    private String imgUrl;
    private String description;
    private Integer totalNum;
    private Date createDateTime;
    
    private Long apply2Id;
    private Apply2POJO apply2POJO;
    
    private WxPersonUserPOJO wxPersonUserPOJO = new WxPersonUserPOJO();
    
    private Boolean beenVoted = false;
    
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
	public Long getApply2Id() {
		return apply2Id;
	}
	public void setApply2Id(Long apply2Id) {
		this.apply2Id = apply2Id;
	}
	public Apply2POJO getApply2POJO() {
		return apply2POJO;
	}
	public void setApply2POJO(Apply2POJO apply2pojo) {
		apply2POJO = apply2pojo;
	}
	public Boolean getBeenVoted() {
		return beenVoted;
	}
	public void setBeenVoted(Boolean beenVoted) {
		this.beenVoted = beenVoted;
	}
	public WxPersonUserPOJO getWxPersonUserPOJO() {
		return wxPersonUserPOJO;
	}
	public void setWxPersonUserPOJO(WxPersonUserPOJO wxPersonUserPOJO) {
		this.wxPersonUserPOJO = wxPersonUserPOJO;
	}

}
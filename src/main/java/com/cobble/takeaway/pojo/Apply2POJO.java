package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Apply2POJO extends BasePOJO {
    private Long apply2Id;
    private Long userId;
    private String proxyOpenId;
    private String openId;
    private String unionId;
    private Long activityId;
    private String description;
    private Date createDateTime;
    
    private List<Apply2AttrPOJO> apply2AttrPOJOs = new ArrayList<Apply2AttrPOJO>();
    
//    private VoteItemPOJO voteItemPOJO = null;

	public Long getApply2Id() {
		return apply2Id;
	}

	public void setApply2Id(Long apply2Id) {
		this.apply2Id = apply2Id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Apply2AttrPOJO> getApply2AttrPOJOs() {
		return apply2AttrPOJOs;
	}

	public void setApply2AttrPOJOs(List<Apply2AttrPOJO> apply2AttrPOJOs) {
		this.apply2AttrPOJOs = apply2AttrPOJOs;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProxyOpenId() {
		return proxyOpenId;
	}

	public void setProxyOpenId(String proxyOpenId) {
		this.proxyOpenId = proxyOpenId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	/*public VoteItemPOJO getVoteItemPOJO() {
		return voteItemPOJO;
	}

	public void setVoteItemPOJO(VoteItemPOJO voteItemPOJO) {
		this.voteItemPOJO = voteItemPOJO;
	}*/
    
}
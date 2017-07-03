package com.cobble.takeaway.pojo;

import java.util.List;


public class Apply2SearchPOJO extends BaseSearchPOJO {
	private Long apply2Id;
    private String openId;
    private String unionId;
    private String description;
    
    private String wxIndexCode;

    private Long activityId;
    
    private List<Long> apply2Ids;

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

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getWxIndexCode() {
		return wxIndexCode;
	}

	public void setWxIndexCode(String wxIndexCode) {
		this.wxIndexCode = wxIndexCode;
	}

	public List<Long> getApply2Ids() {
		return apply2Ids;
	}

	public void setApply2Ids(List<Long> apply2Ids) {
		this.apply2Ids = apply2Ids;
	}
    
}
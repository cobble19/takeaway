package com.cobble.takeaway.pojo;


public class Apply2SearchPOJO extends BaseSearchPOJO {
	private Long apply2Id;
    private String openId;
    private String unionId;
    private String description;

    private Long activityId;

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
    
}
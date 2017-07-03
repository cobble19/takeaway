package com.cobble.takeaway.pojo;

import java.util.List;


public class Apply2AttrSearchPOJO extends BaseSearchPOJO {
	private Long apply2AttrId;
    private String apply2AttrModelName;
    private String apply2AttrData;
    private Integer orderNo;
    private Long activityId;
    private Long apply2Id;
    
    private List<Long> apply2AttrModelIds;
    private List<Long> apply2Ids;
    
	public Long getApply2AttrId() {
		return apply2AttrId;
	}
	public void setApply2AttrId(Long apply2AttrId) {
		this.apply2AttrId = apply2AttrId;
	}
	public String getApply2AttrModelName() {
		return apply2AttrModelName;
	}
	public void setApply2AttrModelName(String apply2AttrModelName) {
		this.apply2AttrModelName = apply2AttrModelName;
	}
	public String getApply2AttrData() {
		return apply2AttrData;
	}
	public void setApply2AttrData(String apply2AttrData) {
		this.apply2AttrData = apply2AttrData;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getApply2Id() {
		return apply2Id;
	}
	public void setApply2Id(Long apply2Id) {
		this.apply2Id = apply2Id;
	}
	public List<Long> getApply2AttrModelIds() {
		return apply2AttrModelIds;
	}
	public void setApply2AttrModelIds(List<Long> apply2AttrModelIds) {
		this.apply2AttrModelIds = apply2AttrModelIds;
	}
	public List<Long> getApply2Ids() {
		return apply2Ids;
	}
	public void setApply2Ids(List<Long> apply2Ids) {
		this.apply2Ids = apply2Ids;
	}
}
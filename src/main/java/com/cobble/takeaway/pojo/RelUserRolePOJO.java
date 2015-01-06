package com.cobble.takeaway.pojo;

public class RelUserRolePOJO extends BasePOJO {
    private Integer relUserId;
    private Integer userId;
    private Integer roleId;
    
	public Integer getRelUserId() {
		return relUserId;
	}
	public void setRelUserId(Integer relUserId) {
		this.relUserId = relUserId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
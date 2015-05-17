package com.cobble.takeaway.pojo;

public class RelUserRolePOJO extends BasePOJO {
    private Long relUserId;
    private Long userId;
    private Long roleId;
    
	public Long getRelUserId() {
		return relUserId;
	}
	public void setRelUserId(Long relUserId) {
		this.relUserId = relUserId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
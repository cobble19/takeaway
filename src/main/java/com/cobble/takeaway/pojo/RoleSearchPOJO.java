package com.cobble.takeaway.pojo;

public class RoleSearchPOJO extends BaseSearchPOJO {
    private Integer roleId;
    private String roleName;
    
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
}
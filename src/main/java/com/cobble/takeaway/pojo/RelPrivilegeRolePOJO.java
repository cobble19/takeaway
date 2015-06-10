package com.cobble.takeaway.pojo;

public class RelPrivilegeRolePOJO extends BasePOJO {
    private Integer relPrivilegeId;
    private Integer privilegeId;
    private Integer roleId;
    
	public Integer getRelPrivilegeId() {
		return relPrivilegeId;
	}
	public void setRelPrivilegeId(Integer relPrivilegeId) {
		this.relPrivilegeId = relPrivilegeId;
	}
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.List;

public class RolePOJO extends BasePOJO {
    private Integer roleId;
    private String roleName;
    
    private List<Integer> privilegeId;
    private Boolean checked = false;
    
    private List<PrivilegePOJO> privilegePOJOs = new ArrayList<PrivilegePOJO>();
    
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
	public List<PrivilegePOJO> getPrivilegePOJOs() {
		return privilegePOJOs;
	}
	public void setPrivilegePOJOs(List<PrivilegePOJO> privilegePOJOs) {
		this.privilegePOJOs = privilegePOJOs;
	}
	public List<Integer> getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(List<Integer> privilegeId) {
		this.privilegeId = privilegeId;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
    
}
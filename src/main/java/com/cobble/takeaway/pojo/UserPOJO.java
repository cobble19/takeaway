package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.List;


public class UserPOJO extends BasePOJO {
    private Long userId;
	private String username;
	private String password;
	private String email;
    private Boolean enable = true;
	private String userType;

    private List<Long> roleId;
	private Boolean checked = false;
	
    private List<RolePOJO> rolePOJOs = new ArrayList<RolePOJO>();
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public List<RolePOJO> getRolePOJOs() {
		return rolePOJOs;
	}
	public void setRolePOJOs(List<RolePOJO> rolePOJOs) {
		this.rolePOJOs = rolePOJOs;
	}
	public List<Long> getRoleId() {
		return roleId;
	}
	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}
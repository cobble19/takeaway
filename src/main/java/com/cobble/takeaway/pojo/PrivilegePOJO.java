package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.List;

public class PrivilegePOJO extends BasePOJO {
    private Integer privilegeId;
    private String privilegeName;
	private String url;
	
	private List<RolePOJO> rolePOJOs = new ArrayList<RolePOJO>();
	
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<RolePOJO> getRolePOJOs() {
		return rolePOJOs;
	}
	public void setRolePOJOs(List<RolePOJO> rolePOJOs) {
		this.rolePOJOs = rolePOJOs;
	}
	
	
}
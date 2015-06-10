package com.cobble.takeaway.pojo;



public class UserPersonPOJO extends BasePOJO {
    private Integer userPersonId;
	private String username;
	private String password;
    private Boolean enable;

	public Integer getUserPersonId() {
		return userPersonId;
	}
	public void setUserPersonId(Integer userPersonId) {
		this.userPersonId = userPersonId;
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
    
}
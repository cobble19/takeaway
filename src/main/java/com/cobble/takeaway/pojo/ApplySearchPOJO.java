package com.cobble.takeaway.pojo;


public class ApplySearchPOJO extends BaseSearchPOJO {
    private Long applyId;
    private String username;
    private String phone;
    
	public Long getApplyId() {
		return applyId;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
}
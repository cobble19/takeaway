package com.cobble.takeaway.pojo;


public class StatusPOJO extends BasePOJO {
	private Boolean success = false;
	private String desc = "";
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

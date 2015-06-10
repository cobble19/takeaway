package com.cobble.takeaway.pojo;

import java.util.List;

public class ExtjsPOJO<T> extends BasePOJO {
	private List<T> gridModelList;
	private Boolean success = true;
	private String message = "";
	private Integer total;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getGridModelList() {
		return gridModelList;
	}
	public void setGridModelList(List<T> gridModelList) {
		this.gridModelList = gridModelList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

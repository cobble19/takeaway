package com.cobble.takeaway.pojo;

import java.util.List;

public class DataTablesPOJO<T> {
	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private List<T> data;
	private String error;
	
	// options
	private String DT_RowId;
	private String DT_RowClass;
	private Object DT_RowData;
	private Object DT_RowAttr;
	
	// self add
	private Boolean success;
	
	
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getDT_RowId() {
		return DT_RowId;
	}
	public void setDT_RowId(String dT_RowId) {
		DT_RowId = dT_RowId;
	}
	public String getDT_RowClass() {
		return DT_RowClass;
	}
	public void setDT_RowClass(String dT_RowClass) {
		DT_RowClass = dT_RowClass;
	}
	public Object getDT_RowData() {
		return DT_RowData;
	}
	public void setDT_RowData(Object dT_RowData) {
		DT_RowData = dT_RowData;
	}
	public Object getDT_RowAttr() {
		return DT_RowAttr;
	}
	public void setDT_RowAttr(Object dT_RowAttr) {
		DT_RowAttr = dT_RowAttr;
	}
	
	
}

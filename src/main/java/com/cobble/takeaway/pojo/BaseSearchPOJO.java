package com.cobble.takeaway.pojo;

import java.io.Serializable;

public class BaseSearchPOJO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int PAGE = 1;
	public static final int START = 0;
	public static final int LIMIT = 10;
	
	private Boolean distinct = true;
	// paging
	private Integer page = PAGE;
	private Integer start = START;
	private Integer limit = LIMIT;
	
	private Boolean paginationFlage = true;
	
	// User 
	private Long userId;
	
	
	// all data
	
	public void withAllData() {
		this.limit = -1;
	}
	
	public Boolean getDistinct() {
		return distinct;
	}
	public void setDistinct(Boolean distinct) {
		this.distinct = distinct;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getPaginationFlage() {
		if (paginationFlage != null && !paginationFlage) {
			start = -1;
			limit = 0;
		}
		return paginationFlage;
	}

	public void setPaginationFlage(Boolean paginationFlage) {
		this.paginationFlage = paginationFlage;
	}
}

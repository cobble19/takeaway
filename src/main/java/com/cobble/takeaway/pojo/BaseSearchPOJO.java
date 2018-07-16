package com.cobble.takeaway.pojo;

import java.io.Serializable;
import java.util.Date;

public class BaseSearchPOJO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int PAGE = 1;
	public static final int START = -1;
	public static final int LIMIT = 0;
	
	private Boolean distinct = true;
	// paging
	private Integer page = PAGE;
	private Integer start = START;
	private Integer limit = LIMIT;
	
	private Boolean paginationFlage = false;
	
	// User 
	private Long userId;
	
	private Date startDateTime;
	private Date endDateTime;
	
	private String sort;
	private String orderBy = "desc";
	
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
		return paginationFlage;
	}

	public void setPaginationFlage(Boolean paginationFlage) {
		if (paginationFlage != null && !paginationFlage) {
			start = -1;
			limit = 0;
		}
		this.paginationFlage = paginationFlage;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}

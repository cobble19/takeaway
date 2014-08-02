package com.cobble.takeaway.pojo;

public class LocationAreaSearchPOJO extends BaseSearchPOJO {
    private Integer locationAreaId;
    private String name;
    private String description;
	public Integer getLocationAreaId() {
		return locationAreaId;
	}
	public void setLocationAreaId(Integer locationAreaId) {
		this.locationAreaId = locationAreaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
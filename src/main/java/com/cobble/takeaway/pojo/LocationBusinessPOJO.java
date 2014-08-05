package com.cobble.takeaway.pojo;

public class LocationBusinessPOJO extends BasePOJO {
    private Integer locationBusinessId;
    private String name;
    private String description;
	public Integer getLocationBusinessId() {
		return locationBusinessId;
	}
	public void setLocationBusinessId(Integer locationBusinessId) {
		this.locationBusinessId = locationBusinessId;
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
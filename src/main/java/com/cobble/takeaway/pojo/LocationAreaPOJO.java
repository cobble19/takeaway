package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.List;

public class LocationAreaPOJO extends BasePOJO {
    private Integer locationAreaId;
    private String name;
    private String description;
    private List<LocationBusinessPOJO> locationBusinessPOJOs = new ArrayList<LocationBusinessPOJO>();
    private Integer countFoodSeller;
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
	public List<LocationBusinessPOJO> getLocationBusinessPOJOs() {
		return locationBusinessPOJOs;
	}
	public void setLocationBusinessPOJOs(
			List<LocationBusinessPOJO> locationBusinessPOJOs) {
		this.locationBusinessPOJOs = locationBusinessPOJOs;
	}
	public Integer getCountFoodSeller() {
		return countFoodSeller;
	}
	public void setCountFoodSeller(Integer countFoodSeller) {
		this.countFoodSeller = countFoodSeller;
	}
}
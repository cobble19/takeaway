package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.List;

public class LocationBusinessPOJO extends BasePOJO {
    private Integer locationBusinessId;
    private String name;
    private String description;
    private List<FoodSellerPOJO> foodSellerPOJOs = new ArrayList<FoodSellerPOJO>();
    private Integer countFoodSeller;
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
	public List<FoodSellerPOJO> getFoodSellerPOJOs() {
		return foodSellerPOJOs;
	}
	public void setFoodSellerPOJOs(List<FoodSellerPOJO> foodSellerPOJOs) {
		this.foodSellerPOJOs = foodSellerPOJOs;
	}
	public Integer getCountFoodSeller() {
		return countFoodSeller;
	}
	public void setCountFoodSeller(Integer countFoodSeller) {
		this.countFoodSeller = countFoodSeller;
	}
}
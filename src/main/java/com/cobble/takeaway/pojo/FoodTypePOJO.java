package com.cobble.takeaway.pojo;

import java.util.ArrayList;
import java.util.List;

public class FoodTypePOJO extends BasePOJO {
	private Integer foodTypeId;
	private String name;
	
	private List<FoodPOJO> foodPOJOs = new ArrayList<FoodPOJO>();
	
	public Integer getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(Integer foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FoodPOJO> getFoodPOJOs() {
		return foodPOJOs;
	}
	public void setFoodPOJOs(List<FoodPOJO> foodPOJOs) {
		this.foodPOJOs = foodPOJOs;
	}
}

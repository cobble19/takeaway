package com.cobble.takeaway.pojo;

public class FoodSearchPOJO extends BaseSearchPOJO {
    private Integer foodId;
    private Integer foodSellerId;
    private String name;
    private Double unitPrice;
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getFoodSellerId() {
		return foodSellerId;
	}
	public void setFoodSellerId(Integer foodSellerId) {
		this.foodSellerId = foodSellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
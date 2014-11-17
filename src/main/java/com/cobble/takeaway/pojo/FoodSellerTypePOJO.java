package com.cobble.takeaway.pojo;

public class FoodSellerTypePOJO extends BasePOJO {
    private Integer foodSellerTypeId;
    private String name;
    private String icon;
	public Integer getFoodSellerTypeId() {
		return foodSellerTypeId;
	}
	public void setFoodSellerTypeId(Integer foodSellerTypeId) {
		this.foodSellerTypeId = foodSellerTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
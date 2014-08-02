package com.cobble.takeaway.pojo;

public class FoodSellerSearchPOJO extends BaseSearchPOJO {
    private Integer foodSellerId;
    private String name;
    private String phone;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
package com.cobble.takeaway.pojo;

public class FoodSellerPOJO extends BasePOJO {
    private Integer foodSellerId;
    private String name;
    private String phone;
    
    private RelBusinessSellerPOJO relBusinessSellerPOJO;
    
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
	public RelBusinessSellerPOJO getRelBusinessSellerPOJO() {
		return relBusinessSellerPOJO;
	}
	public void setRelBusinessSellerPOJO(RelBusinessSellerPOJO relBusinessSellerPOJO) {
		this.relBusinessSellerPOJO = relBusinessSellerPOJO;
	}
}
package com.cobble.takeaway.pojo;

public class RelFoodSellerTypePOJO extends BasePOJO {
    private Integer relFoodSellerTypeId;
    private Integer foodSellerTypeId;
    private Integer foodSellerId;
    
	public Integer getRelFoodSellerTypeId() {
		return relFoodSellerTypeId;
	}
	public void setRelFoodSellerTypeId(Integer relFoodSellerTypeId) {
		this.relFoodSellerTypeId = relFoodSellerTypeId;
	}
	public Integer getFoodSellerTypeId() {
		return foodSellerTypeId;
	}
	public void setFoodSellerTypeId(Integer foodSellerTypeId) {
		this.foodSellerTypeId = foodSellerTypeId;
	}
	public Integer getFoodSellerId() {
		return foodSellerId;
	}
	public void setFoodSellerId(Integer foodSellerId) {
		this.foodSellerId = foodSellerId;
	}
    
}
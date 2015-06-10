package com.cobble.takeaway.pojo;

public class RelBusinessSellerPOJO extends BasePOJO {
    private Integer relBusinessSellerId;
    private Integer locationBusinessId;
    private Integer foodSellerId;
    
	public Integer getRelBusinessSellerId() {
		return relBusinessSellerId;
	}
	public void setRelBusinessSellerId(Integer relBusinessSellerId) {
		this.relBusinessSellerId = relBusinessSellerId;
	}
	public Integer getLocationBusinessId() {
		return locationBusinessId;
	}
	public void setLocationBusinessId(Integer locationBusinessId) {
		this.locationBusinessId = locationBusinessId;
	}
	public Integer getFoodSellerId() {
		return foodSellerId;
	}
	public void setFoodSellerId(Integer foodSellerId) {
		this.foodSellerId = foodSellerId;
	}

}
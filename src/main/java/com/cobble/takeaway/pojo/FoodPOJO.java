package com.cobble.takeaway.pojo;

public class FoodPOJO extends BasePOJO {
    private Integer foodId;
    private Integer foodSellerId;
    private FoodSellerPOJO foodSellerPOJO;
	private String name;
    private Double unitPrice;
    private Integer foodTypeId;
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
	public FoodSellerPOJO getFoodSellerPOJO() {
		return foodSellerPOJO;
	}
	public void setFoodSellerPOJO(FoodSellerPOJO foodSellerPOJO) {
		this.foodSellerPOJO = foodSellerPOJO;
	}
	public Integer getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(Integer foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
}
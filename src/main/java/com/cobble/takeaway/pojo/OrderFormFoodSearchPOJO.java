package com.cobble.takeaway.pojo;

public class OrderFormFoodSearchPOJO extends BaseSearchPOJO {
    private Integer orderFormFoodId;
    private Integer orderFormId;
    private Integer foodId;
    private Integer amount;
	public Integer getOrderFormFoodId() {
		return orderFormFoodId;
	}
	public void setOrderFormFoodId(Integer orderFormFoodId) {
		this.orderFormFoodId = orderFormFoodId;
	}
	public Integer getOrderFormId() {
		return orderFormId;
	}
	public void setOrderFormId(Integer orderFormId) {
		this.orderFormId = orderFormId;
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
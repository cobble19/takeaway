package com.cobble.takeaway.pojo;

import java.util.Date;

public class OrderFormSearchPOJO extends BaseSearchPOJO {
    private Integer orderFormId;
    private Integer foodSellerId;
    private Date defineTime;
	public Integer getOrderFormId() {
		return orderFormId;
	}
	public void setOrderFormId(Integer orderFormId) {
		this.orderFormId = orderFormId;
	}
	public Integer getFoodSellerId() {
		return foodSellerId;
	}
	public void setFoodSellerId(Integer foodSellerId) {
		this.foodSellerId = foodSellerId;
	}
	public Date getDefineTime() {
		return defineTime;
	}
	public void setDefineTime(Date defineTime) {
		this.defineTime = defineTime;
	}
}
package com.cobble.takeaway.pojo;

public class FoodEntity extends BaseEntity {
    private Integer foodId;
    private Integer foodMenuId;
    private String name;
    private Double unitPrice;
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getFoodMenuId() {
		return foodMenuId;
	}
	public void setFoodMenuId(Integer foodMenuId) {
		this.foodMenuId = foodMenuId;
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
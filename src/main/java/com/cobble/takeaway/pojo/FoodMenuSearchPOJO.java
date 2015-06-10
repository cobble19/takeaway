package com.cobble.takeaway.pojo;

public class FoodMenuSearchPOJO extends BaseSearchPOJO {
    private Integer foodMenuId;
    private Integer foodSellerId;
    private String name;
    public Integer getFoodMenuId() {
        return foodMenuId;
    }

    public void setFoodMenuId(Integer foodMenuId) {
        this.foodMenuId = foodMenuId;
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
}
package com.cobble.takeaway.pojo;

import java.util.List;

public class FoodSellerPOJO extends BasePOJO {
    private Integer foodSellerId;
    private String name;
    private String phone;
    
    private RelBusinessSellerPOJO relBusinessSellerPOJO;
    private RelAreaSellerPOJO relAreaSellerPOJO;
    private RelFoodSellerTypePOJO relFoodSellerTypePOJO;
    
    private LocationBusinessPOJO locationBusinessPOJO;
    private LocationAreaPOJO locationAreaPOJO;
    private FoodSellerTypePOJO foodSellerTypePOJO;
    
    //1-n
    private List<LocationBusinessPOJO> locationBusinessPOJOs;
    private List<LocationAreaPOJO> locationAreaPOJOs;
    
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
	public RelAreaSellerPOJO getRelAreaSellerPOJO() {
		return relAreaSellerPOJO;
	}
	public void setRelAreaSellerPOJO(RelAreaSellerPOJO relAreaSellerPOJO) {
		this.relAreaSellerPOJO = relAreaSellerPOJO;
	}
	public List<LocationBusinessPOJO> getLocationBusinessPOJOs() {
		return locationBusinessPOJOs;
	}
	public void setLocationBusinessPOJOs(
			List<LocationBusinessPOJO> locationBusinessPOJOs) {
		this.locationBusinessPOJOs = locationBusinessPOJOs;
	}
	public List<LocationAreaPOJO> getLocationAreaPOJOs() {
		return locationAreaPOJOs;
	}
	public void setLocationAreaPOJOs(List<LocationAreaPOJO> locationAreaPOJOs) {
		this.locationAreaPOJOs = locationAreaPOJOs;
	}
	public LocationBusinessPOJO getLocationBusinessPOJO() {
		return locationBusinessPOJO;
	}
	public void setLocationBusinessPOJO(LocationBusinessPOJO locationBusinessPOJO) {
		this.locationBusinessPOJO = locationBusinessPOJO;
	}
	public LocationAreaPOJO getLocationAreaPOJO() {
		return locationAreaPOJO;
	}
	public void setLocationAreaPOJO(LocationAreaPOJO locationAreaPOJO) {
		this.locationAreaPOJO = locationAreaPOJO;
	}
	public FoodSellerTypePOJO getFoodSellerTypePOJO() {
		return foodSellerTypePOJO;
	}
	public void setFoodSellerTypePOJO(FoodSellerTypePOJO foodSellerTypePOJO) {
		this.foodSellerTypePOJO = foodSellerTypePOJO;
	}
	public RelFoodSellerTypePOJO getRelFoodSellerTypePOJO() {
		return relFoodSellerTypePOJO;
	}
	public void setRelFoodSellerTypePOJO(RelFoodSellerTypePOJO relFoodSellerTypePOJO) {
		this.relFoodSellerTypePOJO = relFoodSellerTypePOJO;
	}
}
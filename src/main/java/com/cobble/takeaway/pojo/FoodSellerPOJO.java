package com.cobble.takeaway.pojo;

import java.util.List;

public class FoodSellerPOJO extends BasePOJO {
    private Integer foodSellerId;
    private String name;
    private String phone;
    private String logoUrl;
    private String businessHours;
    private String deliveryArea;
    private String deliveryPriceMin;
    private Double deliveryFee;
    private String address;
    private String note;
    private String mapAddr;
    
    private RelBusinessSellerPOJO relBusinessSellerPOJO;
    private RelAreaSellerPOJO relAreaSellerPOJO;
    private RelFoodSellerTypePOJO relFoodSellerTypePOJO;
    
    private LocationBusinessPOJO locationBusinessPOJO;
    private LocationAreaPOJO locationAreaPOJO;
    private FoodSellerTypePOJO foodSellerTypePOJO;
    
    //1-n
    private List<LocationBusinessPOJO> locationBusinessPOJOs;
    private List<LocationAreaPOJO> locationAreaPOJOs;
    
    // display food seller details
    private List<FoodTypePOJO> foodTypePOJOs;
    private List<FoodPOJO> foodPOJOs;
    
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((foodSellerId == null) ? 0 : foodSellerId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodSellerPOJO other = (FoodSellerPOJO) obj;
		if (foodSellerId == null) {
			if (other.foodSellerId != null)
				return false;
		} else if (!foodSellerId.equals(other.foodSellerId))
			return false;
		return true;
	}
	public String getBusinessHours() {
		return businessHours;
	}
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	public String getDeliveryArea() {
		return deliveryArea;
	}
	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}
	public String getDeliveryPriceMin() {
		return deliveryPriceMin;
	}
	public void setDeliveryPriceMin(String deliveryPriceMin) {
		this.deliveryPriceMin = deliveryPriceMin;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<FoodTypePOJO> getFoodTypePOJOs() {
		return foodTypePOJOs;
	}
	public void setFoodTypePOJOs(List<FoodTypePOJO> foodTypePOJOs) {
		this.foodTypePOJOs = foodTypePOJOs;
	}
	public List<FoodPOJO> getFoodPOJOs() {
		return foodPOJOs;
	}
	public void setFoodPOJOs(List<FoodPOJO> foodPOJOs) {
		this.foodPOJOs = foodPOJOs;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public Double getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public String getMapAddr() {
		return mapAddr;
	}
	public void setMapAddr(String mapAddr) {
		this.mapAddr = mapAddr;
	}
}
package com.cobble.takeaway.pojo.ecommerce;

import com.cobble.takeaway.pojo.BasePOJO;

public class EcOrderCallWxPayParamPOJO extends BasePOJO {
	private String authorizerAppId;
	private Long productId;
	private Integer unitPrice;
	private Integer quantity;
	
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "EcOrderCallWxPayParamPOJO{" +
				"authorizerAppId='" + authorizerAppId + '\'' +
				", productId=" + productId +
				", unitPrice=" + unitPrice +
				", quantity=" + quantity +
				'}';
	}
}

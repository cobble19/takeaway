package com.cobble.takeaway.pojo.ecommerce;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class EcOrderPOJO extends BasePOJO {
	private Long orderId;
	private Long userId;
	private String openId;
	private Long productId;
	// 分
	private Integer unitPrice;
	private Integer quantity;
	private Double discountRate;
	private Date createDateTime;
	private Date lastModifiedDateTime;
	
	private EcProductPOJO ecProductPOJO = new EcProductPOJO();
	
	public EcProductPOJO getEcProductPOJO() {
		return ecProductPOJO;
	}
	public void setEcProductPOJO(EcProductPOJO ecProductPOJO) {
		this.ecProductPOJO = ecProductPOJO;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
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
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}
	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}
	
}

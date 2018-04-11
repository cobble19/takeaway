package com.cobble.takeaway.pojo.ecommerce;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class EcProductPOJO extends BasePOJO {
	private Long productId;
	private Long userId;
	private String authorizerAppId;
	private String productName;
	private Integer quantityTotal;
	private Integer quantityStock;
	// 分
	private Integer unitPrice;
	
	private String wxCardId;
	
	private Date createDateTime;
	private Date lastModifiedDateTime;
	
	public String getWxCardId() {
		return wxCardId;
	}
	public void setWxCardId(String wxCardId) {
		this.wxCardId = wxCardId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantityTotal() {
		return quantityTotal;
	}
	public void setQuantityTotal(Integer quantityTotal) {
		this.quantityTotal = quantityTotal;
	}
	public Integer getQuantityStock() {
		return quantityStock;
	}
	public void setQuantityStock(Integer quantityStock) {
		this.quantityStock = quantityStock;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
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

package com.cobble.takeaway.pojo.ecommerce;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class EcProductPOJO extends BasePOJO {
	private Long productId;
	private Long userId;
	private String authorizerAppId;
	private String productName;
	private String imgUrl;
	private String buyAbout;
	private Integer quantityTotal;
	private Integer quantityStock;
	// 分
	private Integer unitPrice;
	private Integer limitNumEveryone;
	private Integer limitNumDay;
	
	private String wxCardId;
	private Integer wxCardStock;
	private Integer wxCardLimitNumEveryone;

	private Integer needSubscribe;
	private Integer activeFlag;

	private Date startDateTime;
	private Date endDateTime;
	
	private Date createDateTime;
	private Date lastModifiedDateTime;

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getLimitNumDay() {
		return limitNumDay;
	}

	public void setLimitNumDay(Integer limitNumDay) {
		this.limitNumDay = limitNumDay;
	}

	public Integer getWxCardLimitNumEveryone() {
		return wxCardLimitNumEveryone;
	}

	public void setWxCardLimitNumEveryone(Integer wxCardLimitNumEveryone) {
		this.wxCardLimitNumEveryone = wxCardLimitNumEveryone;
	}

	public Integer getLimitNumEveryone() {
		return limitNumEveryone;
	}

	public void setLimitNumEveryone(Integer limitNumEveryone) {
		this.limitNumEveryone = limitNumEveryone;
	}

	public Integer getWxCardStock() {
		return wxCardStock;
	}

	public void setWxCardStock(Integer wxCardStock) {
		this.wxCardStock = wxCardStock;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Integer getNeedSubscribe() {
		return needSubscribe;
	}
	public void setNeedSubscribe(Integer needSubscribe) {
		this.needSubscribe = needSubscribe;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getBuyAbout() {
		return buyAbout;
	}
	public void setBuyAbout(String buyAbout) {
		this.buyAbout = buyAbout;
	}
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

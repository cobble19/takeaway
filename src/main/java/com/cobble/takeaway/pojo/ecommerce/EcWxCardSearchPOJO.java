package com.cobble.takeaway.pojo.ecommerce;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

import java.util.Date;
import java.util.List;

public class EcWxCardSearchPOJO extends BaseSearchPOJO {
	private Long ecWxCardId;
	private String authorizerAppId;
	private String openId;
	private Long userId;
	private Long wpOrderId;
	private Long ecOrderId;
	private Long ecProductId;
	private String cardId;
	private String cardCode;
	private Integer cardAcquireFlag;
	private String cardStatusWx;
	private String rawData;
	private Date createDateTime;
	private Date lastModifiedDateTime;

	private List<Long> ecOrderIds;

	public Integer getCardAcquireFlag() {
		return cardAcquireFlag;
	}

	public void setCardAcquireFlag(Integer cardAcquireFlag) {
		this.cardAcquireFlag = cardAcquireFlag;
	}

	public String getCardStatusWx() {
		return cardStatusWx;
	}

	public void setCardStatusWx(String cardStatusWx) {
		this.cardStatusWx = cardStatusWx;
	}

	public List<Long> getEcOrderIds() {
		return ecOrderIds;
	}

	public void setEcOrderIds(List<Long> ecOrderIds) {
		this.ecOrderIds = ecOrderIds;
	}

	public Long getEcWxCardId() {
		return ecWxCardId;
	}

	public void setEcWxCardId(Long ecWxCardId) {
		this.ecWxCardId = ecWxCardId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getAuthorizerAppId() {
		return authorizerAppId;
	}

	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getWpOrderId() {
		return wpOrderId;
	}

	public void setWpOrderId(Long wpOrderId) {
		this.wpOrderId = wpOrderId;
	}

	public Long getEcOrderId() {
		return ecOrderId;
	}

	public void setEcOrderId(Long ecOrderId) {
		this.ecOrderId = ecOrderId;
	}

	public Long getEcProductId() {
		return ecProductId;
	}

	public void setEcProductId(Long ecProductId) {
		this.ecProductId = ecProductId;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
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

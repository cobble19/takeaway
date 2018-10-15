package com.cobble.takeaway.pojo.ecommerce;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

import java.util.Date;

public class EcWxCardBaseSearchPOJO extends BaseSearchPOJO {
	private Long ecWxCardBaseId;
	private String authorizerAppId;
	private String openId;
	private Long userId;
	private String cardId;
	private String cardBaseResult;
	private String description;
	private Date createDateTime;
	private Date lastModifiedDateTime;

	public Long getEcWxCardBaseId() {
		return ecWxCardBaseId;
	}

	public void setEcWxCardBaseId(Long ecWxCardBaseId) {
		this.ecWxCardBaseId = ecWxCardBaseId;
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

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardBaseResult() {
		return cardBaseResult;
	}

	public void setCardBaseResult(String cardBaseResult) {
		this.cardBaseResult = cardBaseResult;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

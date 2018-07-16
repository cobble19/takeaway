package com.cobble.takeaway.pojo.ecommerce;

import com.cobble.takeaway.pojo.BasePOJO;
import com.cobble.takeaway.pojo.BaseSearchPOJO;

import java.util.Date;

public class EcCartSearchPOJO extends BaseSearchPOJO {
	private Long ecCartId;
	private Long userId;
	private String openId;
	private String authorizerAppId;
	private Long productId;
	private String productName;
	private Date createDateTime;
	private Date lastModifiedDateTime;

	public Long getEcCartId() {
		return ecCartId;
	}

	public void setEcCartId(Long ecCartId) {
		this.ecCartId = ecCartId;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

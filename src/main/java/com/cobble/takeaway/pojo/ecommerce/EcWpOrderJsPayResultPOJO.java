package com.cobble.takeaway.pojo.ecommerce;

import com.cobble.takeaway.pojo.BasePOJO;

public class EcWpOrderJsPayResultPOJO extends BasePOJO {
//	private String authorizerAppId;
//	private String openId;
	private Long orderId;
	private Long wpOrderId;
	private String resultCode;
	private String resultMsg;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getWpOrderId() {
		return wpOrderId;
	}

	public void setWpOrderId(Long wpOrderId) {
		this.wpOrderId = wpOrderId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	@Override
	public String toString() {
		return "EcWpOrderJsPayResultPOJO{" +
				"orderId=" + orderId +
				", wpOrderId=" + wpOrderId +
				", resultCode='" + resultCode + '\'' +
				", resultMsg='" + resultMsg + '\'' +
				'}';
	}
}

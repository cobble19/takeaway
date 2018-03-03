package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayRefundResultNotifyApiPOJO extends BasePOJO {
	@XmlElement(name="return_code")
	private String returnCode;
	@XmlElement(name="return_msg")
	private String returnMsg;

	@XmlElement(name="appid")
	private String appId;
	@XmlElement(name="mch_id")
	private String mchId;
	@XmlElement(name="nonce_str")
	private String nonceStr;
	@XmlElement(name="req_info")
	private String reqInfo;

	@XmlElement(name="transaction_id")
	private String transactionId;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="refund_id")
	private String refundId;
	@XmlElement(name="out_refund_no")
	private String outRefundNo;
	@XmlElement(name="total_fee")
	private String totalFee;
	@XmlElement(name="settlement_total_fee")
	private String settlementTotalFee;
	@XmlElement(name="refund_fee")
	private String refundFee;
	@XmlElement(name="settlement_refund_fee")
	private String settlementRefundFee;
	@XmlElement(name="refund_status")
	private String refundStatus;
	@XmlElement(name="success_time")
	private String successTime;
	@XmlElement(name="refund_recv_account")
	private String refundRecvAccount;
	@XmlElement(name="refund_account")
	private String refundAccount;
	@XmlElement(name="refund_request_source")
	private String refund;
	
}

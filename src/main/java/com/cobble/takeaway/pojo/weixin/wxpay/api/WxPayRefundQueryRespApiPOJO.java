package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayRefundQueryRespApiPOJO extends BasePOJO {
	@XmlElement(name="return_code")
	private String returnCode;
	@XmlElement(name="return_msg")
	private String returnMsg;

	@XmlElement(name="result_code")
	private String resultCode;
	@XmlElement(name="err_code")
	private String errCode;
	@XmlElement(name="err_code_des")
	private String errCodeDes;
	@XmlElement(name="appid")
	private String appId;
	@XmlElement(name="mch_id")
	private String mchId;
	@XmlElement(name="nonce_str")
	private String nonceStr;
	@XmlElement(name="sign")
	private String sign;
	
	@XmlElement(name="total_refund_count")
	private String totalRefundCount;
	@XmlElement(name="transaction_id")
	private String transactionId;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="total_fee")
	private String totalFee;
	@XmlElement(name="settlement_total_fee")
	private String settlementTotalFee;
	@XmlElement(name="fee_type")
	private String feeType;
	@XmlElement(name="cash_fee")
	private String cashFee;
	@XmlElement(name="refund_count")
	private String refundCount;
	@XmlElement(name="out_refund_no_$n")
	private String outRefundNo$n;
	@XmlElement(name="refund_id_$n")
	private String refundId$n;
	@XmlElement(name="refund_channel_$n")
	private String refundChannel$n;
	@XmlElement(name="refund_fee_$n")
	private String refundFee$n;
	@XmlElement(name="settlement_refund_fee_$n")
	private String settlementRefundFee$n;
	@XmlElement(name="coupon_type_$n_$m")
	private String couponType$n$m;
	@XmlElement(name="coupon_refund_fee_$n")
	private String couponRefundFee$n;
	@XmlElement(name="coupon_refund_count_$n")
	private String couponRefundCount$n;
	@XmlElement(name="coupon_refund_id_$n_$m")
	private String couponRefundId$n$m;
	@XmlElement(name="coupon_refund_fee_$n_$m")
	private String couponRefundFee$n$m;
	@XmlElement(name="refund_status_$n")
	private String refundStatus$n;
	@XmlElement(name="refund_account_$n")
	private String refundAccount$n;
	@XmlElement(name="refund_recv_accout_$n")
	private String refundRecvAccount$n;
	@XmlElement(name="refund_success_time_$n")
	private String refundSuccessTime$n;
	
}

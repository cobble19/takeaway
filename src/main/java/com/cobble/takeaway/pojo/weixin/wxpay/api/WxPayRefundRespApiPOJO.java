package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayRefundRespApiPOJO extends BasePOJO {
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

	@XmlElement(name="transaction_id")
	private String transactionId;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="out_refund_no")
	private String outRefundNo;
	@XmlElement(name="refund_id")
	private String refundId;
	@XmlElement(name="refund_fee")
	private String refundFee;
	@XmlElement(name="settlement_refund_fee")
	private String settlementRefundFee;
	@XmlElement(name="total_fee")
	private String totalFee;
	@XmlElement(name="settlement_total_fee")
	private String settlementTotalFee;
	@XmlElement(name="fee_type")
	private String feeType;
	@XmlElement(name="cash_fee")
	private String cashFee;
	@XmlElement(name="cash_fee_type")
	private String cashFeeType;
	@XmlElement(name="cash_refund_fee")
	private String cashRefundFee;
	@XmlElement(name="coupon_type_$n")
	private String couponType$n;
	@XmlElement(name="coupon_refund_fee")
	private String couponRefundFee;
	@XmlElement(name="coupon_refund_fee_$n")
	private String couponRefundFee$n;
	@XmlElement(name="coupon_refund_count")
	private String couponRefundCount;
	@XmlElement(name="coupon_refund_id_$n")
	private String couponRefundId$n;
}

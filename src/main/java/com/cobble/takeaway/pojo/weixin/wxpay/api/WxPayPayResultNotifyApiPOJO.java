package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayPayResultNotifyApiPOJO extends BasePOJO {
	@XmlElement(name="return_code")
	private String returnCode;
	@XmlElement(name="return_msg")
	private String returnMsg;

	@XmlElement(name="appid")
	private String appId;
	@XmlElement(name="mch_id")
	private String mchId;
	@XmlElement(name="device_info")
	private String deviceInfo;
	@XmlElement(name="nonce_str")
	private String nonceStr;
	@XmlElement(name="sign")
	private String sign;
	@XmlElement(name="sign_type")
	private String signType;
	@XmlElement(name="result_code")
	private String resultCode;
	@XmlElement(name="err_code")
	private String errCode;
	@XmlElement(name="err_code_des")
	private String errCodeDes;
	@XmlElement(name="openid")
	private String openID;
	@XmlElement(name="is_subscribe")
	private String isSubscribe;
	@XmlElement(name="trade_type")
	private String tradeType;
	@XmlElement(name="bank_type")
	private String bankType;
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
	@XmlElement(name="coupon_fee")
	private String couponFee;
	@XmlElement(name="coupon_count")
	private String couponCount;
	@XmlElement(name="coupon_type_$n")
	private String couponType$n;
	@XmlElement(name="coupon_id_$n")
	private String couponId$n;
	@XmlElement(name="coupon_fee_$n")
	private String couponFee$n;
	@XmlElement(name="transaction_id")
	private String transactionId;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="attach")
	private String attach;
	@XmlElement(name="time_end")
	private String timeEnd;
	
}

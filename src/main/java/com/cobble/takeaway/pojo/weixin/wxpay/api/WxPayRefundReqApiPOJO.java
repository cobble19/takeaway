package com.cobble.takeaway.pojo.weixin.wxpay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxPayRefundReqApiPOJO extends BasePOJO {
	/*data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("refund_fee", total_fee);
        data.put("refund_fee_type", "CNY");
        data.put("op_user_id", config.getMchID());*/
	@XmlElement(name="appid")
	private String appId;
	@XmlElement(name="mch_id")
	private String mchId;
	@XmlElement(name="nonce_str")
	private String nonceStr;
	@XmlElement(name="sign")
	private String sign;
	@XmlElement(name="sign_type")
	private String signType;
	@XmlElement(name="transaction_id")
	private String transactionId;
	@XmlElement(name="out_trade_no")
	private String outTradeNo;
	@XmlElement(name="out_refund_no")
	private String ourtRefundNo;
	@XmlElement(name="total_fee")
	private String totalFee;
	@XmlElement(name="refund_fee")
	private String refundFee;
	@XmlElement(name="refund_fee_type")
	private String refundFeeType;
	@XmlElement(name="refund_desc")
	private String refundDesc;
	@XmlElement(name="refund_account")
	private String refundAccount;
	@XmlElement(name="op_user_id")
	private String opUserId;
	
}

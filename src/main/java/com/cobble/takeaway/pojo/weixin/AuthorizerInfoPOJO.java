package com.cobble.takeaway.pojo.weixin;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizerInfoPOJO extends BasePOJO {
	@JsonProperty(value="nick_name")
    private String nickName;
	@JsonProperty(value="head_img")
    private String headImg;
	@JsonProperty(value="service_type_info")
    private TypeInfoPOJO serviceTypeInfoPOJO;
	@JsonProperty(value="verify_type_info")
    private TypeInfoPOJO verifyTypeInfoPOJO;
	@JsonProperty(value="user_name")
    private String userName;
	@JsonProperty(value="alias")
    private String alias;
	@JsonProperty(value="qrcode_url")
    private String qrcodeUrl;
	@JsonProperty(value="business_info")
    private BusinessInfoPOJO businessInfoPOJO;
	@JsonProperty(value="idc")
	private Integer idc;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public TypeInfoPOJO getServiceTypeInfoPOJO() {
		return serviceTypeInfoPOJO;
	}
	public void setServiceTypeInfoPOJO(TypeInfoPOJO serviceTypeInfoPOJO) {
		this.serviceTypeInfoPOJO = serviceTypeInfoPOJO;
	}
	public TypeInfoPOJO getVerifyTypeInfoPOJO() {
		return verifyTypeInfoPOJO;
	}
	public void setVerifyTypeInfoPOJO(TypeInfoPOJO verifyTypeInfoPOJO) {
		this.verifyTypeInfoPOJO = verifyTypeInfoPOJO;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BusinessInfoPOJO getBusinessInfoPOJO() {
		return businessInfoPOJO;
	}
	public void setBusinessInfoPOJO(BusinessInfoPOJO businessInfoPOJO) {
		this.businessInfoPOJO = businessInfoPOJO;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	public Integer getIdc() {
		return idc;
	}
	public void setIdc(Integer idc) {
		this.idc = idc;
	}
}

package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizerInfoApiPOJO extends BasePOJO {
	@JsonProperty(value="nick_name")
    private String nickName;
	@JsonProperty(value="head_img")
    private String headImg;
	@JsonProperty(value="service_type_info")
    private TypeInfoApiPOJO serviceTypeInfoPOJO;
	@JsonProperty(value="verify_type_info")
    private TypeInfoApiPOJO verifyTypeInfoPOJO;
	@JsonProperty(value="user_name")
    private String userName;
	@JsonProperty(value="alias")
    private String alias;
	@JsonProperty(value="qrcode_url")
    private String qrcodeUrl;
	@JsonProperty(value="business_info")
    private BusinessInfoApiPOJO businessInfoPOJO;
	@JsonProperty(value="idc")
	private Integer idc;
	@JsonProperty(value="principal_name")
	private String principalName;
	@JsonProperty(value="signature")
	private String signature;
	
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
	public TypeInfoApiPOJO getServiceTypeInfoPOJO() {
		return serviceTypeInfoPOJO;
	}
	public void setServiceTypeInfoPOJO(TypeInfoApiPOJO serviceTypeInfoPOJO) {
		this.serviceTypeInfoPOJO = serviceTypeInfoPOJO;
	}
	public TypeInfoApiPOJO getVerifyTypeInfoPOJO() {
		return verifyTypeInfoPOJO;
	}
	public void setVerifyTypeInfoPOJO(TypeInfoApiPOJO verifyTypeInfoPOJO) {
		this.verifyTypeInfoPOJO = verifyTypeInfoPOJO;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BusinessInfoApiPOJO getBusinessInfoPOJO() {
		return businessInfoPOJO;
	}
	public void setBusinessInfoPOJO(BusinessInfoApiPOJO businessInfoPOJO) {
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
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}

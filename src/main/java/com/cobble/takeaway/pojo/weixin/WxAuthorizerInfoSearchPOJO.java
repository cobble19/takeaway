package com.cobble.takeaway.pojo.weixin;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

public class WxAuthorizerInfoSearchPOJO extends BaseSearchPOJO {
	private Long wxAuthorizerInfoId;
	private Long userId;
    private String nickName;
    private String headImg;
    private Integer serviceTypeInfo;
    private Integer verifyTypeInfo;
    private String userName;
    private String alias;
    private String qrcodeUrl;
    private String businessInfo;
	private Integer idc;
    private String authorizerAppId;
    private String funcInfo;
    
    private String controlCode;
    
	public Long getWxAuthorizerInfoId() {
		return wxAuthorizerInfoId;
	}
	public void setWxAuthorizerInfoId(Long wxAuthorizerInfoId) {
		this.wxAuthorizerInfoId = wxAuthorizerInfoId;
	}
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
	public Integer getServiceTypeInfo() {
		return serviceTypeInfo;
	}
	public void setServiceTypeInfo(Integer serviceTypeInfo) {
		this.serviceTypeInfo = serviceTypeInfo;
	}
	public Integer getVerifyTypeInfo() {
		return verifyTypeInfo;
	}
	public void setVerifyTypeInfo(Integer verifyTypeInfo) {
		this.verifyTypeInfo = verifyTypeInfo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}
	public Integer getIdc() {
		return idc;
	}
	public void setIdc(Integer idc) {
		this.idc = idc;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public String getFuncInfo() {
		return funcInfo;
	}
	public void setFuncInfo(String funcInfo) {
		this.funcInfo = funcInfo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getControlCode() {
		return controlCode;
	}
	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}
    
	
}

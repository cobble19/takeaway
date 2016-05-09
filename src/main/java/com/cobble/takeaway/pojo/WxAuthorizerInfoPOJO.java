package com.cobble.takeaway.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerInfoPOJO extends BasePOJO {
	@JsonProperty(value="authorizer_info")
    private AuthorizerInfoPOJO authorizerInfoPOJO;
	@JsonProperty(value="qrcode_url")
    private String qrcodeUrl;
	@JsonProperty(value="authorization_info")
    private AuthorizationInfoPOJO authorizationInfo;
	
	class AuthorizerInfoPOJO extends BasePOJO {
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
		@JsonProperty(value="business_info")
	    private BusinessInfoPOJO businessInfoPOJO;
		@JsonProperty(value="alias")
	    private String alias;
		
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
	}
	
	class TypeInfoPOJO extends BasePOJO {
		@JsonProperty(value="id")
	    private Integer id;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}
	
	class BusinessInfoPOJO extends BasePOJO {
		@JsonProperty(value="open_store")
	    private Integer openStore;
		@JsonProperty(value="open_scan")
	    private Integer openScan;
		@JsonProperty(value="open_pay")
	    private Integer openPay;
		@JsonProperty(value="open_card")
	    private Integer openCard;
		@JsonProperty(value="open_shake")
	    private Integer openShake;
		public Integer getOpenStore() {
			return openStore;
		}
		public void setOpenStore(Integer openStore) {
			this.openStore = openStore;
		}
		public Integer getOpenScan() {
			return openScan;
		}
		public void setOpenScan(Integer openScan) {
			this.openScan = openScan;
		}
		public Integer getOpenPay() {
			return openPay;
		}
		public void setOpenPay(Integer openPay) {
			this.openPay = openPay;
		}
		public Integer getOpenCard() {
			return openCard;
		}
		public void setOpenCard(Integer openCard) {
			this.openCard = openCard;
		}
		public Integer getOpenShake() {
			return openShake;
		}
		public void setOpenShake(Integer openShake) {
			this.openShake = openShake;
		}
	}
	
	class AuthorizationInfoPOJO extends BasePOJO {
		@JsonProperty(value="appid")
	    private String appId;
		@JsonProperty(value="func_info")
	    private List<FuncInfoPOJO> funcInfoPOJOList;
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public List<FuncInfoPOJO> getFuncInfoPOJOList() {
			return funcInfoPOJOList;
		}
		public void setFuncInfoPOJOList(List<FuncInfoPOJO> funcInfoPOJOList) {
			this.funcInfoPOJOList = funcInfoPOJOList;
		}
	}
	
	class FuncInfoPOJO extends BasePOJO {
		@JsonProperty(value="funcscope_category")
	    private FuncscopeCategoryPOJO funcscopeCategoryPOJO;

		public FuncscopeCategoryPOJO getFuncscopeCategoryPOJO() {
			return funcscopeCategoryPOJO;
		}

		public void setFuncscopeCategoryPOJO(FuncscopeCategoryPOJO funcscopeCategoryPOJO) {
			this.funcscopeCategoryPOJO = funcscopeCategoryPOJO;
		}
	}
	
	class FuncscopeCategoryPOJO extends BasePOJO {
		@JsonProperty(value="id")
	    private Integer id;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}

	public AuthorizerInfoPOJO getAuthorizerInfoPOJO() {
		return authorizerInfoPOJO;
	}

	public void setAuthorizerInfoPOJO(AuthorizerInfoPOJO authorizerInfoPOJO) {
		this.authorizerInfoPOJO = authorizerInfoPOJO;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	public AuthorizationInfoPOJO getAuthorizationInfo() {
		return authorizationInfo;
	}

	public void setAuthorizationInfo(AuthorizationInfoPOJO authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}

}
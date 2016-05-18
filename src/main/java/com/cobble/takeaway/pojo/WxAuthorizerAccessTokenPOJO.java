package com.cobble.takeaway.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WxAuthorizerAccessTokenPOJO extends BasePOJO {
	@JsonProperty(value="authorization_info")
    private AuthorizationInfoPOJO authorizationInfoPOJO;
	
	public class AuthorizationInfoPOJO extends BasePOJO {
		@JsonProperty(value="authorizer_appid")
	    private String authorizerAppId;
		@JsonProperty(value="authorizer_access_token")
	    private String authorizerAccessToken;
		@JsonProperty(value="expires_in")
	    private Integer expiresIn;
		@JsonProperty(value="authorizer_refresh_token")
	    private String authorizerRefreshToken;
		@JsonProperty(value="func_info")
	    private List<FuncInfoPOJO> funcInfoPOJOList;
		
		public String getAuthorizerAppId() {
			return authorizerAppId;
		}
		public void setAuthorizerAppId(String authorizerAppId) {
			this.authorizerAppId = authorizerAppId;
		}
		public String getAuthorizerAccessToken() {
			return authorizerAccessToken;
		}
		public void setAuthorizerAccessToken(String authorizerAccessToken) {
			this.authorizerAccessToken = authorizerAccessToken;
		}
		public Integer getExpiresIn() {
			return expiresIn;
		}
		public void setExpiresIn(Integer expiresIn) {
			this.expiresIn = expiresIn;
		}
		public String getAuthorizerRefreshToken() {
			return authorizerRefreshToken;
		}
		public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
			this.authorizerRefreshToken = authorizerRefreshToken;
		}
		public List<FuncInfoPOJO> getFuncInfoPOJOList() {
			return funcInfoPOJOList;
		}
		public void setFuncInfoPOJOList(List<FuncInfoPOJO> funcInfoPOJOList) {
			this.funcInfoPOJOList = funcInfoPOJOList;
		}
	}
	
	public class FuncInfoPOJO extends BasePOJO {
		@JsonProperty(value="funcscope_category")
	    private FuncscopeCategoryPOJO funcscopeCategoryPOJO;

		public FuncscopeCategoryPOJO getFuncscopeCategoryPOJO() {
			return funcscopeCategoryPOJO;
		}

		public void setFuncscopeCategoryPOJO(FuncscopeCategoryPOJO funcscopeCategoryPOJO) {
			this.funcscopeCategoryPOJO = funcscopeCategoryPOJO;
		}
	}
	
	public class FuncscopeCategoryPOJO extends BasePOJO {
		@JsonProperty(value="id")
	    private Integer id;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}

	public AuthorizationInfoPOJO getAuthorizationInfoPOJO() {
		return authorizationInfoPOJO;
	}

	public void setAuthorizationInfoPOJO(AuthorizationInfoPOJO authorizationInfoPOJO) {
		this.authorizationInfoPOJO = authorizationInfoPOJO;
	}

}
package com.cobble.takeaway.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* 描述:  网页授权信息  </br>
 */
public class WxOauth2TokenPOJO extends WxBasePOJO {
    // 网页授权接口调用凭证
	@JsonProperty(value="access_token")
	@org.codehaus.jackson.annotate.JsonProperty("access_token")
    private String accessToken;
    // 凭证有效时长
	@JsonProperty(value="expires_in")
	@org.codehaus.jackson.annotate.JsonProperty("expires_in")
    private int expiresIn;
    // 用于刷新凭证
	@JsonProperty(value="refresh_token")
	@org.codehaus.jackson.annotate.JsonProperty("refresh_token")
    private String refreshToken;
    // 用户标识
	@JsonProperty(value="openid")
	@org.codehaus.jackson.annotate.JsonProperty("openid")
    private String openId;
    // 用户授权作用域
	@JsonProperty(value="scope")
	@org.codehaus.jackson.annotate.JsonProperty("scope")
    private String scope;
	// 用户unionID
	@JsonProperty(value="unionid")
	@org.codehaus.jackson.annotate.JsonProperty("unionid")
	private String unionid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}
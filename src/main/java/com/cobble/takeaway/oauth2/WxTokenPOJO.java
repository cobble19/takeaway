package com.cobble.takeaway.oauth2;

/**
* 类名: WxTokenPOJO </br>
* 描述:  凭证  </br>
 */
public class WxTokenPOJO extends BaseWxApiPOJO {
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private int expiresIn;

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
}
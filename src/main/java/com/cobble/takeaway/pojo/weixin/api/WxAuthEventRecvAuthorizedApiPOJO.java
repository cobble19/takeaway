package com.cobble.takeaway.pojo.weixin.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxAuthEventRecvAuthorizedApiPOJO extends BasePOJO {
	@XmlElement(name="AppId")
    private String appId;
	@XmlElement(name="CreateTime")
    private String createTime;
	@XmlElement(name="InfoType")
    private String infoType;
	@XmlElement(name="AuthorizerAppid")
    private String authorizerAppId;
	@XmlElement(name="AuthorizationCode")
    private String authorizationCode;
	@XmlElement(name="AuthorizationCodeExpiredTime")
    private String authorizationCodeExpiredTime;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getAuthorizerAppId() {
		return authorizerAppId;
	}
	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public String getAuthorizationCodeExpiredTime() {
		return authorizationCodeExpiredTime;
	}
	public void setAuthorizationCodeExpiredTime(String authorizationCodeExpiredTime) {
		this.authorizationCodeExpiredTime = authorizationCodeExpiredTime;
	}
	@Override
	public String toString() {
		return "WxAuthEventRecvAuthorizedApiPOJO [appId=" + appId
				+ ", createTime=" + createTime + ", infoType=" + infoType
				+ ", authorizerAppId=" + authorizerAppId
				+ ", authorizationCode=" + authorizationCode
				+ ", authorizationCodeExpiredTime="
				+ authorizationCodeExpiredTime + "]";
	}
	

}
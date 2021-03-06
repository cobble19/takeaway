package com.cobble.takeaway.pojo.weixin.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxComVerifyTicketEncryptApiPOJO extends BasePOJO {
	@XmlElement(name="AppId")
    private String appId;
	@XmlElement(name="Encrypt")
    private String encrypt;
    
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

}
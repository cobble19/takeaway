package com.cobble.takeaway.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="xml")
public class WxComVerifyTicketPOJO extends BasePOJO {
	@XmlElement(name="AppId")
    private String appId;
	@XmlElement(name="CreateTime")
    private String createTime;
	@XmlElement(name="InfoType")
    private String infoType;
	@XmlElement(name="ComponentVerifyTicket")
    private String componentVerifyTicket;
    
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
	public String getComponentVerifyTicket() {
		return componentVerifyTicket;
	}
	public void setComponentVerifyTicket(String componentVerifyTicket) {
		this.componentVerifyTicket = componentVerifyTicket;
	}

}
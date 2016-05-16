package com.cobble.takeaway.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxComVerifyTicketSearchPOJO extends BaseSearchPOJO {
	private Long wxComVerifyTicketId;
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
	public Long getWxComVerifyTicketId() {
		return wxComVerifyTicketId;
	}
	public void setWxComVerifyTicketId(Long wxComVerifyTicketId) {
		this.wxComVerifyTicketId = wxComVerifyTicketId;
	}

}
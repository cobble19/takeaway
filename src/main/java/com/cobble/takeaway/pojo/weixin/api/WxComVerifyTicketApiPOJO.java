package com.cobble.takeaway.pojo.weixin.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cobble.takeaway.pojo.BasePOJO;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class WxComVerifyTicketApiPOJO extends BasePOJO {
	private Long wxComVerifyTicketId;
	/**
	 * component app id
	 */
	@XmlElement(name="AppId")
    private String appId;
	@XmlElement(name="CreateTime")
    private String createTime;
	@XmlElement(name="InfoType")
    private String infoType;
	@XmlElement(name="ComponentVerifyTicket")
    private String componentVerifyTicket;

	private Date createDateTime;
	private Date lastModifiedDateTime;

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

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
	
	@Override
	public String toString() {
		return "WxComVerifyTicketPOJO [wxComVerifyTicketId="
				+ wxComVerifyTicketId + ", appId=" + appId + ", createTime="
				+ createTime + ", infoType=" + infoType
				+ ", componentVerifyTicket=" + componentVerifyTicket + "]";
	}

}
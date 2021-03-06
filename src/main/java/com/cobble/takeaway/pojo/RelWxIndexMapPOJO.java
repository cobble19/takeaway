package com.cobble.takeaway.pojo;

import java.util.Date;

public class RelWxIndexMapPOJO extends BasePOJO {
	private Long relWxIndexMapId;
    private Long userId;
    private Long wxTemplateId;
    private String wxStaticPage;
    private String wxIndexCode;
    private Date createDateTime;
    
	public Long getRelWxIndexMapId() {
		return relWxIndexMapId;
	}
	public void setRelWxIndexMapId(Long relWxIndexMapId) {
		this.relWxIndexMapId = relWxIndexMapId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getWxIndexCode() {
		return wxIndexCode;
	}
	public void setWxIndexCode(String wxIndexCode) {
		this.wxIndexCode = wxIndexCode;
	}
	public Long getWxTemplateId() {
		return wxTemplateId;
	}
	public void setWxTemplateId(Long wxTemplateId) {
		this.wxTemplateId = wxTemplateId;
	}
	public String getWxStaticPage() {
		return wxStaticPage;
	}
	public void setWxStaticPage(String wxStaticPage) {
		this.wxStaticPage = wxStaticPage;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    
}

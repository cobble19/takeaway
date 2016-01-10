package com.cobble.takeaway.pojo;

public class RelWxIndexMapPOJO {
	private Long relWxIndexMapId;
    private Long userId;
    private Long wxTemplateId;
    private String wxStaticPage;
    private String wxIndexCode;
    
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
    
}

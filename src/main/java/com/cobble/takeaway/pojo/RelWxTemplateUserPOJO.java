package com.cobble.takeaway.pojo;


public class RelWxTemplateUserPOJO extends BasePOJO {
    private Long relWxTemplateUserId;
    private Long userId;
    private Long wxTemplateId;
    private Integer displayFlag;
    
    private String wxStaticPage;
    
	public Long getRelWxTemplateUserId() {
		return relWxTemplateUserId;
	}
	public void setRelWxTemplateUserId(Long relWxTemplateUserId) {
		this.relWxTemplateUserId = relWxTemplateUserId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getWxTemplateId() {
		return wxTemplateId;
	}
	public void setWxTemplateId(Long wxTemplateId) {
		this.wxTemplateId = wxTemplateId;
	}
	public Integer getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(Integer displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getWxStaticPage() {
		return wxStaticPage;
	}
	public void setWxStaticPage(String wxStaticPage) {
		this.wxStaticPage = wxStaticPage;
	}
    
    
}
package com.cobble.takeaway.pojo;


public class WxTemplateSearchPOJO extends BaseSearchPOJO {
    private Long wxTemplateId;
    private String wxTemplatePage;
    private String wxTemplateName;
    private String wxTemplateDesc;
    
	public Long getWxTemplateId() {
		return wxTemplateId;
	}
	public void setWxTemplateId(Long wxTemplateId) {
		this.wxTemplateId = wxTemplateId;
	}
	public String getWxTemplatePage() {
		return wxTemplatePage;
	}
	public void setWxTemplatePage(String wxTemplatePage) {
		this.wxTemplatePage = wxTemplatePage;
	}
	public String getWxTemplateName() {
		return wxTemplateName;
	}
	public void setWxTemplateName(String wxTemplateName) {
		this.wxTemplateName = wxTemplateName;
	}
	public String getWxTemplateDesc() {
		return wxTemplateDesc;
	}
	public void setWxTemplateDesc(String wxTemplateDesc) {
		this.wxTemplateDesc = wxTemplateDesc;
	}
    
}
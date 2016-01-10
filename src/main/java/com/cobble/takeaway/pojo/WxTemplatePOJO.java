package com.cobble.takeaway.pojo;


public class WxTemplatePOJO extends BasePOJO {
    private Long wxTemplateId;
    private String wxTemplatePage;
    private String wxTemplateName;
    private String wxTemplateDesc;
    
    private String wxPage;
    
    private String checked;
    
    private RelWxTemplateUserPOJO relWxTemplateUserPOJO;
    
    private RelWxIndexMapPOJO relWxIndexMapPOJO;
    
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
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getWxPage() {
		return wxPage;
	}
	public void setWxPage(String wxPage) {
		this.wxPage = wxPage;
	}
	public RelWxTemplateUserPOJO getRelWxTemplateUserPOJO() {
		return relWxTemplateUserPOJO;
	}
	public void setRelWxTemplateUserPOJO(RelWxTemplateUserPOJO relWxTemplateUserPOJO) {
		this.relWxTemplateUserPOJO = relWxTemplateUserPOJO;
	}
	public RelWxIndexMapPOJO getRelWxIndexMapPOJO() {
		return relWxIndexMapPOJO;
	}
	public void setRelWxIndexMapPOJO(RelWxIndexMapPOJO relWxIndexMapPOJO) {
		this.relWxIndexMapPOJO = relWxIndexMapPOJO;
	}
    
}
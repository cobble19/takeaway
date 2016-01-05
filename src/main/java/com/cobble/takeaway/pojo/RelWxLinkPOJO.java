package com.cobble.takeaway.pojo;


public class RelWxLinkPOJO extends BasePOJO {
    private Long relWxLinkId;
    private Long wxLinkId;
    private Long userId;
    private Long wxTemplateId;
    
	public Long getRelWxLinkId() {
		return relWxLinkId;
	}
	public void setRelWxLinkId(Long relWxLinkId) {
		this.relWxLinkId = relWxLinkId;
	}
	public Long getWxLinkId() {
		return wxLinkId;
	}
	public void setWxLinkId(Long wxLinkId) {
		this.wxLinkId = wxLinkId;
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
    
}
package com.cobble.takeaway.pojo;


public class RelWxAttrPOJO extends BasePOJO {
    private Long relWxAttrId;
    private Long wxAttrId;
    private Long userId;
    private Long wxTemplateId;
    
	public Long getRelWxAttrId() {
		return relWxAttrId;
	}
	public void setRelWxAttrId(Long relWxAttrId) {
		this.relWxAttrId = relWxAttrId;
	}
	public Long getWxAttrId() {
		return wxAttrId;
	}
	public void setWxAttrId(Long wxAttrId) {
		this.wxAttrId = wxAttrId;
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
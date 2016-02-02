package com.cobble.takeaway.pojo;



public class WxAttrPOJO extends BasePOJO {
    private Long wxAttrId;
    private String wxAttrData;
    private Integer orderNo;
    private Integer wxSecOrderNo;
    
    ///
    private Long wxTemplateId;
    private Long userId;
    
	public Long getWxAttrId() {
		return wxAttrId;
	}
	public void setWxAttrId(Long wxAttrId) {
		this.wxAttrId = wxAttrId;
	}
	public String getWxAttrData() {
		return wxAttrData;
	}
	public void setWxAttrData(String wxAttrData) {
		this.wxAttrData = wxAttrData;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getWxSecOrderNo() {
		return wxSecOrderNo;
	}
	public void setWxSecOrderNo(Integer wxSecOrderNo) {
		this.wxSecOrderNo = wxSecOrderNo;
	}
	public Long getWxTemplateId() {
		return wxTemplateId;
	}
	public void setWxTemplateId(Long wxTemplateId) {
		this.wxTemplateId = wxTemplateId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
}
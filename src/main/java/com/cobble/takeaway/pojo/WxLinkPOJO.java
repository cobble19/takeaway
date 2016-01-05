package com.cobble.takeaway.pojo;



public class WxLinkPOJO extends BasePOJO {
    private Long wxLinkId;
    private String title;
    private String imgSrc;
    private String linkUrl;
    private Integer display;
    private Integer orderNo;
    
    ///
    private Long wxTemplateId;
    private Long userId;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Integer getDisplay() {
		return display;
	}
	public void setDisplay(Integer display) {
		this.display = display;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Long getWxLinkId() {
		return wxLinkId;
	}
	public void setWxLinkId(Long wxLinkId) {
		this.wxLinkId = wxLinkId;
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
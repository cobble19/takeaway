package com.cobble.takeaway.pojo;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadPOJO extends BasePOJO {
	private MultipartFile file;
	private Long userId;
	private Long wxTemplateId;
	private Long orderNo;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
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
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}

package com.cobble.takeaway.pojo;


public class HtmlConvertedPOJO extends BasePOJO {
	private Boolean success = false;
	private String desc = "";
	private String errorCode = "";
	private String errorMsg = "";
	
	private String fromFullUrl;
	private String toFilePath;
	private String htmlPath;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getFromFullUrl() {
		return fromFullUrl;
	}
	public void setFromFullUrl(String fromFullUrl) {
		this.fromFullUrl = fromFullUrl;
	}
	public String getToFilePath() {
		return toFilePath;
	}
	public void setToFilePath(String toFilePath) {
		this.toFilePath = toFilePath;
	}
	public String getHtmlPath() {
		return htmlPath;
	}
	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}
	
}

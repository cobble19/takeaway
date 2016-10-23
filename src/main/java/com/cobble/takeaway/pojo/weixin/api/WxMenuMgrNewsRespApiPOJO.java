package com.cobble.takeaway.pojo.weixin.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class WxMenuMgrNewsRespApiPOJO {
	@JsonProperty(value="title")
	@JsonInclude(Include.NON_EMPTY)
	private String title;
	@JsonProperty(value="author")
	@JsonInclude(Include.NON_EMPTY)
	private String author;
	@JsonProperty(value="digest")
	@JsonInclude(Include.NON_EMPTY)
	private String digest;
	@JsonProperty(value="show_cover")
	@JsonInclude(Include.NON_EMPTY)
	private String showCover;
	@JsonProperty(value="cover_url")
	@JsonInclude(Include.NON_EMPTY)
	private String coverUrl;
	@JsonProperty(value="content_url")
	@JsonInclude(Include.NON_EMPTY)
	private String contentUrl;
	@JsonProperty(value="source_url")
	@JsonInclude(Include.NON_EMPTY)
	private String sourceUrl;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getShowCover() {
		return showCover;
	}
	public void setShowCover(String showCover) {
		this.showCover = showCover;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	@Override
	public String toString() {
		return "WxMenuMgrNewsRespApiPOJO [title=" + title + ", author="
				+ author + ", digest=" + digest + ", showCover=" + showCover
				+ ", coverUrl=" + coverUrl + ", contentUrl=" + contentUrl
				+ ", sourceUrl=" + sourceUrl + "]";
	}

}

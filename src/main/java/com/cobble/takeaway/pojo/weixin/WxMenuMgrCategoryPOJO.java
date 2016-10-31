package com.cobble.takeaway.pojo.weixin;

import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxMenuMgrCategoryPOJO extends BasePOJO {
	private Long wxMenuMgrCategoryId;
    private String name;
    private String description;
    private List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJO;
    private Date createDateTime;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public List<WxMenuMgrButtonPOJO> getWxMenuMgrButtonPOJO() {
		return wxMenuMgrButtonPOJO;
	}
	public void setWxMenuMgrButtonPOJO(List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJO) {
		this.wxMenuMgrButtonPOJO = wxMenuMgrButtonPOJO;
	}
	public Long getWxMenuMgrCategoryId() {
		return wxMenuMgrCategoryId;
	}
	public void setWxMenuMgrCategoryId(Long wxMenuMgrCategoryId) {
		this.wxMenuMgrCategoryId = wxMenuMgrCategoryId;
	}
    
    
}

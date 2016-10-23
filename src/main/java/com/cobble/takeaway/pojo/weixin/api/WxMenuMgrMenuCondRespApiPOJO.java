package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrMenuCondRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="menuid")
    private String menuId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


}
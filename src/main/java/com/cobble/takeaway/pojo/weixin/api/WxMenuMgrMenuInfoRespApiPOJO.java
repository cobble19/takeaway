package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrMenuInfoRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="is_menu_open")
	private Integer isMenuOpen;
	@JsonProperty(value="selfmenu_info")
	private WxMenuMgrButtonsRespApiPOJO selfMenuInfo;
	public Integer getIsMenuOpen() {
		return isMenuOpen;
	}
	public void setIsMenuOpen(Integer isMenuOpen) {
		this.isMenuOpen = isMenuOpen;
	}
	public WxMenuMgrButtonsRespApiPOJO getSelfMenuInfo() {
		return selfMenuInfo;
	}
	public void setSelfMenuInfo(WxMenuMgrButtonsRespApiPOJO selfMenuInfo) {
		this.selfMenuInfo = selfMenuInfo;
	}
	@Override
	public String toString() {
		return "WxMenuMgrMenuInfoRespApiPOJO [isMenuOpen=" + isMenuOpen
				+ ", selfMenuInfo=" + selfMenuInfo + "]";
	}

	
}
package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxTagsMgrGetIdListRespApiPOJO extends BaseWxApiPOJO {
	@JsonProperty(value="tagid_list")
    private List<Integer> tagIdList = new ArrayList<Integer>();

	public List<Integer> getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(List<Integer> tagIdList) {
		this.tagIdList = tagIdList;
	}

	
}
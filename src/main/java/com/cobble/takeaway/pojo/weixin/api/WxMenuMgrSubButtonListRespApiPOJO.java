package com.cobble.takeaway.pojo.weixin.api;

import java.util.ArrayList;
import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxMenuMgrSubButtonListRespApiPOJO extends BasePOJO {
	@JsonProperty(value="list")
    private List<WxMenuMgrButtonRespApiPOJO> list = new ArrayList<WxMenuMgrButtonRespApiPOJO>();
	
}
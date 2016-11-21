package com.cobble.takeaway.pojo.weixin.api;

import java.util.List;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WxTagsMgrBatchTaggingReqApiPOJO extends BasePOJO {
	@JsonProperty(value="openid_list")
    private List<String> openIdList;
	@JsonProperty(value="tagid")
    private Integer tagId;

	

	
}
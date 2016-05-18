package com.cobble.takeaway.pojo.weixin;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeInfoPOJO extends BasePOJO {
	@JsonProperty(value="id")
    private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

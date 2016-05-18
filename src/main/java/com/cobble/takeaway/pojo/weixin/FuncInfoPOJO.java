package com.cobble.takeaway.pojo.weixin;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FuncInfoPOJO extends BasePOJO {
	@JsonProperty(value="funcscope_category")
    private FuncscopeCategoryPOJO funcscopeCategoryPOJO;
	
	public FuncInfoPOJO() {
	}

	public FuncscopeCategoryPOJO getFuncscopeCategoryPOJO() {
		return funcscopeCategoryPOJO;
	}

	public void setFuncscopeCategoryPOJO(FuncscopeCategoryPOJO funcscopeCategoryPOJO) {
		this.funcscopeCategoryPOJO = funcscopeCategoryPOJO;
	}
}

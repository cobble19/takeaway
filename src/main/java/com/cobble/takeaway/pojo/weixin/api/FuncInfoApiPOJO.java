package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FuncInfoApiPOJO extends BasePOJO {
	@JsonProperty(value="funcscope_category")
    private FuncscopeCategoryApiPOJO funcscopeCategoryPOJO;
	
	public FuncInfoApiPOJO() {
	}

	public FuncscopeCategoryApiPOJO getFuncscopeCategoryPOJO() {
		return funcscopeCategoryPOJO;
	}

	public void setFuncscopeCategoryPOJO(FuncscopeCategoryApiPOJO funcscopeCategoryPOJO) {
		this.funcscopeCategoryPOJO = funcscopeCategoryPOJO;
	}
}

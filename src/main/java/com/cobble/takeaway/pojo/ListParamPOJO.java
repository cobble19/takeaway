package com.cobble.takeaway.pojo;

import java.util.List;


public class ListParamPOJO<T> extends BasePOJO {
    private List<T> list1;

	public List<T> getList1() {
		return list1;
	}

	public void setList1(List<T> list1) {
		this.list1 = list1;
	}
    
}
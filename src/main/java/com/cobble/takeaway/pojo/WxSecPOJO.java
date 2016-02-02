package com.cobble.takeaway.pojo;

import java.util.List;



public class WxSecPOJO extends BasePOJO {
	private Integer wxSecOrderNo;
    private List<WxAttrPOJO> wxAttrPOJOs;

	public List<WxAttrPOJO> getWxAttrPOJOs() {
		return wxAttrPOJOs;
	}

	public void setWxAttrPOJOs(List<WxAttrPOJO> wxAttrPOJOs) {
		this.wxAttrPOJOs = wxAttrPOJOs;
	}

	public Integer getWxSecOrderNo() {
		return wxSecOrderNo;
	}

	public void setWxSecOrderNo(Integer wxSecOrderNo) {
		this.wxSecOrderNo = wxSecOrderNo;
	}
    
}
package com.cobble.takeaway.oauth2;

import com.cobble.takeaway.pojo.BasePOJO;

public class BaseWxApiPOJO extends BasePOJO {
	private String errcode;
	private String errmsg;
	
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	@Override
	public String toString() {
		return "BaseWxApiPOJO [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}
}

package com.cobble.takeaway.pojo.weixin.api;

import com.cobble.takeaway.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessInfoApiPOJO extends BasePOJO {
	@JsonProperty(value="open_store")
    private Integer openStore;
	@JsonProperty(value="open_scan")
    private Integer openScan;
	@JsonProperty(value="open_pay")
    private Integer openPay;
	@JsonProperty(value="open_card")
    private Integer openCard;
	@JsonProperty(value="open_shake")
    private Integer openShake;
	public Integer getOpenStore() {
		return openStore;
	}
	public void setOpenStore(Integer openStore) {
		this.openStore = openStore;
	}
	public Integer getOpenScan() {
		return openScan;
	}
	public void setOpenScan(Integer openScan) {
		this.openScan = openScan;
	}
	public Integer getOpenPay() {
		return openPay;
	}
	public void setOpenPay(Integer openPay) {
		this.openPay = openPay;
	}
	public Integer getOpenCard() {
		return openCard;
	}
	public void setOpenCard(Integer openCard) {
		this.openCard = openCard;
	}
	public Integer getOpenShake() {
		return openShake;
	}
	public void setOpenShake(Integer openShake) {
		this.openShake = openShake;
	}
}

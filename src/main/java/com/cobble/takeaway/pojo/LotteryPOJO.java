package com.cobble.takeaway.pojo;


public class LotteryPOJO extends BasePOJO {
	private Boolean success;
	private AwardPOJO awardPOJO;
	private Boolean isHappy;
	private String result;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public AwardPOJO getAwardPOJO() {
		return awardPOJO;
	}
	public void setAwardPOJO(AwardPOJO awardPOJO) {
		this.awardPOJO = awardPOJO;
	}
	public Boolean getIsHappy() {
		return isHappy;
	}
	public void setIsHappy(Boolean isHappy) {
		this.isHappy = isHappy;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}

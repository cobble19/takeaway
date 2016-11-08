package com.cobble.takeaway.pojo;

import java.util.Date;

public class AwardSearchPOJO extends BaseSearchPOJO {
	private Long awardId;
	private Long interactiveId;
	private String name;
	private String description;
	private Integer amount;
	private Integer balance;
	private Integer weight;
	private Integer orderNo;
	private Date createDateTime;
	
	private Boolean balanceGt0Flag = false;
	
	public Long getAwardId() {
		return awardId;
	}
	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}
	public Long getInteractiveId() {
		return interactiveId;
	}
	public void setInteractiveId(Long interactiveId) {
		this.interactiveId = interactiveId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Boolean getBalanceGt0Flag() {
		return balanceGt0Flag;
	}
	public void setBalanceGt0Flag(Boolean balanceGt0Flag) {
		this.balanceGt0Flag = balanceGt0Flag;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
}

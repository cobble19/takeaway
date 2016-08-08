package com.cobble.takeaway.pojo;



public class RelVoteUserSearchPOJO extends BaseSearchPOJO {
    private Long relVoteUserId;
    private Long voteId;
    private Long userId;
    
	public Long getRelVoteUserId() {
		return relVoteUserId;
	}
	public void setRelVoteUserId(Long relVoteUserId) {
		this.relVoteUserId = relVoteUserId;
	}
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
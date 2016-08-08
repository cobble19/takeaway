package com.cobble.takeaway.pojo;



public class RelVoteUserPOJO extends BasePOJO {
    private Long relVoteUserId;
    private Long voteId;
    private Long voteItemId;
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
	public Long getVoteItemId() {
		return voteItemId;
	}
	public void setVoteItemId(Long voteItemId) {
		this.voteItemId = voteItemId;
	}

}
package com.cobble.takeaway.pojo;



public class RelVoteUserSearchPOJO extends BaseSearchPOJO {
    private Long relVoteUserId;
    private Long voteId;
    private Long userId;
    private Long voteItemId;
    
	public Long getVoteItemId() {
		return voteItemId;
	}
	public void setVoteItemId(Long voteItemId) {
		this.voteItemId = voteItemId;
	}
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
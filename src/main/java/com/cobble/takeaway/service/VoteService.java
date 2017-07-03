package com.cobble.takeaway.service;

import java.util.List;
import java.util.Map;

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.pojo.VoteSearchPOJO;

public interface VoteService {
	int insert(VotePOJO votePOJO) throws Exception;
	int update(VotePOJO votePOJO) throws Exception;
	List<VotePOJO> finds(VoteSearchPOJO voteSearchPOJO) throws Exception;
	int getCount(VoteSearchPOJO voteSearchPOJO) throws Exception;
	VotePOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
	Map listVoteById4UnifiedBootstrap(Long voteId, 
			Long activityId,
			String activityTitle,
			Long voteItemId,
			Long userId) throws Exception;
	public Map findCurrentVoteItem(VoteItemSearchPOJO voteItemSearchPOJO, VotePOJO votePOJO) throws Exception;
	public DataTablesPOJO<VoteItemPOJO> findVoteItems(VoteItemSearchPOJO voteItemSearchPOJO, VotePOJO votePOJO) throws Exception;

}

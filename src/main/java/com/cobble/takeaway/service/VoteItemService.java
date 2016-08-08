package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;

public interface VoteItemService {
	int insert(VoteItemPOJO voteItemPOJO, Long userId) throws Exception;
	int update(VoteItemPOJO voteItemPOJO) throws Exception;
	List<VoteItemPOJO> finds(VoteItemSearchPOJO voteItemSearchPOJO) throws Exception;
	int getCount(VoteItemSearchPOJO voteItemSearchPOJO) throws Exception;
	VoteItemPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
	List<VoteItemPOJO> findsByVoteId(Long voteId) throws Exception;
	
	int increaseTotalNumPlusOne(VoteItemPOJO voteItemPOJO) throws Exception;

}

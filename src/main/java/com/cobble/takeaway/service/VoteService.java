package com.cobble.takeaway.service;

import java.util.List;

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

}

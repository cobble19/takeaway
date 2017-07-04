package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;


public interface VoteItemMapper {
	int insert(VoteItemPOJO voteItemPOJO) throws Exception;
	int update(VoteItemPOJO voteItemPOJO) throws Exception;
	List<VoteItemPOJO> finds(VoteItemSearchPOJO voteItemSearchPOJO) throws Exception;
	int getCount(VoteItemSearchPOJO voteItemSearchPOJO) throws Exception;
	VoteItemPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	int increaseTotalNumPlusOne(VoteItemPOJO voteItemPOJO) throws Exception;

	int getMaxOrderNo(VoteItemSearchPOJO voteItemSearchPOJO) throws Exception;

}
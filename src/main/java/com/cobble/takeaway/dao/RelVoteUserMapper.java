package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.RelVoteUserPOJO;
import com.cobble.takeaway.pojo.RelVoteUserSearchPOJO;


public interface RelVoteUserMapper {
	int insert(RelVoteUserPOJO relVoteUserPOJO) throws Exception;
	int update(RelVoteUserPOJO relVoteUserPOJO) throws Exception;
	List<RelVoteUserPOJO> finds(RelVoteUserSearchPOJO relVoteUserSearchPOJO) throws Exception;
	int getCount(RelVoteUserSearchPOJO relVoteUserSearchPOJO) throws Exception;
	RelVoteUserPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

}
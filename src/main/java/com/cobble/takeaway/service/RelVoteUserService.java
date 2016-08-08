package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.RelVoteUserPOJO;
import com.cobble.takeaway.pojo.RelVoteUserSearchPOJO;

public interface RelVoteUserService {
	int insert(RelVoteUserPOJO relVoteUserPOJO) throws Exception;
	int update(RelVoteUserPOJO relVoteUserPOJO) throws Exception;
	List<RelVoteUserPOJO> finds(RelVoteUserSearchPOJO relVoteUserSearchPOJO) throws Exception;
	int getCount(RelVoteUserSearchPOJO relVoteUserSearchPOJO) throws Exception;
	RelVoteUserPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}

package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.AwardPOJO;
import com.cobble.takeaway.pojo.AwardSearchPOJO;


public interface AwardMapper {
	int insert(AwardPOJO awardPOJO) throws Exception;
	int update(AwardPOJO awardPOJO) throws Exception;
	int decreaseBalance(AwardPOJO awardPOJO) throws Exception;
	List<AwardPOJO> finds(AwardSearchPOJO awardSearchPOJO) throws Exception;
	int getCount(AwardSearchPOJO awardSearchPOJO) throws Exception;
	AwardPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}
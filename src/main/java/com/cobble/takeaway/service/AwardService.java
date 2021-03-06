package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.AwardPOJO;
import com.cobble.takeaway.pojo.AwardSearchPOJO;

public interface AwardService {
	int insert(AwardPOJO awardPOJO) throws Exception;
	int update(AwardPOJO awardPOJO) throws Exception;
	int decreaseBalance(AwardPOJO awardPOJO) throws Exception;
	List<AwardPOJO> finds(AwardSearchPOJO awardSearchPOJO) throws Exception;
	int getCount(AwardSearchPOJO awardSearchPOJO) throws Exception;
	AwardPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

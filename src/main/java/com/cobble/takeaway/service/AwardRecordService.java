package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.AwardRecordPOJO;
import com.cobble.takeaway.pojo.AwardRecordSearchPOJO;

public interface AwardRecordService {
	int insert(AwardRecordPOJO awardRecordPOJO) throws Exception;
	int update(AwardRecordPOJO awardRecordPOJO) throws Exception;
	List<AwardRecordPOJO> finds(AwardRecordSearchPOJO awardRecordSearchPOJO) throws Exception;
	int getCount(AwardRecordSearchPOJO awardRecordSearchPOJO) throws Exception;
	AwardRecordPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

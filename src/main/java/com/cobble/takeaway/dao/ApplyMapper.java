package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.ApplyPOJO;
import com.cobble.takeaway.pojo.ApplySearchPOJO;
import com.cobble.takeaway.pojo.RelActivityApplyPOJO;


public interface ApplyMapper {
	int insert(ApplyPOJO applyPOJO) throws Exception;
	int insertRelActivityApply(RelActivityApplyPOJO relActivityApplyPOJO) throws Exception;
	int update(ApplyPOJO applyPOJO) throws Exception;
	List<ApplyPOJO> finds(ApplySearchPOJO applySearchPOJO) throws Exception;
	List<ApplyPOJO> findsApplyInActivity(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getCountApplyInActivity(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int findsApplyInActivityByPhone(ApplySearchPOJO applySearchPOJO) throws Exception;
	
	int getCount(ApplySearchPOJO applySearchPOJO) throws Exception;
	ApplyPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}
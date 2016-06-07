package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.Apply2POJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;
import com.cobble.takeaway.pojo.ApplyPOJO;
import com.cobble.takeaway.pojo.RelActivityApply2POJO;


public interface Apply2Mapper {
	int insert(Apply2POJO apply2POJO) throws Exception;
	int update(Apply2POJO apply2POJO) throws Exception;
	List<Apply2POJO> finds(Apply2SearchPOJO apply2SearchPOJO) throws Exception;
	int getCount(Apply2SearchPOJO apply2SearchPOJO) throws Exception;
	List<Apply2POJO> findsApply2InActivity(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getCountApply2InActivity(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int findsApply2InActivityByPhone(Apply2SearchPOJO apply2SearchPOJO) throws Exception;
	Apply2POJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	int insertRelActivityApply2(RelActivityApply2POJO relActivityApply2POJO) throws Exception;
	
	List<Apply2POJO> finds2ByActivityId(Long activityId) throws Exception;

	int findsApply2InActivityByUnionId(Apply2SearchPOJO apply2SearchPOJO) throws Exception;
}
package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;

public interface ActivityService {
	int insert(ActivityPOJO activityPOJO, Long userId) throws Exception;
	int update(ActivityPOJO activityPOJO) throws Exception;
	List<ActivityPOJO> finds(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getCount(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	List<ActivityPOJO> findActives(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getActiveCount(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	ActivityPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;


	List<ActivityPOJO> find4Enterprises(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getCount4Enterprise(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	
	ActivityPOJO find2ById(Long id) throws Exception;

	List<ActivityPOJO> findActivitys4WxPerson(Apply2SearchPOJO apply2SearchPOJO) throws Exception;
	int getActivitys4WxPersonCount(Apply2SearchPOJO apply2SearchPOJO) throws Exception;
	
}

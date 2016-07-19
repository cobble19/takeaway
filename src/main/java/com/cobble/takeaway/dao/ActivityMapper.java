package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;
import com.cobble.takeaway.pojo.RelActivityUserPOJO;


public interface ActivityMapper {
	int insert(ActivityPOJO activityPOJO) throws Exception;
	int insertRelActivityUser(RelActivityUserPOJO relActivityUserPOJO) throws Exception;
	int update(ActivityPOJO activityPOJO) throws Exception;
	List<ActivityPOJO> finds(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getCount(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	List<ActivityPOJO> findActives(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getActiveCount(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	ActivityPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

	List<ActivityPOJO> find4Enterprises(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getCount4Enterprise(ActivitySearchPOJO activitySearchPOJO) throws Exception;

	ActivityPOJO find2ById(Long id) throws Exception;

	List<ActivityPOJO> findActivitys4WxPerson(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getActivitys4WxPersonCount(ActivitySearchPOJO activitySearchPOJO) throws Exception;
}
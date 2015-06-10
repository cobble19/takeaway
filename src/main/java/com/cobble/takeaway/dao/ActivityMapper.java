package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.RelActivityUserPOJO;


public interface ActivityMapper {
	int insert(ActivityPOJO activityPOJO) throws Exception;
	int insertRelActivityUser(RelActivityUserPOJO relActivityUserPOJO) throws Exception;
	int update(ActivityPOJO activityPOJO) throws Exception;
	List<ActivityPOJO> finds(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getCount(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	ActivityPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}
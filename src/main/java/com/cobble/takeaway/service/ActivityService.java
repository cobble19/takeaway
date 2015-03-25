package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;

public interface ActivityService {
	int insert(ActivityPOJO activityPOJO) throws Exception;
	int update(ActivityPOJO activityPOJO) throws Exception;
	List<ActivityPOJO> finds(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	int getCount(ActivitySearchPOJO activitySearchPOJO) throws Exception;
	ActivityPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}

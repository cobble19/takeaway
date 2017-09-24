package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.ActivityGatherPOJO;
import com.cobble.takeaway.pojo.ActivityGatherSearchPOJO;

public interface ActivityGatherService {
	int insert(ActivityGatherPOJO activityGatherPOJO, Long userId) throws Exception;
	int update(ActivityGatherPOJO activityGatherPOJO) throws Exception;
	List<ActivityGatherPOJO> finds(ActivityGatherSearchPOJO activityGatherSearchPOJO) throws Exception;
	int getCount(ActivityGatherSearchPOJO activityGatherSearchPOJO) throws Exception;
	ActivityGatherPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}

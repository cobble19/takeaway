package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.ActivityGatherPOJO;
import com.cobble.takeaway.pojo.ActivityGatherSearchPOJO;


public interface ActivityGatherMapper {
	int insert(ActivityGatherPOJO activityGatherPOJO) throws Exception;
	int update(ActivityGatherPOJO activityGatherPOJO) throws Exception;
	List<ActivityGatherPOJO> finds(ActivityGatherSearchPOJO activityGatherSearchPOJO) throws Exception;
	int getCount(ActivityGatherSearchPOJO activityGatherSearchPOJO) throws Exception;
	ActivityGatherPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

}
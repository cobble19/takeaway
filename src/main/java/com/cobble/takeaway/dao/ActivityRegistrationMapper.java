package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.ActivityRegistrationPOJO;
import com.cobble.takeaway.pojo.ActivityRegistrationSearchPOJO;


public interface ActivityRegistrationMapper {
	int insert(ActivityRegistrationPOJO activityRegistrationPOJO) throws Exception;
	int update(ActivityRegistrationPOJO activityRegistrationPOJO) throws Exception;
	List<ActivityRegistrationPOJO> finds(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO) throws Exception;
	int getCount(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO) throws Exception;
	ActivityRegistrationPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

}
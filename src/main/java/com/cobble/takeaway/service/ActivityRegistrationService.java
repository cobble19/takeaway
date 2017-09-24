package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.ActivityRegistrationPOJO;
import com.cobble.takeaway.pojo.ActivityRegistrationSearchPOJO;

public interface ActivityRegistrationService {
	int insert(ActivityRegistrationPOJO activityRegistrationPOJO, Long userId) throws Exception;
	int update(ActivityRegistrationPOJO activityRegistrationPOJO) throws Exception;
	List<ActivityRegistrationPOJO> finds(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO) throws Exception;
	int getCount(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO) throws Exception;
	ActivityRegistrationPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}

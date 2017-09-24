package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ActivityRegistrationMapper;
import com.cobble.takeaway.pojo.ActivityRegistrationPOJO;
import com.cobble.takeaway.pojo.ActivityRegistrationSearchPOJO;
import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.service.ActivityRegistrationService;
import com.cobble.takeaway.service.ActivityService;

@Service
public class ActivityRegistrationServiceImpl implements ActivityRegistrationService {
	
	@Autowired
	private ActivityRegistrationMapper activityRegistrationMapper;
	@Autowired
	private ActivityService activityService;

	@Override
	public int insert(ActivityRegistrationPOJO activityRegistrationPOJO, Long userId) throws Exception {
		int ret = 0;

		ActivityPOJO activityPOJO = new ActivityPOJO();
		activityPOJO.setTitle(activityRegistrationPOJO.getTitle());
		activityPOJO.setContent(activityRegistrationPOJO.getContent());
//		activityPOJO.setStartDateTime(startDateTime);
//		activityPOJO.setEndDateTime(endDateTime);
		activityPOJO.setTypeCode(1);
		activityPOJO.setPublishType(1);
		activityPOJO.setNeedSubscribe(0);
		activityPOJO.setUserIdEnterprise(userId);
		activityService.insert(activityPOJO, userId);
		
		activityRegistrationPOJO.setActivityId(activityPOJO.getActivityId());

		ret = activityRegistrationMapper.insert(activityRegistrationPOJO);
		return ret;
	}

	@Override
	public int update(ActivityRegistrationPOJO activityRegistrationPOJO) throws Exception {
		int ret = 0;
		ret = activityRegistrationMapper.update(activityRegistrationPOJO);
		return ret;
	}

	@Override
	public List<ActivityRegistrationPOJO> finds(
			ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO) throws Exception {
		List<ActivityRegistrationPOJO> ret = null;
		ret = activityRegistrationMapper.finds(activityRegistrationSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = activityRegistrationMapper.getCount(activityRegistrationSearchPOJO);
		return ret;
	}

	@Override
	public ActivityRegistrationPOJO findById(Long id) throws Exception {
		ActivityRegistrationPOJO ret = null;
		ret = activityRegistrationMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = activityRegistrationMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += activityRegistrationMapper.deleteById(id);
			}
		}
		return ret;
	}


}

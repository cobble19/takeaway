package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ActivityGatherMapper;
import com.cobble.takeaway.pojo.ActivityGatherPOJO;
import com.cobble.takeaway.pojo.ActivityGatherSearchPOJO;
import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.service.ActivityGatherService;
import com.cobble.takeaway.service.ActivityService;

@Service
public class ActivityGatherServiceImpl implements ActivityGatherService {
	
	@Autowired
	private ActivityGatherMapper activityGatherMapper;
	@Autowired
	private ActivityService activityService;

	@Override
	public int insert(ActivityGatherPOJO activityGatherPOJO, Long userId) throws Exception {
		int ret = 0;

		ActivityPOJO activityPOJO = new ActivityPOJO();
		activityPOJO.setTitle(activityGatherPOJO.getTitle());
		activityPOJO.setContent(activityGatherPOJO.getContent());
//		activityPOJO.setStartDateTime(startDateTime);
//		activityPOJO.setEndDateTime(endDateTime);
		activityPOJO.setTypeCode(1);
		activityPOJO.setPublishType(1);
		activityPOJO.setNeedSubscribe(0);
		activityPOJO.setUserIdEnterprise(userId);
		activityService.insert(activityPOJO, userId);
		
		activityGatherPOJO.setActivityId(activityPOJO.getActivityId());

		ret = activityGatherMapper.insert(activityGatherPOJO);
		return ret;
	}

	@Override
	public int update(ActivityGatherPOJO activityGatherPOJO) throws Exception {
		int ret = 0;
		ret = activityGatherMapper.update(activityGatherPOJO);
		return ret;
	}

	@Override
	public List<ActivityGatherPOJO> finds(
			ActivityGatherSearchPOJO activityGatherSearchPOJO) throws Exception {
		List<ActivityGatherPOJO> ret = null;
		ret = activityGatherMapper.finds(activityGatherSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(ActivityGatherSearchPOJO activityGatherSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = activityGatherMapper.getCount(activityGatherSearchPOJO);
		return ret;
	}

	@Override
	public ActivityGatherPOJO findById(Long id) throws Exception {
		ActivityGatherPOJO ret = null;
		ret = activityGatherMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = activityGatherMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += activityGatherMapper.deleteById(id);
			}
		}
		return ret;
	}


}

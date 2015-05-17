package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ActivityMapper;
import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.RelActivityUserPOJO;
import com.cobble.takeaway.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityMapper activityMapper;

	@Override
	public int insert(ActivityPOJO activityPOJO, Long userId) throws Exception {
		int ret = 0;
		ret = activityMapper.insert(activityPOJO);
		RelActivityUserPOJO relActivityUserPOJO = new RelActivityUserPOJO();
		relActivityUserPOJO.setActivityId(activityPOJO.getActivityId());
		relActivityUserPOJO.setUserId(userId);
		activityMapper.insertRelActivityUser(relActivityUserPOJO);
		return ret;
	}

	@Override
	public int update(ActivityPOJO activityPOJO) throws Exception {
		int ret = 0;
		ret = activityMapper.update(activityPOJO);
		return ret;
	}

	@Override
	public List<ActivityPOJO> finds(
			ActivitySearchPOJO activitySearchPOJO) throws Exception {
		List<ActivityPOJO> ret = null;
		ret = activityMapper.finds(activitySearchPOJO);
		return ret;
	}

	@Override
	public int getCount(ActivitySearchPOJO activitySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = activityMapper.getCount(activitySearchPOJO);
		return ret;
	}

	@Override
	public ActivityPOJO findById(Long id) throws Exception {
		ActivityPOJO ret = null;
		ret = activityMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = activityMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += activityMapper.deleteById(id);
			}
		}
		return ret;
	}

}

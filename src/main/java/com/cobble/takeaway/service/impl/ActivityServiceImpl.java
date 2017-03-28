package com.cobble.takeaway.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ActivityMapper;
import com.cobble.takeaway.dao.Apply2Mapper;
import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.Apply2POJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;
import com.cobble.takeaway.pojo.RelActivityUserPOJO;
import com.cobble.takeaway.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private Apply2Mapper apply2Mapper;

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
	public List<ActivityPOJO> findActives(
			ActivitySearchPOJO activitySearchPOJO) throws Exception {
		List<ActivityPOJO> ret = null;
		ret = activityMapper.findActives(activitySearchPOJO);
		return ret;
	}

	@Override
	public int getActiveCount(ActivitySearchPOJO activitySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = activityMapper.getActiveCount(activitySearchPOJO);
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

	@Override
	public List<ActivityPOJO> find4Enterprises(
			ActivitySearchPOJO activitySearchPOJO) throws Exception {
		List<ActivityPOJO> ret = null;
		ret = activityMapper.find4Enterprises(activitySearchPOJO);
		return ret;
	}

	@Override
	public int getCount4Enterprise(ActivitySearchPOJO activitySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = activityMapper.getCount4Enterprise(activitySearchPOJO);
		return ret;
	}

	@Override
	public ActivityPOJO find2ById(Long id) throws Exception {
		ActivityPOJO ret = null;
		ret = activityMapper.find2ById(id);
		
		Apply2SearchPOJO apply2SearchPOJO = new Apply2SearchPOJO();
		apply2SearchPOJO.setActivityId(id);
		
		List<Apply2POJO> apply2pojos = apply2Mapper.finds2ByActivityId(apply2SearchPOJO);
		if (ret != null && CollectionUtils.isNotEmpty(apply2pojos)) {
			ret.setApply2POJOs(apply2pojos);
		}
		
		return ret;
	}
	
	@Override
	public ActivityPOJO find2ById(Long id, Date startDateTime, Date endDateTime) throws Exception {
		ActivityPOJO ret = null;
		ret = activityMapper.find2ById(id);

		Apply2SearchPOJO apply2SearchPOJO = new Apply2SearchPOJO();
		apply2SearchPOJO.setActivityId(id);
		apply2SearchPOJO.setStartDateTime(startDateTime);
		apply2SearchPOJO.setEndDateTime(endDateTime);
		
		List<Apply2POJO> apply2pojos = apply2Mapper.finds2ByActivityId(apply2SearchPOJO);
		if (ret != null && CollectionUtils.isNotEmpty(apply2pojos)) {
			ret.setApply2POJOs(apply2pojos);
		}
		
		return ret;
	}

	@Override
	public List<ActivityPOJO> findActivitys4WxPerson(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		List<ActivityPOJO> ret = null;
		ret = activityMapper.findActivitys4WxPerson(activitySearchPOJO);
		return ret;
	}

	@Override
	public int getActivitys4WxPersonCount(ActivitySearchPOJO activitySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = activityMapper.getActivitys4WxPersonCount(activitySearchPOJO);
		return ret;
	}

}

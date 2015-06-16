package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ApplyMapper;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.ApplyPOJO;
import com.cobble.takeaway.pojo.ApplySearchPOJO;
import com.cobble.takeaway.pojo.RelActivityApplyPOJO;
import com.cobble.takeaway.service.ApplyService;

@Service
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	private ApplyMapper applyMapper;

	@Override
	public int insert(ApplyPOJO applyPOJO) throws Exception {
		int ret = 0;
		ret = applyMapper.insert(applyPOJO);
		RelActivityApplyPOJO relActivityApplyPOJO = new RelActivityApplyPOJO();
		relActivityApplyPOJO.setActivityId(applyPOJO.getActivityId());
		relActivityApplyPOJO.setApplyId(applyPOJO.getApplyId());
		applyMapper.insertRelActivityApply(relActivityApplyPOJO);
		return ret;
	}

	@Override
	public int update(ApplyPOJO applyPOJO) throws Exception {
		int ret = 0;
		ret = applyMapper.update(applyPOJO);
		return ret;
	}

	@Override
	public List<ApplyPOJO> finds(
			ApplySearchPOJO applySearchPOJO) throws Exception {
		List<ApplyPOJO> ret = null;
		ret = applyMapper.finds(applySearchPOJO);
		return ret;
	}

	@Override
	public int getCount(ApplySearchPOJO applySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = applyMapper.getCount(applySearchPOJO);
		return ret;
	}

	@Override
	public ApplyPOJO findById(Long id) throws Exception {
		ApplyPOJO ret = null;
		ret = applyMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = applyMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += applyMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<ApplyPOJO> findsApplyInActivity(Long activityId)
			throws Exception {
		List<ApplyPOJO> ret = null;
		ActivitySearchPOJO activitySearchPOJO = new ActivitySearchPOJO();
		activitySearchPOJO.setActivityId(activityId);
		ret = applyMapper.findsApplyInActivity(activitySearchPOJO);
		return ret;
	}

	@Override
	public Boolean existApplyInActivity(ApplySearchPOJO applySearchPOJO) throws Exception {
		Boolean ret = false;
		int result = applyMapper.findsApplyInActivityByPhone(applySearchPOJO);
		if (result > 0) {
			ret = true;
		}
		return ret;
	}

	@Override
	public int getCountApplyInActivity(Long activityId)
			throws Exception {
		int ret = 0;
		ActivitySearchPOJO activitySearchPOJO = new ActivitySearchPOJO();
		activitySearchPOJO.setActivityId(activityId);
		ret = applyMapper.getCountApplyInActivity(activitySearchPOJO);
		return ret;
	}

}

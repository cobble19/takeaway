package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.ActivityMapper;
import com.cobble.takeaway.dao.VoteMapper;
import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.pojo.VoteSearchPOJO;
import com.cobble.takeaway.service.ActivityService;
import com.cobble.takeaway.service.VoteService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.UserUtil;

@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private VoteMapper voteMapper;
	@Autowired
	private ActivityService activityService;

	@Override
	public int insert(VotePOJO votePOJO) throws Exception {
		int ret = 0;
		Long userId = UserUtil.getCurrentUserId();
		ActivityPOJO activityPOJO = new ActivityPOJO();
		activityPOJO.setTitle(votePOJO.getTitle());
		activityPOJO.setContent(votePOJO.getContent());
//		activityPOJO.setStartDateTime(startDateTime);
//		activityPOJO.setEndDateTime(endDateTime);
		activityPOJO.setTypeCode(1);
		activityPOJO.setPublishType(1);
		activityPOJO.setNeedSubscribe(0);
		
		activityService.insert(activityPOJO, userId);
		votePOJO.setActivityId(activityPOJO.getActivityId());

		ret = voteMapper.insert(votePOJO);
		return ret;
	}

	@Override
	public int update(VotePOJO votePOJO) throws Exception {
		int ret = 0;
		ret = voteMapper.update(votePOJO);
		return ret;
	}

	@Override
	public List<VotePOJO> finds(
			VoteSearchPOJO voteSearchPOJO) throws Exception {
		List<VotePOJO> ret = null;
		ret = voteMapper.finds(voteSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(VoteSearchPOJO voteSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = voteMapper.getCount(voteSearchPOJO);
		return ret;
	}

	@Override
	public VotePOJO findById(Long id) throws Exception {
		VotePOJO ret = null;
		ret = voteMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = voteMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += voteMapper.deleteById(id);
			}
		}
		return ret;
	}


}

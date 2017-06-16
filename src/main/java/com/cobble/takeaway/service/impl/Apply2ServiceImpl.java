package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.Apply2AttrMapper;
import com.cobble.takeaway.dao.Apply2Mapper;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.Apply2AttrModelPOJO;
import com.cobble.takeaway.pojo.Apply2AttrModelSearchPOJO;
import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2AttrSearchPOJO;
import com.cobble.takeaway.pojo.Apply2POJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;
import com.cobble.takeaway.pojo.RelActivityApply2POJO;
import com.cobble.takeaway.service.Apply2Service;

@Service
public class Apply2ServiceImpl implements Apply2Service {
	
	@Autowired
	private Apply2Mapper apply2Mapper;
	@Autowired
	private Apply2AttrMapper apply2AttrMapper;

	@Override
	public int insert(Apply2POJO apply2POJO) throws Exception {
		int ret = 0;
		ret = apply2Mapper.insert(apply2POJO);
		return ret;
	}

	@Override
	public int update(Apply2POJO apply2POJO) throws Exception {
		int ret = 0;
		ret = apply2Mapper.update(apply2POJO);
		return ret;
	}

	@Override
	public List<Apply2POJO> finds(
			Apply2SearchPOJO apply2SearchPOJO) throws Exception {
		List<Apply2POJO> ret = null;
		ret = apply2Mapper.finds(apply2SearchPOJO);
		return ret;
	}

	@Override
	public int getCount(Apply2SearchPOJO apply2SearchPOJO)
			throws Exception {
		int ret = 0;
		ret = apply2Mapper.getCount(apply2SearchPOJO);
		return ret;
	}

	@Override
	public Apply2POJO findById(Long id) throws Exception {
		Apply2POJO ret = null;
		ret = apply2Mapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = apply2Mapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += apply2Mapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<Apply2POJO> findsApply2InActivity(
			ActivitySearchPOJO activitySearchPOJO) throws Exception {
		List<Apply2POJO> ret = null;
		ret = apply2Mapper.findsApply2InActivity(activitySearchPOJO);
		return ret;
	}

	@Override
	public int getCountApply2InActivity(ActivitySearchPOJO activitySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = apply2Mapper.getCountApply2InActivity(activitySearchPOJO);
		return ret;
	}

	@Override
	public int findsApply2InActivityByPhone(Apply2SearchPOJO apply2SearchPOJO)
			throws Exception {
		int ret = 0;
		ret = apply2Mapper.findsApply2InActivityByPhone(apply2SearchPOJO);
		return ret;
	}

	@Override
	public int insertRelActivityApply2(
			RelActivityApply2POJO relActivityApply2POJO) throws Exception {
		int ret = 0;
		ret = apply2Mapper.insertRelActivityApply2(relActivityApply2POJO);
		return ret;
	}

	@Override
	public List<Apply2POJO> finds2ByActivityId(Apply2SearchPOJO apply2SearchPOJO) throws Exception {
		List<Apply2POJO> ret = new ArrayList<Apply2POJO>();
		ret = apply2Mapper.finds2ByActivityId(apply2SearchPOJO);
		// get apply2attr data
		Apply2AttrSearchPOJO apply2AttrSearchPOJO = new Apply2AttrSearchPOJO();
		apply2AttrSearchPOJO.setActivityId(apply2SearchPOJO.getActivityId());
		apply2AttrSearchPOJO.setPaginationFlage(false);
		List<Apply2AttrPOJO> apply2AttrPOJOs = apply2AttrMapper.finds(apply2AttrSearchPOJO);
		// Map<apply2Id, >
		Map<Long, List<Apply2AttrPOJO>> map = new HashMap<Long, List<Apply2AttrPOJO>>();
		
		if (CollectionUtils.isNotEmpty(apply2AttrPOJOs)) {
			for (Apply2AttrPOJO apply2AttrPOJO : apply2AttrPOJOs) {
				List<Apply2AttrPOJO> apply2AttrPOJOs2 = map.get(apply2AttrPOJO.getApply2Id());
				if (CollectionUtils.isEmpty(apply2AttrPOJOs2)) {
					apply2AttrPOJOs2 = new ArrayList<Apply2AttrPOJO>();
				}
				apply2AttrPOJOs2.add(apply2AttrPOJO);
				map.put(apply2AttrPOJO.getApply2Id(), apply2AttrPOJOs2);
			}
		}
		
		if (CollectionUtils.isNotEmpty(ret)) {
			for (Apply2POJO apply2pojo : ret) {
				apply2pojo.setApply2AttrPOJOs(map.get(apply2pojo.getApply2Id()));
			}
		}
		
		
		return ret;
	}
	
	@Override
	public int findsApply2InActivityByUnionId(Apply2SearchPOJO apply2SearchPOJO)
			throws Exception {
		int ret = 0;
		ret = apply2Mapper.findsApply2InActivityByUnionId(apply2SearchPOJO);
		return ret;
	}


}

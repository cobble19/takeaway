package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.Apply2AttrModelMapper;
import com.cobble.takeaway.pojo.Apply2AttrModelPOJO;
import com.cobble.takeaway.pojo.Apply2AttrModelSearchPOJO;
import com.cobble.takeaway.service.Apply2AttrModelService;

@Service
public class Apply2AttrModelServiceImpl implements Apply2AttrModelService {
	
	@Autowired
	private Apply2AttrModelMapper apply2AttrModelMapper;

	@Override
	public int insert(Apply2AttrModelPOJO apply2AttrModelPOJO) throws Exception {
		int ret = 0;
		ret = apply2AttrModelMapper.insert(apply2AttrModelPOJO);
		return ret;
	}

	@Override
	public int update(Apply2AttrModelPOJO apply2AttrModelPOJO) throws Exception {
		int ret = 0;
		ret = apply2AttrModelMapper.update(apply2AttrModelPOJO);
		return ret;
	}

	@Override
	public List<Apply2AttrModelPOJO> finds(
			Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO) throws Exception {
		List<Apply2AttrModelPOJO> ret = null;
		ret = apply2AttrModelMapper.finds(apply2AttrModelSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = apply2AttrModelMapper.getCount(apply2AttrModelSearchPOJO);
		return ret;
	}

	@Override
	public Apply2AttrModelPOJO findById(Long id) throws Exception {
		Apply2AttrModelPOJO ret = null;
		ret = apply2AttrModelMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = apply2AttrModelMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += apply2AttrModelMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public int deleteByActivityId(Long activityId) throws Exception {
		int ret = 0;
		ret = apply2AttrModelMapper.deleteByActivityId(activityId);
		return ret;
	}

	@Override
	public List<Apply2AttrModelPOJO> findsByActivityId(Long activityId)
			throws Exception {
		List<Apply2AttrModelPOJO> ret = null;
		Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO = new Apply2AttrModelSearchPOJO();
		apply2AttrModelSearchPOJO.setActivityId(activityId);
		apply2AttrModelSearchPOJO.setPaginationFlage(false);
		ret = apply2AttrModelMapper.finds(apply2AttrModelSearchPOJO);
		return ret;
	}

}

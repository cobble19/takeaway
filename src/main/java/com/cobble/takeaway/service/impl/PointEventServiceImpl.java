package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.PointEventMapper;
import com.cobble.takeaway.pojo.PointEventPOJO;
import com.cobble.takeaway.pojo.PointEventSearchPOJO;
import com.cobble.takeaway.service.PointEventService;

@Service
public class PointEventServiceImpl implements PointEventService {
	
	@Autowired
	private PointEventMapper pointEventMapper;

	@Override
	public int insert(PointEventPOJO pointEventPOJO, Long userId) throws Exception {
		int ret = 0;
		ret = pointEventMapper.insert(pointEventPOJO);
		return ret;
	}

	@Override
	public int update(PointEventPOJO pointEventPOJO) throws Exception {
		int ret = 0;
		ret = pointEventMapper.update(pointEventPOJO);
		return ret;
	}

	@Override
	public List<PointEventPOJO> finds(
			PointEventSearchPOJO pointEventSearchPOJO) throws Exception {
		List<PointEventPOJO> ret = null;
		ret = pointEventMapper.finds(pointEventSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(PointEventSearchPOJO pointEventSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = pointEventMapper.getCount(pointEventSearchPOJO);
		return ret;
	}

	@Override
	public PointEventPOJO findById(Long id) throws Exception {
		PointEventPOJO ret = null;
		ret = pointEventMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = pointEventMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += pointEventMapper.deleteById(id);
			}
		}
		return ret;
	}


}

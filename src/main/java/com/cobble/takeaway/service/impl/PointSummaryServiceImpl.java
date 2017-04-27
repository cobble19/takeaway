package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.PointSummaryMapper;
import com.cobble.takeaway.pojo.PointSummaryPOJO;
import com.cobble.takeaway.pojo.PointSummarySearchPOJO;
import com.cobble.takeaway.service.PointSummaryService;

@Service
public class PointSummaryServiceImpl implements PointSummaryService {
	
	@Autowired
	private PointSummaryMapper pointSummaryMapper;

	@Override
	public int insert(PointSummaryPOJO pointSummaryPOJO, Long userId) throws Exception {
		int ret = 0;
		ret = pointSummaryMapper.insert(pointSummaryPOJO);
		return ret;
	}

	@Override
	public int update(PointSummaryPOJO pointSummaryPOJO) throws Exception {
		int ret = 0;
		ret = pointSummaryMapper.update(pointSummaryPOJO);
		return ret;
	}

	@Override
	public List<PointSummaryPOJO> finds(
			PointSummarySearchPOJO pointSummarySearchPOJO) throws Exception {
		List<PointSummaryPOJO> ret = null;
		ret = pointSummaryMapper.finds(pointSummarySearchPOJO);
		return ret;
	}

	@Override
	public int getCount(PointSummarySearchPOJO pointSummarySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = pointSummaryMapper.getCount(pointSummarySearchPOJO);
		return ret;
	}

	@Override
	public PointSummaryPOJO findById(Long id) throws Exception {
		PointSummaryPOJO ret = null;
		ret = pointSummaryMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = pointSummaryMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += pointSummaryMapper.deleteById(id);
			}
		}
		return ret;
	}


}

package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.PointRecordMapper;
import com.cobble.takeaway.pojo.PointRecordPOJO;
import com.cobble.takeaway.pojo.PointRecordSearchPOJO;
import com.cobble.takeaway.service.PointRecordService;

@Service
public class PointRecordServiceImpl implements PointRecordService {
	
	@Autowired
	private PointRecordMapper pointRecordMapper;

	@Override
	public int insert(PointRecordPOJO pointRecordPOJO, Long userId) throws Exception {
		int ret = 0;
		ret = pointRecordMapper.insert(pointRecordPOJO);
		return ret;
	}

	@Override
	public int update(PointRecordPOJO pointRecordPOJO) throws Exception {
		int ret = 0;
		ret = pointRecordMapper.update(pointRecordPOJO);
		return ret;
	}

	@Override
	public List<PointRecordPOJO> finds(
			PointRecordSearchPOJO pointRecordSearchPOJO) throws Exception {
		List<PointRecordPOJO> ret = null;
		ret = pointRecordMapper.finds(pointRecordSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(PointRecordSearchPOJO pointRecordSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = pointRecordMapper.getCount(pointRecordSearchPOJO);
		return ret;
	}

	@Override
	public PointRecordPOJO findById(Long id) throws Exception {
		PointRecordPOJO ret = null;
		ret = pointRecordMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = pointRecordMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += pointRecordMapper.deleteById(id);
			}
		}
		return ret;
	}


}

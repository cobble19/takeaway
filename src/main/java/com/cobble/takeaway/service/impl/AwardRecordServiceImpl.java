package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.AwardRecordMapper;
import com.cobble.takeaway.pojo.AwardRecordPOJO;
import com.cobble.takeaway.pojo.AwardRecordSearchPOJO;
import com.cobble.takeaway.service.AwardRecordService;

@Service
public class AwardRecordServiceImpl implements AwardRecordService {
	
	@Autowired
	private AwardRecordMapper awardRecordMapper;

	@Override
	public int insert(AwardRecordPOJO awardRecordPOJO) throws Exception {
		int ret = 0;
		ret = awardRecordMapper.insert(awardRecordPOJO);
		return ret;
	}

	@Override
	public int update(AwardRecordPOJO awardRecordPOJO) throws Exception {
		int ret = 0;
		ret = awardRecordMapper.update(awardRecordPOJO);
		return ret;
	}

	@Override
	public List<AwardRecordPOJO> finds(
			AwardRecordSearchPOJO awardRecordSearchPOJO) throws Exception {
		List<AwardRecordPOJO> ret = null;
		ret = awardRecordMapper.finds(awardRecordSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(AwardRecordSearchPOJO awardRecordSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = awardRecordMapper.getCount(awardRecordSearchPOJO);
		return ret;
	}

	@Override
	public AwardRecordPOJO findById(Long id) throws Exception {
		AwardRecordPOJO ret = null;
		ret = awardRecordMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = awardRecordMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += awardRecordMapper.deleteById(id);
			}
		}
		return ret;
	}

}

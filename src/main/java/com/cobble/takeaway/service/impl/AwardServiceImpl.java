package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.AwardMapper;
import com.cobble.takeaway.pojo.AwardPOJO;
import com.cobble.takeaway.pojo.AwardSearchPOJO;
import com.cobble.takeaway.service.AwardService;

@Service
public class AwardServiceImpl implements AwardService {
	
	@Autowired
	private AwardMapper awardMapper;

	@Override
	public int insert(AwardPOJO awardPOJO) throws Exception {
		int ret = 0;
		ret = awardMapper.insert(awardPOJO);
		return ret;
	}

	@Override
	public int update(AwardPOJO awardPOJO) throws Exception {
		int ret = 0;
		ret = awardMapper.update(awardPOJO);
		return ret;
	}

	@Override
	public List<AwardPOJO> finds(
			AwardSearchPOJO awardSearchPOJO) throws Exception {
		List<AwardPOJO> ret = null;
		ret = awardMapper.finds(awardSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(AwardSearchPOJO awardSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = awardMapper.getCount(awardSearchPOJO);
		return ret;
	}

	@Override
	public AwardPOJO findById(Long id) throws Exception {
		AwardPOJO ret = null;
		ret = awardMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = awardMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += awardMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public int decreaseBalance(AwardPOJO awardPOJO) throws Exception {
		int ret = 0;
		ret = awardMapper.decreaseBalance(awardPOJO);
		return ret;
	}

}

package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.RecommendMapper;
import com.cobble.takeaway.pojo.RecommendPOJO;
import com.cobble.takeaway.pojo.RecommendSearchPOJO;
import com.cobble.takeaway.service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Autowired
	private RecommendMapper recommendMapper;

	@Override
	public int insert(RecommendPOJO recommendPOJO) throws Exception {
		int ret = 0;
		ret = recommendMapper.insert(recommendPOJO);
		return ret;
	}

	@Override
	public int update(RecommendPOJO recommendPOJO) throws Exception {
		int ret = 0;
		ret = recommendMapper.update(recommendPOJO);
		return ret;
	}

	@Override
	public List<RecommendPOJO> finds(
			RecommendSearchPOJO recommendSearchPOJO) throws Exception {
		List<RecommendPOJO> ret = null;
		ret = recommendMapper.finds(recommendSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(RecommendSearchPOJO recommendSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = recommendMapper.getCount(recommendSearchPOJO);
		return ret;
	}

	@Override
	public RecommendPOJO findById(Long id) throws Exception {
		RecommendPOJO ret = null;
		ret = recommendMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = recommendMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += recommendMapper.deleteById(id);
			}
		}
		return ret;
	}

}

package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.RelVoteUserMapper;
import com.cobble.takeaway.pojo.RelVoteUserPOJO;
import com.cobble.takeaway.pojo.RelVoteUserSearchPOJO;
import com.cobble.takeaway.service.RelVoteUserService;

@Service
public class RelVoteUserServiceImpl implements RelVoteUserService {
	
	@Autowired
	private RelVoteUserMapper relVoteUserMapper;

	@Override
	public int insert(RelVoteUserPOJO relVoteUserPOJO) throws Exception {
		int ret = 0;
		ret = relVoteUserMapper.insert(relVoteUserPOJO);
		return ret;
	}

	@Override
	public int update(RelVoteUserPOJO relVoteUserPOJO) throws Exception {
		int ret = 0;
		ret = relVoteUserMapper.update(relVoteUserPOJO);
		return ret;
	}

	@Override
	public List<RelVoteUserPOJO> finds(
			RelVoteUserSearchPOJO relVoteUserSearchPOJO) throws Exception {
		List<RelVoteUserPOJO> ret = null;
		ret = relVoteUserMapper.finds(relVoteUserSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(RelVoteUserSearchPOJO relVoteUserSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = relVoteUserMapper.getCount(relVoteUserSearchPOJO);
		return ret;
	}

	@Override
	public RelVoteUserPOJO findById(Long id) throws Exception {
		RelVoteUserPOJO ret = null;
		ret = relVoteUserMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = relVoteUserMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += relVoteUserMapper.deleteById(id);
			}
		}
		return ret;
	}

}

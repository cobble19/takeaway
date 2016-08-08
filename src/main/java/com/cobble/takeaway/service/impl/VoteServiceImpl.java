package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.VoteMapper;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.pojo.VoteSearchPOJO;
import com.cobble.takeaway.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private VoteMapper voteMapper;

	@Override
	public int insert(VotePOJO votePOJO) throws Exception {
		int ret = 0;
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

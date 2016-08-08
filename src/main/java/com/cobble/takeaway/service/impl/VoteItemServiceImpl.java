package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.VoteItemMapper;
import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;
import com.cobble.takeaway.service.VoteItemService;

@Service
public class VoteItemServiceImpl implements VoteItemService {
	
	@Autowired
	private VoteItemMapper voteItemMapper;

	@Override
	public int insert(VoteItemPOJO voteItemPOJO, Long userId) throws Exception {
		int ret = 0;
		ret = voteItemMapper.insert(voteItemPOJO);
		return ret;
	}

	@Override
	public int update(VoteItemPOJO voteItemPOJO) throws Exception {
		int ret = 0;
		ret = voteItemMapper.update(voteItemPOJO);
		return ret;
	}

	@Override
	public List<VoteItemPOJO> finds(
			VoteItemSearchPOJO voteItemSearchPOJO) throws Exception {
		List<VoteItemPOJO> ret = null;
		ret = voteItemMapper.finds(voteItemSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(VoteItemSearchPOJO voteItemSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = voteItemMapper.getCount(voteItemSearchPOJO);
		return ret;
	}

	@Override
	public VoteItemPOJO findById(Long id) throws Exception {
		VoteItemPOJO ret = null;
		ret = voteItemMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = voteItemMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += voteItemMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<VoteItemPOJO> findsByVoteId(Long voteId) throws Exception {
		List<VoteItemPOJO> ret = null;
		VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
		voteItemSearchPOJO.setVoteId(voteId);
		ret = voteItemMapper.finds(voteItemSearchPOJO);
		return ret;
	}

	@Override
	public int increaseTotalNumPlusOne(VoteItemPOJO voteItemPOJO)
			throws Exception {
		int ret = 0;
		ret = voteItemMapper.increaseTotalNumPlusOne(voteItemPOJO);
		return ret;
	}

}

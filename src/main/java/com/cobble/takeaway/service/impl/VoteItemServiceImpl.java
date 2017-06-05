package com.cobble.takeaway.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.Apply2AttrMapper;
import com.cobble.takeaway.dao.Apply2Mapper;
import com.cobble.takeaway.dao.VoteItemMapper;
import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2AttrSearchPOJO;
import com.cobble.takeaway.pojo.Apply2POJO;
import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.service.VoteItemService;
import com.cobble.takeaway.util.CollectionUtilx;

@Service
public class VoteItemServiceImpl implements VoteItemService {
	
	@Autowired
	private VoteItemMapper voteItemMapper;
	@Autowired
	private Apply2Mapper apply2Mapper;
	@Autowired
	private Apply2AttrMapper apply2AttrMapper;

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
	public Map<Long, VoteItemPOJO> finds4Map(
			VoteItemSearchPOJO voteItemSearchPOJO) throws Exception {
		Map<Long, VoteItemPOJO> ret = new HashMap<Long, VoteItemPOJO>();
		List<VoteItemPOJO> result = voteItemMapper.finds(voteItemSearchPOJO);
		if (CollectionUtils.isNotEmpty(result)) {
			for (VoteItemPOJO voteItemPOJO : result) {
				ret.put(voteItemPOJO.getApply2Id(), voteItemPOJO);
			}
		}
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
	public List<VoteItemPOJO> findsByVote(VotePOJO votePOJO) throws Exception {
		List<VoteItemPOJO> ret = null;
		VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
		voteItemSearchPOJO.setVoteId(votePOJO.getVoteId());
		ret = voteItemMapper.finds(voteItemSearchPOJO);
		
		Long activityId = votePOJO.getActivityId();
		if (activityId != null) {
			String apply2AttrModelIdsStr = votePOJO.getApply2AttrModelIds();
			List<Long> apply2AttrModelIds = CollectionUtilx.string2Longs(apply2AttrModelIdsStr);
			
			if (CollectionUtils.isNotEmpty(ret)) {
				for (VoteItemPOJO voteItemPOJO : ret) {
					Long apply2Id = voteItemPOJO.getApply2Id();
					Apply2POJO apply2pojo = apply2Mapper.findById(apply2Id);
					Apply2AttrSearchPOJO apply2AttrSearchPOJO = new Apply2AttrSearchPOJO();
					apply2AttrSearchPOJO.setApply2Id(apply2Id);
					apply2AttrSearchPOJO.setApply2AttrModelIds(apply2AttrModelIds);
					List<Apply2AttrPOJO> apply2AttrPOJOs = apply2AttrMapper.finds(apply2AttrSearchPOJO);
					if (apply2pojo != null) {
						apply2pojo.setApply2AttrPOJOs(apply2AttrPOJOs);
						voteItemPOJO.setApply2POJO(apply2pojo);
					}
				}
			}
		}
		
		
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

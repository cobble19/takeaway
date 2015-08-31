package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.InteractiveApplyMapper;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.InteractiveApplyPOJO;
import com.cobble.takeaway.pojo.InteractiveApplySearchPOJO;
import com.cobble.takeaway.service.InteractiveApplyService;

@Service
public class InteractiveApplyServiceImpl implements InteractiveApplyService {
	
	@Autowired
	private InteractiveApplyMapper interactiveApplyMapper;

	@Override
	public int insert(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception {
		int ret = 0;
		ret = interactiveApplyMapper.insert(interactiveApplyPOJO);
		return ret;
	}

	@Override
	public int update(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception {
		int ret = 0;
		ret = interactiveApplyMapper.update(interactiveApplyPOJO);
		return ret;
	}

	@Override
	public List<InteractiveApplyPOJO> finds(
			InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception {
		List<InteractiveApplyPOJO> ret = null;
		ret = interactiveApplyMapper.finds(interactiveApplySearchPOJO);
		return ret;
	}

	@Override
	public int getCount(InteractiveApplySearchPOJO interactiveApplySearchPOJO)
			throws Exception {
		int ret = 0;
		ret = interactiveApplyMapper.getCount(interactiveApplySearchPOJO);
		return ret;
	}

	@Override
	public InteractiveApplyPOJO findById(Long id) throws Exception {
		InteractiveApplyPOJO ret = null;
		ret = interactiveApplyMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = interactiveApplyMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += interactiveApplyMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<InteractiveApplyPOJO> findsInteractiveApplyInActivity(Long interactiveId)
			throws Exception {
		List<InteractiveApplyPOJO> ret = null;
		InteractiveApplySearchPOJO interactiveApplySearchPOJO = new InteractiveApplySearchPOJO();
		interactiveApplySearchPOJO.setInteractiveId(interactiveId);
		ret = interactiveApplyMapper.findsInteractiveApplyInActivity(interactiveApplySearchPOJO);
		return ret;
	}

	@Override
	public int getCountApplyInActivity(Long interactiveId)
			throws Exception {
		int ret = 0;
		InteractiveApplySearchPOJO interactiveApplySearchPOJO = new InteractiveApplySearchPOJO();
		interactiveApplySearchPOJO.setInteractiveId(interactiveId);
		ret = interactiveApplyMapper.getCountInteractiveApplyInActivity(interactiveApplySearchPOJO);
		return ret;
	}

	@Override
	public Boolean existInteractiveApplyByUserId(InteractiveApplyPOJO interactiveApplyPOJO)
			throws Exception {
		Boolean ret = false;
		int result = interactiveApplyMapper.getCountInteractiveApplyByUserId(interactiveApplyPOJO);
		
		ret = (result > 0 ? true : false);
		
		return ret;
	}

}

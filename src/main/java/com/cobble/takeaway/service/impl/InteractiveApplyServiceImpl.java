package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.InteractiveApplyMapper;
import com.cobble.takeaway.dao.InteractiveMapper;
import com.cobble.takeaway.pojo.InteractiveApplyPOJO;
import com.cobble.takeaway.pojo.InteractiveApplySearchPOJO;
import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.service.InteractiveApplyService;
import com.cobble.takeaway.service.InteractiveService;

@Service
public class InteractiveApplyServiceImpl implements InteractiveApplyService {
	
	@Autowired
	private InteractiveApplyMapper interactiveApplyMapper;
	@Autowired
	private InteractiveMapper interactiveMapper;
	@Autowired
	private InteractiveService interactiveService;

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
	public List<InteractiveApplyPOJO> findsApplyInInteractive(InteractiveApplySearchPOJO interactiveApplySearchPOJO)
			throws Exception {
		List<InteractiveApplyPOJO> ret = null;
		ret = interactiveApplyMapper.findsApplyInInteractive(interactiveApplySearchPOJO);
		return ret;
	}

	@Override
	public int getCountInteractiveApplyInInteractive(Long interactiveId)
			throws Exception {
		int ret = 0;
		InteractiveApplySearchPOJO interactiveApplySearchPOJO = new InteractiveApplySearchPOJO();
		interactiveApplySearchPOJO.setInteractiveId(interactiveId);
		ret = interactiveApplyMapper.getCountInteractiveApplyInInteractive(interactiveApplySearchPOJO);
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

	@Override
	public List<InteractiveApplyPOJO> findsApplyByVerifyCode(
			InteractiveApplySearchPOJO interactiveApplySearchPOJO)
			throws Exception {
		List<InteractiveApplyPOJO> ret = null;
		ret = interactiveApplyMapper.findsApplyByVerifyCode(interactiveApplySearchPOJO);
		return ret;
	}

	@Override
	public int updateIsWinner(InteractiveApplyPOJO interactiveApplyPOJO)
			throws Exception {
		int ret = interactiveApplyMapper.updateIsWinner(interactiveApplyPOJO);
		return ret;
	}

	@Override
	public List<InteractiveApplyPOJO> getInteractiveApplyWinner(
			Long interactiveId) throws Exception {
		InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);
		
		InteractiveApplySearchPOJO interactiveApplySearchPOJO = new InteractiveApplySearchPOJO();
		interactiveApplySearchPOJO.setInteractiveId(interactiveId);
		interactiveApplySearchPOJO.setStart(0);
		interactiveApplySearchPOJO.setLimit(interactivePOJO.getNumOfWinner());
		List<InteractiveApplyPOJO> interactiveApplyPOJOs = this.findsApplyInInteractive(interactiveApplySearchPOJO);
		
		return interactiveApplyPOJOs;
	}
	

}

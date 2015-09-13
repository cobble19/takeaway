package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.InteractiveMapper;
import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.pojo.InteractiveSearchPOJO;
import com.cobble.takeaway.pojo.RelInteractiveUserPOJO;
import com.cobble.takeaway.service.InteractiveService;

@Service
public class InteractiveServiceImpl implements InteractiveService {
	
	@Autowired
	private InteractiveMapper interactiveMapper;

	@Override
	public int insert(InteractivePOJO interactivePOJO, Long userId) throws Exception {
		int ret = 0;
		ret = interactiveMapper.insert(interactivePOJO);
		RelInteractiveUserPOJO relInteractiveUserPOJO = new RelInteractiveUserPOJO();
		relInteractiveUserPOJO.setInteractiveId(interactivePOJO.getInteractiveId());
		relInteractiveUserPOJO.setUserId(userId);
		interactiveMapper.insertRelInteractiveUser(relInteractiveUserPOJO);
		return ret;
	}

	@Override
	public int update(InteractivePOJO interactivePOJO) throws Exception {
		int ret = 0;
		ret = interactiveMapper.update(interactivePOJO);
		return ret;
	}

	@Override
	public List<InteractivePOJO> finds(
			InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		List<InteractivePOJO> ret = null;
		ret = interactiveMapper.finds(interactiveSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(InteractiveSearchPOJO interactiveSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = interactiveMapper.getCount(interactiveSearchPOJO);
		return ret;
	}

	@Override
	public InteractivePOJO findById(Long id) throws Exception {
		InteractivePOJO ret = null;
		ret = interactiveMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = interactiveMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += interactiveMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public List<InteractivePOJO> findActives(
			InteractiveSearchPOJO interactiveSearchPOJO) throws Exception {
		List<InteractivePOJO> ret = interactiveMapper.findActives(interactiveSearchPOJO);
		return ret;
	}

	@Override
	public int getActiveCount(InteractiveSearchPOJO interactiveSearchPOJO)
			throws Exception {
		int ret = interactiveMapper.getActiveCount(interactiveSearchPOJO);
		return ret;
	}

}

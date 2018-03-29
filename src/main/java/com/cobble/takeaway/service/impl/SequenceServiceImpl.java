package com.cobble.takeaway.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.SequenceMapper;
import com.cobble.takeaway.pojo.SequencePOJO;
import com.cobble.takeaway.pojo.SequenceSearchPOJO;
import com.cobble.takeaway.service.SequenceService;

@Service
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private SequenceMapper sequenceMapper;

	@Override
	public int insert(SequencePOJO sequencePOJO) throws Exception {
		int ret = 0;
		ret = sequenceMapper.insert(sequencePOJO);
		return ret;
	}

	@Override
	public int update(SequencePOJO sequencePOJO) throws Exception {
		int ret = 0;
		ret = sequenceMapper.update(sequencePOJO);
		return ret;
	}

	@Override
	public int increaseByKey(SequencePOJO sequencePOJO) throws Exception {
		int ret = 0;
		ret = sequenceMapper.increaseByKey(sequencePOJO);
		return ret;
	}
	
	@Override
	public List<SequencePOJO> finds(
			SequenceSearchPOJO sequenceSearchPOJO) throws Exception {
		List<SequencePOJO> ret = null;
		ret = sequenceMapper.finds(sequenceSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(SequenceSearchPOJO sequenceSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = sequenceMapper.getCount(sequenceSearchPOJO);
		return ret;
	}

	@Override
	public SequencePOJO findById(Long id) throws Exception {
		SequencePOJO ret = null;
		ret = sequenceMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = sequenceMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += sequenceMapper.deleteById(id);
			}
		}
		return ret;
	}
	
	public Long getNextValue(String key) throws Exception {
		Long ret = -9999L;
		List<SequencePOJO> sequencePOJOs = null;
		SequenceSearchPOJO sequenceSearchPOJO = new SequenceSearchPOJO();
		sequenceSearchPOJO.setSequenceKey(key);
		sequencePOJOs = sequenceMapper.finds(sequenceSearchPOJO);
		if (CollectionUtils.isEmpty(sequencePOJOs)) {
			SequencePOJO sequencePOJO = new SequencePOJO();
			sequencePOJO.setSequenceKey(key);
			sequencePOJO.setSequenceValue(0L);
			sequencePOJO.setCreateDateTime(new Date());
			sequencePOJO.setLastModifiedDateTime(new Date());
			sequenceMapper.insert(sequencePOJO);
		} else {
			SequencePOJO sequencePOJO = sequencePOJOs.get(0);		// get first ONE
			ret = sequencePOJO.getSequenceValue() + 1;
			sequencePOJO.setLastModifiedDateTime(new Date());
			sequenceMapper.increaseByKey(sequencePOJO);
		}
		return ret;
	}
}

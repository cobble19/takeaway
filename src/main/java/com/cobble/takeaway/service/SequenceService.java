package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.SequencePOJO;
import com.cobble.takeaway.pojo.SequenceSearchPOJO;

public interface SequenceService {

	int insert(SequencePOJO sequencePOJO) throws Exception;
	int update(SequencePOJO sequencePOJO) throws Exception;
	int increaseByKey(SequencePOJO sequencePOJO) throws Exception;
	List<SequencePOJO> finds(SequenceSearchPOJO sequenceSearchPOJO) throws Exception;
	
	int getCount(SequenceSearchPOJO sequenceSearchPOJO) throws Exception;
	SequencePOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	Long getNextValue(String key) throws Exception;
}

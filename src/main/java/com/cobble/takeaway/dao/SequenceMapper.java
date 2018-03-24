package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.SequencePOJO;
import com.cobble.takeaway.pojo.SequenceSearchPOJO;


public interface SequenceMapper {
	int insert(SequencePOJO sequencePOJO) throws Exception;
	int update(SequencePOJO sequencePOJO) throws Exception;
	int increateByKey(SequencePOJO sequencePOJO) throws Exception;
	List<SequencePOJO> finds(SequenceSearchPOJO sequenceSearchPOJO) throws Exception;
	int getCount(SequenceSearchPOJO sequenceSearchPOJO) throws Exception;
	
	SequencePOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}
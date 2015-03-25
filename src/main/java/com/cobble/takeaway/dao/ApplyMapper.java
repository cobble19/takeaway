package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.ApplyPOJO;
import com.cobble.takeaway.pojo.ApplySearchPOJO;


public interface ApplyMapper {
	int insert(ApplyPOJO applyPOJO) throws Exception;
	int update(ApplyPOJO applyPOJO) throws Exception;
	List<ApplyPOJO> finds(ApplySearchPOJO applySearchPOJO) throws Exception;
	int getCount(ApplySearchPOJO applySearchPOJO) throws Exception;
	ApplyPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
}
package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.PointRecordPOJO;
import com.cobble.takeaway.pojo.PointRecordSearchPOJO;

public interface PointRecordService {
	int insert(PointRecordPOJO pointRecordPOJO, Long userId) throws Exception;
	int update(PointRecordPOJO pointRecordPOJO) throws Exception;
	List<PointRecordPOJO> finds(PointRecordSearchPOJO pointRecordSearchPOJO) throws Exception;
	int getCount(PointRecordSearchPOJO pointRecordSearchPOJO) throws Exception;
	PointRecordPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

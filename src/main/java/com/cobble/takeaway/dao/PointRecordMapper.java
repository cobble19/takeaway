package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.PointRecordPOJO;
import com.cobble.takeaway.pojo.PointRecordSearchPOJO;


public interface PointRecordMapper {
	int insert(PointRecordPOJO pointRecordPOJO) throws Exception;
	int update(PointRecordPOJO pointRecordPOJO) throws Exception;
	List<PointRecordPOJO> finds(PointRecordSearchPOJO pointRecordSearchPOJO) throws Exception;
	int getCount(PointRecordSearchPOJO pointRecordSearchPOJO) throws Exception;
	PointRecordPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

}
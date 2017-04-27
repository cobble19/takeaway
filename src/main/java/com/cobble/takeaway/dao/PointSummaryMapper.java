package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.PointSummaryPOJO;
import com.cobble.takeaway.pojo.PointSummarySearchPOJO;


public interface PointSummaryMapper {
	int insert(PointSummaryPOJO pointSummaryPOJO) throws Exception;
	int update(PointSummaryPOJO pointSummaryPOJO) throws Exception;
	List<PointSummaryPOJO> finds(PointSummarySearchPOJO pointSummarySearchPOJO) throws Exception;
	int getCount(PointSummarySearchPOJO pointSummarySearchPOJO) throws Exception;
	PointSummaryPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

}
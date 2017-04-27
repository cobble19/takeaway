package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.PointSummaryPOJO;
import com.cobble.takeaway.pojo.PointSummarySearchPOJO;

public interface PointSummaryService {
	int insert(PointSummaryPOJO pointSummaryPOJO, Long userId) throws Exception;
	int update(PointSummaryPOJO pointSummaryPOJO) throws Exception;
	List<PointSummaryPOJO> finds(PointSummarySearchPOJO pointSummarySearchPOJO) throws Exception;
	int getCount(PointSummarySearchPOJO pointSummarySearchPOJO) throws Exception;
	PointSummaryPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

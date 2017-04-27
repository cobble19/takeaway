package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.PointEventPOJO;
import com.cobble.takeaway.pojo.PointEventSearchPOJO;

public interface PointEventService {
	int insert(PointEventPOJO pointEventPOJO, Long userId) throws Exception;
	int update(PointEventPOJO pointEventPOJO) throws Exception;
	List<PointEventPOJO> finds(PointEventSearchPOJO pointEventSearchPOJO) throws Exception;
	int getCount(PointEventSearchPOJO pointEventSearchPOJO) throws Exception;
	PointEventPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
}

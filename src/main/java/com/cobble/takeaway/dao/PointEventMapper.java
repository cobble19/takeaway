package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.PointEventPOJO;
import com.cobble.takeaway.pojo.PointEventSearchPOJO;


public interface PointEventMapper {
	int insert(PointEventPOJO pointEventPOJO) throws Exception;
	int update(PointEventPOJO pointEventPOJO) throws Exception;
	List<PointEventPOJO> finds(PointEventSearchPOJO pointEventSearchPOJO) throws Exception;
	int getCount(PointEventSearchPOJO pointEventSearchPOJO) throws Exception;
	PointEventPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

}
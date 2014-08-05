package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.LocationBusinessPOJO;
import com.cobble.takeaway.pojo.LocationBusinessSearchPOJO;


public interface LocationBusinessMapper {
	int insert(LocationBusinessPOJO locationBusinessPOJO) throws Exception;
	int update(LocationBusinessPOJO locationBusinessPOJO) throws Exception;
	List<LocationBusinessPOJO> finds(LocationBusinessSearchPOJO locationBusinessSearchPOJO) throws Exception;
	int getCount(LocationBusinessSearchPOJO locationBusinessSearchPOJO) throws Exception;
	LocationBusinessPOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
}
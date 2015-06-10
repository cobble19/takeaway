package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.LocationBusinessPOJO;
import com.cobble.takeaway.pojo.LocationBusinessSearchPOJO;

public interface LocationBusinessService {
	int insert(LocationBusinessPOJO locationBusinessPOJO) throws Exception;
	int update(LocationBusinessPOJO locationBusinessPOJO) throws Exception;
	List<LocationBusinessPOJO> finds(LocationBusinessSearchPOJO locationBusinessSearchPOJO) throws Exception;
	int getCount(LocationBusinessSearchPOJO locationBusinessSearchPOJO) throws Exception;
	LocationBusinessPOJO findById(Integer id) throws Exception;
	int delete(Integer id) throws Exception;
	int delete(Integer[] ids) throws Exception;
	
}

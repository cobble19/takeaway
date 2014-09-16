package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.LocationAreaPOJO;
import com.cobble.takeaway.pojo.LocationAreaSearchPOJO;

public interface LocationAreaService {
	int insert(LocationAreaPOJO locationAreaPOJO) throws Exception;
	int update(LocationAreaPOJO locationAreaPOJO) throws Exception;
	List<LocationAreaPOJO> finds(LocationAreaSearchPOJO locationAreaSearchPOJO) throws Exception;
	int getCount(LocationAreaSearchPOJO locationAreaSearchPOJO) throws Exception;
	LocationAreaPOJO findById(Integer id) throws Exception;
	int delete(Integer id) throws Exception;
	int delete(Integer[] ids) throws Exception;

}

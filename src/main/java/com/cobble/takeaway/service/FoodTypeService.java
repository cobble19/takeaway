package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.FoodTypePOJO;
import com.cobble.takeaway.pojo.FoodTypeSearchPOJO;

public interface FoodTypeService {
	int insert(FoodTypePOJO foodTypePOJO) throws Exception;
	int update(FoodTypePOJO foodTypePOJO) throws Exception;
	List<FoodTypePOJO> finds(FoodTypeSearchPOJO foodTypeSearchPOJO) throws Exception;
	int getCount(FoodTypeSearchPOJO foodTypeSearchPOJO) throws Exception;
	FoodTypePOJO findById(Integer id) throws Exception;
	int delete(Integer id) throws Exception;
	int delete(Integer[] ids) throws Exception;

}

package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.FoodPOJO;
import com.cobble.takeaway.pojo.FoodSearchPOJO;

public interface FoodService {
	int insert(FoodPOJO foodPOJO) throws Exception;
	int update(FoodPOJO foodPOJO) throws Exception;
	List<FoodPOJO> finds(FoodSearchPOJO foodSearchPOJO) throws Exception;
	int getCount(FoodSearchPOJO foodSearchPOJO) throws Exception;
	FoodPOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;

}

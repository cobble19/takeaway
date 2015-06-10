package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.FoodTypePOJO;
import com.cobble.takeaway.pojo.FoodTypeSearchPOJO;


public interface FoodTypeMapper {
	int insert(FoodTypePOJO foodTypePOJO) throws Exception;
	int update(FoodTypePOJO foodTypePOJO) throws Exception;
	
	List<FoodTypePOJO> finds(FoodTypeSearchPOJO foodTypeSearchPOJO) throws Exception;
	int getCount(FoodTypeSearchPOJO foodTypeSearchPOJO) throws Exception;
	FoodTypePOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
}
package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.FoodMenuPOJO;
import com.cobble.takeaway.pojo.FoodMenuSearchPOJO;

public interface FoodMenuService {
	int insert(FoodMenuPOJO foodMenuPOJO) throws Exception;
	int update(FoodMenuPOJO foodMenuPOJO) throws Exception;
	List<FoodMenuPOJO> finds(FoodMenuSearchPOJO foodMenuSearchPOJO) throws Exception;
	int getCount(FoodMenuSearchPOJO foodMenuSearchPOJO) throws Exception;
	FoodMenuPOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;

}

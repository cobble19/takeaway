package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.FoodSellerTypePOJO;
import com.cobble.takeaway.pojo.FoodSellerTypeSearchPOJO;


public interface FoodSellerTypeMapper {
	int insert(FoodSellerTypePOJO foodSellerTypePOJO) throws Exception;
	int update(FoodSellerTypePOJO foodSellerTypePOJO) throws Exception;
	List<FoodSellerTypePOJO> finds(FoodSellerTypeSearchPOJO foodSellerTypeSearchPOJO) throws Exception;
	int getCount(FoodSellerTypeSearchPOJO foodSellerTypeSearchPOJO) throws Exception;
	FoodSellerTypePOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
}
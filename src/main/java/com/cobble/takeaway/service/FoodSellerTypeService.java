package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.FoodSellerTypePOJO;
import com.cobble.takeaway.pojo.FoodSellerTypeSearchPOJO;

public interface FoodSellerTypeService {
	int insert(FoodSellerTypePOJO foodSellerTypePOJO) throws Exception;
	int update(FoodSellerTypePOJO foodSellerTypePOJO) throws Exception;
	List<FoodSellerTypePOJO> finds(FoodSellerTypeSearchPOJO foodSellerTypeSearchPOJO) throws Exception;
	int getCount(FoodSellerTypeSearchPOJO foodSellerTypeSearchPOJO) throws Exception;
	FoodSellerTypePOJO findById(Integer id) throws Exception;
	int delete(Integer id) throws Exception;
	int delete(Integer[] ids) throws Exception;

}

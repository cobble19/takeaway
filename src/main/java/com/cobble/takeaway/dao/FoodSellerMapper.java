package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.FoodSellerPOJO;
import com.cobble.takeaway.pojo.FoodSellerSearchPOJO;
import com.cobble.takeaway.pojo.RelAreaSellerPOJO;
import com.cobble.takeaway.pojo.RelBusinessSellerPOJO;
import com.cobble.takeaway.pojo.RelFoodSellerTypePOJO;


public interface FoodSellerMapper {
	int insert(FoodSellerPOJO foodSellerPOJO) throws Exception;
	int update(FoodSellerPOJO foodSellerPOJO) throws Exception;
	
	FoodSellerPOJO findDetail(Integer foodSellerId) throws Exception;
	
	List<FoodSellerPOJO> findsByAreaId(Integer locationAreaId) throws Exception;
	int getCountByAreaId(Integer locationAreaId) throws Exception;
	List<FoodSellerPOJO> findsByBusinessId(Integer locationBusinessId) throws Exception;
	int getCountByBusinessId(Integer locationBusinessId) throws Exception;
	List<FoodSellerPOJO> findsByFoodSellerTypeId(Integer foodSellerTypeId) throws Exception;
	int getCountByFoodSellerTypeId(Integer foodSellerTypeId) throws Exception;
	
	List<FoodSellerPOJO> finds(FoodSellerSearchPOJO foodSellerSearchPOJO) throws Exception;
	int getCount(FoodSellerSearchPOJO foodSellerSearchPOJO) throws Exception;
	FoodSellerPOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
	int insertRelBusinessSeller(RelBusinessSellerPOJO relBusinessSellerPOJO) throws Exception;
	int insertRelAreaSeller(RelAreaSellerPOJO relAreaSellerPOJO) throws Exception;
	int insertRelFoodSellerType(RelFoodSellerTypePOJO relFoodSellerTypePOJO) throws Exception;
	int updateRelBusinessSeller(RelBusinessSellerPOJO relBusinessSellerPOJO) throws Exception;
	int updateRelAreaSeller(RelAreaSellerPOJO relAreaSellerPOJO) throws Exception;
	int updateRelFoodSellerType(RelFoodSellerTypePOJO relFoodSellerTypePOJO) throws Exception;
}
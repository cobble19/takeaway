package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.FoodSellerPOJO;
import com.cobble.takeaway.pojo.FoodSellerSearchPOJO;
import com.cobble.takeaway.pojo.RelBusinessSellerPOJO;


public interface FoodSellerMapper {
	int insert(FoodSellerPOJO foodSellerPOJO) throws Exception;
	int update(FoodSellerPOJO foodSellerPOJO) throws Exception;
	List<FoodSellerPOJO> finds(FoodSellerSearchPOJO foodSellerSearchPOJO) throws Exception;
	int getCount(FoodSellerSearchPOJO foodSellerSearchPOJO) throws Exception;
	FoodSellerPOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;
	int insertRelBusinessSeller(RelBusinessSellerPOJO relBusinessSellerPOJO) throws Exception;
}
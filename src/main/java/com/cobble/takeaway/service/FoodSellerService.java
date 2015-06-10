package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.FoodSellerPOJO;
import com.cobble.takeaway.pojo.FoodSellerSearchPOJO;

public interface FoodSellerService {
	int insert(FoodSellerPOJO foodSellerPOJO) throws Exception;
	int update(FoodSellerPOJO foodSellerPOJO) throws Exception;
	List<FoodSellerPOJO> findsByParam(Integer areaId, Integer businessId, Integer sellerTypeId) throws Exception;
	List<FoodSellerPOJO> finds(FoodSellerSearchPOJO foodSellerSearchPOJO) throws Exception;
	int getCount(FoodSellerSearchPOJO foodSellerSearchPOJO) throws Exception;
	FoodSellerPOJO findById(Integer id) throws Exception;
	int delete(Integer id) throws Exception;
	int delete(Integer[] ids) throws Exception;
	FoodSellerPOJO findDetail(Integer foodSellerId) throws Exception;

}

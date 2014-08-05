package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.OrderFormFoodPOJO;
import com.cobble.takeaway.pojo.OrderFormFoodSearchPOJO;

public interface OrderFormFoodService {
	int insert(OrderFormFoodPOJO orderFormFoodPOJO) throws Exception;
	int update(OrderFormFoodPOJO orderFormFoodPOJO) throws Exception;
	List<OrderFormFoodPOJO> finds(OrderFormFoodSearchPOJO orderFormFoodSearchPOJO) throws Exception;
	int getCount(OrderFormFoodSearchPOJO orderFormFoodSearchPOJO) throws Exception;
	OrderFormFoodPOJO findById(Integer id) throws Exception;
	int deleteById(Integer id) throws Exception;

}

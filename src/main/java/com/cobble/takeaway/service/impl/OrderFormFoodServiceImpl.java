package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.OrderFormFoodMapper;
import com.cobble.takeaway.pojo.OrderFormFoodPOJO;
import com.cobble.takeaway.pojo.OrderFormFoodSearchPOJO;
import com.cobble.takeaway.service.OrderFormFoodService;

@Service
public class OrderFormFoodServiceImpl implements OrderFormFoodService {
	
	@Autowired
	private OrderFormFoodMapper orderFormFoodMapper;

	@Override
	public int insert(OrderFormFoodPOJO orderFormFoodPOJO) throws Exception {
		int ret = 0;
		ret = orderFormFoodMapper.insert(orderFormFoodPOJO);
		return ret;
	}

	@Override
	public int update(OrderFormFoodPOJO orderFormFoodPOJO) throws Exception {
		int ret = 0;
		ret = orderFormFoodMapper.update(orderFormFoodPOJO);
		return ret;
	}

	@Override
	public List<OrderFormFoodPOJO> finds(
			OrderFormFoodSearchPOJO orderFormFoodSearchPOJO) throws Exception {
		List<OrderFormFoodPOJO> ret = null;
		ret = orderFormFoodMapper.finds(orderFormFoodSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(OrderFormFoodSearchPOJO orderFormFoodSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = orderFormFoodMapper.getCount(orderFormFoodSearchPOJO);
		return ret;
	}

	@Override
	public OrderFormFoodPOJO findById(Integer id) throws Exception {
		OrderFormFoodPOJO ret = null;
		ret = orderFormFoodMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Integer id) throws Exception {
		int ret = 0;
		ret = orderFormFoodMapper.deleteById(id);
		return ret;
	}

}

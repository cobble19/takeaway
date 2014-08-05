package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.FoodSellerMapper;
import com.cobble.takeaway.pojo.FoodSellerPOJO;
import com.cobble.takeaway.pojo.FoodSellerSearchPOJO;
import com.cobble.takeaway.service.FoodSellerService;

@Service
public class FoodSellerServiceImpl implements FoodSellerService {
	
	@Autowired
	private FoodSellerMapper foodSellerMapper;

	@Override
	public int insert(FoodSellerPOJO foodSellerPOJO) throws Exception {
		int ret = 0;
		ret = foodSellerMapper.insert(foodSellerPOJO);
		return ret;
	}

	@Override
	public int update(FoodSellerPOJO foodSellerPOJO) throws Exception {
		int ret = 0;
		ret = foodSellerMapper.update(foodSellerPOJO);
		return ret;
	}

	@Override
	public List<FoodSellerPOJO> finds(
			FoodSellerSearchPOJO foodSellerSearchPOJO) throws Exception {
		List<FoodSellerPOJO> ret = null;
		ret = foodSellerMapper.finds(foodSellerSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(FoodSellerSearchPOJO foodSellerSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = foodSellerMapper.getCount(foodSellerSearchPOJO);
		return ret;
	}

	@Override
	public FoodSellerPOJO findById(Integer id) throws Exception {
		FoodSellerPOJO ret = null;
		ret = foodSellerMapper.findById(id);
		return ret;
	}

	@Override
	public int deleteById(Integer id) throws Exception {
		int ret = 0;
		ret = foodSellerMapper.deleteById(id);
		return ret;
	}

}

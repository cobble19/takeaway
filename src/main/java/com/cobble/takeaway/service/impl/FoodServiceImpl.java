package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.FoodMapper;
import com.cobble.takeaway.pojo.FoodPOJO;
import com.cobble.takeaway.pojo.FoodSearchPOJO;
import com.cobble.takeaway.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodMapper foodMapper;

	@Override
	public int insert(FoodPOJO foodPOJO) throws Exception {
		int ret = 0;
		ret = foodMapper.insert(foodPOJO);
		return ret;
	}

	@Override
	public int update(FoodPOJO foodPOJO) throws Exception {
		int ret = 0;
		ret = foodMapper.update(foodPOJO);
		return ret;
	}

	@Override
	public List<FoodPOJO> finds(
			FoodSearchPOJO foodSearchPOJO) throws Exception {
		List<FoodPOJO> ret = null;
		ret = foodMapper.finds(foodSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(FoodSearchPOJO foodSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = foodMapper.getCount(foodSearchPOJO);
		return ret;
	}

	@Override
	public FoodPOJO findById(Integer id) throws Exception {
		FoodPOJO ret = null;
		ret = foodMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = foodMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id: ids) {
			ret += foodMapper.deleteById(id);
		}
		return ret;
	}

}

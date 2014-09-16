package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.FoodMenuMapper;
import com.cobble.takeaway.pojo.FoodMenuPOJO;
import com.cobble.takeaway.pojo.FoodMenuSearchPOJO;
import com.cobble.takeaway.service.FoodMenuService;

@Service
public class FoodMenuServiceImpl implements FoodMenuService {
	
	@Autowired
	private FoodMenuMapper foodMenuMapper;

	@Override
	public int insert(FoodMenuPOJO foodMenuPOJO) throws Exception {
		int ret = 0;
		ret = foodMenuMapper.insert(foodMenuPOJO);
		return ret;
	}

	@Override
	public int update(FoodMenuPOJO foodMenuPOJO) throws Exception {
		int ret = 0;
		ret = foodMenuMapper.update(foodMenuPOJO);
		return ret;
	}

	@Override
	public List<FoodMenuPOJO> finds(
			FoodMenuSearchPOJO foodMenuSearchPOJO) throws Exception {
		List<FoodMenuPOJO> ret = null;
		ret = foodMenuMapper.finds(foodMenuSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(FoodMenuSearchPOJO foodMenuSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = foodMenuMapper.getCount(foodMenuSearchPOJO);
		return ret;
	}

	@Override
	public FoodMenuPOJO findById(Integer id) throws Exception {
		FoodMenuPOJO ret = null;
		ret = foodMenuMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = foodMenuMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id : ids) {
			ret += foodMenuMapper.deleteById(id);
		}
		return ret;
	}

}

package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.FoodSellerTypeMapper;
import com.cobble.takeaway.pojo.FoodSellerTypePOJO;
import com.cobble.takeaway.pojo.FoodSellerTypeSearchPOJO;
import com.cobble.takeaway.service.FoodSellerTypeService;

@Service
public class FoodSellerTypeServiceImpl implements FoodSellerTypeService {
	
	@Autowired
	private FoodSellerTypeMapper foodSellerTypeMapper;

	@Override
	public int insert(FoodSellerTypePOJO foodSellerTypePOJO) throws Exception {
		int ret = 0;
		ret = foodSellerTypeMapper.insert(foodSellerTypePOJO);
		return ret;
	}

	@Override
	public int update(FoodSellerTypePOJO foodSellerTypePOJO) throws Exception {
		int ret = 0;
		ret = foodSellerTypeMapper.update(foodSellerTypePOJO);
		return ret;
	}

	@Override
	public List<FoodSellerTypePOJO> finds(
			FoodSellerTypeSearchPOJO foodSellerTypeSearchPOJO) throws Exception {
		List<FoodSellerTypePOJO> ret = null;
		ret = foodSellerTypeMapper.finds(foodSellerTypeSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(FoodSellerTypeSearchPOJO foodSellerTypeSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = foodSellerTypeMapper.getCount(foodSellerTypeSearchPOJO);
		return ret;
	}

	@Override
	public FoodSellerTypePOJO findById(Integer id) throws Exception {
		FoodSellerTypePOJO ret = null;
		ret = foodSellerTypeMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = foodSellerTypeMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Integer id : ids) {
				ret += foodSellerTypeMapper.deleteById(id);
			}
		}
		return ret;
	}

}

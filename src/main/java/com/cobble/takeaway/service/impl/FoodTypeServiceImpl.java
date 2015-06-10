package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.FoodTypeMapper;
import com.cobble.takeaway.pojo.FoodTypePOJO;
import com.cobble.takeaway.pojo.FoodTypeSearchPOJO;
import com.cobble.takeaway.service.FoodTypeService;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {
	
	@Autowired
	private FoodTypeMapper foodTypeMapper;

	@Override
	public int insert(FoodTypePOJO foodTypePOJO) throws Exception {
		int ret = 0;
		ret = foodTypeMapper.insert(foodTypePOJO);
		return ret;
	}

	@Override
	public int update(FoodTypePOJO foodTypePOJO) throws Exception {
		int ret = 0;
		ret = foodTypeMapper.update(foodTypePOJO);
		return ret;
	}

	@Override
	public List<FoodTypePOJO> finds(
			FoodTypeSearchPOJO foodTypeSearchPOJO) throws Exception {
		List<FoodTypePOJO> ret = null;
		ret = foodTypeMapper.finds(foodTypeSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(FoodTypeSearchPOJO foodTypeSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = foodTypeMapper.getCount(foodTypeSearchPOJO);
		return ret;
	}

	@Override
	public FoodTypePOJO findById(Integer id) throws Exception {
		FoodTypePOJO ret = null;
		ret = foodTypeMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = foodTypeMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Integer id : ids) {
				ret += foodTypeMapper.deleteById(id);
			}
		}
		return ret;
	}

}

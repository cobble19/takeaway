package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.FoodSellerMapper;
import com.cobble.takeaway.pojo.FoodSellerPOJO;
import com.cobble.takeaway.pojo.FoodSellerSearchPOJO;
import com.cobble.takeaway.pojo.RelAreaSellerPOJO;
import com.cobble.takeaway.pojo.RelBusinessSellerPOJO;
import com.cobble.takeaway.service.FoodSellerService;

@Service
public class FoodSellerServiceImpl implements FoodSellerService {
	
	@Autowired
	private FoodSellerMapper foodSellerMapper;

	@Override
	public int insert(FoodSellerPOJO foodSellerPOJO) throws Exception {
		int ret = 0;
		ret = foodSellerMapper.insert(foodSellerPOJO);
		
		if (foodSellerPOJO != null && foodSellerPOJO.getRelBusinessSellerPOJO() != null) {
			RelBusinessSellerPOJO relBusinessSellerPOJO = new RelBusinessSellerPOJO();
			relBusinessSellerPOJO.setFoodSellerId(foodSellerPOJO.getFoodSellerId());
			relBusinessSellerPOJO.setLocationBusinessId(foodSellerPOJO.getRelBusinessSellerPOJO().getLocationBusinessId());
			foodSellerMapper.insertRelBusinessSeller(relBusinessSellerPOJO);
		}
		
		if (foodSellerPOJO != null && foodSellerPOJO.getRelAreaSellerPOJO() != null) {
			RelAreaSellerPOJO relAreaSellerPOJO = new RelAreaSellerPOJO();
			relAreaSellerPOJO.setFoodSellerId(foodSellerPOJO.getFoodSellerId());
			relAreaSellerPOJO.setLocationAreaId(foodSellerPOJO.getRelAreaSellerPOJO().getLocationAreaId());
			foodSellerMapper.insertRelAreaSeller(relAreaSellerPOJO);
		}
		
		return ret;
	}

	@Override
	public int update(FoodSellerPOJO foodSellerPOJO) throws Exception {
		int ret = 0;
		ret = foodSellerMapper.update(foodSellerPOJO);
		if (foodSellerPOJO != null && foodSellerPOJO.getRelBusinessSellerPOJO() != null) {
			RelBusinessSellerPOJO relBusinessSellerPOJO = foodSellerPOJO.getRelBusinessSellerPOJO();
			if (relBusinessSellerPOJO.getLocationBusinessId() != null) {
				if (relBusinessSellerPOJO.getRelBusinessSellerId() == null) {
					relBusinessSellerPOJO.setFoodSellerId(foodSellerPOJO.getFoodSellerId());
					foodSellerMapper.insertRelBusinessSeller(relBusinessSellerPOJO);
				} else {
					foodSellerMapper.updateRelBusinessSeller(relBusinessSellerPOJO);
				}
			}
		}
		
		if (foodSellerPOJO != null && foodSellerPOJO.getRelAreaSellerPOJO() != null) {
			RelAreaSellerPOJO relAreaSellerPOJO = foodSellerPOJO.getRelAreaSellerPOJO();
			if (relAreaSellerPOJO.getLocationAreaId() != null) {
				if (relAreaSellerPOJO.getRelAreaSellerId() == null) {
					relAreaSellerPOJO.setFoodSellerId(foodSellerPOJO.getFoodSellerId());
					foodSellerMapper.insertRelAreaSeller(relAreaSellerPOJO);
				} else {
					foodSellerMapper.updateRelAreaSeller(relAreaSellerPOJO);
				}
			}
		}
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
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = foodSellerMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id: ids) {
			ret += foodSellerMapper.deleteById(id);
		}
		return ret;
	}

}

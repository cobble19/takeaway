package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.FoodSellerMapper;
import com.cobble.takeaway.dao.LocationAreaMapper;
import com.cobble.takeaway.dao.LocationBusinessMapper;
import com.cobble.takeaway.pojo.FoodSellerPOJO;
import com.cobble.takeaway.pojo.LocationAreaPOJO;
import com.cobble.takeaway.pojo.LocationAreaSearchPOJO;
import com.cobble.takeaway.pojo.LocationBusinessPOJO;
import com.cobble.takeaway.service.LocationAreaService;
import com.cobble.takeaway.service.TreeService;

@Service
public class TreeServiceImpl implements TreeService {
	
	@Autowired
	private LocationAreaMapper locationAreaMapper;
	@Autowired
	private LocationBusinessMapper locationBusinessMapper;
	@Autowired
	private FoodSellerMapper foodSellerMapper;

	@Override
	public List<LocationAreaPOJO> findAllAreas() throws Exception {
		List<LocationAreaPOJO> locationAreaPOJOs = locationAreaMapper.finds(null);
		if (CollectionUtils.isEmpty(locationAreaPOJOs)) {
			return locationAreaPOJOs;
		}
		for (LocationAreaPOJO locationAreaPOJO : locationAreaPOJOs) {
			Integer countFoodSellerForArea = 0;
			List<LocationBusinessPOJO> locationBusinessPOJOs = locationBusinessMapper.findsByAreaId(locationAreaPOJO.getLocationAreaId());
			if (!CollectionUtils.isEmpty(locationBusinessPOJOs)) {
				for (LocationBusinessPOJO locationBusinessPOJO : locationBusinessPOJOs) {
					List<FoodSellerPOJO> foodSellerPOJOs = foodSellerMapper.findsByBusinessId(locationBusinessPOJO.getLocationBusinessId());
					if (!CollectionUtils.isEmpty(foodSellerPOJOs)) {
						locationBusinessPOJO.getFoodSellerPOJOs().addAll(foodSellerPOJOs);
						countFoodSellerForArea += foodSellerPOJOs.size();
						locationBusinessPOJO.setCountFoodSeller(foodSellerPOJOs.size());
					}
				}
				locationAreaPOJO.setLocationBusinessPOJOs(locationBusinessPOJOs);
				locationAreaPOJO.setCountFoodSeller(countFoodSellerForArea);
			}
		}
		
		
		
		return locationAreaPOJOs;
	}


}

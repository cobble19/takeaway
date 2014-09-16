package com.cobble.takeaway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.LocationBusinessMapper;
import com.cobble.takeaway.pojo.LocationBusinessPOJO;
import com.cobble.takeaway.pojo.LocationBusinessSearchPOJO;
import com.cobble.takeaway.service.LocationBusinessService;

@Service
public class LocationBusinessServiceImpl implements LocationBusinessService {
	
	@Autowired
	private LocationBusinessMapper locationBusinessMapper;

	@Override
	public int insert(LocationBusinessPOJO locationBusinessPOJO) throws Exception {
		int ret = 0;
		ret = locationBusinessMapper.insert(locationBusinessPOJO);
		return ret;
	}

	@Override
	public int update(LocationBusinessPOJO locationBusinessPOJO) throws Exception {
		int ret = 0;
		ret = locationBusinessMapper.update(locationBusinessPOJO);
		return ret;
	}

	@Override
	public List<LocationBusinessPOJO> finds(
			LocationBusinessSearchPOJO locationBusinessSearchPOJO) throws Exception {
		List<LocationBusinessPOJO> ret = null;
		ret = locationBusinessMapper.finds(locationBusinessSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(LocationBusinessSearchPOJO locationBusinessSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = locationBusinessMapper.getCount(locationBusinessSearchPOJO);
		return ret;
	}

	@Override
	public LocationBusinessPOJO findById(Integer id) throws Exception {
		LocationBusinessPOJO ret = null;
		ret = locationBusinessMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = locationBusinessMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id : ids) {
			ret += locationBusinessMapper.deleteById(id);
		}
		return ret;
	}

}

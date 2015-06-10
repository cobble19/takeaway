package com.cobble.takeaway.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.dao.LocationAreaMapper;
import com.cobble.takeaway.pojo.LocationAreaPOJO;
import com.cobble.takeaway.pojo.LocationAreaSearchPOJO;
import com.cobble.takeaway.service.LocationAreaService;

@Service
public class LocationAreaServiceImpl implements LocationAreaService {
	
	@Autowired
	private LocationAreaMapper locationAreaMapper;

	@Override
	public int insert(LocationAreaPOJO locationAreaPOJO) throws Exception {
		int ret = 0;
		ret = locationAreaMapper.insert(locationAreaPOJO);
		return ret;
	}

	@Override
	public int update(LocationAreaPOJO locationAreaPOJO) throws Exception {
		int ret = 0;
		ret = locationAreaMapper.update(locationAreaPOJO);
		return ret;
	}

	@Override
	public List<LocationAreaPOJO> finds(
			LocationAreaSearchPOJO locationAreaSearchPOJO) throws Exception {
		List<LocationAreaPOJO> ret = null;
		ret = locationAreaMapper.finds(locationAreaSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(LocationAreaSearchPOJO locationAreaSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = locationAreaMapper.getCount(locationAreaSearchPOJO);
		return ret;
	}

	@Override
	public LocationAreaPOJO findById(Integer id) throws Exception {
		LocationAreaPOJO ret = null;
		ret = locationAreaMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = locationAreaMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Integer id : ids) {
				ret += locationAreaMapper.deleteById(id);
			}
		}
		return ret;
	}

}

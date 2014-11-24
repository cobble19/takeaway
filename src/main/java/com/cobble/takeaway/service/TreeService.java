package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.LocationAreaPOJO;
import com.cobble.takeaway.pojo.LocationAreaSearchPOJO;

public interface TreeService {
	List<LocationAreaPOJO> findAllAreas() throws Exception;

}

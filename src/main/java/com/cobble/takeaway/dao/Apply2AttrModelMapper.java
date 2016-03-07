package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.Apply2AttrModelPOJO;
import com.cobble.takeaway.pojo.Apply2AttrModelSearchPOJO;


public interface Apply2AttrModelMapper {
	int insert(Apply2AttrModelPOJO apply2AttrModelPOJO) throws Exception;
	int update(Apply2AttrModelPOJO apply2AttrModelPOJO) throws Exception;
	List<Apply2AttrModelPOJO> finds(Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO) throws Exception;
	int getCount(Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO) throws Exception;
	Apply2AttrModelPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;
	
	int deleteByActivityId(Long activityId) throws Exception;
}
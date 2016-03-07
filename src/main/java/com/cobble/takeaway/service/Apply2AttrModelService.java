package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.Apply2AttrModelPOJO;
import com.cobble.takeaway.pojo.Apply2AttrModelSearchPOJO;

public interface Apply2AttrModelService {
	int insert(Apply2AttrModelPOJO apply2AttrModelPOJO) throws Exception;
	int update(Apply2AttrModelPOJO apply2AttrModelPOJO) throws Exception;
	List<Apply2AttrModelPOJO> finds(Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO) throws Exception;
	int getCount(Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO) throws Exception;
	Apply2AttrModelPOJO findById(Long id) throws Exception;
	
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

	int deleteByActivityId(Long activityId) throws Exception;
	
	List<Apply2AttrModelPOJO> findsByActivityId(Long activityId) throws Exception;
	
}

package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.ApplyPOJO;
import com.cobble.takeaway.pojo.ApplySearchPOJO;

public interface ApplyService {
	int insert(ApplyPOJO applyPOJO) throws Exception;
	int update(ApplyPOJO applyPOJO) throws Exception;
	List<ApplyPOJO> finds(ApplySearchPOJO applySearchPOJO) throws Exception;
	List<ApplyPOJO> findsApplyInActivity(Long activityId) throws Exception;
	
	int getCount(ApplySearchPOJO applySearchPOJO) throws Exception;
	ApplyPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}

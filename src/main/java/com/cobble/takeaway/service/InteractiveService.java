package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.pojo.InteractiveSearchPOJO;

public interface InteractiveService {
	int insert(InteractivePOJO interactivePOJO, Long userId) throws Exception;
	int update(InteractivePOJO interactivePOJO) throws Exception;
	List<InteractivePOJO> finds(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	int getCount(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	List<InteractivePOJO> findActives(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	int getActiveCount(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	InteractivePOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;

}

package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.pojo.InteractiveSearchPOJO;
import com.cobble.takeaway.pojo.RelInteractiveUserPOJO;


public interface InteractiveMapper {
	int insert(InteractivePOJO interactivePOJO) throws Exception;
	int insertRelInteractiveUser(RelInteractiveUserPOJO relInteractiveUserPOJO) throws Exception;
	int update(InteractivePOJO interactivePOJO) throws Exception;
	List<InteractivePOJO> finds(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	List<InteractivePOJO> find4Enterprises(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	int getCount(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	List<InteractivePOJO> findActives(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	int getActiveCount(InteractiveSearchPOJO interactiveSearchPOJO) throws Exception;
	InteractivePOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

	int updateStatus(InteractivePOJO interactivePOJO) throws Exception;
}
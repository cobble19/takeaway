package com.cobble.takeaway.dao;

import java.util.List;

import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.InteractiveApplyPOJO;
import com.cobble.takeaway.pojo.InteractiveApplySearchPOJO;


public interface InteractiveApplyMapper {
	int insert(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception;
	int update(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception;
	List<InteractiveApplyPOJO> finds(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;
	List<InteractiveApplyPOJO> findsApplyInInteractive(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;
	int getCountInteractiveApplyInInteractive(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;
	int getCountInteractiveApplyByUserId(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception;
	
	int getCount(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;
	InteractiveApplyPOJO findById(Long id) throws Exception;
	int deleteById(Long id) throws Exception;

	List<InteractiveApplyPOJO> findsApplyByVerifyCode(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;

	int updateIsWinner(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception;
}
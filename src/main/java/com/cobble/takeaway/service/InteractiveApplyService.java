package com.cobble.takeaway.service;

import java.util.List;

import com.cobble.takeaway.pojo.InteractiveApplyPOJO;
import com.cobble.takeaway.pojo.InteractiveApplySearchPOJO;

public interface InteractiveApplyService {
	int insert(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception;
	int update(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception;
	List<InteractiveApplyPOJO> finds(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;
	List<InteractiveApplyPOJO> findsApplyInInteractive(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;
	int getCountInteractiveApplyInInteractive(Long interactiveId) throws Exception;
	Boolean existInteractiveApplyByUserId(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception;
	
	int getCount(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;
	InteractiveApplyPOJO findById(Long id) throws Exception;
	int delete(Long id) throws Exception;
	int delete(Long[] ids) throws Exception;
	
	List<InteractiveApplyPOJO> findsApplyByVerifyCode(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception;

	int updateIsWinner(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception;
	
	List<InteractiveApplyPOJO> getInteractiveApplyWinner(Long interactiveId) throws Exception;
}

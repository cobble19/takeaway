package com.cobble.takeaway.service;

import com.cobble.takeaway.pojo.LotteryPOJO;

public interface LotteryService {
	LotteryPOJO execute(Long interactiveId, Long userId) throws Exception;
	
}

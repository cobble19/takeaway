package com.cobble.takeaway.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Service;

import com.cobble.takeaway.pojo.AwardPOJO;
import com.cobble.takeaway.pojo.AwardRecordPOJO;
import com.cobble.takeaway.pojo.AwardRecordSearchPOJO;
import com.cobble.takeaway.pojo.AwardSearchPOJO;
import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.pojo.LotteryPOJO;
import com.cobble.takeaway.service.AwardRecordService;
import com.cobble.takeaway.service.AwardService;
import com.cobble.takeaway.service.InteractiveService;
import com.cobble.takeaway.service.LotteryService;
import com.cobble.takeaway.util.UserUtil;

@Service
public class LotteryServiceImpl implements LotteryService {

	private final static Logger logger = LoggerFactory.getLogger(LotteryServiceImpl.class);
	
	@Autowired
	private InteractiveService interactiveService;
	@Autowired
	private AwardService awardService;
	@Autowired
	private AwardRecordService awardRecordService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private final static String UN_AWARD = "未中奖";

	@Override
	public LotteryPOJO execute(Long interactiveId, Long userId)
			throws Exception {
		LotteryPOJO ret = new LotteryPOJO();
		try {
			synchronized (this) {

				InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);

				Date currDate = new Date();
				if (currDate.after(interactivePOJO.getStartDateTime()) && currDate.before(interactivePOJO.getEndDateTime())) {
//					valid = true;
				} else {
					AwardPOJO awardPOJO = new AwardPOJO();
					awardPOJO.setOrderNo(-1);
					ret.setSuccess(true);
					ret.setAwardPOJO(awardPOJO);
					ret.setIsHappy(false);
					ret.setResult("活动未开始");
				}
				
				AwardSearchPOJO awardSearchPOJO = new AwardSearchPOJO();
				awardSearchPOJO.setBalanceGt0Flag(true);
				awardSearchPOJO.setInteractiveId(interactiveId);
				List<AwardPOJO> awardPOJOs = awardService.finds(awardSearchPOJO);
				
				if (userId == null) {
					userId = UserUtil.getCurrentUserId();
				}

				if (userId == null) {
					AwardPOJO awardPOJO = new AwardPOJO();
					awardPOJO.setOrderNo(-1);
					ret.setSuccess(true);
					ret.setAwardPOJO(awardPOJO);
					ret.setIsHappy(false);
					ret.setResult("请登录，才能抽奖");
					return ret;
				}
				
				if (CollectionUtils.isEmpty(awardPOJOs) || userId == null) {
					AwardPOJO awardPOJO = new AwardPOJO();
					awardPOJO.setOrderNo(-1);
					ret.setSuccess(true);
					ret.setAwardPOJO(awardPOJO);
					ret.setIsHappy(false);
					ret.setResult(UN_AWARD);
					return ret;
				}
				
				AwardRecordSearchPOJO awardRecordSearchPOJO = new AwardRecordSearchPOJO();
				awardRecordSearchPOJO.setInteractiveId(interactiveId);
				awardRecordSearchPOJO.setUserId(userId);
//				List<AwardRecordPOJO> awardRecordPOJOs = awardRecordService.finds(awardRecordSearchPOJO);
				int count = awardRecordService.getCount(awardRecordSearchPOJO);
				
				Integer awardNumberPer = interactivePOJO.getAwardNumberPer();
				if (awardNumberPer == null) {
					awardNumberPer = 1;
				}
				if (awardNumberPer > -1) {
					if (count - awardNumberPer >= 0) {
						AwardPOJO awardPOJO = new AwardPOJO();
						awardPOJO.setOrderNo(-1);
						ret.setSuccess(true);
						ret.setAwardPOJO(awardPOJO);
						ret.setIsHappy(false);
						ret.setResult("您的抽奖次数已用完");
						return ret;
					}
				}
				
				if (count > 0) {
					logger.info("userId: {}, count: {}", userId, count);
					/*ret.put("success", true);
					ret.put("isHappy", false);
					ret.put("result", "已获得活动奖品");
					return ret;*/
				}
				
				final Map<Long, Integer> awardAmountMap = new ConcurrentHashMap<Long, Integer>(); // 奖品 <--> 奖品总量
				final Map<Long, Integer> awardBalanceMap = new ConcurrentHashMap<Long, Integer>(); // 奖品 <--> 奖品库存
				final Map<Long, Integer> awardWeightMap = new ConcurrentHashMap<Long, Integer>(); // 奖品 <--> 奖品权重
				
				int totalWeight = 0;
				
				for (int i = 0; i < awardPOJOs.size(); i++) {
					AwardPOJO awardPOJO = awardPOJOs.get(i);
					awardAmountMap.put(awardPOJO.getAwardId(), awardPOJO.getAmount());
					awardBalanceMap.put(awardPOJO.getAwardId(), awardPOJO.getBalance());
					awardWeightMap.put(awardPOJO.getAwardId(), awardPOJO.getWeight());
					totalWeight += awardPOJO.getWeight();
				}
				
				int random = new Random().nextInt(totalWeight);
				
				int prev = 0;
				for (int i = 0; i < awardPOJOs.size(); i++) {
					AwardPOJO awardPOJO = awardPOJOs.get(i);
					int weight = awardPOJO.getWeight();
					
					if (random >= prev && random < prev + weight) {
						// 恭喜, happy, you get an award
						ret.setSuccess(true);
						ret.setAwardPOJO(awardPOJO);
						if (UN_AWARD.equalsIgnoreCase(awardPOJO.getName()) || 0 >= awardPOJO.getBalance()) {
							ret.setIsHappy(true);
							ret.setResult(UN_AWARD);
						} else {
							ret.setIsHappy(true);
							ret.setResult("恭喜获得奖品");
						}
						// decrease award.balance, and insert awardrecord
						if (!UN_AWARD.equalsIgnoreCase(awardPOJO.getName())) {
							awardService.decreaseBalance(awardPOJO);
						}
						
						AwardRecordPOJO awardRecordPOJO = new AwardRecordPOJO();
						awardRecordPOJO.setAwardId(awardPOJO.getAwardId());
						awardRecordPOJO.setHitDateTime(new Date());
						awardRecordPOJO.setInteractiveId(interactiveId);
						awardRecordPOJO.setUserId(userId);
						awardRecordService.insert(awardRecordPOJO);
						break;
					}
					prev += weight;
				}
			}
		} catch (Exception e) {
			ret.setSuccess(false);
//			ret.setAwardPOJO(awardPOJO);
			ret.setIsHappy(false);
			ret.setResult(UN_AWARD);
			logger.error("抽奖程序出错: {}", e);
			//throw e;
		}
		return ret;
	}
	


}

package com.cobble.takeaway.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.AwardPOJO;
import com.cobble.takeaway.pojo.AwardRecordPOJO;
import com.cobble.takeaway.pojo.AwardRecordSearchPOJO;
import com.cobble.takeaway.pojo.AwardSearchPOJO;
import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.service.AwardRecordService;
import com.cobble.takeaway.service.AwardService;
import com.cobble.takeaway.service.InteractiveService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class LotteryController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(LotteryController.class);
	
	@Autowired
	private InteractiveService interactiveService;
	@Autowired
	private AwardService awardService;
	@Autowired
	private AwardRecordService awardRecordService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping(value = "/api/unified/lottery/{interactiveId}/happy", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map add4WebAPI(@PathVariable(value="interactiveId") Long interactiveId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		try {
			InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);
			AwardSearchPOJO awardSearchPOJO = new AwardSearchPOJO();
			awardSearchPOJO.setBalanceGt0Flag(true);
			awardSearchPOJO.setInteractiveId(interactiveId);
			List<AwardPOJO> awardPOJOs = awardService.finds(awardSearchPOJO);

			Long userId = UserUtil.getCurrentUserId();
			
			if (CollectionUtils.isEmpty(awardPOJOs) || userId == null) {
				ret.put("success", true);
				ret.put("isHappy", false);
				ret.put("result", "没有奖品");
				return ret;
			}
			
			AwardRecordSearchPOJO awardRecordSearchPOJO = new AwardRecordSearchPOJO();
			awardRecordSearchPOJO.setInteractiveId(interactiveId);
			awardRecordSearchPOJO.setUserId(userId);
//			List<AwardRecordPOJO> awardRecordPOJOs = awardRecordService.finds(awardRecordSearchPOJO);
			int count = awardRecordService.getCount(awardRecordSearchPOJO);
			
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
					ret.put("success", true);
					ret.put("awardPOJO", awardPOJO);
					if ("未中奖".equalsIgnoreCase(awardPOJO.getName()) || 0 >= awardPOJO.getBalance()) {
						ret.put("isHappy", false);
						ret.put("result", "无奖品");
					} else {
						ret.put("isHappy", true);
						ret.put("result", "恭喜获得奖品");
					}
					// decrease award.balance, and insert awardrecord
					awardService.decreaseBalance(awardPOJO);
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
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("isHappy", false);
			ret.put("result", "无奖品1");
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	

}

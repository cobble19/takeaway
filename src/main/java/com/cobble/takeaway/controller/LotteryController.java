package com.cobble.takeaway.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.LotteryPOJO;
import com.cobble.takeaway.service.LotteryService;

@Controller
public class LotteryController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(LotteryController.class);
	
/*	@Autowired
	private InteractiveService interactiveService;
	@Autowired
	private AwardService awardService;
	@Autowired
	private AwardRecordService awardRecordService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();*/
	
	@Autowired
	private LotteryService lotteryService;
	
	@RequestMapping(value = "/api/unified/lottery/{interactiveId}/happy", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public LotteryPOJO add4WebAPI(@PathVariable(value="interactiveId") Long interactiveId, @RequestParam(value="userId", required=false) Long userId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LotteryPOJO ret = new LotteryPOJO();
		ret = lotteryService.execute(interactiveId, userId);
		
		return ret;
	}
	

}

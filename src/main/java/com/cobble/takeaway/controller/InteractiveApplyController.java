package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.InteractiveApplyPOJO;
import com.cobble.takeaway.pojo.InteractiveApplySearchPOJO;
import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.pojo.InteractiveSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.InteractiveApplyService;
import com.cobble.takeaway.service.InteractiveService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class InteractiveApplyController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(InteractiveApplyController.class);
	
	public final static Integer IS_WINNER = 1;
	public final static Integer IS_NOT_WINNER = 0;
	public final static Integer IS_VERIFIED = 1;
	public final static Integer IS_NOT_VERIFIED = 0;
	
	@Autowired
	private InteractiveApplyService interactiveApplyService;
	@Autowired
	private InteractiveService interactiveService;

	@RequestMapping(value = "/web/person/interactive/prize/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<InteractiveApplyPOJO> prizeByUserId(@PathVariable("userId") Long userId) throws Exception {
		DataTablesPOJO<InteractiveApplyPOJO> ret = new DataTablesPOJO<InteractiveApplyPOJO>();
		try {
			InteractiveApplySearchPOJO interactiveApplySearchPOJO = new InteractiveApplySearchPOJO();
			interactiveApplySearchPOJO.setUserId(userId);
			interactiveApplySearchPOJO.setIsWinner(IS_WINNER);
			List<InteractiveApplyPOJO> interactiveApplyPOJOs = interactiveApplyService.findsApplyInInteractive(interactiveApplySearchPOJO);
			
			int count = CollectionUtils.isEmpty(interactiveApplyPOJOs) ? 0 : interactiveApplyPOJOs.size();
			
			ret.setData(interactiveApplyPOJOs);
			ret.setRecordsTotal(count);
			ret.setDraw(1);
			ret.setRecordsFiltered(count);
		} catch (Exception e) {
			logger.error("applyInActivity error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/person/interactive/{interactiveId}/apply/winner", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<InteractiveApplyPOJO> applyWinnerInInteractive(@PathVariable("interactiveId") Long interactiveId) throws Exception {
		DataTablesPOJO<InteractiveApplyPOJO> ret = new DataTablesPOJO<InteractiveApplyPOJO>();
		try {
			//InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);
			/*InteractiveApplySearchPOJO interactiveApplySearchPOJO = new InteractiveApplySearchPOJO();
			interactiveApplySearchPOJO.setInteractiveId(interactiveId);
			interactiveApplySearchPOJO.setStart(0);
			interactiveApplySearchPOJO.setLimit(interactivePOJO.getNumOfWinner());*/
			List<InteractiveApplyPOJO> interactiveApplyPOJOs = interactiveApplyService.getInteractiveApplyWinner(interactiveId);
			int count = CollectionUtils.isEmpty(interactiveApplyPOJOs) ? 0 : interactiveApplyPOJOs.size();
			ret.setData(interactiveApplyPOJOs);
			ret.setRecordsTotal(count);
			ret.setDraw(1);
			ret.setRecordsFiltered(count);
		} catch (Exception e) {
			logger.error("applyInActivity error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/person/interactive/{interactiveId}/apply", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<InteractiveApplyPOJO> applyInInteractive(@PathVariable("interactiveId") Long interactiveId) throws Exception {
		DataTablesPOJO<InteractiveApplyPOJO> ret = new DataTablesPOJO<InteractiveApplyPOJO>();
		try {
			InteractiveApplySearchPOJO interactiveApplySearchPOJO = new InteractiveApplySearchPOJO();
			interactiveApplySearchPOJO.setInteractiveId(interactiveId);
			interactiveApplySearchPOJO.setStart(0);
			interactiveApplySearchPOJO.setLimit(10);
			List<InteractiveApplyPOJO> interactiveApplyPOJOs = interactiveApplyService.findsApplyInInteractive(interactiveApplySearchPOJO);
			int count = CollectionUtils.isEmpty(interactiveApplyPOJOs) ? 0 : interactiveApplyPOJOs.size();
			ret.setData(interactiveApplyPOJOs);
			ret.setRecordsTotal(count);
			ret.setDraw(1);
			ret.setRecordsFiltered(count);
		} catch (Exception e) {
			logger.error("applyInActivity error.", e);
			throw e;
		}
		
		return ret;
	}

	/*@RequestMapping(value = "/web/person/interactiveApplyInActivity/{interactiveId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<InteractiveApplyPOJO> applyInActivity(@PathVariable("interactiveId") Long interactiveId) throws Exception {
		DataTablesPOJO<InteractiveApplyPOJO> ret = new DataTablesPOJO<InteractiveApplyPOJO>();
		try {
			List<InteractiveApplyPOJO> interactiveApplyPOJOs = interactiveApplyService.findsInteractiveApplyInActivity(interactiveId);
			int count = interactiveApplyService.getCountApplyInActivity(interactiveId);
			ret.setData(interactiveApplyPOJOs);
			ret.setRecordsTotal(count);
			ret.setDraw(1);
			ret.setRecordsFiltered(count);
		} catch (Exception e) {
			logger.error("applyInActivity error.", e);
			throw e;
		}
		
		return ret;
	}*/

/*	@RequestMapping(value = "/web/person/interactiveApply/exist", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO existApplyInActivity(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			Boolean result = interactiveApplyService.existApplyInActivity(interactiveApplySearchPOJO);
			if (result) {
				ret.setSuccess(true);
//				ret.setDesc("存在手机号：" + interactiveApplySearchPOJO.getPhone());
			} else {
				ret.setSuccess(false);
//				ret.setDesc("不存在手机号：" + interactiveApplySearchPOJO.getPhone());
			}
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}*/
	

	@RequestMapping(value = "/web/person/interactiveApply/verify", method = {RequestMethod.GET})
	@ResponseBody
	public StatusPOJO verify(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null || userId == 0) {
				ret.setSuccess(false);
				ret.setDesc("请登录！");
				return ret;
			}
			interactiveApplySearchPOJO.setIsWinner(IS_WINNER);
			List<InteractiveApplyPOJO> result = interactiveApplyService.findsApplyByVerifyCode(interactiveApplySearchPOJO);
			
			if (!CollectionUtils.isEmpty(result) && result.size() == 1) {
				InteractiveApplyPOJO interactiveApplyPOJO  = result.get(0);
				if (interactiveApplyPOJO.getIsWinner() == IS_WINNER) {
					if (interactiveApplyPOJO.getIsVerified() == IS_NOT_VERIFIED) {
						ret.setSuccess(true);
						ret.setDesc("验证成功");
						interactiveApplyPOJO.setIsVerified(IS_VERIFIED);
						interactiveApplyService.update(interactiveApplyPOJO);
					} else {
						ret.setSuccess(false);
						ret.setDesc("验证码已经被使用");
					}
					try {
						if (interactiveApplyPOJO.getInteractivePOJO().getPrizeEndDateTime().getTime()
								<= new Date().getTime()) {
							ret.setSuccess(false);
							ret.setDesc("验证码已经过期!");
						}
					} catch (Exception e) {
						logger.error("奖品截止日期 error, {}", e);
					}
				} else {
					ret.setSuccess(false);
					ret.setDesc("不是获奖人");
				}
			} else {
				ret.setSuccess(false);
				ret.setDesc("无效的验证码");
			}

		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/person/interactiveApply/add", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO apply(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null || userId == 0) {
				ret.setSuccess(false);
				ret.setDesc("请登录！");
				return ret;
			}
			interactiveApplyPOJO.setUserId(userId);
			
			Boolean exist = interactiveApplyService.existInteractiveApplyByUserId(interactiveApplyPOJO);
			if (exist) {
				ret.setSuccess(false);
				ret.setDesc("您已经提交过答案！");
				return ret;
			}
			interactiveApplyPOJO.setIsWinner(IS_NOT_WINNER);
			interactiveApplyPOJO.setIsVerified(IS_NOT_VERIFIED);
			String verifyCode = userId + "" + interactiveApplyPOJO.getInteractiveId() + new Date() + RandomStringUtils.random(8);
			verifyCode = DigestUtils.sha1Hex(verifyCode).substring(0, 8);
			interactiveApplyPOJO.setVerifyCode(verifyCode);
			int result = interactiveApplyService.insert(interactiveApplyPOJO);
			ret.setSuccess(true);
			
			// 处理获奖人和活动状态
			dealInteractiveStatus(interactiveApplyPOJO);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	private void dealInteractiveStatus(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception {
		logger.debug("dealInteractiveStatus start... , interactiveApplyPOJO: {}", interactiveApplyPOJO);
		DealStatusThread dealStatusThread = new DealStatusThread(interactiveApplyPOJO, interactiveService);
		dealStatusThread.start();
	}
	
	class DealStatusThread extends Thread {
		private InteractiveApplyPOJO interactiveApplyPOJO;
		private InteractiveService interactiveService;
		public DealStatusThread(InteractiveApplyPOJO interactiveApplyPOJO, InteractiveService interactiveService) {
			this.interactiveApplyPOJO = interactiveApplyPOJO;
			this.interactiveService = interactiveService;
		}

		@Override
		public void run() {
			try {
				logger.info("DealStatusThread start..., interactiveApplyPOJO: {}", interactiveApplyPOJO);
				Long interactiveId = interactiveApplyPOJO.getInteractiveId();

				logger.info("DealStatusThread ..., interactiveId: {}", interactiveId);
				InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);
				logger.info("DealStatusThread ..., interactivePOJO: {}, status: {}", interactivePOJO, interactivePOJO.getStatus());
				Integer status = interactivePOJO.getStatus();

				logger.info("DealStatusThread ..., status: {}", status);
				if (-1 == status) {
					//update status -1 -> 0
					interactivePOJO.setStatus(0);
					interactiveService.updateStatus(interactivePOJO);
					// get end datetime
					Date endDateTime = interactivePOJO.getEndDateTime();
					long interval = endDateTime.getTime() - (new Date().getTime()) + (3 * 1000);
					if (interval < 0) {
						interval = 1;
					}
					// 倒计时定时更新结束状态 , update status 0 -> 1

					logger.info("DealStatusThread sleep start..., interval: {}", interval);
					Thread.sleep(interval);
					logger.info("DealStatusThread sleep end..., interval: {}", interval);
					

					logger.info("interactiveService .updateStatus start...");
					interactivePOJO.setStatus(1);
					interactiveService.updateStatus(interactivePOJO);
					logger.info("interactiveService.updateStatus end...");
					// update 获奖人 IS_WINNER 0->1

//					InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);
					/*InteractiveApplySearchPOJO interactiveApplySearchPOJO = new InteractiveApplySearchPOJO();
					interactiveApplySearchPOJO.setInteractiveId(interactiveId);
					interactiveApplySearchPOJO.setStart(0);
					interactiveApplySearchPOJO.setLimit(interactivePOJO.getNumOfWinner());*/
					

					logger.info("interactiveApplyService.updateIsWinner start...");
					List<InteractiveApplyPOJO> interactiveApplyPOJOs = interactiveApplyService.getInteractiveApplyWinner(interactiveId);
					
					if (!CollectionUtils.isEmpty(interactiveApplyPOJOs)) {
						for (InteractiveApplyPOJO interactiveApplyPOJO : interactiveApplyPOJOs) {
							interactiveApplyPOJO.setIsWinner(1);	// 1-winner
							interactiveApplyService.updateIsWinner(interactiveApplyPOJO);
						}
					}

					logger.info("interactiveApplyService.updateIsWinner end...");
					
				}
				
				// 每天凌晨0：00 1. 更新活动status， 如果结束0->1, then 步骤2 else end...
				// 2.更新update 获奖人 IS_WINNER 0->1
			} catch (Exception e) {
				logger.error("DealStatusThread error {}", e);
			}
			
		}
		
	}
	
	public void ScheduleDealInteractiveStatus() throws Exception {
		// 1. 查询所有的非结束活动 -1 and 0
		InteractiveSearchPOJO interactiveSearchPOJO = new InteractiveSearchPOJO();
		interactiveSearchPOJO.setStatus(-1);

		List<InteractivePOJO> interactivePOJOs_1 = interactiveService.finds(interactiveSearchPOJO);
		
		interactiveSearchPOJO.setStatus(0);
		
		List<InteractivePOJO> interactivePOJOs0 = interactiveService.finds(interactiveSearchPOJO);
		
		List<InteractivePOJO> all = new ArrayList<InteractivePOJO>();
		
		if (!CollectionUtils.isEmpty(interactivePOJOs_1)) {
			all.addAll(interactivePOJOs_1);
		}

		if (!CollectionUtils.isEmpty(interactivePOJOs0)) {
			all.addAll(interactivePOJOs0);
		}
		
		Date date = new Date();
		for (InteractivePOJO interactivePOJO : all) {
			Date endDateTime = interactivePOJO.getEndDateTime();
			if (endDateTime == null) {
				continue;
			}
			
			if (endDateTime.before(date)) {	// 活动结束
				interactivePOJO.setStatus(1);	// 1-end
				interactiveService.updateStatus(interactivePOJO);
				
				List<InteractiveApplyPOJO> interactiveApplyPOJOs = interactiveApplyService.getInteractiveApplyWinner(interactivePOJO.getInteractiveId());
				
				if (!CollectionUtils.isEmpty(interactiveApplyPOJOs)) {
					for (InteractiveApplyPOJO interactiveApplyPOJO : interactiveApplyPOJOs) {
						interactiveApplyPOJO.setIsWinner(1);	// 1-winner
						interactiveApplyService.updateIsWinner(interactiveApplyPOJO);
					}
				}
			}
			
		}
		
	}
	
	@RequestMapping(value = "/web/interactiveApply/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(InteractiveApplySearchPOJO interactiveApplySearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<InteractiveApplyPOJO> interactiveApplyPOJOList = new ArrayList<InteractiveApplyPOJO>();
		interactiveApplyPOJOList = interactiveApplyService.finds(interactiveApplySearchPOJO);
//		int total = interactiveApplyService.getCount(interactiveApplySearchPOJO);
		ret.addObject("interactiveApplyPOJOList", interactiveApplyPOJOList);
		ret.setViewName("page/interactiveApply_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/interactiveApply", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<InteractiveApplyPOJO> findApply(InteractiveApplySearchPOJO interactiveApplySearchPOJO, Model model) throws Exception {
		ExtjsPOJO<InteractiveApplyPOJO> ret = new ExtjsPOJO<InteractiveApplyPOJO>();
		List<InteractiveApplyPOJO> interactiveApplyPOJOList = new ArrayList<InteractiveApplyPOJO>();
		interactiveApplyPOJOList = interactiveApplyService.finds(interactiveApplySearchPOJO);
		int total = interactiveApplyService.getCount(interactiveApplySearchPOJO);
		/*InteractiveApplyPOJO interactiveApplyPOJO = new InteractiveApplyPOJO();
		interactiveApplyPOJO.setApplyId(1);
		interactiveApplyPOJO.setName("hefei");
		interactiveApplyPOJO.setDescription("描述信息。。。");
		interactiveApplyPOJOList.add(interactiveApplyPOJO);*/
		
		ret.setGridModelList(interactiveApplyPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("interactiveApplyPOJOList", interactiveApplyPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/mgr/interactiveApply/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(InteractiveApplyPOJO interactiveApplyPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = interactiveApplyService.insert(interactiveApplyPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/interactiveApply/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(InteractiveApplyPOJO interactiveApplyPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = interactiveApplyService.update(interactiveApplyPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/interactiveApply/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = interactiveApplyService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

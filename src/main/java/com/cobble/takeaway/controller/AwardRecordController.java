package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.AwardRecordPOJO;
import com.cobble.takeaway.pojo.AwardRecordSearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.InteractivePOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.AwardRecordService;
import com.cobble.takeaway.service.InteractiveService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class AwardRecordController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(AwardRecordController.class);
	
	@Autowired
	private AwardRecordService awardRecordService;
	@Autowired
	private InteractiveService interactiveService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/api/unified/awardRecord/checkValid", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map checkValid(@RequestParam(value="interactiveId") Long interactiveId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		try {
			InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);
			Integer awardNumberPer = interactivePOJO.getAwardNumberPer();
			if (awardNumberPer == null) {
				awardNumberPer = 1;
			}
			
			Date currDate = new Date();
			Boolean valid = true;
			String description = "可以抽奖";
			if (currDate.after(interactivePOJO.getStartDateTime()) && currDate.before(interactivePOJO.getEndDateTime())) {
//				valid = true;
			} else {
				valid = false;
				description = "活动还没有开始";
			}
			
			Long userId = UserUtil.getCurrentUserId();

			AwardRecordSearchPOJO awardRecordSearchPOJO = new AwardRecordSearchPOJO();
			awardRecordSearchPOJO.setInteractiveId(interactiveId);
			awardRecordSearchPOJO.setUserId(userId);
			int count = awardRecordService.getCount(awardRecordSearchPOJO);
			
			if (count - awardNumberPer >= 0) {
				valid = false;
				description = "抽奖次数已达上限:" + awardNumberPer;
			}
			
			ret.put("success", true);
			ret.put("valid", valid);
			ret.put("description", description);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			ret.put("valid", false);
			ret.put("description", "不能抽奖了");
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/awardRecord/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(AwardRecordPOJO awardRecordPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (awardRecordPOJO == null) {
				throw new Exception("awardRecordPOJO can't is NULL.");
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/awardRecord/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(AwardRecordPOJO awardRecordPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (awardRecordPOJO == null) {
				throw new Exception("awardRecordPOJO can't is NULL.");
			}
			int result = -1;
			if (awardRecordPOJO.getAwardRecordId() != null && awardRecordPOJO.getAwardRecordId() > 0l) {
				result = awardRecordService.update(awardRecordPOJO);
			} else {
				result = awardRecordService.insert(awardRecordPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/awardRecord/{awardRecordId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public AwardRecordPOJO query(@PathVariable("awardRecordId") Long awardRecordId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AwardRecordPOJO ret = new AwardRecordPOJO();
		try {
			ret = awardRecordService.findById(awardRecordId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/awardRecord/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<AwardRecordPOJO> query(AwardRecordSearchPOJO awardRecordSearchPOJO) throws Exception {
		DataTablesPOJO<AwardRecordPOJO> ret = new DataTablesPOJO<AwardRecordPOJO>();
		try {
			List<AwardRecordPOJO> awardRecordPOJOs = awardRecordService.finds(awardRecordSearchPOJO);
			ret.setData(awardRecordPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/awardRecord/awardRecordByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<AwardRecordPOJO> queryByUserId(AwardRecordSearchPOJO awardRecordSearchPOJO) throws Exception {
		DataTablesPOJO<AwardRecordPOJO> ret = new DataTablesPOJO<AwardRecordPOJO>();
		try {
//			awardRecordSearchPOJO.setUserId(UserUtil.getCurrentUser().getUserId());
			awardRecordSearchPOJO.setPaginationFlage(false);
			List<AwardRecordPOJO> awardRecordPOJOs = awardRecordService.finds(awardRecordSearchPOJO);
			ret.setData(awardRecordPOJOs);
		} catch (Exception e) {
			logger.error("awardRecordByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/awardRecord", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<AwardRecordPOJO> findAwardRecord(AwardRecordSearchPOJO awardRecordSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<AwardRecordPOJO> ret = new ExtjsPOJO<AwardRecordPOJO>();
		List<AwardRecordPOJO> awardRecordPOJOList = new ArrayList<AwardRecordPOJO>();
		awardRecordPOJOList = awardRecordService.finds(awardRecordSearchPOJO);
		int total = awardRecordService.getCount(awardRecordSearchPOJO);
		
		ret.setGridModelList(awardRecordPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("awardRecordPOJOList", awardRecordPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/awardRecord/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(AwardRecordPOJO awardRecordPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = awardRecordService.insert(awardRecordPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/awardRecord/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(AwardRecordPOJO awardRecordPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = awardRecordService.update(awardRecordPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/awardRecord/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = awardRecordService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

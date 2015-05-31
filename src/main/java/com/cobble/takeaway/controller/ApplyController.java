package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.ApplyPOJO;
import com.cobble.takeaway.pojo.ApplySearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.ApplyService;

@Controller
public class ApplyController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);
	
	@Autowired
	private ApplyService applyService;
	

	@RequestMapping(value = "/web/person/applyInActivity/{activityId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ApplyPOJO> applyInActivity(@PathVariable("activityId") Long activityId) throws Exception {
		DataTablesPOJO<ApplyPOJO> ret = new DataTablesPOJO<ApplyPOJO>();
		try {
			List<ApplyPOJO> applyPOJOs = applyService.findsApplyInActivity(activityId);
			ret.setData(applyPOJOs);
		} catch (Exception e) {
			LOGGER.error("applyInActivity error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/person/apply/add", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO apply(ApplyPOJO applyPOJO) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = applyService.insert(applyPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/apply/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(ApplySearchPOJO applySearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<ApplyPOJO> applyPOJOList = new ArrayList<ApplyPOJO>();
		applyPOJOList = applyService.finds(applySearchPOJO);
//		int total = applyService.getCount(applySearchPOJO);
		ret.addObject("applyPOJOList", applyPOJOList);
		ret.setViewName("page/apply_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<ApplyPOJO> findApply(ApplySearchPOJO applySearchPOJO, Model model) throws Exception {
		ExtjsPOJO<ApplyPOJO> ret = new ExtjsPOJO<ApplyPOJO>();
		List<ApplyPOJO> applyPOJOList = new ArrayList<ApplyPOJO>();
		applyPOJOList = applyService.finds(applySearchPOJO);
		int total = applyService.getCount(applySearchPOJO);
		/*ApplyPOJO applyPOJO = new ApplyPOJO();
		applyPOJO.setApplyId(1);
		applyPOJO.setName("hefei");
		applyPOJO.setDescription("描述信息。。。");
		applyPOJOList.add(applyPOJO);*/
		
		ret.setGridModelList(applyPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("applyPOJOList", applyPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(ApplyPOJO applyPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = applyService.insert(applyPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(ApplyPOJO applyPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = applyService.update(applyPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = applyService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

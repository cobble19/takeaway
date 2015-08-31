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

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.InteractiveApplyPOJO;
import com.cobble.takeaway.pojo.InteractiveApplySearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.InteractiveApplyService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class InteractiveApplyController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(InteractiveApplyController.class);
	
	@Autowired
	private InteractiveApplyService interactiveApplyService;
	

	@RequestMapping(value = "/web/person/interactiveApplyInActivity/{interactiveId}", produces = {MediaType.APPLICATION_JSON_VALUE})
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
			LOGGER.error("applyInActivity error.", e);
			throw e;
		}
		
		return ret;
	}

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
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}*/
	
	@RequestMapping(value = "/web/person/interactiveApply/add", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO apply(InteractiveApplyPOJO interactiveApplyPOJO) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		
		
		try {
			Long userId = UserUtil.getCurrentUser().getUserId();
			interactiveApplyPOJO.setUserId(userId);
			
			Boolean exist = interactiveApplyService.existInteractiveApplyByUserId(interactiveApplyPOJO);
			if (exist) {
				ret.setSuccess(false);
				ret.setDesc("您已经提交过答案！");
				return ret;
			}
			
			int result = interactiveApplyService.insert(interactiveApplyPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
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
			LOGGER.error("insert error.", e);
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
			LOGGER.error("insert error.", e);
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
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

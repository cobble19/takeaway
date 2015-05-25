package com.cobble.takeaway.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.ActivityService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class ActivityController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value = "/web/enterprise/activity/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4Web(ActivityPOJO activityPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityService.insert(activityPOJO, UserUtil.getCurrentUser().getUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/activity/{activityId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ActivityPOJO query(@PathVariable("activityId") Long activityId) throws Exception {
		ActivityPOJO ret = new ActivityPOJO();
		try {
			ret = activityService.findById(activityId);
		} catch (Exception e) {
			LOGGER.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/activity/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityPOJO> query(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		DataTablesPOJO<ActivityPOJO> ret = new DataTablesPOJO<ActivityPOJO>();
		try {
			List<ActivityPOJO> activityPOJOs = activityService.finds(activitySearchPOJO);
			ret.setData(activityPOJOs);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/enterprise/activityByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityPOJO> queryByUserId(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		DataTablesPOJO<ActivityPOJO> ret = new DataTablesPOJO<ActivityPOJO>();
		activitySearchPOJO.setUserId(UserUtil.getCurrentUser().getUserId());
		try {
			List<ActivityPOJO> activityPOJOs = activityService.finds(activitySearchPOJO);
			ret.setData(activityPOJOs);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	/*@RequestMapping(value = "/web/activity/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<ActivityPOJO> activityPOJOList = new ArrayList<ActivityPOJO>();
		activityPOJOList = activityService.finds(activitySearchPOJO);
//		int total = activityService.getCount(activitySearchPOJO);
		ret.addObject("activityPOJOList", activityPOJOList);
		ret.setViewName("page/activity_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activity", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<ActivityPOJO> findActivity(ActivitySearchPOJO activitySearchPOJO, Model model) throws Exception {
		ExtjsPOJO<ActivityPOJO> ret = new ExtjsPOJO<ActivityPOJO>();
		List<ActivityPOJO> activityPOJOList = new ArrayList<ActivityPOJO>();
		activityPOJOList = activityService.finds(activitySearchPOJO);
		int total = activityService.getCount(activitySearchPOJO);
		ActivityPOJO activityPOJO = new ActivityPOJO();
		activityPOJO.setActivityId(1);
		activityPOJO.setName("hefei");
		activityPOJO.setDescription("描述信息。。。");
		activityPOJOList.add(activityPOJO);
		
		ret.setGridModelList(activityPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("activityPOJOList", activityPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activity/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(ActivityPOJO activityPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityService.insert(activityPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activity/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(ActivityPOJO activityPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityService.update(activityPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activity/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}*/

}
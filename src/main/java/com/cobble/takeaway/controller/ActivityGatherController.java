package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ActivityGatherPOJO;
import com.cobble.takeaway.pojo.ActivityGatherSearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.ActivityGatherService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class ActivityGatherController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(ActivityGatherController.class);
	
	@Autowired
	private ActivityGatherService activityGatherService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/web/unified/activitygather/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(ActivityGatherPOJO activityGatherPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityGatherPOJO == null) {
				throw new Exception("activityGatherPOJO can't is NULL.");
			}
			int result = -1;

			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			activityGatherPOJO.setUserId(userId);
			if (activityGatherPOJO.getActivityGatherId() != null) {
				result = activityGatherService.update(activityGatherPOJO);
			} else {
				result = activityGatherService.insert(activityGatherPOJO, userId);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/activityGather_detail.jsp?activityGatherId=" + activityGatherPOJO.getActivityGatherId();
		url = "/web/unified/usercenter#create_activity_gather";

		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/api/unified/activitygather/publishType", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO publishType(ActivityGatherPOJO activityGatherPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityGatherPOJO == null) {
				throw new Exception("activityGatherPOJO can't is NULL.");
			}
			if (activityGatherPOJO.getActivityGatherId() == null || activityGatherPOJO.getPublishType() == null) {
				throw new Exception("activityGatherId/publishType can't is NULL. activityGatherId: " + activityGatherPOJO.getActivityGatherId() + ", publishType: " + activityGatherPOJO.getPublishType());
			}
			int result = -1;
			
			result = activityGatherService.update(activityGatherPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/activitygather/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(ActivityGatherPOJO activityGatherPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityGatherPOJO == null) {
				throw new Exception("activityGatherPOJO can't is NULL.");
			}
			int result = -1;
			if (activityGatherPOJO.getActivityGatherId() != null) {
				result = activityGatherService.update(activityGatherPOJO);
			} else {
				result = activityGatherService.insert(activityGatherPOJO, UserUtil.getCurrentUser().getUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/activitygather/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(ActivityGatherPOJO activityGatherPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityGatherPOJO == null) {
				throw new Exception("activityGatherPOJO can't is NULL.");
			}
			int result = -1;
			if (activityGatherPOJO.getActivityGatherId() != null) {
				result = activityGatherService.update(activityGatherPOJO);
			} else {
				result = activityGatherService.insert(activityGatherPOJO, UserUtil.getCurrentUser().getUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/enterprise/activityGather_detail.jsp?activityGatherId=" + activityGatherPOJO.getActivityGatherId();
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/api/unified/activitygather/{activityGatherId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ActivityGatherPOJO query(@PathVariable("activityGatherId") Long activityGatherId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActivityGatherPOJO ret = new ActivityGatherPOJO();
		try {
			ret = activityGatherService.findById(activityGatherId);
			if (ret != null && ret.getWxAuthorizerInfoPOJO() != null) {
				String authorizerAppId = ret.getWxAuthorizerInfoPOJO().getAuthorizerAppId();
				HttpSession session = request.getSession();
				session.setAttribute(CommonConstant.AUTHORIZER_APP_ID, authorizerAppId);
			}
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/activitygather/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityGatherPOJO> query(ActivityGatherSearchPOJO activityGatherSearchPOJO) throws Exception {
		DataTablesPOJO<ActivityGatherPOJO> ret = new DataTablesPOJO<ActivityGatherPOJO>();
		try {
			List<ActivityGatherPOJO> activityGatherPOJOs = activityGatherService.finds(activityGatherSearchPOJO);
			ret.setData(activityGatherPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/activityGatherByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityGatherPOJO> queryByUserId(ActivityGatherSearchPOJO activityGatherSearchPOJO) throws Exception {
		DataTablesPOJO<ActivityGatherPOJO> ret = new DataTablesPOJO<ActivityGatherPOJO>();
		activityGatherSearchPOJO.setUserId(UserUtil.getCurrentUserId());
		try {
			activityGatherSearchPOJO.setPaginationFlage(false);
			List<ActivityGatherPOJO> activityGatherPOJOs = activityGatherService.finds(activityGatherSearchPOJO);
			ret.setData(activityGatherPOJOs);
		} catch (Exception e) {
			logger.error("activityGatherByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/activityGather/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(ActivityGatherSearchPOJO activityGatherSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<ActivityGatherPOJO> activityGatherPOJOList = new ArrayList<ActivityGatherPOJO>();
		activityGatherPOJOList = activityGatherService.finds(activityGatherSearchPOJO);
//		int total = activityGatherService.getCount(activityGatherSearchPOJO);
		ret.addObject("activityGatherPOJOList", activityGatherPOJOList);
		ret.setViewName("page/activityGather_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activityGather", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<ActivityGatherPOJO> findActivityGather(ActivityGatherSearchPOJO activityGatherSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<ActivityGatherPOJO> ret = new ExtjsPOJO<ActivityGatherPOJO>();
		List<ActivityGatherPOJO> activityGatherPOJOList = new ArrayList<ActivityGatherPOJO>();
		activityGatherPOJOList = activityGatherService.finds(activityGatherSearchPOJO);
		int total = activityGatherService.getCount(activityGatherSearchPOJO);
		
		ret.setGridModelList(activityGatherPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("activityGatherPOJOList", activityGatherPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activityGather/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(ActivityGatherPOJO activityGatherPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityGatherService.insert(activityGatherPOJO, UserUtil.getCurrentUser().getUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activityGather/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(ActivityGatherPOJO activityGatherPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityGatherService.update(activityGatherPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activityGather/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityGatherService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

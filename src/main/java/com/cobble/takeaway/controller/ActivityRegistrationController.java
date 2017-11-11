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

import com.cobble.takeaway.pojo.ActivityRegistrationPOJO;
import com.cobble.takeaway.pojo.ActivityRegistrationSearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.ActivityRegistrationService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class ActivityRegistrationController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(ActivityRegistrationController.class);
	
	@Autowired
	private ActivityRegistrationService activityRegistrationService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/web/unified/activityregistration/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(ActivityRegistrationPOJO activityRegistrationPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityRegistrationPOJO == null) {
				throw new Exception("activityRegistrationPOJO can't is NULL.");
			}
			int result = -1;

			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			activityRegistrationPOJO.setUserId(userId);
			
			if (activityRegistrationPOJO.getActivityRegistrationId() != null) {
				result = activityRegistrationService.update(activityRegistrationPOJO);
			} else {
				result = activityRegistrationService.insert(activityRegistrationPOJO, userId);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/activityRegistration_detail.jsp?activityRegistrationId=" + activityRegistrationPOJO.getActivityRegistrationId();
		url = "/web/unified/usercenter#create_activity_registration";
		url = "/page/unified/activity_registration_single.jsp";

		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/api/unified/activityregistration/publishType", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO publishType(ActivityRegistrationPOJO activityRegistrationPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityRegistrationPOJO == null) {
				throw new Exception("activityRegistrationPOJO can't is NULL.");
			}
			if (activityRegistrationPOJO.getActivityRegistrationId() == null || activityRegistrationPOJO.getPublishType() == null) {
				throw new Exception("activityRegistrationId/publishType can't is NULL. activityRegistrationId: " + activityRegistrationPOJO.getActivityRegistrationId() + ", publishType: " + activityRegistrationPOJO.getPublishType());
			}
			int result = -1;
			
			result = activityRegistrationService.update(activityRegistrationPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/activityregistration/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(ActivityRegistrationPOJO activityRegistrationPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityRegistrationPOJO == null) {
				throw new Exception("activityRegistrationPOJO can't is NULL.");
			}
			int result = -1;
			if (activityRegistrationPOJO.getActivityRegistrationId() != null) {
				result = activityRegistrationService.update(activityRegistrationPOJO);
			} else {
				result = activityRegistrationService.insert(activityRegistrationPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/activityregistration/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(ActivityRegistrationPOJO activityRegistrationPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityRegistrationPOJO == null) {
				throw new Exception("activityRegistrationPOJO can't is NULL.");
			}
			int result = -1;
			if (activityRegistrationPOJO.getActivityRegistrationId() != null) {
				result = activityRegistrationService.update(activityRegistrationPOJO);
			} else {
				result = activityRegistrationService.insert(activityRegistrationPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/enterprise/activityRegistration_detail.jsp?activityRegistrationId=" + activityRegistrationPOJO.getActivityRegistrationId();
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/api/unified/activityregistration/{activityRegistrationId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ActivityRegistrationPOJO query(@PathVariable("activityRegistrationId") Long activityRegistrationId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActivityRegistrationPOJO ret = new ActivityRegistrationPOJO();
		try {
			ret = activityRegistrationService.findById(activityRegistrationId);
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
	
	@RequestMapping(value = "/web/unified/activityregistration/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityRegistrationPOJO> query(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO) throws Exception {
		DataTablesPOJO<ActivityRegistrationPOJO> ret = new DataTablesPOJO<ActivityRegistrationPOJO>();
		try {
			List<ActivityRegistrationPOJO> activityRegistrationPOJOs = activityRegistrationService.finds(activityRegistrationSearchPOJO);
			ret.setData(activityRegistrationPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/activityRegistrationByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityRegistrationPOJO> queryByUserId(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO) throws Exception {
		DataTablesPOJO<ActivityRegistrationPOJO> ret = new DataTablesPOJO<ActivityRegistrationPOJO>();
		activityRegistrationSearchPOJO.setUserId(UserUtil.getCurrentUserId());
		try {
			activityRegistrationSearchPOJO.setPaginationFlage(false);
			List<ActivityRegistrationPOJO> activityRegistrationPOJOs = activityRegistrationService.finds(activityRegistrationSearchPOJO);
			ret.setData(activityRegistrationPOJOs);
		} catch (Exception e) {
			logger.error("activityRegistrationByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/activityRegistration/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<ActivityRegistrationPOJO> activityRegistrationPOJOList = new ArrayList<ActivityRegistrationPOJO>();
		activityRegistrationPOJOList = activityRegistrationService.finds(activityRegistrationSearchPOJO);
//		int total = activityRegistrationService.getCount(activityRegistrationSearchPOJO);
		ret.addObject("activityRegistrationPOJOList", activityRegistrationPOJOList);
		ret.setViewName("page/activityRegistration_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activityRegistration", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<ActivityRegistrationPOJO> findActivityRegistration(ActivityRegistrationSearchPOJO activityRegistrationSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<ActivityRegistrationPOJO> ret = new ExtjsPOJO<ActivityRegistrationPOJO>();
		List<ActivityRegistrationPOJO> activityRegistrationPOJOList = new ArrayList<ActivityRegistrationPOJO>();
		activityRegistrationPOJOList = activityRegistrationService.finds(activityRegistrationSearchPOJO);
		int total = activityRegistrationService.getCount(activityRegistrationSearchPOJO);
		
		ret.setGridModelList(activityRegistrationPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("activityRegistrationPOJOList", activityRegistrationPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activityRegistration/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(ActivityRegistrationPOJO activityRegistrationPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityRegistrationService.insert(activityRegistrationPOJO, UserUtil.getCurrentUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activityRegistration/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(ActivityRegistrationPOJO activityRegistrationPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityRegistrationService.update(activityRegistrationPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activityRegistration/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityRegistrationService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

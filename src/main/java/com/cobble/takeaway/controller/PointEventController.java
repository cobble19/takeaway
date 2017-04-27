package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.PointEventPOJO;
import com.cobble.takeaway.pojo.PointEventSearchPOJO;
import com.cobble.takeaway.pojo.PointEventPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.PointEventService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class PointEventController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(PointEventController.class);
	
	@Autowired
	private PointEventService pointEventService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	
	@RequestMapping(value = "/api/unified/pointEvent/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebPI(PointEventPOJO pointEventPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (pointEventPOJO == null) {
				throw new Exception("pointEventPOJO can't is NULL.");
			}
			int result = -1;
			if (pointEventPOJO.getPointEventId() != null) {
				result = pointEventService.update(pointEventPOJO);
			} else {
				result = pointEventService.insert(pointEventPOJO, UserUtil.getCurrentUser().getUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/pointEvent/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(PointEventPOJO pointEventPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (pointEventPOJO == null) {
				throw new Exception("pointEventPOJO can't is NULL.");
			}
			int result = -1;
			if (pointEventPOJO.getPointEventId() != null) {
				result = pointEventService.update(pointEventPOJO);
			} else {
				result = pointEventService.insert(pointEventPOJO, UserUtil.getCurrentUser().getUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/enterprise/pointEvent_detail.jsp?pointEventId=" + pointEventPOJO.getPointEventId();

		url = "/web/unified/usercenter#point_event";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	

	@RequestMapping(value = "/web/unified/pointEvent/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(PointEventPOJO pointEventPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (pointEventPOJO == null) {
				throw new Exception("pointEventPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			PointEventPOJO pointEventPOJO2 = pointEventService.findById(pointEventPOJO.getPointEventId());
			ret.addObject("pointEventPOJO", pointEventPOJO2);
			ret.setViewName("/page/unified/point_event_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/pointEvent/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<PointEventPOJO> query(PointEventSearchPOJO pointEventSearchPOJO) throws Exception {
		DataTablesPOJO<PointEventPOJO> ret = new DataTablesPOJO<PointEventPOJO>();
		try {
			List<PointEventPOJO> pointEventPOJOs = pointEventService.finds(pointEventSearchPOJO);
			ret.setData(pointEventPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/pointEvent/pointEventByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<PointEventPOJO> queryByUserId(PointEventSearchPOJO pointEventSearchPOJO) throws Exception {
		DataTablesPOJO<PointEventPOJO> ret = new DataTablesPOJO<PointEventPOJO>();
		pointEventSearchPOJO.setUserId(UserUtil.getCurrentUser().getUserId());
		try {
			List<PointEventPOJO> pointEventPOJOs = pointEventService.finds(pointEventSearchPOJO);
			ret.setData(pointEventPOJOs);
		} catch (Exception e) {
			logger.error("pointEventByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/pointEvent", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<PointEventPOJO> findPointEvent(PointEventSearchPOJO pointEventSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<PointEventPOJO> ret = new ExtjsPOJO<PointEventPOJO>();
		List<PointEventPOJO> pointEventPOJOList = new ArrayList<PointEventPOJO>();
		pointEventPOJOList = pointEventService.finds(pointEventSearchPOJO);
		int total = pointEventService.getCount(pointEventSearchPOJO);
		
		ret.setGridModelList(pointEventPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("pointEventPOJOList", pointEventPOJOList);
		model.addAttribute("success", true);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointEvent/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(PointEventPOJO pointEventPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointEventService.insert(pointEventPOJO, UserUtil.getCurrentUser().getUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointEvent/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(PointEventPOJO pointEventPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointEventService.update(pointEventPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointEvent/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointEventService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

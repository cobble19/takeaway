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
import com.cobble.takeaway.pojo.PointSummaryPOJO;
import com.cobble.takeaway.pojo.PointSummaryPOJO;
import com.cobble.takeaway.pojo.PointSummarySearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.PointSummaryService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class PointSummaryController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(PointSummaryController.class);
	
	@Autowired
	private PointSummaryService pointSummaryService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	
	@RequestMapping(value = "/api/unified/pointSummary/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebPI(PointSummaryPOJO pointSummaryPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (pointSummaryPOJO == null) {
				throw new Exception("pointSummaryPOJO can't is NULL.");
			}
			int result = -1;
			if (pointSummaryPOJO.getPointSummaryId() != null) {
				result = pointSummaryService.update(pointSummaryPOJO);
			} else {
				result = pointSummaryService.insert(pointSummaryPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/pointSummary/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(PointSummaryPOJO pointSummaryPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (pointSummaryPOJO == null) {
				throw new Exception("pointSummaryPOJO can't is NULL.");
			}
			int result = -1;
			if (pointSummaryPOJO.getPointSummaryId() != null) {
				result = pointSummaryService.update(pointSummaryPOJO);
			} else {
				result = pointSummaryService.insert(pointSummaryPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/enterprise/pointSummary_detail.jsp?pointSummaryId=" + pointSummaryPOJO.getPointSummaryId();

		url = "/web/unified/usercenter#point_summary";
		url = "/page/unified/point_summary_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	

	@RequestMapping(value = "/web/unified/pointSummary/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(PointSummaryPOJO pointSummaryPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (pointSummaryPOJO == null) {
				throw new Exception("pointSummaryPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			PointSummaryPOJO pointSummaryPOJO2 = pointSummaryService.findById(pointSummaryPOJO.getPointSummaryId());
			ret.addObject("pointSummaryPOJO", pointSummaryPOJO2);
			ret.setViewName("/page/unified/point_summary_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/pointSummary/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<PointSummaryPOJO> query(PointSummarySearchPOJO pointSummarySearchPOJO) throws Exception {
		DataTablesPOJO<PointSummaryPOJO> ret = new DataTablesPOJO<PointSummaryPOJO>();
		try {
			List<PointSummaryPOJO> pointSummaryPOJOs = pointSummaryService.finds(pointSummarySearchPOJO);
			ret.setData(pointSummaryPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/pointSummary/pointSummaryByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<PointSummaryPOJO> queryByUserId(PointSummarySearchPOJO pointSummarySearchPOJO) throws Exception {
		DataTablesPOJO<PointSummaryPOJO> ret = new DataTablesPOJO<PointSummaryPOJO>();
//		pointSummarySearchPOJO.setUserId(UserUtil.getCurrentUserId());
		try {
			List<PointSummaryPOJO> pointSummaryPOJOs = pointSummaryService.finds(pointSummarySearchPOJO);
			ret.setData(pointSummaryPOJOs);
		} catch (Exception e) {
			logger.error("pointSummaryByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/pointSummary", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<PointSummaryPOJO> findPointSummary(PointSummarySearchPOJO pointSummarySearchPOJO, Model model) throws Exception {
		ExtjsPOJO<PointSummaryPOJO> ret = new ExtjsPOJO<PointSummaryPOJO>();
		List<PointSummaryPOJO> pointSummaryPOJOList = new ArrayList<PointSummaryPOJO>();
		pointSummaryPOJOList = pointSummaryService.finds(pointSummarySearchPOJO);
		int total = pointSummaryService.getCount(pointSummarySearchPOJO);
		
		ret.setGridModelList(pointSummaryPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("pointSummaryPOJOList", pointSummaryPOJOList);
		model.addAttribute("success", true);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointSummary/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(PointSummaryPOJO pointSummaryPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointSummaryService.insert(pointSummaryPOJO, UserUtil.getCurrentUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointSummary/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(PointSummaryPOJO pointSummaryPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointSummaryService.update(pointSummaryPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointSummary/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointSummaryService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

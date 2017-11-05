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
import com.cobble.takeaway.pojo.PointRecordPOJO;
import com.cobble.takeaway.pojo.PointRecordSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.weixin.WxRespMsgPOJO;
import com.cobble.takeaway.service.PointRecordService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class PointRecordController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(PointRecordController.class);
	
	@Autowired
	private PointRecordService pointRecordService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	
	@RequestMapping(value = "/api/unified/pointRecord/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebPI(PointRecordPOJO pointRecordPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (pointRecordPOJO == null) {
				throw new Exception("pointRecordPOJO can't is NULL.");
			}
			int result = -1;
			if (pointRecordPOJO.getPointRecordId() != null) {
				result = pointRecordService.update(pointRecordPOJO);
			} else {
				result = pointRecordService.insert(pointRecordPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/pointRecord/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(PointRecordPOJO pointRecordPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (pointRecordPOJO == null) {
				throw new Exception("pointRecordPOJO can't is NULL.");
			}
			int result = -1;
			if (pointRecordPOJO.getPointRecordId() != null) {
				result = pointRecordService.update(pointRecordPOJO);
			} else {
				result = pointRecordService.insert(pointRecordPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/enterprise/pointRecord_detail.jsp?pointRecordId=" + pointRecordPOJO.getPointRecordId();

		url = "/web/unified/usercenter#point_record";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	

	@RequestMapping(value = "/web/unified/pointRecord/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(PointRecordPOJO pointRecordPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (pointRecordPOJO == null) {
				throw new Exception("pointRecordPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			PointRecordPOJO pointRecordPOJO2 = pointRecordService.findById(pointRecordPOJO.getPointRecordId());
			ret.addObject("pointRecordPOJO", pointRecordPOJO2);
			ret.setViewName("/page/unified/point_record_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/pointRecord/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<PointRecordPOJO> query(PointRecordSearchPOJO pointRecordSearchPOJO) throws Exception {
		DataTablesPOJO<PointRecordPOJO> ret = new DataTablesPOJO<PointRecordPOJO>();
		try {
			List<PointRecordPOJO> pointRecordPOJOs = pointRecordService.finds(pointRecordSearchPOJO);
			ret.setData(pointRecordPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/pointRecord/pointRecordByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<PointRecordPOJO> queryByUserId(PointRecordSearchPOJO pointRecordSearchPOJO) throws Exception {
		DataTablesPOJO<PointRecordPOJO> ret = new DataTablesPOJO<PointRecordPOJO>();
		pointRecordSearchPOJO.setUserId(UserUtil.getCurrentUserId());
		try {
			List<PointRecordPOJO> pointRecordPOJOs = pointRecordService.finds(pointRecordSearchPOJO);
			ret.setData(pointRecordPOJOs);
		} catch (Exception e) {
			logger.error("pointRecordByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/pointRecord", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<PointRecordPOJO> findPointRecord(PointRecordSearchPOJO pointRecordSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<PointRecordPOJO> ret = new ExtjsPOJO<PointRecordPOJO>();
		List<PointRecordPOJO> pointRecordPOJOList = new ArrayList<PointRecordPOJO>();
		pointRecordPOJOList = pointRecordService.finds(pointRecordSearchPOJO);
		int total = pointRecordService.getCount(pointRecordSearchPOJO);
		
		ret.setGridModelList(pointRecordPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("pointRecordPOJOList", pointRecordPOJOList);
		model.addAttribute("success", true);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointRecord/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(PointRecordPOJO pointRecordPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointRecordService.insert(pointRecordPOJO, UserUtil.getCurrentUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointRecord/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(PointRecordPOJO pointRecordPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointRecordService.update(pointRecordPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/pointRecord/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = pointRecordService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

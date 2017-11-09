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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.status.WarnStatus;

import com.cobble.takeaway.pojo.AwardPOJO;
import com.cobble.takeaway.pojo.AwardSearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.AwardService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class AwardController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(AwardController.class);
	
	@Autowired
	private AwardService awardService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/web/unified/award/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(AwardPOJO awardPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (awardPOJO == null) {
				throw new Exception("awardPOJO can't is NULL.");
			}
			int result = -1;
			if (awardPOJO.getAwardId() != null) {
				result = awardService.update(awardPOJO);
			} else {
				result = awardService.insert(awardPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/award_detail.jsp?awardId=" + awardPOJO.getAwardId();
//		url = "/web/unified/usercenter#award";
		url = "/page/unified/award_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}


	@RequestMapping(value = "/web/unified/award/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView update(@RequestParam(value="awardId") Long awardId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			AwardPOJO awardPOJO = awardService.findById(awardId);
			
			ret.addObject("awardPOJO", awardPOJO);
			ret.setViewName("/page/unified/award_update");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	
	@RequestMapping(value = "/api/unified/award/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(AwardPOJO awardPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (awardPOJO == null) {
				throw new Exception("awardPOJO can't is NULL.");
			}
			int result = -1;
			if (awardPOJO.getAwardId() != null) {
				result = awardService.update(awardPOJO);
			} else {
				result = awardService.insert(awardPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/award/{awardId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public AwardPOJO query(@PathVariable("awardId") Long awardId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AwardPOJO ret = new AwardPOJO();
		try {
			ret = awardService.findById(awardId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/award/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<AwardPOJO> query(AwardSearchPOJO awardSearchPOJO) throws Exception {
		DataTablesPOJO<AwardPOJO> ret = new DataTablesPOJO<AwardPOJO>();
		try {
			List<AwardPOJO> awardPOJOs = awardService.finds(awardSearchPOJO);
			ret.setData(awardPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/award/awardByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<AwardPOJO> queryByUserId(AwardSearchPOJO awardSearchPOJO) throws Exception {
		DataTablesPOJO<AwardPOJO> ret = new DataTablesPOJO<AwardPOJO>();
		try {
//			awardSearchPOJO.setUserId(UserUtil.getCurrentUserId());
			awardSearchPOJO.setPaginationFlage(false);
			List<AwardPOJO> awardPOJOs = awardService.finds(awardSearchPOJO);
			ret.setData(awardPOJOs);
		} catch (Exception e) {
			logger.error("awardByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/award", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<AwardPOJO> findAward(AwardSearchPOJO awardSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<AwardPOJO> ret = new ExtjsPOJO<AwardPOJO>();
		List<AwardPOJO> awardPOJOList = new ArrayList<AwardPOJO>();
		awardPOJOList = awardService.finds(awardSearchPOJO);
		int total = awardService.getCount(awardSearchPOJO);
		
		ret.setGridModelList(awardPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("awardPOJOList", awardPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/award/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(AwardPOJO awardPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = awardService.insert(awardPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/award/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(AwardPOJO awardPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = awardService.update(awardPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/award/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = awardService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

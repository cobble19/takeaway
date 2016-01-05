package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.RecommendPOJO;
import com.cobble.takeaway.pojo.RecommendSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.RecommendService;

@Controller
public class RecommendController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private RecommendService recommendService;
	

	@RequestMapping(value = "/web/recommend/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(RecommendSearchPOJO recommendSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<RecommendPOJO> recommendPOJOList = new ArrayList<RecommendPOJO>();
		recommendPOJOList = recommendService.finds(recommendSearchPOJO);
//		int total = recommendService.getCount(recommendSearchPOJO);
		ret.addObject("recommendPOJOList", recommendPOJOList);
		ret.setViewName("page/recommend_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/recommend", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<RecommendPOJO> findRecommend(RecommendSearchPOJO recommendSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<RecommendPOJO> ret = new ExtjsPOJO<RecommendPOJO>();
		List<RecommendPOJO> recommendPOJOList = new ArrayList<RecommendPOJO>();
		recommendPOJOList = recommendService.finds(recommendSearchPOJO);
		int total = recommendService.getCount(recommendSearchPOJO);
		/*RecommendPOJO recommendPOJO = new RecommendPOJO();
		recommendPOJO.setRecommendId(1);
		recommendPOJO.setName("hefei");
		recommendPOJO.setDescription("描述信息。。。");
		recommendPOJOList.add(recommendPOJO);*/
		
		ret.setGridModelList(recommendPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("recommendPOJOList", recommendPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/mgr/recommend/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(RecommendPOJO recommendPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = recommendService.insert(recommendPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/recommend/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(RecommendPOJO recommendPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = recommendService.update(recommendPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/recommend/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = recommendService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

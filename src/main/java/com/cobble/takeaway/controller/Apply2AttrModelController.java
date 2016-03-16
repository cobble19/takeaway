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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.Apply2AttrModelPOJO;
import com.cobble.takeaway.pojo.Apply2AttrModelSearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.WxAttrPOJO;
import com.cobble.takeaway.service.Apply2AttrModelService;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
public class Apply2AttrModelController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(Apply2AttrModelController.class);
	
	@Autowired
	private Apply2AttrModelService apply2AttrModelService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


	@RequestMapping(value = "/api/apply2AttrModel/delete/{activityId}", produces = {MediaType.APPLICATION_JSON_VALUE}
				, method = {RequestMethod.GET})
	@ResponseBody
	public StatusPOJO delete4WebAPIByActivityId(@PathVariable(value = "activityId") Long activityId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			apply2AttrModelService.deleteByActivityId(activityId);
			
			/*String base = HttpRequestUtil.getBase(request);
			Long userId = UserUtil.getCurrentUser().getUserId();*/
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
//			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/apply2AttrModel/{activityId}", produces = {MediaType.APPLICATION_JSON_VALUE}
				, method = {RequestMethod.GET})
	@ResponseBody
	public DataTablesPOJO<Apply2AttrModelPOJO> query4WebAPIByActivityId(@PathVariable(value = "activityId") Long activityId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTablesPOJO<Apply2AttrModelPOJO> ret = new DataTablesPOJO<Apply2AttrModelPOJO>();
		try {
			List<Apply2AttrModelPOJO> apply2AttrModelPOJOs = apply2AttrModelService.findsByActivityId(activityId);
			
			/*String base = HttpRequestUtil.getBase(request);
			Long userId = UserUtil.getCurrentUser().getUserId();*/
			
			ret.setData(apply2AttrModelPOJOs);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
//			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/media/apply2AttrModels/add", produces = {MediaType.APPLICATION_JSON_VALUE}
				, method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO add4WebAPI(@RequestBody String body, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			List<Apply2AttrModelPOJO> apply2AttrModelPOJOs = (List<Apply2AttrModelPOJO>) JsonUtils.convertToJavaBeanList(body
													, new TypeReference<List<Apply2AttrModelPOJO>>() { });
			if (CollectionUtils.isEmpty(apply2AttrModelPOJOs)) {
				throw new Exception("apply2AttrModelPOJOs can't is NULL.");
			}
			
			String base = HttpRequestUtil.getBase(request);
			Long userId = UserUtil.getCurrentUser().getUserId();
			
			for (int i = 0; i < apply2AttrModelPOJOs.size(); i++) {
				Apply2AttrModelPOJO apply2AttrModelPOJO = apply2AttrModelPOJOs.get(i);
				apply2AttrModelService.deleteByActivityId(apply2AttrModelPOJO.getActivityId());
			}
			for (int i = 0; i < apply2AttrModelPOJOs.size(); i++) {
				Apply2AttrModelPOJO apply2AttrModelPOJO = apply2AttrModelPOJOs.get(i);
				apply2AttrModelService.insert(apply2AttrModelPOJO);
			}
			
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
//			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/apply2AttrModel/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<Apply2AttrModelPOJO> query(Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO) throws Exception {
		DataTablesPOJO<Apply2AttrModelPOJO> ret = new DataTablesPOJO<Apply2AttrModelPOJO>();
		try {
			List<Apply2AttrModelPOJO> apply2AttrModelPOJOs = apply2AttrModelService.finds(apply2AttrModelSearchPOJO);
			ret.setData(apply2AttrModelPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/apply2AttrModel/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<Apply2AttrModelPOJO> apply2AttrModelPOJOList = new ArrayList<Apply2AttrModelPOJO>();
		apply2AttrModelPOJOList = apply2AttrModelService.finds(apply2AttrModelSearchPOJO);
//		int total = apply2AttrModelService.getCount(apply2AttrModelSearchPOJO);
		ret.addObject("apply2AttrModelPOJOList", apply2AttrModelPOJOList);
		ret.setViewName("page/apply2AttrModel_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2AttrModel", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<Apply2AttrModelPOJO> findApply2AttrModel(Apply2AttrModelSearchPOJO apply2AttrModelSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<Apply2AttrModelPOJO> ret = new ExtjsPOJO<Apply2AttrModelPOJO>();
		List<Apply2AttrModelPOJO> apply2AttrModelPOJOList = new ArrayList<Apply2AttrModelPOJO>();
		apply2AttrModelPOJOList = apply2AttrModelService.finds(apply2AttrModelSearchPOJO);
		int total = apply2AttrModelService.getCount(apply2AttrModelSearchPOJO);
		
		ret.setGridModelList(apply2AttrModelPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("apply2AttrModelPOJOList", apply2AttrModelPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2AttrModel/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(Apply2AttrModelPOJO apply2AttrModelPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2AttrModelService.insert(apply2AttrModelPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2AttrModel/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(Apply2AttrModelPOJO apply2AttrModelPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2AttrModelService.update(apply2AttrModelPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2AttrModel/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2AttrModelService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

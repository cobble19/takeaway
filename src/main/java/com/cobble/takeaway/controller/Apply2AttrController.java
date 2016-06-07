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

import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2AttrSearchPOJO;
import com.cobble.takeaway.pojo.ApplySearchPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.WxAttrPOJO;
import com.cobble.takeaway.service.Apply2AttrService;
import com.cobble.takeaway.service.Apply2Service;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
public class Apply2AttrController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(Apply2AttrController.class);
	
	@Autowired
	private Apply2AttrService apply2AttrService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/api/apply2Attr/apply2/exist", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO existApply2InActivity(Apply2AttrSearchPOJO apply2AttrSearchPOJO) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2AttrService.getCount(apply2AttrSearchPOJO);
			if (result > 0) {
				ret.setSuccess(true);
				ret.setDesc("存在手机号：" + apply2AttrSearchPOJO.getApply2AttrData());
			} else {
				ret.setSuccess(false);
				ret.setDesc("不存在手机号：" + apply2AttrSearchPOJO.getApply2AttrData());
			}
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/apply2Attr/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<Apply2AttrPOJO> query(Apply2AttrSearchPOJO apply2AttrSearchPOJO) throws Exception {
		DataTablesPOJO<Apply2AttrPOJO> ret = new DataTablesPOJO<Apply2AttrPOJO>();
		try {
			List<Apply2AttrPOJO> apply2AttrPOJOs = apply2AttrService.finds(apply2AttrSearchPOJO);
			ret.setData(apply2AttrPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/apply2Attr/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(Apply2AttrSearchPOJO apply2AttrSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<Apply2AttrPOJO> apply2AttrPOJOList = new ArrayList<Apply2AttrPOJO>();
		apply2AttrPOJOList = apply2AttrService.finds(apply2AttrSearchPOJO);
//		int total = apply2AttrService.getCount(apply2AttrSearchPOJO);
		ret.addObject("apply2AttrPOJOList", apply2AttrPOJOList);
		ret.setViewName("page/apply2Attr_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2Attr", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<Apply2AttrPOJO> findApply2Attr(Apply2AttrSearchPOJO apply2AttrSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<Apply2AttrPOJO> ret = new ExtjsPOJO<Apply2AttrPOJO>();
		List<Apply2AttrPOJO> apply2AttrPOJOList = new ArrayList<Apply2AttrPOJO>();
		apply2AttrPOJOList = apply2AttrService.finds(apply2AttrSearchPOJO);
		int total = apply2AttrService.getCount(apply2AttrSearchPOJO);
		
		ret.setGridModelList(apply2AttrPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("apply2AttrPOJOList", apply2AttrPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2Attr/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(Apply2AttrPOJO apply2AttrPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2AttrService.insert(apply2AttrPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2Attr/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(Apply2AttrPOJO apply2AttrPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2AttrService.update(apply2AttrPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/apply2Attr/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = apply2AttrService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

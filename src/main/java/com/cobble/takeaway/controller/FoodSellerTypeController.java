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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.FoodSellerTypePOJO;
import com.cobble.takeaway.pojo.FoodSellerTypeSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.FoodSellerTypeService;

@Controller
//@RequestMapping("/manager")
public class FoodSellerTypeController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(FoodSellerTypeController.class);
	
	@Autowired
	private FoodSellerTypeService foodSellerTypeService;
	
	@RequestMapping(value = "/web/foodSellerType")
	public ModelAndView findFoodSellerType(FoodSellerTypeSearchPOJO foodSellerTypeSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<FoodSellerTypePOJO> foodSellerTypePOJOList = foodSellerTypeService.finds(foodSellerTypeSearchPOJO);
		ret.addObject("foodSellerTypePOJOList", foodSellerTypePOJOList);
		ret.setViewName("page/food_seller_type");
		return ret;
	}
	
	@RequestMapping(value = "/manager/foodSellerType", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<FoodSellerTypePOJO> findFoodSellerType(FoodSellerTypeSearchPOJO foodSellerTypeSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<FoodSellerTypePOJO> ret = new ExtjsPOJO<FoodSellerTypePOJO>();
		List<FoodSellerTypePOJO> foodSellerTypePOJOList = new ArrayList<FoodSellerTypePOJO>();
		foodSellerTypePOJOList = foodSellerTypeService.finds(foodSellerTypeSearchPOJO);
		int total = foodSellerTypeService.getCount(foodSellerTypeSearchPOJO);
		/*FoodSellerTypePOJO foodSellerTypePOJO = new FoodSellerTypePOJO();
		foodSellerTypePOJO.setFoodSellerTypeId(1);
		foodSellerTypePOJO.setName("hefei");
		foodSellerTypePOJO.setDescription("描述信息。。。");
		foodSellerTypePOJOList.add(foodSellerTypePOJO);*/
		
		ret.setGridModelList(foodSellerTypePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("foodSellerTypePOJOList", foodSellerTypePOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/manager/foodSellerType/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(FoodSellerTypePOJO foodSellerTypePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodSellerTypeService.insert(foodSellerTypePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/manager/foodSellerType/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(FoodSellerTypePOJO foodSellerTypePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodSellerTypeService.update(foodSellerTypePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/manager/foodSellerType/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodSellerTypeService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

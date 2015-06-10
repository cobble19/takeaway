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
import com.cobble.takeaway.pojo.FoodTypePOJO;
import com.cobble.takeaway.pojo.FoodTypeSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.FoodTypeService;

@Controller
public class FoodTypeController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(FoodTypeController.class);
	
	@Autowired
	private FoodTypeService foodTypeService;
	
	@RequestMapping(value = "/web/foodType")
	public ModelAndView findFoodType(FoodTypeSearchPOJO foodTypeSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<FoodTypePOJO> foodTypePOJOList = foodTypeService.finds(foodTypeSearchPOJO);
		ret.addObject("foodTypePOJOList", foodTypePOJOList);
		ret.setViewName("page/food_type");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/foodType", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<FoodTypePOJO> findFoodType(FoodTypeSearchPOJO foodTypeSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<FoodTypePOJO> ret = new ExtjsPOJO<FoodTypePOJO>();
		List<FoodTypePOJO> foodTypePOJOList = new ArrayList<FoodTypePOJO>();
		foodTypePOJOList = foodTypeService.finds(foodTypeSearchPOJO);
		int total = foodTypeService.getCount(foodTypeSearchPOJO);
		/*FoodTypePOJO foodTypePOJO = new FoodTypePOJO();
		foodTypePOJO.setFoodTypeId(1);
		foodTypePOJO.setName("hefei");
		foodTypePOJO.setDescription("描述信息。。。");
		foodTypePOJOList.add(foodTypePOJO);*/
		
		ret.setGridModelList(foodTypePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("foodTypePOJOList", foodTypePOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/mgr/foodType/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(FoodTypePOJO foodTypePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodTypeService.insert(foodTypePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/foodType/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(FoodTypePOJO foodTypePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodTypeService.update(foodTypePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/foodType/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodTypeService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

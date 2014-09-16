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

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.FoodPOJO;
import com.cobble.takeaway.pojo.FoodSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.FoodService;

@Controller
@RequestMapping("/manager")
public class FoodController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(FoodController.class);
	
	@Autowired
	private FoodService foodService;
	
	@RequestMapping(value = "/food", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<FoodPOJO> findFood(FoodSearchPOJO foodSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<FoodPOJO> ret = new ExtjsPOJO<FoodPOJO>();
		List<FoodPOJO> foodPOJOList = new ArrayList<FoodPOJO>();
		foodPOJOList = foodService.finds(foodSearchPOJO);
		int total = foodService.getCount(foodSearchPOJO);
		/*FoodPOJO foodPOJO = new FoodPOJO();
		foodPOJO.setFoodId(1);
		foodPOJO.setName("hefei");
		foodPOJO.setDescription("描述信息。。。");
		foodPOJOList.add(foodPOJO);*/
		
		ret.setGridModelList(foodPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("foodPOJOList", foodPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/food/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(FoodPOJO foodPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodService.insert(foodPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/food/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(FoodPOJO foodPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodService.update(foodPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/food/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

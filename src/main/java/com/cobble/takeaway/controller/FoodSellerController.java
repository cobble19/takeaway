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
import com.cobble.takeaway.pojo.FoodSellerPOJO;
import com.cobble.takeaway.pojo.FoodSellerSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.FoodSellerService;

@Controller
@RequestMapping("/manager")
public class FoodSellerController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(FoodSellerController.class);
	
	@Autowired
	private FoodSellerService foodSellerService;
	
	@RequestMapping(value = "/foodSeller", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<FoodSellerPOJO> findFoodSeller(FoodSellerSearchPOJO foodSellerSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<FoodSellerPOJO> ret = new ExtjsPOJO<FoodSellerPOJO>();
		List<FoodSellerPOJO> foodSellerPOJOList = new ArrayList<FoodSellerPOJO>();
		foodSellerPOJOList = foodSellerService.finds(foodSellerSearchPOJO);
		int total = foodSellerService.getCount(foodSellerSearchPOJO);
		/*FoodSellerPOJO foodSellerPOJO = new FoodSellerPOJO();
		foodSellerPOJO.setFoodSellerId(1);
		foodSellerPOJO.setName("hefei");
		foodSellerPOJO.setDescription("描述信息。。。");
		foodSellerPOJOList.add(foodSellerPOJO);*/
		
		ret.setGridModelList(foodSellerPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("foodSellerPOJOList", foodSellerPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/foodSeller/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(FoodSellerPOJO foodSellerPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodSellerService.insert(foodSellerPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/foodSeller/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(FoodSellerPOJO foodSellerPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodSellerService.update(foodSellerPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("update error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/foodSeller/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodSellerService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("delete error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

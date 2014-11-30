package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.FoodPOJO;
import com.cobble.takeaway.pojo.FoodSellerPOJO;
import com.cobble.takeaway.pojo.FoodSellerSearchPOJO;
import com.cobble.takeaway.pojo.FoodTypePOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.FoodSellerService;
import com.cobble.takeaway.service.FoodService;

@Controller
public class FoodSellerController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(FoodSellerController.class);
	
	@Autowired
	private FoodSellerService foodSellerService;
	@Autowired
	private FoodService foodService;
	
	@RequestMapping(value = "/web/foodSellerDetail", method = {RequestMethod.GET})
	public ModelAndView findFoodSellers(@RequestParam(value="foodSellerId", required=false) Integer foodSellerId) throws Exception {
		ModelAndView ret = new ModelAndView();
		FoodSellerPOJO foodSellerPOJO = foodSellerService.findDetail(foodSellerId);
		//int total = foodSellerService.getCount(foodSellerSearchPOJO);
//		foodSellerPOJO.getFoodTypePOJOs().get(0).getFoodPOJOs().get(0).getName();
		if (foodSellerPOJO != null) {
			List<FoodTypePOJO> foodTypePOJOs = foodSellerPOJO.getFoodTypePOJOs();
			List<FoodPOJO> foodPOJOs = foodSellerPOJO.getFoodPOJOs();
			if (!CollectionUtils.isEmpty(foodTypePOJOs) && !CollectionUtils.isEmpty(foodPOJOs)) {
				for (FoodTypePOJO foodTypePOJO : foodTypePOJOs) {
					int foodTypeId = foodTypePOJO.getFoodTypeId();
					for (FoodPOJO foodPOJO : foodPOJOs) {
						int foodTypeId2 = foodPOJO.getFoodTypeId();
						if (foodTypeId == foodTypeId2) {
							foodTypePOJO.getFoodPOJOs().add(foodPOJO);
						}
					}
				}
			}
		}
		ret.addObject("foodSellerPOJO", foodSellerPOJO);
		ret.setViewName("page/food_seller_detail");
		return ret;
	}

	@RequestMapping(value = "/web/foodSellers/search", method = {RequestMethod.GET})
	public ModelAndView findFoodSellerSearchs(@RequestParam(value="keyword", required=false) String keyword) throws Exception {
		ModelAndView ret = new ModelAndView();
		FoodSellerSearchPOJO foodSellerSearchPOJO = new FoodSellerSearchPOJO();
		foodSellerSearchPOJO.setStart(-1);
		foodSellerSearchPOJO.setLimit(-1);
		foodSellerSearchPOJO.setName(keyword);
		List<FoodSellerPOJO> foodSellerPOJOList = new ArrayList<FoodSellerPOJO>();
		foodSellerPOJOList = foodSellerService.finds(foodSellerSearchPOJO);
		//int total = foodSellerService.getCount(foodSellerSearchPOJO);
		ret.addObject("foodSellerPOJOList", foodSellerPOJOList);
		ret.setViewName("page/food_sellers");
		return ret;
	}
	
	@RequestMapping(value = "/web/foodSellers", method = {RequestMethod.GET})
	public ModelAndView findFoodSellers(@RequestParam(value="areaId", required=false) Integer areaId
			, @RequestParam(value="businessId", required=false) Integer businessId
			, @RequestParam(value="sellerTypeId", required=false) Integer sellerTypeId) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<FoodSellerPOJO> foodSellerPOJOList = new ArrayList<FoodSellerPOJO>();
		foodSellerPOJOList = foodSellerService.findsByParam(areaId, businessId, sellerTypeId);
		//int total = foodSellerService.getCount(foodSellerSearchPOJO);
		ret.addObject("foodSellerPOJOList", foodSellerPOJOList);
		ret.setViewName("page/food_sellers");
		return ret;
	}
	
	@RequestMapping(value = "/web/foodSeller", method = {RequestMethod.GET})
	public ModelAndView findFoodSeller(FoodSellerSearchPOJO foodSellerSearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<FoodSellerPOJO> foodSellerPOJOList = new ArrayList<FoodSellerPOJO>();
		foodSellerPOJOList = foodSellerService.finds(foodSellerSearchPOJO);
		//int total = foodSellerService.getCount(foodSellerSearchPOJO);
		ret.addObject("foodSellerPOJOList", foodSellerPOJOList);
		ret.setViewName("page/food_seller");
		return ret;
	}

	@RequestMapping(value = "/web/foodSeller/{foodSellerId}", method = {RequestMethod.GET})
	public ModelAndView findFoodSellerOne(@PathVariable("foodSellerId") Integer foodSellerId) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<FoodPOJO> foodPOJOList = new ArrayList<FoodPOJO>();
		foodPOJOList = foodService.findsByFoodSellerId(foodSellerId);
		ret.addObject("foodPOJOList", foodPOJOList);
		ret.setViewName("page/food_seller_one");
		return ret;
	}
	@RequestMapping(value = "/manager/foodSeller", produces = {MediaType.APPLICATION_JSON_VALUE})
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
	
	@RequestMapping(value = "/manager/foodSeller/add", produces = {MediaType.APPLICATION_JSON_VALUE})
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
	
	@RequestMapping(value = "/manager/foodSeller/update", produces = {MediaType.APPLICATION_JSON_VALUE})
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
	
	@RequestMapping(value = "/manager/foodSeller/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
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

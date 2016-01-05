package com.cobble.takeaway.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	private final static Logger logger = LoggerFactory.getLogger(FoodSellerController.class);
	
	@Autowired
	private FoodSellerService foodSellerService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private MessageSource messageSource;
	
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
				Map<Integer, List<FoodPOJO>> foodMap = new HashMap<Integer, List<FoodPOJO>>();
				for (FoodPOJO foodPOJO : foodPOJOs) {
					int foodTypeId = foodPOJO.getFoodTypeId();
					List<FoodPOJO> foodPOJOTemps = foodMap.get(foodTypeId);
					if (CollectionUtils.isEmpty(foodPOJOTemps)) {
						foodPOJOTemps = new ArrayList<FoodPOJO>();
					}
					foodPOJOTemps.add(foodPOJO);
					foodMap.put(foodTypeId, foodPOJOTemps);
				}
				for (FoodTypePOJO foodTypePOJO : foodTypePOJOs) {
					int foodTypeId = foodTypePOJO.getFoodTypeId();
					List<FoodPOJO> foodPOJOTemps = foodMap.get(foodTypeId);
					foodTypePOJO.setFoodPOJOs(foodPOJOTemps);
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
	
	@RequestMapping(value = "/web/foodSellersPure", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(@RequestParam(value="areaId", required=false) Integer areaId
			, @RequestParam(value="businessId", required=false) Integer businessId
			, @RequestParam(value="sellerTypeId", required=false) Integer sellerTypeId) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<FoodSellerPOJO> foodSellerPOJOList = new ArrayList<FoodSellerPOJO>();
		foodSellerPOJOList = foodSellerService.findsByParam(areaId, businessId, sellerTypeId);
		//int total = foodSellerService.getCount(foodSellerSearchPOJO);
		ret.addObject("foodSellerPOJOList", foodSellerPOJOList);
		ret.setViewName("page/food_sellers_pure");
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
	@RequestMapping(value = "/mgr/foodSeller", produces = {MediaType.APPLICATION_JSON_VALUE})
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
	
	@RequestMapping(value = "/mgr/foodSeller/add", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	@ResponseBody
	public StatusPOJO add(FoodSellerPOJO foodSellerPOJO, @RequestParam("file") MultipartFile file, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (file != null && !file.isEmpty()) {
				logger.info("Uploading logoUrl file: " + file.getOriginalFilename());
				String dir = messageSource.getMessage("files.directory", null, null);
				File dest = new File(dir + File.separator + file.getOriginalFilename());
				file.transferTo(dest);
				logger.info("Upload Success logoUrl file: " + file.getOriginalFilename());
			}
			foodSellerPOJO.setLogoUrl(file.getOriginalFilename());
			
			int result = foodSellerService.insert(foodSellerPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/foodSeller/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(FoodSellerPOJO foodSellerPOJO, @RequestParam("file") MultipartFile file, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (file != null && !file.isEmpty()) {
				logger.info("Uploading logoUrl file: " + file.getOriginalFilename());
				String dir = messageSource.getMessage("files.directory", null, null);
				File dest = new File(dir + File.separator + file.getOriginalFilename());
				file.transferTo(dest);
				logger.info("Upload Success logoUrl file: " + file.getOriginalFilename());
			}
			foodSellerPOJO.setLogoUrl(file.getOriginalFilename());
			int result = foodSellerService.update(foodSellerPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("update error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/foodSeller/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = foodSellerService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("delete error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

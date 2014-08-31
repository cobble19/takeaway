package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.LocationBusinessPOJO;

@Controller
@RequestMapping("/manager")
public class LocationBusinessController extends BaseController {
	
	@RequestMapping(value = "/locationBusiness", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<LocationBusinessPOJO> findLocationBusiness(Model model) {
		ExtjsPOJO<LocationBusinessPOJO> ret = new ExtjsPOJO<LocationBusinessPOJO>();
		List<LocationBusinessPOJO> locationBusinessPOJOList = new ArrayList<LocationBusinessPOJO>();
		LocationBusinessPOJO locationBusinessPOJO = new LocationBusinessPOJO();
		locationBusinessPOJO.setLocationBusinessId(1);
		locationBusinessPOJO.setName("黄潜望");
		locationBusinessPOJO.setDescription("描述信息。。。");
		locationBusinessPOJOList.add(locationBusinessPOJO);
		
		ret.setGridModelList(locationBusinessPOJOList);
		ret.setSuccess(true);
		ret.setTotal(1);
		/*model.addAttribute("locationBusinessPOJOList", locationBusinessPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
}

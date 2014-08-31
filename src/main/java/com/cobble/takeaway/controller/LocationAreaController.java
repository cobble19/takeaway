package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.LocationAreaPOJO;

@Controller
@RequestMapping("/manager")
public class LocationAreaController extends BaseController {
	
	@RequestMapping(value = "/locationArea", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<LocationAreaPOJO> findLocationArea(Model model) {
		ExtjsPOJO<LocationAreaPOJO> ret = new ExtjsPOJO<LocationAreaPOJO>();
		List<LocationAreaPOJO> locationAreaPOJOList = new ArrayList<LocationAreaPOJO>();
		LocationAreaPOJO locationAreaPOJO = new LocationAreaPOJO();
		locationAreaPOJO.setLocationAreaId(1);
		locationAreaPOJO.setName("hefei");
		locationAreaPOJO.setDescription("描述信息。。。");
		locationAreaPOJOList.add(locationAreaPOJO);
		
		ret.setGridModelList(locationAreaPOJOList);
		ret.setSuccess(true);
		ret.setTotal(1);
		/*model.addAttribute("locationAreaPOJOList", locationAreaPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
}

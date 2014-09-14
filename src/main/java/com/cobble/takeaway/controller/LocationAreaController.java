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
import com.cobble.takeaway.pojo.LocationAreaPOJO;
import com.cobble.takeaway.pojo.LocationAreaSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.LocationAreaService;

@Controller
@RequestMapping("/manager")
public class LocationAreaController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(LocationAreaController.class);
	
	@Autowired
	private LocationAreaService locationAreaService;
	
	@RequestMapping(value = "/locationArea", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<LocationAreaPOJO> findLocationArea(LocationAreaSearchPOJO locationAreaSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<LocationAreaPOJO> ret = new ExtjsPOJO<LocationAreaPOJO>();
		List<LocationAreaPOJO> locationAreaPOJOList = new ArrayList<LocationAreaPOJO>();
		locationAreaPOJOList = locationAreaService.finds(locationAreaSearchPOJO);
		int total = locationAreaService.getCount(locationAreaSearchPOJO);
		/*LocationAreaPOJO locationAreaPOJO = new LocationAreaPOJO();
		locationAreaPOJO.setLocationAreaId(1);
		locationAreaPOJO.setName("hefei");
		locationAreaPOJO.setDescription("描述信息。。。");
		locationAreaPOJOList.add(locationAreaPOJO);*/
		
		ret.setGridModelList(locationAreaPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("locationAreaPOJOList", locationAreaPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/locationArea/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(LocationAreaPOJO locationAreaPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = locationAreaService.insert(locationAreaPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

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
import com.cobble.takeaway.pojo.LocationBusinessPOJO;
import com.cobble.takeaway.pojo.LocationBusinessSearchPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.service.LocationBusinessService;

@Controller
@RequestMapping("/manager")
public class LocationBusinessController extends BaseController {
private final static Logger LOGGER = LoggerFactory.getLogger(LocationBusinessController.class);
	
	@Autowired
	private LocationBusinessService locationBusinessService;
	
	@RequestMapping(value = "/locationBusiness", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<LocationBusinessPOJO> findLocationBusiness(LocationBusinessSearchPOJO locationBusinessSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<LocationBusinessPOJO> ret = new ExtjsPOJO<LocationBusinessPOJO>();
		List<LocationBusinessPOJO> locationBusinessPOJOList = new ArrayList<LocationBusinessPOJO>();
		locationBusinessPOJOList = locationBusinessService.finds(locationBusinessSearchPOJO);
		int total = locationBusinessService.getCount(locationBusinessSearchPOJO);
		
		ret.setGridModelList(locationBusinessPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/locationBusiness/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(LocationBusinessPOJO locationBusinessPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = locationBusinessService.insert(locationBusinessPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/locationBusiness/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(LocationBusinessPOJO locationBusinessPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = locationBusinessService.update(locationBusinessPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/locationBusiness/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = locationBusinessService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
}

package com.cobble.takeaway.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.LocationAreaPOJO;
import com.cobble.takeaway.service.TreeService;

@Controller
public class TreeController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(TreeController.class);
	
	@Autowired
	private TreeService treeService;
	
	@RequestMapping(value = "web/tree")
	public ModelAndView tree() throws Exception {
		ModelAndView ret = new ModelAndView();
		List<LocationAreaPOJO> locationAreaPOJOList = treeService.findAllAreas();
		ret.addObject("locationAreaPOJOList", locationAreaPOJOList);
		ret.setViewName("page/tree");
		return ret;
	}
	
}

package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.WxLinkPOJO;
import com.cobble.takeaway.pojo.WxLinkSearchPOJO;
import com.cobble.takeaway.service.WxLinkService;

@Controller
public class WxLinkController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(WxLinkController.class);
	
	@Autowired
	private WxLinkService wxLinkService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping(value = "/web/media/wxLink/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4Web(WxLinkPOJO wxLinkPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxLinkPOJO == null) {
				throw new Exception("wxLinkPOJO can't is NULL.");
			}
			int result = -1;
			if (wxLinkPOJO.getWxLinkId() != null && wxLinkPOJO.getWxLinkId() > 0l) {
				result = wxLinkService.update(wxLinkPOJO);
			} else {
				result = wxLinkService.insert(wxLinkPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/media/wxLink/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxLinkPOJO> listWxLink(WxLinkSearchPOJO wxLinkSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxLinkPOJO> ret = new ExtjsPOJO<WxLinkPOJO>();
		List<WxLinkPOJO> wxLinkPOJOList = new ArrayList<WxLinkPOJO>();
		wxLinkPOJOList = wxLinkService.finds(wxLinkSearchPOJO);
		int total = wxLinkService.getCount(wxLinkSearchPOJO);
		
		ret.setGridModelList(wxLinkPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("wxLinkPOJOList", wxLinkPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxLink", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxLinkPOJO> findWxLink(WxLinkSearchPOJO wxLinkSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxLinkPOJO> ret = new ExtjsPOJO<WxLinkPOJO>();
		List<WxLinkPOJO> wxLinkPOJOList = new ArrayList<WxLinkPOJO>();
		wxLinkPOJOList = wxLinkService.finds(wxLinkSearchPOJO);
		int total = wxLinkService.getCount(wxLinkSearchPOJO);
		
		ret.setGridModelList(wxLinkPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxLinkPOJOList", wxLinkPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxLink/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxLinkPOJO wxLinkPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxLinkService.insert(wxLinkPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxLink/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxLinkPOJO wxLinkPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxLinkService.update(wxLinkPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxLink/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxLinkService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

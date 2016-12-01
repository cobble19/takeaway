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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.service.WxMenuMgrButtonService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxMenuMgrButtonController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMenuMgrButtonController.class);
	
	@Autowired
	private WxMenuMgrButtonService wxMenuMgrButtonService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@RequestMapping(value = "/api/unified/wxMenuMgrButton/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}

			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxMenuMgrButtonPOJO.getWxMenuMgrButtonId() != null) {
				result = wxMenuMgrButtonService.update(wxMenuMgrButtonPOJO);
			} else {
				result = wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/wxMenuMgrButton/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxMenuMgrButtonPOJO.getWxMenuMgrButtonId() != null) {
				result = wxMenuMgrButtonService.update(wxMenuMgrButtonPOJO);
			} else {
				result = wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/web/unified/wxMenuMgrButtonDetail?wxMenuMgrButtonId=" + wxMenuMgrButtonPOJO.getWxMenuMgrButtonId();
		url = "/web/unified/usercenter";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/web/unified/wxMenuMgrButton/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 = wxMenuMgrButtonService.findById(wxMenuMgrButtonPOJO.getWxMenuMgrButtonId());
			ret.addObject("wxMenuMgrButtonPOJO", wxMenuMgrButtonPOJO2);
			ret.setViewName("/page/unified/wx_menu_mgr_button_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgrButton/{wxMenuMgrButtonId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public WxMenuMgrButtonPOJO query(@PathVariable("wxMenuMgrButtonId") Long wxMenuMgrButtonId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WxMenuMgrButtonPOJO ret = new WxMenuMgrButtonPOJO();
		try {
			ret = wxMenuMgrButtonService.findById(wxMenuMgrButtonId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgrButton/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrButtonPOJO> query(WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO) throws Exception {
		DataTablesPOJO<WxMenuMgrButtonPOJO> ret = new DataTablesPOJO<WxMenuMgrButtonPOJO>();
		try {
			List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = wxMenuMgrButtonService.finds(wxMenuMgrButtonSearchPOJO);
			ret.setData(wxMenuMgrButtonPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/wxMenuMgrButton/wxMenuMgrButtonByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrButtonPOJO> queryByUserId(WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO) throws Exception {
		DataTablesPOJO<WxMenuMgrButtonPOJO> ret = new DataTablesPOJO<WxMenuMgrButtonPOJO>();
		Long userId = wxMenuMgrButtonSearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUser().getUserId();
		}
		wxMenuMgrButtonSearchPOJO.setUserId(userId);
		try {
			List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = wxMenuMgrButtonService.finds(wxMenuMgrButtonSearchPOJO);
			ret.setData(wxMenuMgrButtonPOJOs);
		} catch (Exception e) {
			logger.error("wxMenuMgrButtonByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/wxMenuMgrButton", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxMenuMgrButtonPOJO> findWxMenuMgrButton(WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxMenuMgrButtonPOJO> ret = new ExtjsPOJO<WxMenuMgrButtonPOJO>();
		List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOList = new ArrayList<WxMenuMgrButtonPOJO>();
		wxMenuMgrButtonPOJOList = wxMenuMgrButtonService.finds(wxMenuMgrButtonSearchPOJO);
		int total = wxMenuMgrButtonService.getCount(wxMenuMgrButtonSearchPOJO);
		
		ret.setGridModelList(wxMenuMgrButtonPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxMenuMgrButtonPOJOList", wxMenuMgrButtonPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrButton/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrButton/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrButtonService.update(wxMenuMgrButtonPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrButton/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrButtonService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
}

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
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRulePOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRuleSearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrCategoryService;
import com.cobble.takeaway.service.WxMenuMgrMatchRuleService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxMenuMgrMatchRuleController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMenuMgrMatchRuleController.class);
	
	@Autowired
	private WxMenuMgrMatchRuleService wxMenuMgrMatchRuleService;
	@Autowired
	private WxMenuMgrCategoryService wxMenuMgrCategoryService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@RequestMapping(value = "/api/unified/wxMenuMgrMatchRule/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMenuMgrMatchRulePOJO == null) {
				throw new Exception("wxMenuMgrMatchRulePOJO can't is NULL.");
			}

			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxMenuMgrMatchRulePOJO.getWxMenuMgrMatchRuleId() != null) {
				result = wxMenuMgrMatchRuleService.update(wxMenuMgrMatchRulePOJO);
			} else {
				result = wxMenuMgrMatchRuleService.insert(wxMenuMgrMatchRulePOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/wxMenuMgrMatchRule/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMenuMgrMatchRulePOJO == null) {
				throw new Exception("wxMenuMgrMatchRulePOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxMenuMgrMatchRulePOJO.getWxMenuMgrMatchRuleId() != null) {
				result = wxMenuMgrMatchRuleService.update(wxMenuMgrMatchRulePOJO);
			} else {
				result = wxMenuMgrMatchRuleService.insert(wxMenuMgrMatchRulePOJO);
			}
			WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO = new WxMenuMgrCategoryPOJO();
			wxMenuMgrCategoryService.update(wxMenuMgrCategoryPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/web/unified/wxMenuMgrMatchRuleDetail?wxMenuMgrMatchRuleId=" + wxMenuMgrMatchRulePOJO.getWxMenuMgrMatchRuleId();

//		url = "/web/unified/usercenter#wx_menu_mgr_match_rule";
		url = "/page/unified/wx_menu_mgr_match_rule_single.jsp";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/web/unified/wxMenuMgrMatchRule/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (wxMenuMgrMatchRulePOJO == null) {
				throw new Exception("wxMenuMgrMatchRulePOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO2 = wxMenuMgrMatchRuleService.findById(wxMenuMgrMatchRulePOJO.getWxMenuMgrMatchRuleId());
			ret.addObject("wxMenuMgrMatchRulePOJO", wxMenuMgrMatchRulePOJO2);
			ret.setViewName("/page/unified/wx_menu_mgr_match_rule_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgrMatchRule/{wxMenuMgrMatchRuleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public WxMenuMgrMatchRulePOJO query(@PathVariable("wxMenuMgrMatchRuleId") Long wxMenuMgrMatchRuleId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WxMenuMgrMatchRulePOJO ret = new WxMenuMgrMatchRulePOJO();
		try {
			ret = wxMenuMgrMatchRuleService.findById(wxMenuMgrMatchRuleId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgrMatchRule/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrMatchRulePOJO> query(WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO) throws Exception {
		DataTablesPOJO<WxMenuMgrMatchRulePOJO> ret = new DataTablesPOJO<WxMenuMgrMatchRulePOJO>();
		try {
			List<WxMenuMgrMatchRulePOJO> wxMenuMgrMatchRulePOJOs = wxMenuMgrMatchRuleService.finds(wxMenuMgrMatchRuleSearchPOJO);
			ret.setData(wxMenuMgrMatchRulePOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/wxMenuMgrMatchRule/wxMenuMgrMatchRuleByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrMatchRulePOJO> queryByUserId(WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO) throws Exception {
		DataTablesPOJO<WxMenuMgrMatchRulePOJO> ret = new DataTablesPOJO<WxMenuMgrMatchRulePOJO>();
		Long userId = wxMenuMgrMatchRuleSearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUserId();
		}
		wxMenuMgrMatchRuleSearchPOJO.setUserId(userId);
		try {
			List<WxMenuMgrMatchRulePOJO> wxMenuMgrMatchRulePOJOs = wxMenuMgrMatchRuleService.finds(wxMenuMgrMatchRuleSearchPOJO);
			ret.setData(wxMenuMgrMatchRulePOJOs);
		} catch (Exception e) {
			logger.error("wxMenuMgrMatchRuleByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/wxMenuMgrMatchRule", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxMenuMgrMatchRulePOJO> findWxMenuMgrMatchRule(WxMenuMgrMatchRuleSearchPOJO wxMenuMgrMatchRuleSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxMenuMgrMatchRulePOJO> ret = new ExtjsPOJO<WxMenuMgrMatchRulePOJO>();
		List<WxMenuMgrMatchRulePOJO> wxMenuMgrMatchRulePOJOList = new ArrayList<WxMenuMgrMatchRulePOJO>();
		wxMenuMgrMatchRulePOJOList = wxMenuMgrMatchRuleService.finds(wxMenuMgrMatchRuleSearchPOJO);
		int total = wxMenuMgrMatchRuleService.getCount(wxMenuMgrMatchRuleSearchPOJO);
		
		ret.setGridModelList(wxMenuMgrMatchRulePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxMenuMgrMatchRulePOJOList", wxMenuMgrMatchRulePOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrMatchRule/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrMatchRuleService.insert(wxMenuMgrMatchRulePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrMatchRule/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrMatchRuleService.update(wxMenuMgrMatchRulePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrMatchRule/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrMatchRuleService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
}

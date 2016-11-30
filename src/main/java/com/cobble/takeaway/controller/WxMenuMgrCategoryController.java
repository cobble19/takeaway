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
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategorySearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrCategoryService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxMenuMgrCategoryController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMenuMgrCategoryController.class);
	
	@Autowired
	private WxMenuMgrCategoryService wxMenuMgrCategoryService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@RequestMapping(value = "/api/unified/wxMenuMgrCategory/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMenuMgrCategoryPOJO == null) {
				throw new Exception("wxMenuMgrCategoryPOJO can't is NULL.");
			}

			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxMenuMgrCategoryPOJO.getWxMenuMgrCategoryId() != null) {
				result = wxMenuMgrCategoryService.update(wxMenuMgrCategoryPOJO);
			} else {
				result = wxMenuMgrCategoryService.insert(wxMenuMgrCategoryPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/wxMenuMgrCategory/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMenuMgrCategoryPOJO == null) {
				throw new Exception("wxMenuMgrCategoryPOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxMenuMgrCategoryPOJO.getWxMenuMgrCategoryId() != null) {
				result = wxMenuMgrCategoryService.update(wxMenuMgrCategoryPOJO);
			} else {
				result = wxMenuMgrCategoryService.insert(wxMenuMgrCategoryPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/web/unified/wxMenuMgrCategoryDetail?wxMenuMgrCategoryId=" + wxMenuMgrCategoryPOJO.getWxMenuMgrCategoryId();

		url = "/web/unified/usercenter#wx_menu_mgr_category";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/web/unified/wxMenuMgrCategory/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (wxMenuMgrCategoryPOJO == null) {
				throw new Exception("wxMenuMgrCategoryPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO2 = wxMenuMgrCategoryService.findById(wxMenuMgrCategoryPOJO.getWxMenuMgrCategoryId());
			ret.addObject("wxMenuMgrCategoryPOJO", wxMenuMgrCategoryPOJO2);
			ret.setViewName("/page/unified/wx_menu_mgr_category_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgrCategory/{wxMenuMgrCategoryId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public WxMenuMgrCategoryPOJO query(@PathVariable("wxMenuMgrCategoryId") Long wxMenuMgrCategoryId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WxMenuMgrCategoryPOJO ret = new WxMenuMgrCategoryPOJO();
		try {
			ret = wxMenuMgrCategoryService.findById(wxMenuMgrCategoryId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgrCategory/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrCategoryPOJO> query(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception {
		DataTablesPOJO<WxMenuMgrCategoryPOJO> ret = new DataTablesPOJO<WxMenuMgrCategoryPOJO>();
		try {
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.finds(wxMenuMgrCategorySearchPOJO);
			ret.setData(wxMenuMgrCategoryPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/wxMenuMgrCategory/wxMenuMgrCategoryByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrCategoryPOJO> queryByUserId(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO) throws Exception {
		DataTablesPOJO<WxMenuMgrCategoryPOJO> ret = new DataTablesPOJO<WxMenuMgrCategoryPOJO>();
		Long userId = wxMenuMgrCategorySearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUser().getUserId();
		}
		wxMenuMgrCategorySearchPOJO.setUserId(userId);
		try {
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.finds(wxMenuMgrCategorySearchPOJO);
			ret.setData(wxMenuMgrCategoryPOJOs);
		} catch (Exception e) {
			logger.error("wxMenuMgrCategoryByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/wxMenuMgrCategory", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxMenuMgrCategoryPOJO> findWxMenuMgrCategory(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxMenuMgrCategoryPOJO> ret = new ExtjsPOJO<WxMenuMgrCategoryPOJO>();
		List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOList = new ArrayList<WxMenuMgrCategoryPOJO>();
		wxMenuMgrCategoryPOJOList = wxMenuMgrCategoryService.finds(wxMenuMgrCategorySearchPOJO);
		int total = wxMenuMgrCategoryService.getCount(wxMenuMgrCategorySearchPOJO);
		
		ret.setGridModelList(wxMenuMgrCategoryPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxMenuMgrCategoryPOJOList", wxMenuMgrCategoryPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrCategory/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrCategoryService.insert(wxMenuMgrCategoryPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrCategory/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrCategoryService.update(wxMenuMgrCategoryPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrCategory/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrCategoryService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
}

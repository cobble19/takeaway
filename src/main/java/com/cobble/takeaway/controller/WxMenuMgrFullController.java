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
import com.cobble.takeaway.pojo.weixin.WxMenuMgrFullPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrFullSearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrFullService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxMenuMgrFullController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMenuMgrFullController.class);
	
	@Autowired
	private WxMenuMgrFullService wxMenuMgrFullService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@RequestMapping(value = "/api/unified/wxMenuMgrFull/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(WxMenuMgrFullPOJO wxMenuMgrFullPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMenuMgrFullPOJO == null) {
				throw new Exception("wxMenuMgrFullPOJO can't is NULL.");
			}

			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxMenuMgrFullPOJO.getWxMenuMgrFullId() != null) {
				result = wxMenuMgrFullService.update(wxMenuMgrFullPOJO);
			} else {
				result = wxMenuMgrFullService.insert(wxMenuMgrFullPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/wxMenuMgrFull/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(WxMenuMgrFullPOJO wxMenuMgrFullPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxMenuMgrFullPOJO == null) {
				throw new Exception("wxMenuMgrFullPOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxMenuMgrFullPOJO.getWxMenuMgrFullId() != null) {
				result = wxMenuMgrFullService.update(wxMenuMgrFullPOJO);
			} else {
				result = wxMenuMgrFullService.insert(wxMenuMgrFullPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/web/unified/wxMenuMgrFullDetail?wxMenuMgrFullId=" + wxMenuMgrFullPOJO.getWxMenuMgrFullId();

		url = "/web/unified/usercenter#wx_menu_mgr_full";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/web/unified/wxMenuMgrFull/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(WxMenuMgrFullPOJO wxMenuMgrFullPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (wxMenuMgrFullPOJO == null) {
				throw new Exception("wxMenuMgrFullPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			WxMenuMgrFullPOJO wxMenuMgrFullPOJO2 = wxMenuMgrFullService.findById(wxMenuMgrFullPOJO.getWxMenuMgrFullId());
			ret.addObject("wxMenuMgrFullPOJO", wxMenuMgrFullPOJO2);
			ret.setViewName("/page/unified/wx_menu_mgr_full_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgrFull/{wxMenuMgrFullId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public WxMenuMgrFullPOJO query(@PathVariable("wxMenuMgrFullId") Long wxMenuMgrFullId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WxMenuMgrFullPOJO ret = new WxMenuMgrFullPOJO();
		try {
			ret = wxMenuMgrFullService.findById(wxMenuMgrFullId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgrFull/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrFullPOJO> query(WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO) throws Exception {
		DataTablesPOJO<WxMenuMgrFullPOJO> ret = new DataTablesPOJO<WxMenuMgrFullPOJO>();
		try {
			List<WxMenuMgrFullPOJO> wxMenuMgrFullPOJOs = wxMenuMgrFullService.finds(wxMenuMgrFullSearchPOJO);
			ret.setData(wxMenuMgrFullPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/wxMenuMgrFull/wxMenuMgrFullByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrFullPOJO> queryByUserId(WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO) throws Exception {
		DataTablesPOJO<WxMenuMgrFullPOJO> ret = new DataTablesPOJO<WxMenuMgrFullPOJO>();
		Long userId = wxMenuMgrFullSearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUserId();
		}
		wxMenuMgrFullSearchPOJO.setUserId(userId);
		try {
			List<WxMenuMgrFullPOJO> wxMenuMgrFullPOJOs = wxMenuMgrFullService.finds(wxMenuMgrFullSearchPOJO);
			ret.setData(wxMenuMgrFullPOJOs);
		} catch (Exception e) {
			logger.error("wxMenuMgrFullByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/wxMenuMgrFull", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxMenuMgrFullPOJO> findWxMenuMgrFull(WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxMenuMgrFullPOJO> ret = new ExtjsPOJO<WxMenuMgrFullPOJO>();
		List<WxMenuMgrFullPOJO> wxMenuMgrFullPOJOList = new ArrayList<WxMenuMgrFullPOJO>();
		wxMenuMgrFullPOJOList = wxMenuMgrFullService.finds(wxMenuMgrFullSearchPOJO);
		int total = wxMenuMgrFullService.getCount(wxMenuMgrFullSearchPOJO);
		
		ret.setGridModelList(wxMenuMgrFullPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxMenuMgrFullPOJOList", wxMenuMgrFullPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrFull/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxMenuMgrFullPOJO wxMenuMgrFullPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrFullService.insert(wxMenuMgrFullPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrFull/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxMenuMgrFullPOJO wxMenuMgrFullPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrFullService.update(wxMenuMgrFullPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxMenuMgrFull/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxMenuMgrFullService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
}

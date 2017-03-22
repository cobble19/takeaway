package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.service.WxMenuMgrButtonService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxMenuMgrButtonController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMenuMgrButtonController.class);
	
	@Autowired
	private WxMenuMgrButtonService wxMenuMgrButtonService;
	@Autowired
	private WxAuthorizerInfoService wxAuthorizerInfoService;
	
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
//		ModelAndView ret = new ModelAndView();

		HttpSession session = request.getSession();
		try {
			if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			/*HttpSession session = request.getSession();
			String authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			if (StringUtils.isBlank(authorizerAppId)) {
				WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = wxAuthorizerInfoService.findWxAuthorizerInfoByUserId(userId);
				if (wxAuthorizerInfoPOJO != null) {
					authorizerAppId = wxAuthorizerInfoPOJO.getAuthorizerAppId();
				}
			}*/
			
			if (wxMenuMgrButtonPOJO.getWxMenuMgrButtonId() != null) {
				result = wxMenuMgrButtonService.update(wxMenuMgrButtonPOJO);
			} else {
				// if level=1 and count >= 3, can't insert
				// if level=2 and count >= 5, can't insert
				WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO = new WxMenuMgrButtonSearchPOJO();
				wxMenuMgrButtonSearchPOJO.setAuthorizerAppId(wxMenuMgrButtonPOJO.getAuthorizerAppId());
				wxMenuMgrButtonSearchPOJO.setLevel(wxMenuMgrButtonPOJO.getLevel());
				wxMenuMgrButtonSearchPOJO.setWxMenuMgrCategoryId(wxMenuMgrButtonPOJO.getWxMenuMgrCategoryId());
				if (1 != wxMenuMgrButtonPOJO.getLevel()) {
					wxMenuMgrButtonSearchPOJO.setParentButtonId(wxMenuMgrButtonPOJO.getParentButtonId());
				}
				int count = wxMenuMgrButtonService.getCount(wxMenuMgrButtonSearchPOJO);
				
				if (1 == wxMenuMgrButtonPOJO.getLevel() && count >= 3) {
					throw new Exception("本微信公众号:" + wxMenuMgrButtonPOJO.getAuthorizerAppId()
							+ ", 已经有三个一级菜单, 不能添加了!");
				} else if (2 == wxMenuMgrButtonPOJO.getLevel() && count >= 5) {
					throw new Exception("本微信公众号:" + wxMenuMgrButtonPOJO.getAuthorizerAppId() + "一级菜单:" + wxMenuMgrButtonPOJO.getParentButtonId()
							+ ", 已经有五个二级级菜单, 不能添加了!");
				}
				
				result = wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJO);
			}
			ret.setSuccess(true);

			session.setAttribute("wxMenuMgrEntrySuccess", true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			session.setAttribute("wxMenuMgrEntrySuccess", false);
			session.setAttribute("wxMenuMgrEntryMsg", e.getMessage());
			/*ret.addObject("success", false);
			ret.addObject("msg", e.getMessage());*/
			//throw e;
		}
		
		
		String url = "/web/unified/wxMenuMgrButtonDetail?wxMenuMgrButtonId=" + wxMenuMgrButtonPOJO.getWxMenuMgrButtonId();
		url = "/web/unified/usercenter#wx_menu_mgr_entry";

		redirectStrategy.sendRedirect(request, response, url);;
		
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

package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserSearchPOJO;
import com.cobble.takeaway.service.WxPersonUserService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxPersonUserController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxPersonUserController.class);
	
	@Autowired
	private WxPersonUserService wxPersonUserService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@RequestMapping(value = "/api/unified/wxPersonUser/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(WxPersonUserPOJO wxPersonUserPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxPersonUserPOJO == null) {
				throw new Exception("wxPersonUserPOJO can't is NULL.");
			}

			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxPersonUserPOJO.getWxPersonUserId() != null) {
				result = wxPersonUserService.update(wxPersonUserPOJO);
			} else {
				result = wxPersonUserService.insert(wxPersonUserPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/wxPersonUser/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(WxPersonUserPOJO wxPersonUserPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxPersonUserPOJO == null) {
				throw new Exception("wxPersonUserPOJO can't is NULL.");
			}
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxPersonUserPOJO.getWxPersonUserId() != null) {
				result = wxPersonUserService.update(wxPersonUserPOJO);
			} else {
				result = wxPersonUserService.insert(wxPersonUserPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/web/unified/wxPersonUserDetail?wxPersonUserId=" + wxPersonUserPOJO.getWxPersonUserId();

		url = "/web/unified/usercenter#wx_person_user";
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/web/unified/wxPersonUser/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(WxPersonUserPOJO wxPersonUserPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (wxPersonUserPOJO == null) {
				throw new Exception("wxPersonUserPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			WxPersonUserPOJO wxPersonUserPOJO2 = wxPersonUserService.findById(wxPersonUserPOJO.getWxPersonUserId());
			ret.addObject("wxPersonUserPOJO", wxPersonUserPOJO2);
			ret.setViewName("/page/unified/wx_person_user_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxPersonUser/{wxPersonUserId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public WxPersonUserPOJO query(@PathVariable("wxPersonUserId") Long wxPersonUserId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WxPersonUserPOJO ret = new WxPersonUserPOJO();
		try {
			ret = wxPersonUserService.findById(wxPersonUserId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxPersonUser/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxPersonUserPOJO> query(WxPersonUserSearchPOJO wxPersonUserSearchPOJO) throws Exception {
		DataTablesPOJO<WxPersonUserPOJO> ret = new DataTablesPOJO<WxPersonUserPOJO>();
		try {
			List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
			ret.setData(wxPersonUserPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/wxPersonUser/wxPersonUserByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxPersonUserPOJO> queryByUserId(WxPersonUserSearchPOJO wxPersonUserSearchPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTablesPOJO<WxPersonUserPOJO> ret = new DataTablesPOJO<WxPersonUserPOJO>();
		Long userId = wxPersonUserSearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUser().getUserId();
		}
		
		HttpSession session = request.getSession();
		String authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
		wxPersonUserSearchPOJO.setAuthorizerAppId(authorizerAppId);
		
		wxPersonUserSearchPOJO.setPaginationFlage(false);
		
//		wxPersonUserSearchPOJO.setUserId(userId);
		try {
			List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
			ret.setData(wxPersonUserPOJOs);
		} catch (Exception e) {
			logger.error("wxPersonUserByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/wxPersonUser", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxPersonUserPOJO> findWxPersonUser(WxPersonUserSearchPOJO wxPersonUserSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxPersonUserPOJO> ret = new ExtjsPOJO<WxPersonUserPOJO>();
		List<WxPersonUserPOJO> wxPersonUserPOJOList = new ArrayList<WxPersonUserPOJO>();
		wxPersonUserPOJOList = wxPersonUserService.finds(wxPersonUserSearchPOJO);
		int total = wxPersonUserService.getCount(wxPersonUserSearchPOJO);
		
		ret.setGridModelList(wxPersonUserPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxPersonUserPOJOList", wxPersonUserPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxPersonUser/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxPersonUserPOJO wxPersonUserPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxPersonUserService.insert(wxPersonUserPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxPersonUser/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxPersonUserPOJO wxPersonUserPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxPersonUserService.update(wxPersonUserPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxPersonUser/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxPersonUserService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
}

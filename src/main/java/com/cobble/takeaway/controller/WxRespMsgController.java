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
import com.cobble.takeaway.pojo.weixin.WxRespMsgPOJO;
import com.cobble.takeaway.pojo.weixin.WxRespMsgSearchPOJO;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.service.WxRespMsgService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxRespMsgController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxRespMsgController.class);
	
	@Autowired
	private WxRespMsgService wxRespMsgService;
	@Autowired
	private WxAuthorizerInfoService wxAuthorizerInfoService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


	@RequestMapping(value = "/api/unified/wxRespMsg/disable", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO disableWxRespMsgSystem4WebAPI(/*WxRespMsgPOJO wxRespMsgPOJO, */
			Model model, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			String authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			String msgType = CommonConstant.MSG_TYPE_SYSTEM;
			String msgReceive = null;
			String msgSend = null;
			// first delete old/original system keyword
			WxRespMsgPOJO wxRespMsgPOJO = new WxRespMsgPOJO();
			wxRespMsgPOJO.setAuthorizerAppId(authorizerAppId);
			wxRespMsgPOJO.setMsgType(msgType);
			/*wxRespMsgPOJO.setMsgReceive(msgReceive);
			wxRespMsgPOJO.setMsgSend(msgSend);*/
			wxRespMsgPOJO.setUserId(userId);
			
			Integer delRet = wxRespMsgService.delete(wxRespMsgPOJO);
			logger.debug("wxRespMsgService delete size: {}, wxRespMsgPOJO: {}", delRet, wxRespMsgPOJO);
			
			ret.setSuccess(true);
			ret.setErrorCode("DISABLE_SYSTEM");
			ret.setErrorMsg("Disable resp msg system");
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	@RequestMapping(value = "/api/unified/wxRespMsg/enable", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO enableWxRespMsgSystem4WebAPI(/*WxRespMsgPOJO wxRespMsgPOJO, */
			Model model, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			String authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			String msgType = CommonConstant.MSG_TYPE_SYSTEM;
			String msgReceive = null;
			String msgSend = null;
			// first delete old/original system keyword
			WxRespMsgPOJO wxRespMsgPOJO = new WxRespMsgPOJO();
			wxRespMsgPOJO.setAuthorizerAppId(authorizerAppId);
			wxRespMsgPOJO.setMsgType(msgType);
			/*wxRespMsgPOJO.setMsgReceive(msgReceive);
			wxRespMsgPOJO.setMsgSend(msgSend);*/
			wxRespMsgPOJO.setUserId(userId);
			
			Integer delRet = wxRespMsgService.delete(wxRespMsgPOJO);
			logger.debug("wxRespMsgService delete size: {}, wxRespMsgPOJO: {}", delRet, wxRespMsgPOJO);
			
			// insert three system resp msg
			final int RESP_MSG_SYSTEM_COUNT = 3;
			List<String> msgReceives = new ArrayList<String>();
			msgReceives.add("001");
			msgReceives.add("002");
			msgReceives.add("003");
			
			List<String> msgSends = new ArrayList<String>();
			msgSends.add("001");
			msgSends.add("002");
			msgSends.add("003");
			
			for (int i = 0; i < RESP_MSG_SYSTEM_COUNT; i++) {
				wxRespMsgPOJO = new WxRespMsgPOJO();
				msgReceive = msgReceives.get(i);
				msgSend = msgSends.get(i);
				
				wxRespMsgPOJO.setAuthorizerAppId(authorizerAppId);
				wxRespMsgPOJO.setMsgType(msgType);
				wxRespMsgPOJO.setMsgReceive(msgReceive);
				wxRespMsgPOJO.setMsgSend(msgSend);
				wxRespMsgPOJO.setUserId(userId);

				result = wxRespMsgService.insert(wxRespMsgPOJO);
			}
			
			ret.setSuccess(true);
			ret.setErrorCode("ENABLE_SYSTEM");
			ret.setErrorMsg("Enable resp msg system");
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxRespMsg/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(WxRespMsgPOJO wxRespMsgPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxRespMsgPOJO == null) {
				throw new Exception("wxRespMsgPOJO can't is NULL.");
			}

			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			if (wxRespMsgPOJO.getWxRespMsgId() != null) {
				result = wxRespMsgService.update(wxRespMsgPOJO);
			} else {
				result = wxRespMsgService.insert(wxRespMsgPOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/wxRespMsg/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(WxRespMsgPOJO wxRespMsgPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
//		ModelAndView ret = new ModelAndView();

		HttpSession session = request.getSession();
		try {
			if (wxRespMsgPOJO == null) {
				throw new Exception("wxRespMsgPOJO can't is NULL.");
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
			
			if (wxRespMsgPOJO.getWxRespMsgId() != null) {
				result = wxRespMsgService.update(wxRespMsgPOJO);
			} else {
				result = wxRespMsgService.insert(wxRespMsgPOJO);
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
		
		
		String url = "/web/unified/wxRespMsgDetail?wxRespMsgId=" + wxRespMsgPOJO.getWxRespMsgId();
		url = "/web/unified/usercenter#wx_resp_msg";

		redirectStrategy.sendRedirect(request, response, url);;
		
		return null;
	}
	
	@RequestMapping(value = "/web/unified/wxRespMsg/showupdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public ModelAndView showupdate4Web(WxRespMsgPOJO wxRespMsgPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			if (wxRespMsgPOJO == null) {
				throw new Exception("wxRespMsgPOJO can't is NULL.");
			}
			Long userId = UserUtil.getCurrentUserId();
			if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}
			
			WxRespMsgPOJO wxRespMsgPOJO2 = wxRespMsgService.findById(wxRespMsgPOJO.getWxRespMsgId());
			ret.addObject("wxRespMsgPOJO", wxRespMsgPOJO2);
			ret.setViewName("/page/unified/wx_resp_msg_update");
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxRespMsg/{wxRespMsgId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public WxRespMsgPOJO query(@PathVariable("wxRespMsgId") Long wxRespMsgId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WxRespMsgPOJO ret = new WxRespMsgPOJO();
		try {
			ret = wxRespMsgService.findById(wxRespMsgId);
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxRespMsg/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxRespMsgPOJO> query(WxRespMsgSearchPOJO wxRespMsgSearchPOJO) throws Exception {
		DataTablesPOJO<WxRespMsgPOJO> ret = new DataTablesPOJO<WxRespMsgPOJO>();
		try {
			List<WxRespMsgPOJO> wxRespMsgPOJOs = wxRespMsgService.finds(wxRespMsgSearchPOJO);
			ret.setData(wxRespMsgPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/wxRespMsg/wxRespMsgByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<WxRespMsgPOJO> queryByUserId(WxRespMsgSearchPOJO wxRespMsgSearchPOJO) throws Exception {
		DataTablesPOJO<WxRespMsgPOJO> ret = new DataTablesPOJO<WxRespMsgPOJO>();
		Long userId = wxRespMsgSearchPOJO.getUserId();
		if (userId == null || userId <= 0) {
			userId = UserUtil.getCurrentUserId();
		}
		wxRespMsgSearchPOJO.setUserId(userId);
		try {
			List<WxRespMsgPOJO> wxRespMsgPOJOs = wxRespMsgService.finds(wxRespMsgSearchPOJO);
			ret.setData(wxRespMsgPOJOs);
		} catch (Exception e) {
			logger.error("wxRespMsgByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/mgr/wxRespMsg", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxRespMsgPOJO> findWxRespMsg(WxRespMsgSearchPOJO wxRespMsgSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxRespMsgPOJO> ret = new ExtjsPOJO<WxRespMsgPOJO>();
		List<WxRespMsgPOJO> wxRespMsgPOJOList = new ArrayList<WxRespMsgPOJO>();
		wxRespMsgPOJOList = wxRespMsgService.finds(wxRespMsgSearchPOJO);
		int total = wxRespMsgService.getCount(wxRespMsgSearchPOJO);
		
		ret.setGridModelList(wxRespMsgPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxRespMsgPOJOList", wxRespMsgPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxRespMsg/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxRespMsgPOJO wxRespMsgPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxRespMsgService.insert(wxRespMsgPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxRespMsg/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxRespMsgPOJO wxRespMsgPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxRespMsgService.update(wxRespMsgPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxRespMsg/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxRespMsgService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
}

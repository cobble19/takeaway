package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategorySearchPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrButtonReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrMenuInfoRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrSelfMenuInfoButtonRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrSelfMenuInfoRespApiPOJO;
import com.cobble.takeaway.service.WxMenuMgrButtonService;
import com.cobble.takeaway.service.WxMenuMgrCategoryService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.HttpClientUtil;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxMenuMgrController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMenuMgrController.class);
	
	@Autowired
	private WxMenuMgrButtonService wxMenuMgrButtonService;
	@Autowired
	private WxMenuMgrCategoryService wxMenuMgrCategoryService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping(value = "/api/unified/wxMenuMgr/memuinfo")
	@ResponseBody
	public ModelAndView memuinfo4Api(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			
			String authorizerAppId = wxMenuMgrCategorySearchPOJO.getAuthorizerAppId();
			
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new Exception("authorizerAppId can't is NULL.");
			}
			
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/

			String url = HttpRequestUtil.getBase(request) + "/web/wx/third/" + authorizerAppId + "/menu/menuinfo";
			String resp = HttpClientUtil.get(url);
			
			DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO> temp = new DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO>();
			DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO> dataTablesPOJO = JsonUtils.convertToJavaBean(resp, temp.getClass());
			if (dataTablesPOJO == null) {
				ret.addObject("success", false);
				return ret;
			}
			List<WxMenuMgrMenuInfoRespApiPOJO> wxMenuMgrMenuInfoRespApiPOJOs = dataTablesPOJO.getData();
			if (CollectionUtils.isEmpty(wxMenuMgrMenuInfoRespApiPOJOs)) {
				ret.addObject("success", false);
				return ret;
			}
			// only ONE
			WxMenuMgrMenuInfoRespApiPOJO wxMenuMgrMenuInfoRespApiPOJO = wxMenuMgrMenuInfoRespApiPOJOs.get(0);
			if (wxMenuMgrMenuInfoRespApiPOJO == null) {
				ret.addObject("success", false);
				return ret;
			}
			
			WxMenuMgrSelfMenuInfoRespApiPOJO wxMenuMgrSelfMenuInfoRespApiPOJO = wxMenuMgrMenuInfoRespApiPOJO.getSelfMenuInfo();
			List<WxMenuMgrSelfMenuInfoButtonRespApiPOJO> buttonList = wxMenuMgrSelfMenuInfoRespApiPOJO.getButton();
			if (CollectionUtils.isNotEmpty(buttonList)) {
				// add category
				WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO = new WxMenuMgrCategoryPOJO();
				wxMenuMgrCategoryPOJO.setAuthorizerAppId(authorizerAppId);
				wxMenuMgrCategoryPOJO.setName("API获取");
				wxMenuMgrCategoryPOJO.setDescription("API获取");
				wxMenuMgrCategoryService.insert(wxMenuMgrCategoryPOJO);
				Long wxMenuMgrCategoryId = wxMenuMgrCategoryPOJO.getWxMenuMgrCategoryId();
				
				
				for (int i = 0; i < buttonList.size(); i++) {
					WxMenuMgrSelfMenuInfoButtonRespApiPOJO wxMenuMgrSelfMenuInfoButtonRespApiPOJO1 = buttonList.get(i);
					WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO = new WxMenuMgrButtonPOJO();
					wxMenuMgrButtonPOJO.setBtnKey(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getKey());
					wxMenuMgrButtonPOJO.setLevel(CommonConstant.WX_MENU_LEVEL_1);
					wxMenuMgrButtonPOJO.setMediaId(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getMediaId());
					wxMenuMgrButtonPOJO.setMenuId("");
					wxMenuMgrButtonPOJO.setName(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getName());
					wxMenuMgrButtonPOJO.setParentButtonId(-9999L);
					wxMenuMgrButtonPOJO.setType(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getType());
					wxMenuMgrButtonPOJO.setUrl(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getUrl());
					wxMenuMgrButtonPOJO.setValue(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getValue());
					
					wxMenuMgrButtonPOJO.setAuthorizerAppId(authorizerAppId);
					wxMenuMgrButtonPOJO.setWxMenuMgrCategoryId(wxMenuMgrCategoryId);
					wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJO);
					
					Long wxMenuMgrButtonId = wxMenuMgrButtonPOJO.getWxMenuMgrButtonId();
					
					WxMenuMgrSelfMenuInfoButtonRespApiPOJO wxMenuMgrSelfMenuInfoButtonRespApiPOJO2 = wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getSubButton();
					
					if (wxMenuMgrSelfMenuInfoButtonRespApiPOJO2 == null) {
						continue;
					}
					List<WxMenuMgrSelfMenuInfoButtonRespApiPOJO> subButtonList = wxMenuMgrSelfMenuInfoButtonRespApiPOJO2.getList();
					if (CollectionUtils.isEmpty(subButtonList)) {
						continue;
					}
					
					for (int j = 0; j < subButtonList.size(); j++) {
						WxMenuMgrSelfMenuInfoButtonRespApiPOJO wxMenuMgrSelfMenuInfoButtonRespApiPOJOLevel2 = subButtonList.get(j);
						WxMenuMgrButtonPOJO wxMenuMgrButtonPOJOLevel2 = new WxMenuMgrButtonPOJO();
						wxMenuMgrButtonPOJOLevel2.setBtnKey(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getKey());
						wxMenuMgrButtonPOJOLevel2.setLevel(CommonConstant.WX_MENU_LEVEL_2);
						wxMenuMgrButtonPOJOLevel2.setMediaId(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getMediaId());
						wxMenuMgrButtonPOJOLevel2.setMenuId("");
						wxMenuMgrButtonPOJOLevel2.setName(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getName());
						wxMenuMgrButtonPOJOLevel2.setParentButtonId(wxMenuMgrButtonId);
						wxMenuMgrButtonPOJOLevel2.setType(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getType());
						wxMenuMgrButtonPOJOLevel2.setUrl(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getUrl());
						wxMenuMgrButtonPOJOLevel2.setValue(wxMenuMgrSelfMenuInfoButtonRespApiPOJO1.getValue());
						
						wxMenuMgrButtonPOJOLevel2.setAuthorizerAppId(authorizerAppId);
						wxMenuMgrButtonPOJOLevel2.setWxMenuMgrCategoryId(wxMenuMgrCategoryId);
						wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJOLevel2);
					}
					
				}
			}
			
			ret.addObject("success", true);
			ret.addObject("description", resp);
			ret.addObject("dataTablesPOJO", dataTablesPOJO);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.addObject("success", false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgr/publish")
	@ResponseBody
	public ModelAndView publish4Api(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
//			wxMenuMgrCategorySearchPOJO = new WxMenuMgrCategorySearchPOJO();
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.findFull(wxMenuMgrCategorySearchPOJO);
			
			if (CollectionUtils.isEmpty(wxMenuMgrCategoryPOJOs)) {
				ret.addObject("success", false);
				ret.addObject("description", "Not Found WxMenuMgrCategoryPOJOs");
			}

			WxMenuMgrReqApiPOJO wxMenuMgrReqApiPOJO = new WxMenuMgrReqApiPOJO();
			
			List<WxMenuMgrButtonReqApiPOJO> button = new ArrayList<WxMenuMgrButtonReqApiPOJO>();
			
			// only ONE
			WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO = wxMenuMgrCategoryPOJOs.get(0);
			List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = wxMenuMgrCategoryPOJO.getWxMenuMgrButtonPOJOs();
			if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs)) {
				ret.addObject("success", false);
				ret.addObject("description", "Not Found wxMenuMgrButtonPOJOs");
			}
			
			for (int i = 0; i < wxMenuMgrButtonPOJOs.size(); i++) {
				WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO = wxMenuMgrButtonPOJOs.get(i);
				WxMenuMgrButtonReqApiPOJO wxMenuMgrButtonReqApiPOJO = new WxMenuMgrButtonReqApiPOJO();
				
				wxMenuMgrButtonReqApiPOJO.setType(wxMenuMgrButtonPOJO.getType());
				wxMenuMgrButtonReqApiPOJO.setUrl(wxMenuMgrButtonPOJO.getUrl());
				wxMenuMgrButtonReqApiPOJO.setKey(wxMenuMgrButtonPOJO.getBtnKey());
				wxMenuMgrButtonReqApiPOJO.setMediaId(wxMenuMgrButtonPOJO.getMediaId());
				wxMenuMgrButtonReqApiPOJO.setName(wxMenuMgrButtonPOJO.getName());
				List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrButtonPOJO.getWxMenuMgrButtonPOJOs();
				if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
					continue;
				}

				List<WxMenuMgrButtonReqApiPOJO> subButton = new ArrayList<WxMenuMgrButtonReqApiPOJO>();
				
				for (int j = 0; j < wxMenuMgrButtonPOJOs2.size(); j++) {
					WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 = wxMenuMgrButtonPOJOs2.get(i);
					WxMenuMgrButtonReqApiPOJO wxMenuMgrButtonReqApiPOJO2 = new WxMenuMgrButtonReqApiPOJO();
					
					wxMenuMgrButtonReqApiPOJO2.setType(wxMenuMgrButtonPOJO2.getType());
					wxMenuMgrButtonReqApiPOJO2.setUrl(wxMenuMgrButtonPOJO2.getUrl());
					wxMenuMgrButtonReqApiPOJO2.setKey(wxMenuMgrButtonPOJO2.getBtnKey());
					wxMenuMgrButtonReqApiPOJO2.setMediaId(wxMenuMgrButtonPOJO2.getMediaId());
					wxMenuMgrButtonReqApiPOJO2.setName(wxMenuMgrButtonPOJO2.getName());
					subButton.add(wxMenuMgrButtonReqApiPOJO2);
				}
				
				wxMenuMgrButtonReqApiPOJO.setSubButton(subButton);
				button.add(wxMenuMgrButtonReqApiPOJO);
			}
			wxMenuMgrReqApiPOJO.setButton(button);
			
			String url = HttpRequestUtil.getBase(request) + "/web/wx/third/" + wxMenuMgrCategorySearchPOJO.getAuthorizerAppId() + "/menu/create";
			String resp = HttpClientUtil.post(url, JsonUtils.convertToJson(wxMenuMgrReqApiPOJO));
			
			ret.addObject("success", true);
			ret.addObject("description", resp);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.addObject("success", false);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/unified/wxMenuMgr/list")
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrCategoryPOJO> list4Api(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTablesPOJO<WxMenuMgrCategoryPOJO> ret = new DataTablesPOJO<WxMenuMgrCategoryPOJO>();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO = new WxMenuMgrCategorySearchPOJO();
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.findFull(wxMenuMgrCategorySearchPOJO);
			
			ret.setData(wxMenuMgrCategoryPOJOs);
			int size = CollectionUtils.isEmpty(wxMenuMgrCategoryPOJOs) ? 0 : wxMenuMgrCategoryPOJOs.size();
			ret.setRecordsTotal(size);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/unified/wxMenuMgr/list")
	public ModelAndView list4Web(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO = new WxMenuMgrCategorySearchPOJO();
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.findFull(wxMenuMgrCategorySearchPOJO);
			
			ret.addObject("wxMenuMgrCategoryPOJOs", wxMenuMgrCategoryPOJOs);
			ret.setViewName("/page/unified/wx_menu_mgr_inc_do");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}
	
}
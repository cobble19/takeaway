package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategorySearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrEntryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrFullPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrFullSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrMatchRulePOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrButtonReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrButtonRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrButtonsRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrMenuCondDeleteReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrMenuCondMatchRuleReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrMenuCondMatchRuleRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrMenuCondReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrMenuInfoRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrSelfMenuInfoButtonRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrSelfMenuInfoRespApiPOJO;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.service.WxMenuMgrButtonService;
import com.cobble.takeaway.service.WxMenuMgrCategoryService;
import com.cobble.takeaway.service.WxMenuMgrFullService;
import com.cobble.takeaway.service.WxMenuMgrMatchRuleService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.DateUtil;
import com.cobble.takeaway.util.HttpClientUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxMenuMgrController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMenuMgrController.class);
	
	@Autowired
	private WxMenuMgrButtonService wxMenuMgrButtonService;
	@Autowired
	private WxMenuMgrCategoryService wxMenuMgrCategoryService;
	@Autowired
	private WxMenuMgrFullService wxMenuMgrFullService;
	@Autowired
	private WxAuthorizerInfoService wxAuthorizerInfoService;
	@Autowired
	private WxMenuMgrMatchRuleService wxMenuMgrMatchRuleService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/api/unified/wxMenuMgr/{authorizerAppId}/menu/conditional/delete", method = {RequestMethod.POST})
	@ResponseBody
	public Map menuMgrConditionalDelete(/*WxMenuMgrMenuCondDeleteReqApiPOJO wxMenuMgrMenuCondDeleteReqApiPOJO,*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			
			
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new Exception("authorizerAppId can't is NULL.");
			}
			
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/

			String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
					+ "/web/wx/third/" + authorizerAppId + "/menu/conditional/delete";
			
			// test request POJO<->requestBody
			WxMenuMgrMenuCondDeleteReqApiPOJO wxMenuMgrMenuCondDeleteReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxMenuMgrMenuCondDeleteReqApiPOJO.class);
			requestBody = JsonUtils.convertToJson(wxMenuMgrMenuCondDeleteReqApiPOJO);
			
			String resp = HttpClientUtil.postHttpsJson(url, requestBody);
			logger.debug("result: " + result);
			
			BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(resp, BaseWxApiPOJO.class);
			if (baseWxApiPOJO == null) {
				ret.put("success", false);
				return ret;
			}
			
			ret.put("success", true);
			ret.put("description", resp);
			//ret.put("dataTablesPOJO", dataTablesPOJO);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgr/{authorizerAppId}/menu/delete")
	@ResponseBody
	public Map memuDelete4Api(@PathVariable(value="authorizerAppId") String authorizerAppId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			
			
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new Exception("authorizerAppId can't is NULL.");
			}
			
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/

			String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
					+ "/web/wx/third/" + authorizerAppId + "/menu/delete";
			String resp = HttpClientUtil.get(url);
			
			BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(resp, BaseWxApiPOJO.class);
			if (baseWxApiPOJO == null) {
				ret.put("success", false);
				return ret;
			}
			
			ret.put("success", true);
			ret.put("description", resp);
			//ret.put("dataTablesPOJO", dataTablesPOJO);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			throw e;
		}
		
		return ret;
	}
	

	@RequestMapping(value = "/api/unified/wxMenuMgr/entry/list")
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrEntryPOJO> entryList4Api(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTablesPOJO<WxMenuMgrEntryPOJO> ret = new DataTablesPOJO<WxMenuMgrEntryPOJO>();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			HttpSession session = request.getSession();
			String authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			if (StringUtils.isBlank(authorizerAppId)) {
				WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = wxAuthorizerInfoService.findWxAuthorizerInfoByUserId(userId);
				if (wxAuthorizerInfoPOJO != null) {
					authorizerAppId = wxAuthorizerInfoPOJO.getAuthorizerAppId();
				}
			}
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			// Full
			/*WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO = new WxMenuMgrFullSearchPOJO();
//			wxMenuMgrFullSearchPOJO.setUserId(userId);
			wxMenuMgrFullSearchPOJO.setAuthorizerAppId(authorizerAppId);*/
			
//			List<WxMenuMgrFullPOJO> wxMenuMgrFullPOJOs = wxMenuMgrFullService.finds(wxMenuMgrFullSearchPOJO);
			/*if (CollectionUtils.isNotEmpty(wxMenuMgrFullPOJOs)) {
				for (int i = 0; i < wxMenuMgrFullPOJOs.size(); i++) {
					WxMenuMgrFullPOJO wxMenuMgrFullPOJO = wxMenuMgrFullPOJOs.get(i);
					Long wxMenuMgrFullId = wxMenuMgrFullPOJO.getWxMenuMgrFullId();

					// Category
					WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO = new WxMenuMgrCategorySearchPOJO();
					wxMenuMgrCategorySearchPOJO.setWxMenuMgrFullId(wxMenuMgrFullId);
					List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.findFull(wxMenuMgrCategorySearchPOJO);
					wxMenuMgrFullPOJO.setWxMenuMgrCategoryPOJOs(wxMenuMgrCategoryPOJOs);
					if (CollectionUtils.isNotEmpty(wxMenuMgrCategoryPOJOs)) {
						for (WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO : wxMenuMgrCategoryPOJOs) {
							Long wxMenuMgrMatchRuleId = wxMenuMgrCategoryPOJO.getWxMenuMgrMatchRuleId();
							WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO = wxMenuMgrMatchRuleService.findById(wxMenuMgrMatchRuleId);
							wxMenuMgrCategoryPOJO.setWxMenuMgrMatchRulePOJO(wxMenuMgrMatchRulePOJO);
						}
					}
				}
			}*/

			WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO = new WxMenuMgrCategorySearchPOJO();
			wxMenuMgrCategorySearchPOJO.setAuthorizerAppId(authorizerAppId);
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.findFull(wxMenuMgrCategorySearchPOJO);
//			wxMenuMgrFullPOJO.setWxMenuMgrCategoryPOJOs(wxMenuMgrCategoryPOJOs);
			if (CollectionUtils.isNotEmpty(wxMenuMgrCategoryPOJOs)) {
				for (WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO : wxMenuMgrCategoryPOJOs) {
					Long wxMenuMgrMatchRuleId = wxMenuMgrCategoryPOJO.getWxMenuMgrMatchRuleId();
					if (wxMenuMgrMatchRuleId != null) {
						WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO = wxMenuMgrMatchRuleService.findById(wxMenuMgrMatchRuleId);
						wxMenuMgrCategoryPOJO.setWxMenuMgrMatchRulePOJO(wxMenuMgrMatchRulePOJO);
					}
				}
			}
			
			List<WxMenuMgrEntryPOJO> wxMenuMgrEntryPOJOs = new ArrayList<WxMenuMgrEntryPOJO>();
			
			WxMenuMgrEntryPOJO temp = null;
			
			// 生成1级菜单, 2级菜单
			if (CollectionUtils.isNotEmpty(wxMenuMgrCategoryPOJOs)) {
//						for (int j = 0; j < wxMenuMgrCategoryPOJOs.size(); j++) {
					WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO = wxMenuMgrCategoryPOJOs.get(0);
					
					// 保存category, 来保存如果一级菜单不足3个
					temp = new WxMenuMgrEntryPOJO();
//							temp.setWxMenuMgrFullPOJO(wxMenuMgrFullPOJO);
					temp.setWxMenuMgrCategoryPOJO(wxMenuMgrCategoryPOJO);
					
					// 本菜单的所有一级菜单
					List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs1 = wxMenuMgrCategoryPOJO.getWxMenuMgrButtonPOJOs();
					if (!CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs1)) {

						for (int m = 0; m < wxMenuMgrButtonPOJOs1.size(); m++) {
							// 一个一级菜单
							WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO1 = wxMenuMgrButtonPOJOs1.get(m);
							// 本一级菜单的所有二级菜单
							List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs();
							// 没有二级菜单, 放入一级菜单
							if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
								WxMenuMgrEntryPOJO wxMenuMgrEntryPOJO = new WxMenuMgrEntryPOJO();
//										wxMenuMgrEntryPOJO.setWxMenuMgrFullPOJO(wxMenuMgrFullPOJO);
								wxMenuMgrEntryPOJO.setWxMenuMgrCategoryPOJO(wxMenuMgrCategoryPOJO);
								wxMenuMgrEntryPOJO.setLevel1ButtonPOJO(wxMenuMgrButtonPOJO1);
								wxMenuMgrEntryPOJOs.add(wxMenuMgrEntryPOJO);
								continue;
							}
							WxMenuMgrEntryPOJO wxMenuMgrEntryPOJO = new WxMenuMgrEntryPOJO();
//									wxMenuMgrEntryPOJO.setWxMenuMgrFullPOJO(wxMenuMgrFullPOJO);
							wxMenuMgrEntryPOJO.setWxMenuMgrCategoryPOJO(wxMenuMgrCategoryPOJO);
							wxMenuMgrEntryPOJO.setLevel1ButtonPOJO(wxMenuMgrButtonPOJO1);
							for (int n = 0; n < wxMenuMgrButtonPOJOs2.size(); n++) {
								switch (n) {
									case 0: {
										wxMenuMgrEntryPOJO.setLevel2Button1POJO(wxMenuMgrButtonPOJOs2.get(0));
										break;
									}
									case 1: {
										wxMenuMgrEntryPOJO.setLevel2Button2POJO(wxMenuMgrButtonPOJOs2.get(1));
										break;
									}
									case 2: {
										wxMenuMgrEntryPOJO.setLevel2Button3POJO(wxMenuMgrButtonPOJOs2.get(2));
										break;
									}
									case 3: {
										wxMenuMgrEntryPOJO.setLevel2Button4POJO(wxMenuMgrButtonPOJOs2.get(3));
										break;
									}
									case 4: {
										wxMenuMgrEntryPOJO.setLevel2Button5POJO(wxMenuMgrButtonPOJOs2.get(4));
										break;
									}
									default: {
										logger.error("wxMenuMgrButtonPOJOs2 size: {}", wxMenuMgrButtonPOJOs2.size());
										break;
									}
								}
							}
							wxMenuMgrEntryPOJOs.add(wxMenuMgrEntryPOJO);
							
						}
					}
//						}	//end for
			}
					
					
			
			if (wxMenuMgrEntryPOJOs.size() < 3) {
				// 添加备份行
				if (temp == null) {
					WxMenuMgrFullPOJO wxMenuMgrFullPOJO = new WxMenuMgrFullPOJO();
					wxMenuMgrFullPOJO.setAuthorizerAppId(authorizerAppId);
					wxMenuMgrFullPOJO.setName("FullEmptyPlaceHolder");
					wxMenuMgrFullService.insert(wxMenuMgrFullPOJO);
					
					WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO = new WxMenuMgrCategoryPOJO();
					wxMenuMgrCategoryPOJO.setAuthorizerAppId(authorizerAppId);
					wxMenuMgrCategoryPOJO.setName("CategoryEmptyPlaceHolder");
					wxMenuMgrCategoryPOJO.setWxMenuMgrFullId(wxMenuMgrFullPOJO.getWxMenuMgrFullId());
					wxMenuMgrCategoryService.insert(wxMenuMgrCategoryPOJO);
					
					temp = new WxMenuMgrEntryPOJO();
					temp.setWxMenuMgrFullPOJO(wxMenuMgrFullPOJO);
					temp.setWxMenuMgrCategoryPOJO(wxMenuMgrCategoryPOJO);
				}
				for (int i = wxMenuMgrEntryPOJOs.size(); i < 3; i++) {
					wxMenuMgrEntryPOJOs.add(temp);
				}
			}
			
			ret.setData(wxMenuMgrEntryPOJOs);
			int size = CollectionUtils.isEmpty(wxMenuMgrEntryPOJOs) ? 0 : wxMenuMgrEntryPOJOs.size();
			
			
			ret.setRecordsTotal(size);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgr/listFull")
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrFullPOJO> listFull4Api(WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTablesPOJO<WxMenuMgrFullPOJO> ret = new DataTablesPOJO<WxMenuMgrFullPOJO>();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			int result = -1;
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/
			// Full
			WxMenuMgrFullSearchPOJO wxMenuMgrFullSearchPOJO = new WxMenuMgrFullSearchPOJO();
//			wxMenuMgrFullSearchPOJO.setUserId(userId);
			wxMenuMgrFullSearchPOJO.setAuthorizerAppId(wxMenuMgrButtonPOJO.getAuthorizerAppId());
			
			List<WxMenuMgrFullPOJO> wxMenuMgrFullPOJOs = wxMenuMgrFullService.finds(wxMenuMgrFullSearchPOJO);
			if (CollectionUtils.isNotEmpty(wxMenuMgrFullPOJOs)) {
				for (int i = 0; i < wxMenuMgrFullPOJOs.size(); i++) {
					WxMenuMgrFullPOJO wxMenuMgrFullPOJO = wxMenuMgrFullPOJOs.get(i);
					Long wxMenuMgrFullId = wxMenuMgrFullPOJO.getWxMenuMgrFullId();

					// Category
					WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO = new WxMenuMgrCategorySearchPOJO();
					wxMenuMgrCategorySearchPOJO.setWxMenuMgrFullId(wxMenuMgrFullId);
					List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.findFull(wxMenuMgrCategorySearchPOJO);
					wxMenuMgrFullPOJO.setWxMenuMgrCategoryPOJOs(wxMenuMgrCategoryPOJOs);
					if (CollectionUtils.isNotEmpty(wxMenuMgrCategoryPOJOs)) {
						for (WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO : wxMenuMgrCategoryPOJOs) {
							Long wxMenuMgrMatchRuleId = wxMenuMgrCategoryPOJO.getWxMenuMgrMatchRuleId();
							WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO = wxMenuMgrMatchRuleService.findById(wxMenuMgrMatchRuleId);
							wxMenuMgrCategoryPOJO.setWxMenuMgrMatchRulePOJO(wxMenuMgrMatchRulePOJO);
						}
					}
				}
			}
			
			ret.setData(wxMenuMgrFullPOJOs);
			int size = CollectionUtils.isEmpty(wxMenuMgrFullPOJOs) ? 0 : wxMenuMgrFullPOJOs.size();
			ret.setRecordsTotal(size);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgr/menu/get")
	@ResponseBody
	public Map memuGet4Api(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
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

			String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
						+ "/web/wx/third/" + authorizerAppId + "/menu/get";
			String resp = HttpClientUtil.get(url);
			
			WxMenuMgrRespApiPOJO wxMenuMgrRespApiPOJO = JsonUtils.convertToJavaBean(resp, WxMenuMgrRespApiPOJO.class);
			if (wxMenuMgrRespApiPOJO == null) {
				ret.put("success", false);
				return ret;
			}
			
			// 默认菜单
			WxMenuMgrButtonsRespApiPOJO menu = wxMenuMgrRespApiPOJO.getMenu();
			// 个人定制菜单
			List<WxMenuMgrButtonsRespApiPOJO> conditionalMenuList = wxMenuMgrRespApiPOJO.getConditionalMenu();
			if (null == menu) {
				ret.put("success", false);
				return ret;
			}
			
			String nameRandom = DateUtil.toStr(new Date(), "yyyy-MM-dd'T'HHmm");
			
			// insert full
			WxMenuMgrFullPOJO wxMenuMgrFullPOJO = new WxMenuMgrFullPOJO();
			wxMenuMgrFullPOJO.setAuthorizerAppId(authorizerAppId);
			wxMenuMgrFullPOJO.setName("From API Full" + nameRandom);
			wxMenuMgrFullPOJO.setDescription("From API Full");
			wxMenuMgrFullService.insert(wxMenuMgrFullPOJO);
			
			// 默认菜单
			List<WxMenuMgrButtonRespApiPOJO> buttonList = menu.getButton();
			String menuId = menu.getMenuId();
			if (CollectionUtils.isNotEmpty(buttonList)) {
				// add category
				WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO = new WxMenuMgrCategoryPOJO();
				wxMenuMgrCategoryPOJO.setAuthorizerAppId(authorizerAppId);
				wxMenuMgrCategoryPOJO.setName("API获取" + nameRandom);
				wxMenuMgrCategoryPOJO.setDescription("API获取");
				wxMenuMgrCategoryPOJO.setMenuId(menuId);
				wxMenuMgrCategoryPOJO.setWxMenuMgrFullId(wxMenuMgrFullPOJO.getWxMenuMgrFullId());
//				wxMenuMgrCategoryPOJO.setWxMenuMgrMatchRuleId(wxMenuMgrMatchRuleId);
				wxMenuMgrCategoryService.insert(wxMenuMgrCategoryPOJO);
				Long wxMenuMgrCategoryId = wxMenuMgrCategoryPOJO.getWxMenuMgrCategoryId();
				
				for (int i = 0; i < buttonList.size(); i++) {
					WxMenuMgrButtonRespApiPOJO wxMenuMgrButtonRespApiPOJO = buttonList.get(i);
					WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO = new WxMenuMgrButtonPOJO();
					wxMenuMgrButtonPOJO.setBtnKey(wxMenuMgrButtonRespApiPOJO.getKey());
					wxMenuMgrButtonPOJO.setLevel(CommonConstant.WX_MENU_LEVEL_1);
					wxMenuMgrButtonPOJO.setMediaId(wxMenuMgrButtonRespApiPOJO.getMediaId());
					wxMenuMgrButtonPOJO.setMenuId("");
					wxMenuMgrButtonPOJO.setName(wxMenuMgrButtonRespApiPOJO.getName());
					wxMenuMgrButtonPOJO.setParentButtonId(-9999L);
					wxMenuMgrButtonPOJO.setType(wxMenuMgrButtonRespApiPOJO.getType());
					wxMenuMgrButtonPOJO.setUrl(wxMenuMgrButtonRespApiPOJO.getUrl());
					wxMenuMgrButtonPOJO.setValue(wxMenuMgrButtonRespApiPOJO.getValue());
					
					wxMenuMgrButtonPOJO.setAuthorizerAppId(authorizerAppId);
					wxMenuMgrButtonPOJO.setWxMenuMgrCategoryId(wxMenuMgrCategoryId);
					wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJO);
					
					Long wxMenuMgrButtonId = wxMenuMgrButtonPOJO.getWxMenuMgrButtonId();
					
					List<WxMenuMgrButtonRespApiPOJO> wxMenuMgrButtonRespApiPOJOs2 = wxMenuMgrButtonRespApiPOJO.getSubButton();
					
					if (CollectionUtils.isNotEmpty(wxMenuMgrButtonRespApiPOJOs2)) {
						for (int j = 0; j < wxMenuMgrButtonRespApiPOJOs2.size(); j++) {
							WxMenuMgrButtonRespApiPOJO wxMenuMgrButtonRespApiPOJO2 = wxMenuMgrButtonRespApiPOJOs2.get(j);
							WxMenuMgrButtonPOJO wxMenuMgrButtonPOJOLevel2 = new WxMenuMgrButtonPOJO();
							wxMenuMgrButtonPOJOLevel2.setBtnKey(wxMenuMgrButtonRespApiPOJO2.getKey());
							wxMenuMgrButtonPOJOLevel2.setLevel(CommonConstant.WX_MENU_LEVEL_2);
							wxMenuMgrButtonPOJOLevel2.setMediaId(wxMenuMgrButtonRespApiPOJO2.getMediaId());
							wxMenuMgrButtonPOJOLevel2.setMenuId("");
							wxMenuMgrButtonPOJOLevel2.setName(wxMenuMgrButtonRespApiPOJO2.getName());
							wxMenuMgrButtonPOJOLevel2.setParentButtonId(wxMenuMgrButtonId);
							wxMenuMgrButtonPOJOLevel2.setType(wxMenuMgrButtonRespApiPOJO2.getType());
							wxMenuMgrButtonPOJOLevel2.setUrl(wxMenuMgrButtonRespApiPOJO2.getUrl());
							wxMenuMgrButtonPOJOLevel2.setValue(wxMenuMgrButtonRespApiPOJO2.getValue());
							
							wxMenuMgrButtonPOJOLevel2.setAuthorizerAppId(authorizerAppId);
							wxMenuMgrButtonPOJOLevel2.setWxMenuMgrCategoryId(wxMenuMgrCategoryId);
							wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJOLevel2);
						}	// end for
					}	// end if
				}	// end for
			}
			
			// 个人自定义菜单
			if (CollectionUtils.isNotEmpty(conditionalMenuList)) {
				for (int m = 0; m < conditionalMenuList.size(); m++) {
					WxMenuMgrButtonsRespApiPOJO conditionalMenu = conditionalMenuList.get(m);
					buttonList = conditionalMenu.getButton();
					menuId = conditionalMenu.getMenuId();
					WxMenuMgrMenuCondMatchRuleRespApiPOJO wxMenuMgrMenuCondMatchRuleRespApiPOJO = conditionalMenu.getWxMenuMgrMenuCondMatchRuleRespApiPOJO();
					Long wxMenuMgrMatchRuleId = -1L;
					if (null != wxMenuMgrMenuCondMatchRuleRespApiPOJO) {
						WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO = new WxMenuMgrMatchRulePOJO();
						wxMenuMgrMatchRulePOJO.setGroupId(wxMenuMgrMenuCondMatchRuleRespApiPOJO.getGroupId());
						wxMenuMgrMatchRulePOJO.setCountry(wxMenuMgrMenuCondMatchRuleRespApiPOJO.getCountry());
						wxMenuMgrMatchRulePOJO.setProvince(wxMenuMgrMenuCondMatchRuleRespApiPOJO.getProvince());
						wxMenuMgrMatchRulePOJO.setCity(wxMenuMgrMenuCondMatchRuleRespApiPOJO.getCity());
						wxMenuMgrMatchRulePOJO.setSex(wxMenuMgrMenuCondMatchRuleRespApiPOJO.getSex());
						wxMenuMgrMatchRulePOJO.setClientPlatformType(wxMenuMgrMenuCondMatchRuleRespApiPOJO.getClientPlatformType());
						wxMenuMgrMatchRulePOJO.setLanguage(wxMenuMgrMenuCondMatchRuleRespApiPOJO.getLanguage());
						wxMenuMgrMatchRuleService.insert(wxMenuMgrMatchRulePOJO);
						wxMenuMgrMatchRuleId  = wxMenuMgrMatchRulePOJO.getWxMenuMgrMatchRuleId();
					}
					if (CollectionUtils.isNotEmpty(buttonList)) {
						// add category
						WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO = new WxMenuMgrCategoryPOJO();
						wxMenuMgrCategoryPOJO.setAuthorizerAppId(authorizerAppId);
						wxMenuMgrCategoryPOJO.setName("API获取" + nameRandom);
						wxMenuMgrCategoryPOJO.setDescription("API获取");
						wxMenuMgrCategoryPOJO.setMenuId(menuId);
						wxMenuMgrCategoryPOJO.setWxMenuMgrFullId(wxMenuMgrFullPOJO.getWxMenuMgrFullId());
						wxMenuMgrCategoryPOJO.setWxMenuMgrMatchRuleId(wxMenuMgrMatchRuleId);
						wxMenuMgrCategoryService.insert(wxMenuMgrCategoryPOJO);
						Long wxMenuMgrCategoryId = wxMenuMgrCategoryPOJO.getWxMenuMgrCategoryId();
						
						for (int i = 0; i < buttonList.size(); i++) {
							WxMenuMgrButtonRespApiPOJO wxMenuMgrButtonRespApiPOJO = buttonList.get(i);
							WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO = new WxMenuMgrButtonPOJO();
							wxMenuMgrButtonPOJO.setBtnKey(wxMenuMgrButtonRespApiPOJO.getKey());
							wxMenuMgrButtonPOJO.setLevel(CommonConstant.WX_MENU_LEVEL_1);
							wxMenuMgrButtonPOJO.setMediaId(wxMenuMgrButtonRespApiPOJO.getMediaId());
							wxMenuMgrButtonPOJO.setMenuId("");
							wxMenuMgrButtonPOJO.setName(wxMenuMgrButtonRespApiPOJO.getName());
							wxMenuMgrButtonPOJO.setParentButtonId(-9999L);
							wxMenuMgrButtonPOJO.setType(wxMenuMgrButtonRespApiPOJO.getType());
							wxMenuMgrButtonPOJO.setUrl(wxMenuMgrButtonRespApiPOJO.getUrl());
							wxMenuMgrButtonPOJO.setValue(wxMenuMgrButtonRespApiPOJO.getValue());
							
							wxMenuMgrButtonPOJO.setAuthorizerAppId(authorizerAppId);
							wxMenuMgrButtonPOJO.setWxMenuMgrCategoryId(wxMenuMgrCategoryId);
							wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJO);
							
							Long wxMenuMgrButtonId = wxMenuMgrButtonPOJO.getWxMenuMgrButtonId();
							
							List<WxMenuMgrButtonRespApiPOJO> wxMenuMgrButtonRespApiPOJOs2 = wxMenuMgrButtonRespApiPOJO.getSubButton();
							
							if (CollectionUtils.isNotEmpty(wxMenuMgrButtonRespApiPOJOs2)) {
								for (int j = 0; j < wxMenuMgrButtonRespApiPOJOs2.size(); j++) {
									WxMenuMgrButtonRespApiPOJO wxMenuMgrButtonRespApiPOJO2 = wxMenuMgrButtonRespApiPOJOs2.get(j);
									WxMenuMgrButtonPOJO wxMenuMgrButtonPOJOLevel2 = new WxMenuMgrButtonPOJO();
									wxMenuMgrButtonPOJOLevel2.setBtnKey(wxMenuMgrButtonRespApiPOJO2.getKey());
									wxMenuMgrButtonPOJOLevel2.setLevel(CommonConstant.WX_MENU_LEVEL_2);
									wxMenuMgrButtonPOJOLevel2.setMediaId(wxMenuMgrButtonRespApiPOJO2.getMediaId());
									wxMenuMgrButtonPOJOLevel2.setMenuId("");
									wxMenuMgrButtonPOJOLevel2.setName(wxMenuMgrButtonRespApiPOJO2.getName());
									wxMenuMgrButtonPOJOLevel2.setParentButtonId(wxMenuMgrButtonId);
									wxMenuMgrButtonPOJOLevel2.setType(wxMenuMgrButtonRespApiPOJO2.getType());
									wxMenuMgrButtonPOJOLevel2.setUrl(wxMenuMgrButtonRespApiPOJO2.getUrl());
									wxMenuMgrButtonPOJOLevel2.setValue(wxMenuMgrButtonRespApiPOJO2.getValue());
									
									wxMenuMgrButtonPOJOLevel2.setAuthorizerAppId(authorizerAppId);
									wxMenuMgrButtonPOJOLevel2.setWxMenuMgrCategoryId(wxMenuMgrCategoryId);
									wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJOLevel2);
								}	// end for
							}	// end if
						}	// end for
					}
				}	// end for
				
			}	// if
			
			ret.put("success", true);
			ret.put("description", resp);
			//ret.put("dataTablesPOJO", dataTablesPOJO);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgr/menuinfo")
	@ResponseBody
	public Map memuinfo4Api(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
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

			String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
						+ "/web/wx/third/" + authorizerAppId + "/menu/menuinfo";
			String resp = HttpClientUtil.get(url);
			
			DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO> dataTablesPOJO = JsonUtils.convertToJavaBeanDT(resp, WxMenuMgrMenuInfoRespApiPOJO.class);
			if (dataTablesPOJO == null) {
				ret.put("success", false);
				return ret;
			}
			List<WxMenuMgrMenuInfoRespApiPOJO> wxMenuMgrMenuInfoRespApiPOJOs = dataTablesPOJO.getData();
			if (CollectionUtils.isEmpty(wxMenuMgrMenuInfoRespApiPOJOs)) {
				ret.put("success", false);
				return ret;
			}
			// only ONE
			WxMenuMgrMenuInfoRespApiPOJO wxMenuMgrMenuInfoRespApiPOJO = wxMenuMgrMenuInfoRespApiPOJOs.get(0);
			if (wxMenuMgrMenuInfoRespApiPOJO == null) {
				ret.put("success", false);
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
						wxMenuMgrButtonPOJOLevel2.setBtnKey(wxMenuMgrSelfMenuInfoButtonRespApiPOJOLevel2.getKey());
						wxMenuMgrButtonPOJOLevel2.setLevel(CommonConstant.WX_MENU_LEVEL_2);
						wxMenuMgrButtonPOJOLevel2.setMediaId(wxMenuMgrSelfMenuInfoButtonRespApiPOJOLevel2.getMediaId());
						wxMenuMgrButtonPOJOLevel2.setMenuId("");
						wxMenuMgrButtonPOJOLevel2.setName(wxMenuMgrSelfMenuInfoButtonRespApiPOJOLevel2.getName());
						wxMenuMgrButtonPOJOLevel2.setParentButtonId(wxMenuMgrButtonId);
						wxMenuMgrButtonPOJOLevel2.setType(wxMenuMgrSelfMenuInfoButtonRespApiPOJOLevel2.getType());
						wxMenuMgrButtonPOJOLevel2.setUrl(wxMenuMgrSelfMenuInfoButtonRespApiPOJOLevel2.getUrl());
						wxMenuMgrButtonPOJOLevel2.setValue(wxMenuMgrSelfMenuInfoButtonRespApiPOJOLevel2.getValue());
						
						wxMenuMgrButtonPOJOLevel2.setAuthorizerAppId(authorizerAppId);
						wxMenuMgrButtonPOJOLevel2.setWxMenuMgrCategoryId(wxMenuMgrCategoryId);
						wxMenuMgrButtonService.insert(wxMenuMgrButtonPOJOLevel2);
					}
					
				}
			}
			
			ret.put("success", true);
			ret.put("description", resp);
			ret.put("dataTablesPOJO", dataTablesPOJO);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxMenuMgr/publish", method = {RequestMethod.GET})
	@ResponseBody
	public Map publish4Api(WxMenuMgrCategorySearchPOJO wxMenuMgrCategorySearchPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
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
				ret.put("success", false);
				ret.put("description", "Not Found WxMenuMgrCategoryPOJOs");
			}

			// 默认菜单
			WxMenuMgrReqApiPOJO wxMenuMgrReqApiPOJO = new WxMenuMgrReqApiPOJO();
			// 个人定制菜单
			WxMenuMgrMenuCondReqApiPOJO wxMenuMgrMenuCondReqApiPOJO = new WxMenuMgrMenuCondReqApiPOJO();
			
			List<WxMenuMgrButtonReqApiPOJO> button = new ArrayList<WxMenuMgrButtonReqApiPOJO>();
			
			// only ONE
			WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO = wxMenuMgrCategoryPOJOs.get(0);
			
			WxMenuMgrMatchRulePOJO wxMenuMgrMatchRulePOJO = null;
			
			Long wxMenuMgrMatchRuleId = wxMenuMgrCategoryPOJO.getWxMenuMgrMatchRuleId();
			if (wxMenuMgrMatchRuleId != null) {
				wxMenuMgrMatchRulePOJO = wxMenuMgrMatchRuleService.findById(wxMenuMgrMatchRuleId);
				if (wxMenuMgrMatchRulePOJO != null) {
					WxMenuMgrMenuCondMatchRuleReqApiPOJO wxMenuMgrMenuCondMatchRuleReqApiPOJO = new WxMenuMgrMenuCondMatchRuleReqApiPOJO();
					BeanUtils.copyProperties(wxMenuMgrMatchRulePOJO, wxMenuMgrMenuCondMatchRuleReqApiPOJO);
					wxMenuMgrMenuCondMatchRuleReqApiPOJO.setTagId(wxMenuMgrMatchRulePOJO.getGroupId() + "");
					wxMenuMgrMenuCondReqApiPOJO.setWxMenuMgrMenuCondMatchRuleReqApiPOJO(wxMenuMgrMenuCondMatchRuleReqApiPOJO);
					wxMenuMgrMenuCondReqApiPOJO.setButton(button);
				}
			}
			
			List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = wxMenuMgrCategoryPOJO.getWxMenuMgrButtonPOJOs();
			if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs)) {
				ret.put("success", false);
				ret.put("description", "Not Found wxMenuMgrButtonPOJOs");
			}
			
			for (int i = 0; i < wxMenuMgrButtonPOJOs.size(); i++) {
				WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO = wxMenuMgrButtonPOJOs.get(i);
				WxMenuMgrButtonReqApiPOJO wxMenuMgrButtonReqApiPOJO = new WxMenuMgrButtonReqApiPOJO();
				
				/*wxMenuMgrButtonReqApiPOJO.setType(wxMenuMgrButtonPOJO.getType());
				wxMenuMgrButtonReqApiPOJO.setUrl(wxMenuMgrButtonPOJO.getUrl());
				wxMenuMgrButtonReqApiPOJO.setKey(wxMenuMgrButtonPOJO.getBtnKey());*/
				/*对于不同的菜单类型，value的值意义不同。官网上设置的自定义菜单： 
				Text:保存文字到value； 
				Img、voice：保存mediaID到value； 
				Video：保存视频下载链接到value； 
				News：保存图文消息到news_info，同时保存mediaID到value； 
				View：保存链接到url。 使用API设置的自定义菜单： 
				click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、 pic_weixin、location_select：保存值到key；
				view：保存链接到url*/
				List<String> typeMediaIds = Arrays.asList("img", "voice","news");
				
				String type = "text".equalsIgnoreCase(wxMenuMgrButtonPOJO.getType()) ? "click" : wxMenuMgrButtonPOJO.getType();
				
				String mediaId = wxMenuMgrButtonPOJO.getMediaId();
				if (typeMediaIds.contains(wxMenuMgrButtonPOJO.getType())) {
					type = "media_id";
					mediaId = wxMenuMgrButtonPOJO.getValue();
				}
				String url = wxMenuMgrButtonPOJO.getUrl();
				if ("video".equalsIgnoreCase(wxMenuMgrButtonPOJO.getType())) {
					type = "view";
					url = wxMenuMgrButtonPOJO.getValue();
				}
				
				String key = "text".equalsIgnoreCase(wxMenuMgrButtonPOJO.getType()) ? wxMenuMgrButtonPOJO.getValue() : wxMenuMgrButtonPOJO.getBtnKey();
				
				wxMenuMgrButtonReqApiPOJO.setType(type);
				wxMenuMgrButtonReqApiPOJO.setUrl(url);
				wxMenuMgrButtonReqApiPOJO.setKey(key);
				
				wxMenuMgrButtonReqApiPOJO.setMediaId(mediaId);
				wxMenuMgrButtonReqApiPOJO.setName(wxMenuMgrButtonPOJO.getName());
				
				List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrButtonPOJO.getWxMenuMgrButtonPOJOs();
				if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
					// add button withnot level 2 button
					button.add(wxMenuMgrButtonReqApiPOJO);
					continue;
				}

				List<WxMenuMgrButtonReqApiPOJO> subButton = new ArrayList<WxMenuMgrButtonReqApiPOJO>();
				
				for (int j = 0; j < wxMenuMgrButtonPOJOs2.size(); j++) {
					WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 = wxMenuMgrButtonPOJOs2.get(j);
					WxMenuMgrButtonReqApiPOJO wxMenuMgrButtonReqApiPOJO2 = new WxMenuMgrButtonReqApiPOJO();
					type = "text".equalsIgnoreCase(wxMenuMgrButtonPOJO2.getType()) ? "click" : wxMenuMgrButtonPOJO2.getType();
					
					mediaId = wxMenuMgrButtonPOJO2.getMediaId();
					if (typeMediaIds.contains(wxMenuMgrButtonPOJO2.getType())) {
						type = "media_id";
						mediaId = wxMenuMgrButtonPOJO2.getValue();
					}
					url = wxMenuMgrButtonPOJO2.getUrl();
					if ("video".equalsIgnoreCase(wxMenuMgrButtonPOJO2.getType())) {
						type = "view";
						url = wxMenuMgrButtonPOJO2.getValue();
					}
					
					key = "text".equalsIgnoreCase(wxMenuMgrButtonPOJO2.getType()) ? wxMenuMgrButtonPOJO2.getValue() : wxMenuMgrButtonPOJO2.getBtnKey();
					wxMenuMgrButtonReqApiPOJO2.setType(type);
					wxMenuMgrButtonReqApiPOJO2.setUrl(url);
					wxMenuMgrButtonReqApiPOJO2.setKey(key);
					wxMenuMgrButtonReqApiPOJO2.setMediaId(mediaId);
					wxMenuMgrButtonReqApiPOJO2.setName(wxMenuMgrButtonPOJO2.getName());
					subButton.add(wxMenuMgrButtonReqApiPOJO2);
				}
				
				wxMenuMgrButtonReqApiPOJO.setSubButton(subButton);
				// add button contains level 2 button
				button.add(wxMenuMgrButtonReqApiPOJO);
			}
			wxMenuMgrReqApiPOJO.setButton(button);
			
			String url = "";
			String resp = "";
			if (wxMenuMgrMatchRulePOJO == null) {
				url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1" 
						+ "/web/wx/third/" + wxMenuMgrCategorySearchPOJO.getAuthorizerAppId() + "/menu/create";
				resp = HttpClientUtil.postJson(url, JsonUtils.convertToJson(wxMenuMgrReqApiPOJO));
			} else {
				url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1" 
						+ "/web/wx/third/" + wxMenuMgrCategorySearchPOJO.getAuthorizerAppId() + "/menu/conditional/add";
				resp = HttpClientUtil.postJson(url, JsonUtils.convertToJson(wxMenuMgrMenuCondReqApiPOJO));
			}
			
			ret.put("success", true);
			ret.put("description", resp);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
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
			wxMenuMgrCategorySearchPOJO.setAuthorizerAppId(wxMenuMgrButtonPOJO.getAuthorizerAppId());
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

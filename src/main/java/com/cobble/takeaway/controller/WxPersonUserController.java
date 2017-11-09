package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserSearchPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxMenuMgrMenuCondDeleteReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxTagsMgrBatchTaggingReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxUserGetDataRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxUserGetRespApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxUserInfoApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxUserInfoBatchGetReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxUserInfoListBatchGetReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxUserInfoListBatchGetRespApiPOJO;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.service.WxPersonUserService;
import com.cobble.takeaway.spring.security.MyUser;
import com.cobble.takeaway.util.CollectionUtilx;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.HttpClientUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxPersonUserController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxPersonUserController.class);
	
	@Autowired
	private WxPersonUserService wxPersonUserService;
	@Autowired
	private UserService userService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


	@RequestMapping(value = "/api/unified/wxPersonUser/{authorizerAppId}/user/addtag", method = {RequestMethod.POST})
	@ResponseBody
	public Map userAddTag(/*WxMenuMgrMenuCondDeleteReqApiPOJO wxMenuMgrMenuCondDeleteReqApiPOJO,*/
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
			
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/

			String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
					+ "/api/wx/third/" + authorizerAppId + "/tags/batchtagging";
			
			// test request POJO<->requestBody
			WxTagsMgrBatchTaggingReqApiPOJO wxTagsMgrBatchTaggingReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxTagsMgrBatchTaggingReqApiPOJO.class);
			requestBody = JsonUtils.convertToJson(wxTagsMgrBatchTaggingReqApiPOJO);
			
			String result = "";
			result = HttpClientUtil.postHttpsJson(url, requestBody);
			BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(result, BaseWxApiPOJO.class);
			logger.debug("result: " + result);
			
			if (baseWxApiPOJO == null) {
				ret.put("success", false);
				return ret;
			}
			
			ret.put("success", true);
			ret.put("description", result);
			//ret.put("dataTablesPOJO", dataTablesPOJO);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/unified/wxPersonUser/{authorizerAppId}/user/adduserinfos", method = {RequestMethod.GET})
	@ResponseBody
	public Map userAddUserInfos(/*WxMenuMgrMenuCondDeleteReqApiPOJO wxMenuMgrMenuCondDeleteReqApiPOJO,*/
			/*@RequestBody String requestBody
			, */@PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		try {
			/*if (wxMenuMgrButtonPOJO == null) {
				throw new Exception("wxMenuMgrButtonPOJO can't is NULL.");
			}*/
			
			
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new Exception("authorizerAppId can't is NULL.");
			}
			
			Long userId = UserUtil.getCurrentUserId();
			/*if (userId == null) {
				throw new Exception("userId can't is NULL.");
			}*/

			// all userinfos
			WxUserInfoListBatchGetRespApiPOJO wxUserInfoListBatchGetRespApiPOJOTotal = new WxUserInfoListBatchGetRespApiPOJO();
			
			// 获取所有的OPEN IDs
			WxUserGetRespApiPOJO wxUserGetRespApiPOJOTotal = new WxUserGetRespApiPOJO();
			Integer count = 0;
			String nextOpenId = "";
			do {
				String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
						+ "/api/wx/third/" + authorizerAppId + "/user/get"
						+ "?nextOpenId=" + nextOpenId;;
				
				/*// test request POJO<->requestBody
				WxMenuMgrMenuCondDeleteReqApiPOJO wxMenuMgrMenuCondDeleteReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxMenuMgrMenuCondDeleteReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxMenuMgrMenuCondDeleteReqApiPOJO);*/
				
				String resp = HttpClientUtil.get(url);
				
				WxUserGetRespApiPOJO wxUserGetRespApiPOJO = JsonUtils.convertToJavaBean(resp, WxUserGetRespApiPOJO.class);
				int total = wxUserGetRespApiPOJO.getTotal();
				count = wxUserGetRespApiPOJO.getCount();
				nextOpenId = wxUserGetRespApiPOJO.getNextOpenId();
				
				if (count > 0) {
					WxUserGetDataRespApiPOJO data = wxUserGetRespApiPOJOTotal.getData();
					if (data == null) {
						data = new WxUserGetDataRespApiPOJO();
						wxUserGetRespApiPOJOTotal.setData(data);
					}
					List<String> openIds = data.getOpenIds();
					if (CollectionUtils.isEmpty(openIds)) {
						openIds = new ArrayList<String>();
						data.setOpenIds(openIds);
					}
					
					if (wxUserGetRespApiPOJO != null && wxUserGetRespApiPOJO.getData() != null 
							&& CollectionUtils.isNotEmpty(wxUserGetRespApiPOJO.getData().getOpenIds())) {
						openIds.addAll(wxUserGetRespApiPOJO.getData().getOpenIds());
						wxUserGetRespApiPOJOTotal.setTotal(total);
						wxUserGetRespApiPOJOTotal.setCount(wxUserGetRespApiPOJOTotal.getCount() == null ? 0 : wxUserGetRespApiPOJOTotal.getCount() + count);
					}
				}
				
			} while (count > 0 && StringUtils.isNotBlank(nextOpenId));
			
			// 获得user infos
			if (wxUserGetRespApiPOJOTotal != null && wxUserGetRespApiPOJOTotal.getData() != null 
					&& CollectionUtils.isNotEmpty(wxUserGetRespApiPOJOTotal.getData().getOpenIds())) {
				List<String> openIds = wxUserGetRespApiPOJOTotal.getData().getOpenIds();
				
				int size = openIds.size();
				final int STEP = 100;
				int beginIndex = 0;
				int endIndex = beginIndex + STEP;
				
				do {
					if (endIndex >= size) {
						endIndex = size - 1;
					}
					
					String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
							+ "/api/wx/third/" + authorizerAppId + "/user/info/batchget"
							;
					// test request POJO<->requestBody
					WxUserInfoListBatchGetReqApiPOJO wxUserInfoListBatchGetReqApiPOJO = new WxUserInfoListBatchGetReqApiPOJO();
					List<WxUserInfoBatchGetReqApiPOJO> userList = new ArrayList<WxUserInfoBatchGetReqApiPOJO>();
					for (int i = beginIndex; i <= endIndex; i++) {
						WxUserInfoBatchGetReqApiPOJO wxUserInfoBatchGetReqApiPOJO = new WxUserInfoBatchGetReqApiPOJO();
						wxUserInfoBatchGetReqApiPOJO.setOpenId(openIds.get(i));
						wxUserInfoBatchGetReqApiPOJO.setLang("zh_CN");
						
						userList.add(wxUserInfoBatchGetReqApiPOJO);
					}
					wxUserInfoListBatchGetReqApiPOJO.setUserList(userList);
					//WxUserInfoListBatchGetReqApiPOJO wxUserInfoListBatchGetReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxUserInfoListBatchGetReqApiPOJO.class);
					String requestBody = JsonUtils.convertToJson(wxUserInfoListBatchGetReqApiPOJO);
					
					String result = HttpClientUtil.postHttpsJson(url, requestBody);
					//result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
					logger.debug("result: " + result);
					WxUserInfoListBatchGetRespApiPOJO wxUserInfoListBatchGetRespApiPOJO = JsonUtils.convertToJavaBean(result, WxUserInfoListBatchGetRespApiPOJO.class);
					
					List<WxUserInfoApiPOJO> userInfoList = wxUserInfoListBatchGetRespApiPOJOTotal.getUserInfoList();
					if (CollectionUtils.isEmpty(userInfoList)) {
						userInfoList = new ArrayList<WxUserInfoApiPOJO>();
						wxUserInfoListBatchGetRespApiPOJOTotal.setUserInfoList(userInfoList);
					}
					if (wxUserInfoListBatchGetRespApiPOJO != null && CollectionUtils.isNotEmpty(wxUserInfoListBatchGetRespApiPOJO.getUserInfoList())) {
						List<WxUserInfoApiPOJO> currentUserInfos = wxUserInfoListBatchGetRespApiPOJO.getUserInfoList();
						userInfoList.addAll(currentUserInfos);
						
						for (WxUserInfoApiPOJO wxUserInfoApiPOJO : currentUserInfos) {

							// insert into DB
							// insert Wx person user into DB
							UserPOJO userPOJO1 = userService.findUserByName(wxUserInfoApiPOJO.getOpenId());
							UserPOJO userPOJO = new UserPOJO();
							if (userPOJO1 == null) {
								userPOJO.setUsername(wxUserInfoApiPOJO.getOpenId());
								userPOJO.setPassword(wxUserInfoApiPOJO.getOpenId());
								userPOJO.setUserType(MyUser.PERSON);
								userPOJO.setNickname(wxUserInfoApiPOJO.getNickname());
								userService.insert(userPOJO);
							} else {
								BeanUtils.copyProperties(userPOJO1, userPOJO);
							}
							
							WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
//							wxPersonUserSearchPOJO.setUnionId(wxUserInfoApiPOJO.getUnionId());
							wxPersonUserSearchPOJO.setOpenId(wxUserInfoApiPOJO.getOpenId());
							wxPersonUserSearchPOJO.setUserId(userPOJO.getUserId());
							List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
							WxPersonUserPOJO wxPersonUserPOJO = new WxPersonUserPOJO();
							if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
								BeanUtils.copyProperties(wxUserInfoApiPOJO, wxPersonUserPOJO);
								
								List<String> tagidList = wxUserInfoApiPOJO.getTagidList();
								String tagidListStr = "";
								if (!CollectionUtils.isEmpty(tagidList)) {
									tagidListStr = CollectionUtilx.nullSafeToString(tagidList);
								}
								wxPersonUserPOJO.setTagidList(tagidListStr);
								
								wxPersonUserPOJO.setUserId(userPOJO.getUserId());
								wxPersonUserPOJO.setAuthorizerAppId(authorizerAppId);
								wxPersonUserService.insert(wxPersonUserPOJO);
							} else {
								wxPersonUserPOJO = wxPersonUserPOJOs.get(0);
							}
							logger.debug("Get wxPersonUserPOJO: {}", wxPersonUserPOJO);
						}
					}
					
					beginIndex = endIndex + 1;
					endIndex = beginIndex + STEP;
				} while (beginIndex < size);
				
				
			}
			
			if (wxUserInfoListBatchGetRespApiPOJOTotal.getUserInfoList() == null) {
				ret.put("success", false);
				return ret;
			}
			
			ret.put("success", true);
			ret.put("description", wxUserInfoListBatchGetRespApiPOJOTotal.getUserInfoList() == null ? "size: 0" : "size: " + wxUserInfoListBatchGetRespApiPOJOTotal.getUserInfoList().size());
			ret.put("wxUserInfoListBatchGetRespApiPOJOTotal", wxUserInfoListBatchGetRespApiPOJOTotal);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			throw e;
		}
		
		return ret;
	}
	
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

//		url = "/web/unified/usercenter#wx_person_user";
		url = "/page/unified/wx_person_user_single.jsp";
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
			userId = UserUtil.getCurrentUserId();
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

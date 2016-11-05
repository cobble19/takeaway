package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.cobble.takeaway.pojo.weixin.WxMenuMgrButtonSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategoryPOJO;
import com.cobble.takeaway.pojo.weixin.WxMenuMgrCategorySearchPOJO;
import com.cobble.takeaway.service.WxMenuMgrButtonService;
import com.cobble.takeaway.service.WxMenuMgrCategoryService;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxMenuMgrController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxMenuMgrController.class);
	
	@Autowired
	private WxMenuMgrButtonService wxMenuMgrButtonService;
	@Autowired
	private WxMenuMgrCategoryService wxMenuMgrCategoryService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
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
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.finds(wxMenuMgrCategorySearchPOJO);
			
			WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO = new WxMenuMgrButtonSearchPOJO();
			List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = wxMenuMgrButtonService.finds(wxMenuMgrButtonSearchPOJO);
			
			if (CollectionUtils.isNotEmpty(wxMenuMgrCategoryPOJOs) && CollectionUtils.isNotEmpty(wxMenuMgrButtonPOJOs)) {
				// level = 1
				for (WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO2 : wxMenuMgrCategoryPOJOs) {
					for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 : wxMenuMgrButtonPOJOs) {
						String authorizerAppIdCate = wxMenuMgrCategoryPOJO2.getAuthorizerAppId();
						String authorizerAppIdBtn = wxMenuMgrButtonPOJO2.getAuthorizerAppId();
						
						Long wxMenuMgrCategoryId0 = wxMenuMgrCategoryPOJO2.getWxMenuMgrCategoryId();
						Long wxMenuMgrCategoryId1 = wxMenuMgrButtonPOJO2.getWxMenuMgrCategoryId();
						
						int level = wxMenuMgrButtonPOJO2.getLevel();
						Long parentButtonId = wxMenuMgrButtonPOJO2.getParentButtonId();
						
						if (StringUtils.isBlank(authorizerAppIdCate)) {
							continue;
						}
						
						if (wxMenuMgrCategoryId0 == null) {
							continue;
						}
						
						if (authorizerAppIdCate.equalsIgnoreCase(authorizerAppIdBtn)
								&& wxMenuMgrCategoryId0.longValue() == wxMenuMgrCategoryId1) {
							if (CommonConstant.WX_MENU_LEVEL_1 == level) {
								List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrCategoryPOJO2.getWxMenuMgrButtonPOJOs();
								if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
									wxMenuMgrCategoryPOJO2.setWxMenuMgrButtonPOJOs(new ArrayList<WxMenuMgrButtonPOJO>());
								}
								wxMenuMgrCategoryPOJO2.getWxMenuMgrButtonPOJOs().add(wxMenuMgrButtonPOJO2);
							}
						}
						
					}
				}	// end level = 1
				// level = 2
				for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO1 : wxMenuMgrButtonPOJOs) {
					for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 : wxMenuMgrButtonPOJOs) {
						String authorizerAppIdBtn1 = wxMenuMgrButtonPOJO1.getAuthorizerAppId();
						String authorizerAppIdBtn2 = wxMenuMgrButtonPOJO2.getAuthorizerAppId();

						Long wxMenuMgrCategoryId0 = wxMenuMgrButtonPOJO1.getWxMenuMgrCategoryId();
						Long wxMenuMgrCategoryId1 = wxMenuMgrButtonPOJO2.getWxMenuMgrCategoryId();
						
						int level = wxMenuMgrButtonPOJO1.getLevel();
						Long buttonId = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonId();
						Long parentButtonId = wxMenuMgrButtonPOJO2.getParentButtonId();
						
						if (StringUtils.isBlank(authorizerAppIdBtn1)) {
							continue;
						}
						if (wxMenuMgrCategoryId0 == null) {
							continue;
						}
						
						if (authorizerAppIdBtn1.equalsIgnoreCase(authorizerAppIdBtn2)
								&& buttonId.longValue() == parentButtonId && CommonConstant.WX_MENU_LEVEL_1 == level
								&& wxMenuMgrCategoryId0.longValue() == wxMenuMgrCategoryId1) {
							List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs();
							if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
								wxMenuMgrButtonPOJO1.setWxMenuMgrButtonPOJOs(new ArrayList<WxMenuMgrButtonPOJO>());
							}
							wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs().add(wxMenuMgrButtonPOJO2);
						}
						
					}
				}	// end level = 2
			}
			
			ret.addObject("success", true);
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
			
			/*WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO = new WxMenuMgrButtonSearchPOJO();
			List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = wxMenuMgrButtonService.finds(wxMenuMgrButtonSearchPOJO);
			
			if (CollectionUtils.isNotEmpty(wxMenuMgrCategoryPOJOs) && CollectionUtils.isNotEmpty(wxMenuMgrButtonPOJOs)) {
				// level = 1
				for (WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO2 : wxMenuMgrCategoryPOJOs) {
					for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 : wxMenuMgrButtonPOJOs) {
						String authorizerAppIdCate = wxMenuMgrCategoryPOJO2.getAuthorizerAppId();
						String authorizerAppIdBtn = wxMenuMgrButtonPOJO2.getAuthorizerAppId();
						
						Long wxMenuMgrCategoryId0 = wxMenuMgrCategoryPOJO2.getWxMenuMgrCategoryId();
						Long wxMenuMgrCategoryId1 = wxMenuMgrButtonPOJO2.getWxMenuMgrCategoryId();
						
						int level = wxMenuMgrButtonPOJO2.getLevel();
						Long parentButtonId = wxMenuMgrButtonPOJO2.getParentButtonId();
						
						if (StringUtils.isBlank(authorizerAppIdCate)) {
							continue;
						}
						
						if (wxMenuMgrCategoryId0 == null) {
							continue;
						}
						
						if (authorizerAppIdCate.equalsIgnoreCase(authorizerAppIdBtn)
								&& wxMenuMgrCategoryId0.longValue() == wxMenuMgrCategoryId1) {
							if (CommonConstant.WX_MENU_LEVEL_1 == level) {
								List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrCategoryPOJO2.getWxMenuMgrButtonPOJOs();
								if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
									wxMenuMgrCategoryPOJO2.setWxMenuMgrButtonPOJOs(new ArrayList<WxMenuMgrButtonPOJO>());
								}
								wxMenuMgrCategoryPOJO2.getWxMenuMgrButtonPOJOs().add(wxMenuMgrButtonPOJO2);
							}
						}
						
					}
				}	// end level = 1
				// level = 2
				for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO1 : wxMenuMgrButtonPOJOs) {
					for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 : wxMenuMgrButtonPOJOs) {
						String authorizerAppIdBtn1 = wxMenuMgrButtonPOJO1.getAuthorizerAppId();
						String authorizerAppIdBtn2 = wxMenuMgrButtonPOJO2.getAuthorizerAppId();

						Long wxMenuMgrCategoryId0 = wxMenuMgrButtonPOJO1.getWxMenuMgrCategoryId();
						Long wxMenuMgrCategoryId1 = wxMenuMgrButtonPOJO2.getWxMenuMgrCategoryId();
						
						int level = wxMenuMgrButtonPOJO1.getLevel();
						Long buttonId = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonId();
						Long parentButtonId = wxMenuMgrButtonPOJO2.getParentButtonId();
						
						if (StringUtils.isBlank(authorizerAppIdBtn1)) {
							continue;
						}
						if (wxMenuMgrCategoryId0 == null) {
							continue;
						}
						
						if (authorizerAppIdBtn1.equalsIgnoreCase(authorizerAppIdBtn2)
								&& buttonId.longValue() == parentButtonId && CommonConstant.WX_MENU_LEVEL_1 == level
								&& wxMenuMgrCategoryId0.longValue() == wxMenuMgrCategoryId1) {
							List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs();
							if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
								wxMenuMgrButtonPOJO1.setWxMenuMgrButtonPOJOs(new ArrayList<WxMenuMgrButtonPOJO>());
							}
							wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs().add(wxMenuMgrButtonPOJO2);
						}
						
					}
				}	// end level = 2
			}*/
			
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
			List<WxMenuMgrCategoryPOJO> wxMenuMgrCategoryPOJOs = wxMenuMgrCategoryService.finds(wxMenuMgrCategorySearchPOJO);
			
			WxMenuMgrButtonSearchPOJO wxMenuMgrButtonSearchPOJO = new WxMenuMgrButtonSearchPOJO();
			List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs = wxMenuMgrButtonService.finds(wxMenuMgrButtonSearchPOJO);
			
			if (CollectionUtils.isNotEmpty(wxMenuMgrCategoryPOJOs) && CollectionUtils.isNotEmpty(wxMenuMgrButtonPOJOs)) {
				// level = 1
				for (WxMenuMgrCategoryPOJO wxMenuMgrCategoryPOJO2 : wxMenuMgrCategoryPOJOs) {
					for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 : wxMenuMgrButtonPOJOs) {
						String authorizerAppIdCate = wxMenuMgrCategoryPOJO2.getAuthorizerAppId();
						String authorizerAppIdBtn = wxMenuMgrButtonPOJO2.getAuthorizerAppId();
						int level = wxMenuMgrButtonPOJO2.getLevel();
						Long parentButtonId = wxMenuMgrButtonPOJO2.getParentButtonId();
						
						if (StringUtils.isBlank(authorizerAppIdCate)) {
							continue;
						}
						
						if (authorizerAppIdCate.equalsIgnoreCase(authorizerAppIdBtn)) {
							if (CommonConstant.WX_MENU_LEVEL_1 == level) {
								List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrCategoryPOJO2.getWxMenuMgrButtonPOJOs();
								if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
									wxMenuMgrCategoryPOJO2.setWxMenuMgrButtonPOJOs(new ArrayList<WxMenuMgrButtonPOJO>());
								}
								wxMenuMgrCategoryPOJO2.getWxMenuMgrButtonPOJOs().add(wxMenuMgrButtonPOJO2);
							}
						}
						
					}
				}	// end level = 1
				// level = 2
				for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO1 : wxMenuMgrButtonPOJOs) {
					for (WxMenuMgrButtonPOJO wxMenuMgrButtonPOJO2 : wxMenuMgrButtonPOJOs) {
						String authorizerAppIdBtn1 = wxMenuMgrButtonPOJO1.getAuthorizerAppId();
						String authorizerAppIdBtn2 = wxMenuMgrButtonPOJO2.getAuthorizerAppId();
						int level = wxMenuMgrButtonPOJO1.getLevel();
						Long buttonId = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonId();
						Long parentButtonId = wxMenuMgrButtonPOJO2.getParentButtonId();
						
						if (StringUtils.isBlank(authorizerAppIdBtn1)) {
							continue;
						}
						if (authorizerAppIdBtn1.equalsIgnoreCase(authorizerAppIdBtn2)
								&& buttonId.longValue() == parentButtonId && CommonConstant.WX_MENU_LEVEL_1 == level) {
							List<WxMenuMgrButtonPOJO> wxMenuMgrButtonPOJOs2 = wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs();
							if (CollectionUtils.isEmpty(wxMenuMgrButtonPOJOs2)) {
								wxMenuMgrButtonPOJO1.setWxMenuMgrButtonPOJOs(new ArrayList<WxMenuMgrButtonPOJO>());
							}
							wxMenuMgrButtonPOJO1.getWxMenuMgrButtonPOJOs().add(wxMenuMgrButtonPOJO2);
						}
						
					}
				}	// end level = 2
			}
			
			ret.addObject("wxMenuMgrCategoryPOJOs", wxMenuMgrCategoryPOJOs);
			ret.setViewName("/page/unified/wx_menu_mgr_inc_do");
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		
		return ret;
	}
	
}

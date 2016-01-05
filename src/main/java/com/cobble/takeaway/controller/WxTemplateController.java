package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.RelWxTemplateUserPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.WxTemplatePOJO;
import com.cobble.takeaway.pojo.WxTemplateSearchPOJO;
import com.cobble.takeaway.service.WxTemplateService;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class WxTemplateController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxTemplateController.class);
	
	@Autowired
	private WxTemplateService wxTemplateService;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/api/web/media/relWxTemplate/status/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO updateRelWxTempalte4Status(RelWxTemplateUserPOJO relWxTemplateUserPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxTemplateService.updateRelWxTemplateUser4Status(relWxTemplateUserPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/web/media/wxTemplate/byUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxTemplatePOJO> byUserId(WxTemplatePOJO wxTemplatePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExtjsPOJO<WxTemplatePOJO> ret = new ExtjsPOJO<WxTemplatePOJO>();
		try {
			Long userId = UserUtil.getCurrentUser().getUserId();
			WxTemplateSearchPOJO wxTemplateSearchPOJO = new WxTemplateSearchPOJO();
			wxTemplateSearchPOJO.setUserId(userId);
			List<WxTemplatePOJO> wxTemplatePOJOs = wxTemplateService.findsByUserId(wxTemplateSearchPOJO);
			ret.setGridModelList(wxTemplatePOJOs);
			ret.setSuccess(true);
			int total = CollectionUtils.isEmpty(wxTemplatePOJOs) ? 0 : wxTemplatePOJOs.size();
			ret.setTotal(total);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/media/wxTemplate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView showTemplate(@RequestParam(value = "wxTemplateId", required = false) Long wxTemplateId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<WxTemplatePOJO> wxTemplatePOJOs = new ArrayList<WxTemplatePOJO>();
		WxTemplatePOJO wxTemplate = null;
		try {
			wxTemplatePOJOs = wxTemplateService.finds(null);
			if (!CollectionUtils.isEmpty(wxTemplatePOJOs)) {
				if (wxTemplateId != null) {
					for (WxTemplatePOJO wxTemplatePOJO : wxTemplatePOJOs) {
						if (wxTemplatePOJO.getWxTemplateId().longValue() == wxTemplateId) {
							wxTemplatePOJO.setChecked("checked");
							wxTemplate = wxTemplatePOJO;
						}
					}
					if (wxTemplate == null) {
						wxTemplate = wxTemplatePOJOs.get(0);
						wxTemplate.setChecked("checked");
					}
				} else {
					wxTemplate = wxTemplatePOJOs.get(0);
					wxTemplate.setChecked("checked");
				}
			}
			
			
			ret.addObject("wxTemplatePOJOs", wxTemplatePOJOs);
			String viewName = wxTemplate.getWxTemplatePage();
			viewName = viewName.substring(0, viewName.indexOf(".jsp"));
			ret.setViewName(viewName);
			
		} catch (Exception e) {
			logger.error("error: {}", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/web/media/wxTemplate/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebApi(WxTemplatePOJO wxTemplatePOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (wxTemplatePOJO == null) {
				throw new Exception("wxTemplatePOJO can't is NULL.");
			}
			int result = -1;
			if (wxTemplatePOJO.getWxTemplateId() != null && wxTemplatePOJO.getWxTemplateId() > 0L) {
				result = wxTemplateService.update(wxTemplatePOJO);
			} else {
				result = wxTemplateService.insert(wxTemplatePOJO);
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/media/wxTemplate/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxTemplatePOJO> listWxTemplate(WxTemplateSearchPOJO wxTemplateSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxTemplatePOJO> ret = new ExtjsPOJO<WxTemplatePOJO>();
		List<WxTemplatePOJO> wxTemplatePOJOList = new ArrayList<WxTemplatePOJO>();
		wxTemplatePOJOList = wxTemplateService.finds(wxTemplateSearchPOJO);
		int total = wxTemplateService.getCount(wxTemplateSearchPOJO);
		
		ret.setGridModelList(wxTemplatePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		/*model.addAttribute("wxTemplatePOJOList", wxTemplatePOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);*/
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxTemplate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<WxTemplatePOJO> findWxTemplate(WxTemplateSearchPOJO wxTemplateSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<WxTemplatePOJO> ret = new ExtjsPOJO<WxTemplatePOJO>();
		List<WxTemplatePOJO> wxTemplatePOJOList = new ArrayList<WxTemplatePOJO>();
		wxTemplatePOJOList = wxTemplateService.finds(wxTemplateSearchPOJO);
		int total = wxTemplateService.getCount(wxTemplateSearchPOJO);
		
		ret.setGridModelList(wxTemplatePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("wxTemplatePOJOList", wxTemplatePOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxTemplate/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(WxTemplatePOJO wxTemplatePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxTemplateService.insert(wxTemplatePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxTemplate/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(WxTemplatePOJO wxTemplatePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxTemplateService.update(wxTemplatePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/wxTemplate/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = wxTemplateService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}

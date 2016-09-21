package com.cobble.takeaway.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;
import com.cobble.takeaway.service.WxAuthorizerInfoService;

@Controller
public class WxAuthorizerInfoController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WxAuthorizerInfoController.class);
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	WxAuthorizerInfoService wxAuthorizerInfoService;
	
	
	@RequestMapping(value = "/api/wx/authorizer", method = {RequestMethod.GET})
	@ResponseBody
	public WxAuthorizerInfoPOJO authorizer(WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception {
		WxAuthorizerInfoPOJO ret = null;
		try {
			List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
			if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
				ret = wxAuthorizerInfoPOJOs.get(0);
			}
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	} 
}

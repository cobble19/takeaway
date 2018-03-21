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

import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderSearchPOJO;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;

@Controller
public class WpOrderController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(WpOrderController.class);
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	WpOrderService wpOrderService;
	
	
	@RequestMapping(value = "/api/wx/wxpay/unifiedorder", method = {RequestMethod.GET})
	@ResponseBody
	public WpOrderPOJO unifiedOrder(WpOrderSearchPOJO wpOrderSearchPOJO) throws Exception {
		WpOrderPOJO ret = null;
		try {
			List<WpOrderPOJO> wpOrderPOJOs = wpOrderService.finds(wpOrderSearchPOJO);
			if (!CollectionUtils.isEmpty(wpOrderPOJOs)) {
				ret = wpOrderPOJOs.get(0);
			}
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	} 
}

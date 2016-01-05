package com.cobble.takeaway.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobble.takeaway.pojo.HtmlConvertedPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.util.HttpClientUtil;

@Controller
public class CommonController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/web/common/toHtml", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HtmlConvertedPOJO toHtml(@RequestParam(value = "fromFullUrl", required = true) String fromFullUrl,
			@RequestParam(value = "toFilePath", required = false) String toFilePath, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HtmlConvertedPOJO ret = new HtmlConvertedPOJO();
		try {
			String resp = HttpClientUtil.get(fromFullUrl);
			String dir = messageSource.getMessage("files.directory", null, null);
			File dest = new File(dir + File.separator + "htmls" + File.separator + toFilePath);
			FileUtils.writeStringToFile(dest, resp, "UTF-8");
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("toHtml error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
}

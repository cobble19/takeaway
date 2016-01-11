package com.cobble.takeaway.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cobble.takeaway.util.DateUtil;

@Controller
public class MyHtmlEditorController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(MyHtmlEditorController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	
	@RequestMapping(value = "/htmleditor/pic/add", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	@ResponseBody
	public Map add(@RequestParam("pic") MultipartFile file, Model model) throws Exception {
		Map ret = new HashMap();
		try {
			if (file != null && !file.isEmpty()) {
				logger.info("Uploading Html Editor file: " + file.getOriginalFilename());
				
				String fileName = DateUtil.toStr(new Date(), "yyyyMMddHHmmss.SSS")
						+ "." + RandomStringUtils.randomAlphabetic(5)
						+ "." + file.getOriginalFilename();
				
				String dir = messageSource.getMessage("files.directory", null, null);
				File dest = new File(dir + File.separator + "images" + File.separator + fileName);
				file.transferTo(dest);
				logger.info("Upload Success Html Editor file: " + dest.getName());
				ret.put("file_url", "files/images" + "/" + fileName);
			}
			
			ret.put("success", true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.put("success", false);
			throw e;
		}
		
		return ret;
	}
	
}

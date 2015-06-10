package com.cobble.takeaway.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

import com.cobble.takeaway.pojo.FoodSellerPOJO;

@Controller
public class MyHtmlEditorController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MyHtmlEditorController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	
	@RequestMapping(value = "/htmleditor/pic/add", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	@ResponseBody
	public Map add(@RequestParam("pic") MultipartFile file, Model model) throws Exception {
		Map ret = new HashMap();
		try {
			if (file != null && !file.isEmpty()) {
				LOGGER.info("Uploading Html Editor file: " + file.getOriginalFilename());
				String dir = messageSource.getMessage("files.directory", null, null);
				File dest = new File(dir + File.separator + file.getOriginalFilename());
				file.transferTo(dest);
				LOGGER.info("Upload Success Html Editor file: " + file.getOriginalFilename());
				ret.put("file_url", file.getOriginalFilename());
			}
			
			ret.put("success", true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.put("success", false);
			throw e;
		}
		
		return ret;
	}
	
}

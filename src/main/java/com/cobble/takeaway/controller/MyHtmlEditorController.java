package com.cobble.takeaway.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cobble.takeaway.pojo.FileUploadPOJO;
import com.cobble.takeaway.pojo.RelWxIndexMapPOJO;
import com.cobble.takeaway.pojo.RelWxIndexMapSearchPOJO;
import com.cobble.takeaway.service.RelWxIndexMapService;
import com.cobble.takeaway.util.DateUtil;
import com.cobble.takeaway.util.FileUtil;

@Controller
public class MyHtmlEditorController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(MyHtmlEditorController.class);
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private RelWxIndexMapService relWxIndexMapService;
	
	
	@RequestMapping(value = "/htmleditor/pic/add", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	@ResponseBody
	public Map add(@RequestParam("pic") MultipartFile file, @RequestParam("userId") Long userId
			, @RequestParam(value = "wxTemplateId", required = false) Long wxTemplateId, @RequestParam(value = "orderNo", required = false) Integer orderNo, Model model) throws Exception {
		Map ret = new HashMap();
		try {
//			MultipartFile file = fileUploadPOJO.getFile();
			if (file != null && !file.isEmpty()) {
				logger.info("Uploading Html Editor file: " + file.getOriginalFilename());
				String ext = FilenameUtils.getExtension(file.getOriginalFilename());
				/*String fileName = DateUtil.toStr(new Date(), "yyyyMMddHHmmss.SSS")
						+ "." + RandomStringUtils.randomAlphabetic(5)
						+ "." + ext;*/
				String fileName = "";
				if (userId != null && wxTemplateId != null
						&& orderNo != null) {
					fileName = userId + "_"
							+ wxTemplateId + "_"
							+ orderNo + "_"
							+ "." + ext;
				} else {
					fileName = DateUtil.toStr(new Date(), "yyyyMMddHHmmss.SSS")
							+ "." + RandomStringUtils.randomAlphabetic(5)
							+ "." + ext;
				}
				
				String wxIndexCode = "notfounduser";
				if (userId != null) {
					RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO = new RelWxIndexMapSearchPOJO();
					relWxIndexMapSearchPOJO.setUserId(userId);
					List<RelWxIndexMapPOJO> relWxIndexMapPOJOs = relWxIndexMapService.finds(relWxIndexMapSearchPOJO);
					if (CollectionUtils.isNotEmpty(relWxIndexMapPOJOs)) {
						wxIndexCode = relWxIndexMapPOJOs.get(0).getWxIndexCode();
					}
				}
				
				String dir = messageSource.getMessage("files.directory", null, null);
				File dest = FileUtil.createFile(dir + File.separator + "images" + File.separator + wxIndexCode + File.separator + fileName);
				file.transferTo(dest);
				logger.info("Upload Success Html Editor file: " + dest.getName());
				ret.put("file_url", "files/images" + "/" + wxIndexCode + "/" + fileName);
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

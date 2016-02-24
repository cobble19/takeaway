package com.cobble.takeaway.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.cobble.takeaway.pojo.HtmlConvertedPOJO;

public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
//	private static MessageSource messageSource = (MessageSource) BeanUtil.get("messageSource");
	
	public static HtmlConvertedPOJO url2Html(String fromFullUrl, String toFileFullPath) {
		HtmlConvertedPOJO ret = null;
		try {
			String resp = HttpClientUtil.get(fromFullUrl);
			
			toFileFullPath = toFileFullPath.replace("/", File.separator);
			
			logger.info("fromFullUrl: {}, toFileFullPath: {}", fromFullUrl, toFileFullPath);
			
			File dest = new File(toFileFullPath);
			if (!dest.exists()) {
				dest.createNewFile();
			}
			FileUtils.writeStringToFile(dest, resp, "UTF-8");
			ret = new HtmlConvertedPOJO();
			ret.setSuccess(true);
			ret.setFromFullUrl(fromFullUrl);
			ret.setToFilePath(toFileFullPath);
		} catch (Exception e) {
			logger.error("toHtml error: {}", e);
			ret.setSuccess(false);
		}
		
		return ret;
	}
	
	public static HtmlConvertedPOJO url2Html(String fromFullUrl, String toFileFullPath, String serverName, String nickname) {
		HtmlConvertedPOJO ret = null;
		try {
			String resp = HttpClientUtil.get(fromFullUrl);
			
			resp = resp.replace("127.0.0.1", serverName);
			resp = resp.replace("<title>得味驿站</title>", "<title>" + nickname + "</title>");
			
			toFileFullPath = toFileFullPath.replace("/", File.separator);
			
			logger.info("fromFullUrl: {}, toFileFullPath: {}", fromFullUrl, toFileFullPath);
			
			File dest = new File(toFileFullPath);
			if (!dest.exists()) {
				dest.createNewFile();
			}
			FileUtils.writeStringToFile(dest, resp, "UTF-8");
			ret = new HtmlConvertedPOJO();
			ret.setSuccess(true);
			ret.setFromFullUrl(fromFullUrl);
			ret.setToFilePath(toFileFullPath);
		} catch (Exception e) {
			logger.error("toHtml error: {}", e);
			ret.setSuccess(false);
		}
		
		return ret;
	}
}

package com.cobble.takeaway.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobble.takeaway.pojo.HtmlConvertedPOJO;

public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
//	private static MessageSource messageSource = (MessageSource) BeanUtil.get("messageSource");
	
	/**
	 * 
	 * @param fromFullUrl 
	 * @param toDir /opt/app/www/files
	 * @param toFilePath	wx/authorizer/qrcode
	 * @return
	 */
	public static HtmlConvertedPOJO url2File(String fromFullUrl, String toDir, String toFilePath) {
		HtmlConvertedPOJO ret = new HtmlConvertedPOJO();
		try {

			CloseableHttpClient httpclient = HttpClients.createDefault();

            String toFileFullPath = toDir + File.separator + toFilePath;
            String htmlPath = "files" + "/" + toFilePath.replace("\\", "/");
	        try {
	            HttpGet httpget = new HttpGet(fromFullUrl);

	            logger.info("Executing request " + httpget.getRequestLine());
	            
	            CloseableHttpResponse response = httpclient.execute(httpget);
	            
	            logger.debug("Response: {}", response);
	            
	            int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();
                    OutputStream os = new FileOutputStream(toFileFullPath);
                    IOUtils.copy(is, os);
                    IOUtils.closeQuietly(os);
                    IOUtils.closeQuietly(is);
                    EntityUtils.consumeQuietly(entity);

        			toFileFullPath = toFileFullPath.replace("/", File.separator);
        			ret = new HtmlConvertedPOJO();
        			ret.setSuccess(true);
        			ret.setFromFullUrl(fromFullUrl);
        			ret.setToFilePath(toFileFullPath);
        			ret.setHtmlPath(htmlPath);
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
	        } catch (ClientProtocolException e) {
	        	logger.error("ClientProtocolException: {}", e);
//	        	throw e;
			} catch (IOException e) {
	        	logger.error("IOException: {}", e);
//	        	throw e;
			} finally {
	            try {
					httpclient.close();
				} catch (IOException e) {
		        	logger.error("httpclient.close IOException: {}", e.getMessage());
				}
	        }			
		} catch (Exception e) {
			logger.error("toHtml error: {}", e);
			ret.setSuccess(false);
		}
		return ret;
	}
	
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

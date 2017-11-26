package com.cobble.takeaway.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;















import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenReqApiPOJO;

public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static String get(String url) throws Exception {
		if (StringUtils.isNotBlank(url)) {
			if (url.contains("?10000skip=true")) {
				url = url.replace("?10000skip=true", "");
			}
			if (url.contains("10000skip=true")) {
				url = url.replace("10000skip=true", "");
			}
		}
		
		String ret = "";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);

            logger.info("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            ret = responseBody;
            logger.debug("url: {}, responseBody: {}", url, responseBody);
        } catch (ClientProtocolException e) {
        	logger.error("url: {}, ClientProtocolException: {}", url, e);
//        	throw e;
		} catch (IOException e) {
        	logger.error("url: {}, IOException: {}", url, e);
//        	throw e;
		} finally {
            try {
				httpclient.close();
			} catch (IOException e) {
	        	logger.error("url: {}, httpclient.close IOException: {}", url, e.getMessage());
			}
        }
		return ret;
	}
	
	public static String postJson(String url, String requestBody) throws Exception {
		String ret = "";
		try {
			ret = post(url, requestBody, ContentType.APPLICATION_JSON);
		} catch (Exception e) {
			logger.error("url: {}, postJson: {}", url, e);
		}
		return ret;
	}
	
	public static String post(String url, String requestBody) throws Exception {
		
		String ret = "";
		try {
			ret = post(url, requestBody, null);
		} catch (Exception e) {
			logger.error("post url exception: {}", e);
		}
		return ret;
	}
	
	public static String post(String url, String requestBody, ContentType contentType) throws Exception {
		if (StringUtils.isNotBlank(url)) {
			if (url.contains("?10000skip=true")) {
				url = url.replace("?10000skip=true", "");
			}
			if (url.contains("10000skip=true")) {
				url = url.replace("10000skip=true", "");
			}
		}
		
		String ret = "";
		logger.info("Params, Url: {}, RequestBody: {}, ContentType: {}", url, requestBody, contentType);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
			logger.info("Executing request " + httppost.getRequestLine());

			StringEntity reqEntity = new StringEntity(requestBody, contentType);
			reqEntity.setChunked(true);

			httppost.setEntity(reqEntity);

			System.out.println("Executing request: "
					+ httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
	            logger.info("url: {}, Response Status: {}", url, response.getStatusLine());
				String responseBody = EntityUtils.toString(response.getEntity());
				ret = responseBody;
				logger.info("url: {}, Response Body: {}", url, responseBody);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return ret;
	}
	
	public static String postHttpsJson(String url, String requestBody) throws Exception {
		String ret = "";
		ret = postHttps(url, requestBody, ContentType.APPLICATION_JSON);
		return ret;
	}
	
	public static String postHttps(String url, String requestBody, ContentType contentType) throws Exception {
		if (StringUtils.isNotBlank(url)) {
			if (url.contains("?10000skip=true")) {
				url = url.replace("?10000skip=true", "");
			}
			if (url.contains("10000skip=true")) {
				url = url.replace("10000skip=true", "");
			}
		}
		
		String ret = "";
		logger.info("Params, Url: {}, RequestBody: {}, ContentType: {}", url, requestBody, contentType);
		CloseableHttpClient httpclient = getHttpsClient();
		CloseableHttpResponse response = null;
		try {
			HttpPost httppost = new HttpPost(url);
			logger.info("Executing request: " + httppost.getRequestLine());

			StringEntity reqEntity = new StringEntity(requestBody, contentType);
//			reqEntity.setChunked(true);

			httppost.setEntity(reqEntity);

			response = httpclient.execute(httppost);
            logger.info("url: {}, Response Status: {}", url, response.getStatusLine());
            Header[] headers = response.getAllHeaders();
    			StringBuilder headerSb = new StringBuilder();
            if (ArrayUtils.isNotEmpty(headers)) {
	            	for (Header header : headers) {
	            		headerSb.append(header.getName());
	            		headerSb.append(" = ");
	            		headerSb.append(header.getValue());
	            		headerSb.append(", ");
	            	}
            }
            
			String responseBody = EntityUtils.toString(response.getEntity());
			ret = responseBody;
			logger.info("url: {}, Headers: {}, Response Body: {}", url, headerSb.toString(), responseBody);
		} finally {
			try {
				response.close();
				httpclient.close();
			} catch (Exception e) {
				logger.error("close response/client: {}", e);
			}
		}
		return ret;
	}
	
	public static CloseableHttpClient getHttpsClient()  {
        SSLContext sslcontext = null;
        CloseableHttpClient httpclient = null;
		try {
			sslcontext = SSLContexts.custom()
			        .loadTrustMaterial(new TrustStrategy() {
						
						@Override
						public boolean isTrusted(X509Certificate[] chain, String authType)
								throws CertificateException {
							return true;
						}
					})
			        .build();

	        // Allow TLSv1 protocol only
	        /*SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.getDefaultHostnameVerifier());*/
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
	        httpclient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException: {}", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException: {}", e);
		} catch (KeyStoreException e) {
			logger.error("KeyStoreException: {}", e);
		} catch (Exception e) {
			logger.error("Exception: {}", e);
		}
        
        return httpclient;
	}
	
	public static void main(String[] argv) {
		try {
//			String result = HttpClientUtil.get("https://www.google.com.hk");
			WxComAccessTokenReqApiPOJO wxComAccessTokenReqPOJO = new WxComAccessTokenReqApiPOJO();
			wxComAccessTokenReqPOJO.setComponentAppId("wx2bec8614a6c47443");
			wxComAccessTokenReqPOJO.setComponentAppSecret("ed898841d710be0ec6cd7bf19e5f7ced");
			wxComAccessTokenReqPOJO.setComponentVerifyTicket("ticket@@@5jyb50N-eJvcxNBZMlVf2IQmof7xWB1wA27s3ETFTm5MrdtE67mjFne96IjrbtDlxiArfhvaUB0XsLwtD3nQkg");
			String result = HttpClientUtil.postHttpsJson("https://api.weixin.qq.com/cgi-bin/component/api_component_token", 
						JsonUtils.convertToJson(wxComAccessTokenReqPOJO));
		} catch (Exception e) {
			logger.error("{}", e);
		}
	}
}

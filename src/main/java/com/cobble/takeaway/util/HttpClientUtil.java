package com.cobble.takeaway.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static String get(String url) throws Exception {
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
            logger.debug("----------------------------------------");
            logger.debug(responseBody);
        } catch (ClientProtocolException e) {
        	logger.error("ClientProtocolException: {}", e);
        	throw e;
		} catch (IOException e) {
        	logger.error("IOException: {}", e);
        	throw e;
		} finally {
            try {
				httpclient.close();
			} catch (IOException e) {
	        	logger.error("httpclient.close IOException: {}", e.getMessage());
			}
        }
		return ret;
	}
	
	public static void main(String[] argv) {
		try {
			String result = HttpClientUtil.get("https://www.google.com.hk");
		} catch (Exception e) {
			logger.error("{}", e);
		}
	}
}

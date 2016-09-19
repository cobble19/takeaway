package com.cobble.takeaway.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class TestFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			if (req instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest)req;
				String signature = request.getParameter("signature");
				String timestamp = request.getParameter("timestamp");
				String nonce = request.getParameter("nonce");
				String openid = request.getParameter("openid");
				String encryptType = request.getParameter("encrypt_type");
				String msgSignature = request.getParameter("msg_signature");

				if (StringUtils.isNotBlank(msgSignature)) {
					logger.info("Request params, signature: {}, timestamp: {}, nonce: {}, openid: {}, encrypt_type: {}, msg_signature: {}",
							signature, timestamp, nonce, openid, encryptType, msgSignature);
					/// may be not get request stream,
					/// this test is not working
					BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = in.readLine()) != null) {
						sb.append(line);
					}
					logger.info("request from InputStream: {}", sb);
					String uri = request.getRequestURI();
					String qs = request.getQueryString();
					logger.info("uri: " + uri + ", qs: " + qs);
					
					String token = "token_dwyz";
					String encodingAesKey = "encAESKey0123A1B2C3D4E5h6i7j8k9l0O1P2Q3R4S5";
					String appId = "wx2bec8614a6c47443";
					logger.info("token: {}, encodingAesKey: {}, appId: {}", token, encodingAesKey, appId);
				
					WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
					String result = pc.decryptMsg(msgSignature, timestamp, nonce, sb.toString());
					logger.info("Paintext: {}", result);
				}
			}
		} catch (Exception e) {
			logger.error("Test Filter: {}", e);
		}
		filterChain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

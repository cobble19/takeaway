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
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestParam;

import com.cobble.takeaway.util.BeanUtil;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class TestFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);
	private static MessageSource messageSource = (MessageSource) BeanUtil.get("messageSource");

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
					
					if (messageSource == null) {
						messageSource = (MessageSource) BeanUtil.get("messageSource");
					}
					
					String token = messageSource.getMessage("WX.third.msgVerifyToken", null, null);
					String encodingAesKey = messageSource.getMessage("WX.third.msgEncKey", null, null);
					String appId = messageSource.getMessage("WX.third.clientId", null, null);
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

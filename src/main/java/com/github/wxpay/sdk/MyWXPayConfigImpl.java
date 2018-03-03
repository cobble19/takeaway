package com.github.wxpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.cobble.takeaway.util.BeanUtil;
import com.cobble.takeaway.util.CommonConstant;

public class MyWXPayConfigImpl extends WXPayConfig {
	private static final Logger logger = LoggerFactory.getLogger(MyWXPayConfigImpl.class);

	private byte[] certData;
	private static MyWXPayConfigImpl INSTANCE = null;
//	private static MessageSource messageSource = null;
//    static {
//	    	try {
//	    		messageSource = (MessageSource) BeanUtil.get("messageSource");
//	    	} catch (Exception e) {
//			logger.error("Got messageSource exception: ", e);
//		}
//    }
	
	private MyWXPayConfigImpl() throws Exception {
		String certPath = null;
		try {
//			certPath = messageSource.getMessage("files.directory.private.security", null, null) + "/apiclient_cert.p12";
			if (StringUtils.isBlank(certPath)) {
				certPath = "/opt/app/private/security" + "/apiclient_cert.p12";
			}
//			 String certPath =
//			 "/Users/bange/Documents/geby/takeaway/cert/apiclient_cert.p12";
			logger.info("certPath: {}", certPath);
		} catch (Exception e) {
			logger.error("获取微信支付证书文件失败", e);
		}
		try {
			File file = new File(certPath);
			InputStream certStream = new FileInputStream(file);
			this.certData = new byte[(int) file.length()];
			certStream.read(this.certData);
			certStream.close();
		} catch (Exception e) {
			logger.error("初始化失败", e);
		}
	}

	public synchronized static MyWXPayConfigImpl getInstance() throws Exception {
		if (INSTANCE == null) {
			if (INSTANCE == null) {
				INSTANCE = new MyWXPayConfigImpl();
			}
		}
		return INSTANCE;
	}

	public String getAppID() {
		return CommonConstant.DWYZ_AUTHORIZER_APP_ID;
	}

	public String getMchID() {
		return "1425213902";
	}

	public String getKey() {
		return "1425213902";
	}

	public InputStream getCertStream() {
		ByteArrayInputStream certBis;
		certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	public int getHttpConnectTimeoutMs() {
		return 2000;
	}

	public int getHttpReadTimeoutMs() {
		return 10000;
	}

	IWXPayDomain getWXPayDomain() {
		return MyWXPayDomainSimpleImpl.instance();
	}

	public String getPrimaryDomain() {
		return "api.mch.weixin.qq.com";
	}

	public String getAlternateDomain() {
		return "api2.mch.weixin.qq.com";
	}

	@Override
	public int getReportWorkerNum() {
		return 1;
	}

	@Override
	public int getReportBatchSize() {
		return 2;
	}
}

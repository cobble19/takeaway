package com.cobble.takeaway.service.ecommerce.impl;

import com.cobble.takeaway.controller.Oauth2Controller;
import com.cobble.takeaway.service.ecommerce.RefreshTokenHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RefreshTokenHandleServiceImpl implements RefreshTokenHandleService {
	private static final Logger logger = LoggerFactory.getLogger(RefreshTokenHandleServiceImpl.class);
	
	private static Boolean threadPoolStatus = false;
	
	@Autowired
	private Oauth2Controller oauth2Controller;

	/**
	 * 10分钟运行一次, 尝试刷新wxComAccessToken
	 * 10分钟运行一次, 尝试刷新wxAuthorizerToken
	 */
	@PostConstruct
	public void refreshToken() {
		try {
			if (!threadPoolStatus) {
				threadPoolStatus = true;
				logger.info("定期刷新wxComAccessToken和wxAuthorizerToken, 线程池 start...");
				ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
				RefreshWxComAccessTokenThread refreshWxComAccessTokenThread = new RefreshWxComAccessTokenThread();
				RefreshWxAuthorizerTokenThread refreshWxAuthorizerTokenThread = new RefreshWxAuthorizerTokenThread();
	 			long initialDelay = 5 * 60;
				long period = 10 * 60;
				TimeUnit unit = TimeUnit.SECONDS;
				executorService.scheduleAtFixedRate(refreshWxComAccessTokenThread, initialDelay, period, unit);
				executorService.scheduleAtFixedRate(refreshWxAuthorizerTokenThread, initialDelay, period, unit);
				logger.info("定期刷新wxComAccessToken和wxAuthorizerToken, 开启完成, 线程池 done..., initialDelay: {}, period: {}", initialDelay, period);
			} else {
				logger.info("定期刷新wxComAccessToken和wxAuthorizerToken, 线程池已经被启动");
			}
		} catch (Exception e) {
			logger.error("refreshToken Exception: ", e);
		}
	}
	public class RefreshWxComAccessTokenThread extends Thread {
		@Override
		public void run() {
			try {
				logger.info("定期 RefreshWxComAccessTokenThread, thread start...");
				oauth2Controller.refreshWxComAccessToken();
				logger.info("定期 RefreshWxComAccessTokenThread, thread finish...");
			} catch (Exception e) {
				logger.error("RefreshWxComAccessTokenThread Exception: ", e);
			}
		}
	}

	public class RefreshWxAuthorizerTokenThread extends Thread {
		@Override
		public void run() {
			try {
				logger.info("定期 RefreshWxAuthorizerTokenThread, thread start...");
				oauth2Controller.refreshWxAuthorizerToken();
				logger.info("定期 RefreshWxAuthorizerTokenThread, thread finish...");
			} catch (Exception e) {
				logger.error("RefreshWxAuthorizerTokenThread Exception: ", e);
			}
		}
	}
}

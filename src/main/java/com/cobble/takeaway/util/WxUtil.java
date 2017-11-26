package com.cobble.takeaway.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenSearchApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketSearchApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxPreAuthCodeApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxPreAuthCodeReqApiPOJO;
import com.cobble.takeaway.service.WxComAccessTokenService;
import com.cobble.takeaway.service.WxComVerifyTicketService;

public class WxUtil {
	private static final Logger logger = LoggerFactory.getLogger(WxUtil.class);
	

	public static String getWxComLoginUrl() throws Exception {
		return getWxComLoginUrl(null);
	}
	
	public static String getWxComLoginUrl(Map redirectReqParamMap) throws Exception {
		String wxComLoginUrl = null;

		try {

			WxComVerifyTicketService wxComVerifyTicketService = (WxComVerifyTicketService) BeanUtil.get("wxComVerifyTicketServiceImpl");
			WxComAccessTokenService wxComAccessTokenService = (WxComAccessTokenService) BeanUtil.get("wxComAccessTokenServiceImpl");
			MessageSource messageSource = (MessageSource) BeanUtil.get("messageSource");

			String wxThirdClientId = messageSource.getMessage("WX.third.clientId", null, null);
			String wxThirdSecret = messageSource.getMessage("WX.third.secret", null, null);

			String wxThirdAuthorizationUrl = messageSource.getMessage("WX.third.authorizationUrl", null, null);
			String wxThirdRedirectUri = messageSource.getMessage("WX.third.redirectUri", null, null);
			String redirectReqParams = "?abc=1";
			if (MapUtils.isNotEmpty(redirectReqParamMap)) {
				Set keySet = redirectReqParamMap.keySet();
				Iterator it = keySet.iterator();
				while (it.hasNext()) {
					Object key = it.next();
					Object value = redirectReqParamMap.get(key);
					redirectReqParams += "&" + key + "=" + value;
					redirectReqParams = redirectReqParams.replace("&" + key + "=", "%26" + key + "%3D");
				}
				wxThirdRedirectUri += redirectReqParams; 
			}
			
			String wxThirdAccessTokenUrl = messageSource.getMessage("WX.third.accessTokenUrl", null, null);
			String wxThirdPreAuthCodeUrl = messageSource.getMessage("WX.third.preAuthCodeUrl", null, null);
			
			
			WxComAccessTokenReqApiPOJO wxComAccessTokenReqPOJO = new WxComAccessTokenReqApiPOJO();
			wxComAccessTokenReqPOJO.setComponentAppId(wxThirdClientId);
			wxComAccessTokenReqPOJO.setComponentAppSecret(wxThirdSecret);
			
			WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchPOJO = new WxComVerifyTicketSearchApiPOJO();
			List<WxComVerifyTicketApiPOJO> wxComVerifyTicketPOJOs = wxComVerifyTicketService.finds(wxComVerifyTicketSearchPOJO);
			String componentVerifyTicket = "";
			if (!CollectionUtils.isEmpty(wxComVerifyTicketPOJOs)) {
				componentVerifyTicket = wxComVerifyTicketPOJOs.get(0).getComponentVerifyTicket();
			}
			
			WxComAccessTokenApiPOJO wxComAccessTokenPOJO = null;
			WxComAccessTokenSearchApiPOJO wxTokenSearchPOJO = new WxComAccessTokenSearchApiPOJO();
			List<WxComAccessTokenApiPOJO> wxComAccessTokenPOJOs = wxComAccessTokenService.finds(wxTokenSearchPOJO);
			if (!CollectionUtils.isEmpty(wxComAccessTokenPOJOs)) {
				wxComAccessTokenPOJO = wxComAccessTokenPOJOs.get(0);
			}
			if (wxComAccessTokenPOJO == null) {
				wxComAccessTokenReqPOJO.setComponentVerifyTicket(componentVerifyTicket);
				String wxComAccessTokenStr = HttpClientUtil.postHttpsJson(wxThirdAccessTokenUrl, JsonUtils.convertToJson(wxComAccessTokenReqPOJO));
				wxComAccessTokenPOJO = JsonUtils.convertToJavaBean(wxComAccessTokenStr, WxComAccessTokenApiPOJO.class);
				wxComAccessTokenPOJO.setCreateDateTime(new Date());

				wxComAccessTokenService.insert(wxComAccessTokenPOJO);
			}
			
			
			WxPreAuthCodeReqApiPOJO wxPreAuthCodeReqPOJO = new WxPreAuthCodeReqApiPOJO();
			wxPreAuthCodeReqPOJO.setComponentAppId(wxThirdClientId);
			String preAuthCodeStr = HttpClientUtil.postHttpsJson(wxThirdPreAuthCodeUrl.replace("COMPONENT_ACCESS_TOKEN", wxComAccessTokenPOJO.getComponentAccessToken()), 
						JsonUtils.convertToJson(wxPreAuthCodeReqPOJO));
			WxPreAuthCodeApiPOJO wxPreAuthCodePOJO = JsonUtils.convertToJavaBean(preAuthCodeStr, WxPreAuthCodeApiPOJO.class);
			
			wxComLoginUrl = wxThirdAuthorizationUrl
									.replace("COMPONENT_APPID", wxThirdClientId)
									.replace("PRE_AUTH_CODE", wxPreAuthCodePOJO.getPreAuthCode())
									.replace("REDIRECT_URI", wxThirdRedirectUri)
									;
		} catch (Exception e) {
			logger.error("get wxComLoginUrl exception: {}", e);
		}
		return wxComLoginUrl;
	}
	
	public static void main(String[] args) {
		logger.info("Test....");
		
		String str = "C:/abc/def/gh";
		logger.info(str);
		str = str.replaceAll("/", "\\\\");
		logger.info(str);
		str = "C:\\abc\\def\\gh";
		logger.info(str);
		str = str.replaceAll("\\\\", "/");
		logger.info(str);
	}
}

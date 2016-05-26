package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.oauth2.MyRedirectStrategy;
import com.cobble.takeaway.oauth2.WxOauth2TokenPOJO;
import com.cobble.takeaway.oauth2.WxUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;
import com.cobble.takeaway.pojo.weixin.api.FuncInfoApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerAccessTokenApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerAccessTokenReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerInfoApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerInfoReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenSearchApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketEncryptApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketSearchApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxPreAuthCodeApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxPreAuthCodeReqApiPOJO;
import com.cobble.takeaway.service.WxAuthorizerAccessTokenService;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.service.WxComAccessTokenService;
import com.cobble.takeaway.service.WxComVerifyTicketService;
import com.cobble.takeaway.util.HttpClientUtil;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.XmlUtils;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@Controller
public class Oauth2Controller extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(Oauth2Controller.class);
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WxComVerifyTicketService wxComVerifyTicketService;
	@Autowired
	private WxComAccessTokenService wxComAccessTokenService;
	@Autowired
	private WxAuthorizerAccessTokenService wxAuthorizerAccessTokenService;
	@Autowired
	private WxAuthorizerInfoService wxAuthorizerInfoService;
	
	private MyRedirectStrategy myRedirectStrategy = new MyRedirectStrategy();
	@Value("${WX.clientId}")
	private String clientId;
	@Value("${WX.secret}")
	private String secret;
	@Value("${WX.accessTokenUrl}")
	private String accessTokenUrl;
	@Value("${WX.authorizationUri}")
	private String authorizationUri;
	@Value("${WX.redirectUri}")
	private String redirectUri;
	@Value("${WX.scope}")
	private String scope;
	@Value("${WX.myProfileUrl}")
	private String myProfileUrl;
	@Value("${WX.globalLogoutUrl}")
	private String globalLogoutUrl;
	@Value("${WX.userInfoUidUrl}")
	private String userInfoUidUrl;
	@Value("${WX.siteLoginUrl}")
	private String siteLoginUrl;
	
	@Value("${WX.third.clientId}")
	private String wxThirdClientId;
	@Value("${WX.third.secret}")
	private String wxThirdSecret;
	@Value("${WX.third.sendDomain}")
	private String wxThirdSendDomain;
	@Value("${WX.third.authEventRecieve}")
	private String wxThirdAuthEventRecieve;
	@Value("${WX.third.msgVerifyToken}")
	private String wxThirdMsgVerifyToken;
	@Value("${WX.third.msgEncKey}")
	private String wxThirdMsgEncKey;
	@Value("${WX.third.msgEventRecieve}")
	private String wxThirdMsgEventRecieve;
	@Value("${WX.third.devDomain}")
	private String wxThirdDevDomain;
	@Value("${WX.third.authorizationUrl}")
	private String wxThirdAuthorizationUrl;
	@Value("${WX.third.redirectUri}")
	private String wxThirdRedirectUri;
	@Value("${WX.third.accessTokenUrl}")
	private String wxThirdAccessTokenUrl;
	@Value("${WX.third.preAuthCodeUrl}")
	private String wxThirdPreAuthCodeUrl;
	@Value("${WX.third.authorizerAccessTokenUrl}")
	private String wxThirdAuthorizerAccessTokenUrl;
	@Value("${WX.third.authorizerRefreshTokenUrl}")
	private String wxThirdAuthorizerRefreshTokenUrl;
	@Value("${WX.third.authorizerInfoUrl}")
	private String wxThirdAuthorizerInfoUrl;
	
	// 第三方网页登陆
	@Value("${WX.third.web.authorizeUrl}")
	private String wxThirdWebAuthorizeUrl;
	@Value("${WX.third.web.redirectUrl}")
	private String wxThirdWebRedirectUrl;
	@Value("${WX.third.web.accessTokenUrl}")
	private String wxThirdWebAccessTokenUrl;
	@Value("${WX.third.web.refreshTokenUrl}")
	private String wxThirdWebRefreshTokenUrl;
	/*@Value("${WX.third.web.userInfoUrl}")
	private String wxThirdWebUserInfoUrl;*/
	

	@RequestMapping(value = "/web/wx/oauth2/third/web/authCode"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView wxWebAuthCode(@RequestParam(value="code", required=false) String code
			, @RequestParam(value="state", required=false) String state
			, @RequestParam(value="appid", required=false) String appid
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login begin...");
			logger.info("code: {}, state: {}, appid: {}", code, state, appid);
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login uri: " + uri + ", qs: " + qs);
			if (StringUtils.isNotBlank(code)) {
				// get token

				WxAuthorizerInfoReqApiPOJO wxAuthorizerAccessTokenReqPOJO = new WxAuthorizerInfoReqApiPOJO();
				wxAuthorizerAccessTokenReqPOJO.setAuthorizerAppId(appid);
				wxAuthorizerAccessTokenReqPOJO.setComponentAppId(wxThirdClientId);

				WxComAccessTokenSearchApiPOJO wxTokenSearchPOJO = new WxComAccessTokenSearchApiPOJO();
				List<WxComAccessTokenApiPOJO> wxComAccessTokenPOJOs = wxComAccessTokenService.finds(wxTokenSearchPOJO);
				String componentAccessToken = "";
				if (!CollectionUtils.isEmpty(wxComAccessTokenPOJOs)) {
					componentAccessToken = wxComAccessTokenPOJOs.get(0).getComponentAccessToken();
				}
				
				String myAccessTokenUrl = wxThirdWebAccessTokenUrl
						.replace("COMPONENT_APPID", wxThirdClientId)
						.replace("APPID", appid)
						.replace("CODE", code)
						.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken);
				String result = HttpClientUtil.get(myAccessTokenUrl);
				WxOauth2TokenPOJO wxOauth2TokenPOJO = JsonUtils.convertToJavaBean(result, WxOauth2TokenPOJO.class);
				// get user info
				String profileUrl = myProfileUrl.replace("ACCESS_TOKEN", wxOauth2TokenPOJO.getAccessToken())
									.replace("OPENID", wxOauth2TokenPOJO.getOpenId());
				String userInfo = HttpClientUtil.get(profileUrl);
				WxUserPOJO wxUserPOJO = JsonUtils.convertToJavaBean(userInfo, WxUserPOJO.class);
				// get user info unionID
				/*String myUserInfoUidUrl = userInfoUidUrl.replace("ACCESS_TOKEN", wxOauth2TokenPOJO.getAccessToken())
											.replace("OPENID", wxOauth2TokenPOJO.getOpenId());
				String myUserInfoUid = HttpClientUtil.get(myUserInfoUidUrl);*/
				
				String msg = "openid: " + wxOauth2TokenPOJO.getOpenId() + ", nickname: " + wxUserPOJO.getNickname()
						+ "\n" + "Token: \n" + result + "\n" + "UserInfo: \n" + userInfo + "\n"
						/*+ "MyUserInfoUid: \n" + myUserInfoUid*/;
				ret.addObject("msg", msg);
				
				HttpSession session = request.getSession();
				session.setAttribute("msg", msg);
				
				myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/web/wx/oauth2/success");
			} else {
				
			}
			
//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return null;
//		return ret;
	}

	@RequestMapping(value = "/web/wx/oauth2/third/web/login")
	public ModelAndView wxWebLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login success begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login success uri: " + uri + ", qs: " + qs);
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
			List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
			WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
			if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
				wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(0);
			}
			String wxWebLoginUrl = "";
			String wxUrl = "";
			String wxEncodeUrl = "";
			if (wxAuthorizerInfoPOJO != null) {
				wxWebLoginUrl = wxThirdWebAuthorizeUrl
				.replace("COMPONENT_APPID", wxThirdClientId)
				.replace("APPID", wxAuthorizerInfoPOJO.getAuthorizerAppId())
				.replace("REDIRECT_URI", wxThirdWebRedirectUrl)
				.replace("SCOPE", scope)
				.replace("STATE", RandomStringUtils.randomAlphabetic(6))
				;
				wxUrl = wxWebLoginUrl;
				wxEncodeUrl = wxWebLoginUrl;
				wxWebLoginUrl = myRedirectStrategy.encodeQueryParam(wxWebLoginUrl);
				wxEncodeUrl = myRedirectStrategy.encodeUrl(response, wxEncodeUrl);
				wxUrl = myRedirectStrategy.encodeContent(wxUrl);
			}
			
			ret.addObject("wxWebLoginUrl", wxWebLoginUrl);
			ret.addObject("wxEncodeUrl", wxEncodeUrl);
			ret.addObject("wxUrl", wxUrl);
			ret.setViewName("/page/weixin/web_login");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/third/authorizerInfo"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView authorizerInfo(@RequestParam(value="componentAppId", required=false) String componentAppId,
			@RequestParam(value="authorizerAppId", required=false) String authorizerAppId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("authorizerInfo begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("authorizerInfo uri: " + uri + ", qs: " + qs);
			WxAuthorizerInfoReqApiPOJO wxAuthorizerAccessTokenReqPOJO = new WxAuthorizerInfoReqApiPOJO();
			wxAuthorizerAccessTokenReqPOJO.setAuthorizerAppId(authorizerAppId);
			wxAuthorizerAccessTokenReqPOJO.setComponentAppId(componentAppId);

			WxComAccessTokenSearchApiPOJO wxTokenSearchPOJO = new WxComAccessTokenSearchApiPOJO();
			List<WxComAccessTokenApiPOJO> wxComAccessTokenPOJOs = wxComAccessTokenService.finds(wxTokenSearchPOJO);
			String componentAccessToken = "";
			if (!CollectionUtils.isEmpty(wxComAccessTokenPOJOs)) {
				componentAccessToken = wxComAccessTokenPOJOs.get(0).getComponentAccessToken();
			}
			String wxThirdAuthorizerInfo = HttpClientUtil.postHttpsJson(wxThirdAuthorizerInfoUrl.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken), 
							JsonUtils.convertToJson(wxAuthorizerAccessTokenReqPOJO));
			WxAuthorizerInfoApiPOJO wxAuthorizerInfoPOJO = JsonUtils.convertToJavaBean(wxThirdAuthorizerInfo, WxAuthorizerInfoApiPOJO.class);
			
			// 保存授权者信息到数据库
			com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO2 = new com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO();
			String nickName = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getNickName();
			wxAuthorizerInfoPOJO2.setNickName(nickName);
			String headImg = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getHeadImg();
			wxAuthorizerInfoPOJO2.setHeadImg(headImg);
			Integer serviceTypeInfo = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getServiceTypeInfoPOJO().getId();
			wxAuthorizerInfoPOJO2.setServiceTypeInfo(serviceTypeInfo);
			Integer verifyTypeInfo = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getVerifyTypeInfoPOJO().getId();
			wxAuthorizerInfoPOJO2.setVerifyTypeInfo(verifyTypeInfo);
			String userName = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getUserName();
			wxAuthorizerInfoPOJO2.setUserName(userName);
			String alias = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getAlias();
			wxAuthorizerInfoPOJO2.setAlias(alias);
			String qrcodeUrl = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getQrcodeUrl();
			wxAuthorizerInfoPOJO2.setQrcodeUrl(qrcodeUrl);
			String businessInfo = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenStore()
					 			+ "," + wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenScan()
					 			+ "," + wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenPay()
					 			+ "," + wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenCard()
					 			+ "," + wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenShake();
			wxAuthorizerInfoPOJO2.setBusinessInfo(businessInfo);
			Integer idc = wxAuthorizerInfoPOJO.getAuthorizerInfoPOJO().getIdc();
			wxAuthorizerInfoPOJO2.setIdc(idc);
			wxAuthorizerInfoPOJO2.setAuthorizerAppId(authorizerAppId);
			String funcInfo = "";
			List<FuncInfoApiPOJO> funcInfoPOJOs = wxAuthorizerInfoPOJO.getAuthorizationInfo4AuthzerPOJO().getFuncInfoPOJOList();
			List<Integer> ids = new ArrayList<Integer>();
			if (!CollectionUtils.isEmpty(funcInfoPOJOs)) {
				for (int i = 0; i < funcInfoPOJOs.size(); i++) {
					ids.add(funcInfoPOJOs.get(i).getFuncscopeCategoryPOJO().getId());
				}
				funcInfo = StringUtils.join(ids, ",");
			}
			wxAuthorizerInfoPOJO2.setFuncInfo(funcInfo);
			wxAuthorizerInfoPOJO2.setCreateDateTime(new Date());
			wxAuthorizerInfoService.insert(wxAuthorizerInfoPOJO2);
			
			ret.addObject("msg", wxThirdAuthorizerInfo);
			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("authorizerInfo error.", e);
			throw e;
		}
		
//		return null;
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/third/authCode"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView thirdAuthCode(@RequestParam(value="auth_code", required=false) String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("authCode begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("authCode uri: " + uri + ", qs: " + qs);
			if (StringUtils.isNotBlank(code)) {
				// 组建去获取授权者token请求
				WxAuthorizerAccessTokenReqApiPOJO wxAuthorizerAccessTokenReqPOJO = new WxAuthorizerAccessTokenReqApiPOJO();
				wxAuthorizerAccessTokenReqPOJO.setAuthorizationCode(code);
				wxAuthorizerAccessTokenReqPOJO.setComponentAppId(wxThirdClientId);
				
				WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchPOJO = new WxComAccessTokenSearchApiPOJO();
				List<WxComAccessTokenApiPOJO> wxComAccessTokenPOJOs = wxComAccessTokenService.finds(wxComAccessTokenSearchPOJO);
				String componentAccessToken = "";
				if (!CollectionUtils.isEmpty(wxComAccessTokenPOJOs)) {
					componentAccessToken = wxComAccessTokenPOJOs.get(0).getComponentAccessToken();
				}
				// 获取授权者token信息
				String wxThirdAuthorizerToken = HttpClientUtil.postHttpsJson(wxThirdAuthorizerAccessTokenUrl.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken), 
								JsonUtils.convertToJson(wxAuthorizerAccessTokenReqPOJO));
				WxAuthorizerAccessTokenApiPOJO wxAuthorizerAccessTokenPOJO = JsonUtils.convertToJavaBean(wxThirdAuthorizerToken, WxAuthorizerAccessTokenApiPOJO.class);
				
				// 保存授权者token信息到数据库
				com.cobble.takeaway.pojo.weixin.WxAuthorizerAccessTokenPOJO wxAuthorizerAccessTokenPOJO2 = new com.cobble.takeaway.pojo.weixin.WxAuthorizerAccessTokenPOJO();
				String authorizerAccessToken = wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getAuthorizerAccessToken();
				wxAuthorizerAccessTokenPOJO2.setAuthorizerAccessToken(authorizerAccessToken);
				String authorizerAppId = wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getAuthorizerAppId();
				wxAuthorizerAccessTokenPOJO2.setAuthorizerAppId(authorizerAppId);
				String authorizerRefreshToken = wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getAuthorizerRefreshToken();
				wxAuthorizerAccessTokenPOJO2.setAuthorizerRefreshToken(authorizerRefreshToken);
				wxAuthorizerAccessTokenPOJO2.setCreateDateTime(new Date());
				Integer expiresIn = wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getExpiresIn();
				wxAuthorizerAccessTokenPOJO2.setExpiresIn(expiresIn);
				String funcInfo = "";
				List<FuncInfoApiPOJO> funcInfoPOJOs = wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getFuncInfoPOJOList();
				List<Integer> ids = new ArrayList<Integer>();
				if (!CollectionUtils.isEmpty(funcInfoPOJOs)) {
					for (int i = 0; i < funcInfoPOJOs.size(); i++) {
						ids.add(funcInfoPOJOs.get(i).getFuncscopeCategoryPOJO().getId());
					}
					funcInfo = StringUtils.join(ids, ",");
				}
				wxAuthorizerAccessTokenPOJO2.setFuncInfo(funcInfo);
				wxAuthorizerAccessTokenService.insert(wxAuthorizerAccessTokenPOJO2);
				// 显示获取授权者信息
				myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/web/wx/oauth2/third/authorizerInfo"
						+ "?componentAppId=" + wxThirdClientId + "&authorizerAppId=" + wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getAuthorizerAppId());
//				myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/web/wx/oauth2/success");
			}
			
//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("authCode error.", e);
			throw e;
		}
		
		return null;
//		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/comLogin")
	public ModelAndView wxComLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login success begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login success uri: " + uri + ", qs: " + qs);
			
			WxComAccessTokenReqApiPOJO wxComAccessTokenReqPOJO = new WxComAccessTokenReqApiPOJO();
			wxComAccessTokenReqPOJO.setComponentAppId(wxThirdClientId);
			wxComAccessTokenReqPOJO.setComponentAppSecret(wxThirdSecret);
			
			WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchPOJO = new WxComVerifyTicketSearchApiPOJO();
			List<WxComVerifyTicketApiPOJO> wxComVerifyTicketPOJOs = wxComVerifyTicketService.finds(wxComVerifyTicketSearchPOJO);
			String componentVerifyTicket = "";
			if (!CollectionUtils.isEmpty(wxComVerifyTicketPOJOs)) {
				componentVerifyTicket = wxComVerifyTicketPOJOs.get(0).getComponentVerifyTicket();
			}
			wxComAccessTokenReqPOJO.setComponentVerifyTicket(componentVerifyTicket);
			String wxComAccessTokenStr = HttpClientUtil.postHttpsJson(wxThirdAccessTokenUrl, JsonUtils.convertToJson(wxComAccessTokenReqPOJO));
			WxComAccessTokenApiPOJO wxComAccessTokenPOJO = JsonUtils.convertToJavaBean(wxComAccessTokenStr, WxComAccessTokenApiPOJO.class);
			wxComAccessTokenPOJO.setCreateDateTime(new Date());
			
			wxComAccessTokenService.insert(wxComAccessTokenPOJO);
			
			WxPreAuthCodeReqApiPOJO wxPreAuthCodeReqPOJO = new WxPreAuthCodeReqApiPOJO();
			wxPreAuthCodeReqPOJO.setComponentAppId(wxThirdClientId);
			String preAuthCodeStr = HttpClientUtil.postHttpsJson(wxThirdPreAuthCodeUrl.replace("COMPONENT_ACCESS_TOKEN", wxComAccessTokenPOJO.getComponentAccessToken()), 
						JsonUtils.convertToJson(wxPreAuthCodeReqPOJO));
			WxPreAuthCodeApiPOJO wxPreAuthCodePOJO = JsonUtils.convertToJavaBean(preAuthCodeStr, WxPreAuthCodeApiPOJO.class);
			
			String wxComLoginUrl = wxThirdAuthorizationUrl
									.replace("COMPONENT_APPID", wxThirdClientId)
									.replace("PRE_AUTH_CODE", wxPreAuthCodePOJO.getPreAuthCode())
									.replace("REDIRECT_URI", wxThirdRedirectUri)
									;
			ret.addObject("wxComLoginUrl", wxComLoginUrl);
			ret.setViewName("/page/weixin/com_login");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/wx/authEventRecieve", method=RequestMethod.POST)
	@ResponseBody
	public String authEventRecieve(@RequestBody String requestBody, @RequestParam(value="signature", required = false) String signature,
			@RequestParam(value="timestamp", required = false) String timestamp,
			@RequestParam(value="nonce", required = false) String nonce,
			@RequestParam(value="encrypt_type", required = false) String encryptType,
			@RequestParam(value="msg_signature", required = false) String msgSignature,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			logger.info("authEventRecieve begin...");
			logger.info("Request params, signature: {}, timestamp: {}, nonce: {}, encrypt_type: {}, msg_signature: {}",
					signature, timestamp, nonce, encryptType, msgSignature);
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("authEventRecieve uri: " + uri + ", qs: " + qs);
			logger.info("requestBody: " + requestBody);
			
			String token = messageSource.getMessage("WX.third.msgVerifyToken", null, null);
			String encodingAesKey = messageSource.getMessage("WX.third.msgEncKey", null, null);
			String appId = messageSource.getMessage("WX.third.clientId", null, null);
			logger.info("token: {}, encodingAesKey: {}, appId: {}", token, encodingAesKey, appId);
			
			WxComVerifyTicketEncryptApiPOJO wxComVerifyTicketEncryptPOJO = XmlUtils.convertToJavaBean(requestBody, WxComVerifyTicketEncryptApiPOJO.class);
			String encrypt = wxComVerifyTicketEncryptPOJO.getEncrypt();
			logger.info("encrypt: {}", encrypt);
			String format = "<xml><ToUserName><![CDATA[%1$s]]></ToUserName><Encrypt><![CDATA[%2$s]]></Encrypt></xml>";
			String fromXML = String.format(format, appId, encrypt);
			
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String result = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
			logger.info("Paintext: {}", result);
			
			WxComVerifyTicketApiPOJO wxComVerifyTicketPOJO = XmlUtils.convertToJavaBean(result, WxComVerifyTicketApiPOJO.class);
			logger.info("wxComVerifyTicketPOJO: {}", wxComVerifyTicketPOJO);
			wxComVerifyTicketService.insert(wxComVerifyTicketPOJO);
			
			/*BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			logger.info("From request: " + sb.toString());*/
			
//			response.getOutputStream().print("success");
			
//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("error: ", e);
//			throw e;
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/web/wx/oauth2/siteLogin")
	public ModelAndView siteLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login uri: " + uri + ", qs: " + qs);
			String mySiteLoginUrl = siteLoginUrl.replace("APPID", clientId)
										.replace("REDIRECT_URI", redirectUri).replace("SCOPE", "snsapi_login")
										.replace("STATE", RandomStringUtils.randomAlphabetic(6));
			myRedirectStrategy.sendRedirect(request, response, mySiteLoginUrl);
			return null;
//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("authorize error: ", e);
			throw e;
		}
		
//		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/success", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login success begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login success uri: " + uri + ", qs: " + qs);
			HttpSession session = request.getSession();
			String msg = (String) session.getAttribute("msg");
			ret.addObject("msg", msg);
			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/login"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView login(@RequestParam(value="code", required=false) String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login uri: " + uri + ", qs: " + qs);
			if (StringUtils.isNotBlank(code)) {
				// get token
				String myAccessTokenUrl = accessTokenUrl.replace("APPID", clientId)
											.replace("SECRET", secret).replace("CODE", code);
				String result = HttpClientUtil.get(myAccessTokenUrl);
				WxOauth2TokenPOJO wxOauth2TokenPOJO = JsonUtils.convertToJavaBean(result, WxOauth2TokenPOJO.class);
				// get user info
				String profileUrl = myProfileUrl.replace("ACCESS_TOKEN", wxOauth2TokenPOJO.getAccessToken())
									.replace("OPENID", wxOauth2TokenPOJO.getOpenId());
				String userInfo = HttpClientUtil.get(profileUrl);
				WxUserPOJO wxUserPOJO = JsonUtils.convertToJavaBean(userInfo, WxUserPOJO.class);
				// get user info unionID
				/*String myUserInfoUidUrl = userInfoUidUrl.replace("ACCESS_TOKEN", wxOauth2TokenPOJO.getAccessToken())
											.replace("OPENID", wxOauth2TokenPOJO.getOpenId());
				String myUserInfoUid = HttpClientUtil.get(myUserInfoUidUrl);*/
				
				String msg = "openid: " + wxOauth2TokenPOJO.getOpenId() + ", nickname: " + wxUserPOJO.getNickname()
						+ "\n" + "Token: \n" + result + "\n" + "UserInfo: \n" + userInfo + "\n"
						/*+ "MyUserInfoUid: \n" + myUserInfoUid*/;
				ret.addObject("msg", msg);
				
				HttpSession session = request.getSession();
				session.setAttribute("msg", msg);
				
				myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/web/wx/oauth2/success");
			}
			
//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return null;
//		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/authorize")
	public ModelAndView authorize(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login uri: " + uri + ", qs: " + qs);
			String myAuthorizationUrl = authorizationUri.replace("APPID", clientId)
										.replace("REDIRECT_URI", redirectUri).replace("SCOPE", scope)
										.replace("STATE", RandomStringUtils.randomAlphabetic(6));
			myRedirectStrategy.sendRedirect(request, response, myAuthorizationUrl);
			return null;
//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("authorize error: ", e);
			throw e;
		}
		
//		return ret;
	}
	
}

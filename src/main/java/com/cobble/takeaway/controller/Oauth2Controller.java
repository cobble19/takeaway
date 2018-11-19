package com.cobble.takeaway.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.cobble.takeaway.pojo.*;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcWxCardSearchPOJO;
import com.cobble.takeaway.pojo.weixin.*;
import com.cobble.takeaway.pojo.weixin.api.*;
import com.cobble.takeaway.service.ecommerce.EcWxCardService;
import com.cobble.takeaway.service.weixin.WxMsgEventLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.oauth2.BaseWxApiPOJO;
import com.cobble.takeaway.oauth2.MyRedirectStrategy;
import com.cobble.takeaway.oauth2.WxOauth2TokenApiPOJO;
import com.cobble.takeaway.oauth2.WxUserApiPOJO;
import com.cobble.takeaway.pojo.ecommerce.EcOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.WpOrderPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayOrderQueryReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.wxpay.api.WxPayUnifiedOrderReqApiPOJO;
import com.cobble.takeaway.service.ActivityService;
import com.cobble.takeaway.service.AwardRecordService;
import com.cobble.takeaway.service.AwardService;
import com.cobble.takeaway.service.InteractiveService;
import com.cobble.takeaway.service.PointEventService;
import com.cobble.takeaway.service.PointRecordService;
import com.cobble.takeaway.service.PointSummaryService;
import com.cobble.takeaway.service.RelWxIndexMapService;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.service.WxAuthorizerAccessTokenService;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.service.WxAuthorizerRefreshTokenService;
import com.cobble.takeaway.service.WxComAccessTokenService;
import com.cobble.takeaway.service.WxComVerifyTicketService;
import com.cobble.takeaway.service.WxPayService;
import com.cobble.takeaway.service.WxPersonUserService;
import com.cobble.takeaway.service.WxRespMsgService;
import com.cobble.takeaway.service.ecommerce.EcOrderService;
import com.cobble.takeaway.service.weixin.wxpay.WpOrderService;
import com.cobble.takeaway.service.weixin.wxpay.WpResultNotifyService;
import com.cobble.takeaway.spring.security.MyUser;
import com.cobble.takeaway.util.BeanUtil;
import com.cobble.takeaway.util.CacheUtil;
import com.cobble.takeaway.util.CollectionUtilx;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.ConfigurationUtil;
import com.cobble.takeaway.util.DateUtil;
import com.cobble.takeaway.util.FileUtil;
import com.cobble.takeaway.util.HttpClientUtil;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;
import com.cobble.takeaway.util.WxUtil;
import com.cobble.takeaway.util.XmlUtils;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.github.wxpay.sdk.WXPayUtil;
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
	@Autowired
	private WxAuthorizerRefreshTokenService wxAuthorizerRefreshTokenService;
	
	@Autowired
	private WxPersonUserService wxPersonUserService;
	/*@Autowired
	private WxPersonUserService wxPersonUserService;*/
	/*@Autowired
	private RelWxPuOpenService relWxPuOpenService;*/
	
	@Autowired
	private WxRespMsgService wxRespMsgService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private RelWxIndexMapService relWxIndexMapService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private PointEventService pointEventService;
	@Autowired
	private PointRecordService pointRecordService;
	@Autowired
	private PointSummaryService pointSummaryService;
	

	@Autowired
	private InteractiveService interactiveService;
	@Autowired
	private AwardService awardService;
	@Autowired
	private AwardRecordService awardRecordService;
	
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private WpOrderService wpOrderService;
	@Autowired
	private WpResultNotifyService wpResultNotifyService;
	
	@Autowired
	private EcOrderService ecOrderService;
	@Autowired
	private WxMsgEventLogService wxMsgEventLogService;
	@Autowired
	private EcWxCardService ecWxCardService;
	
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
	@Value("${WX.userGetUrl}")
	private String userGetUrl;
	@Value("${WX.userInfoUidBatchGetUrl}")
	private String userInfoUidBatchGetUrl;
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
	

	@Value("${WX.autoTest.authorizerAppId}")
	private String wxAutoTestAuthorizerAppId;
	@Value("${WX.autoTest.username}")
	private String wxAutoTestUsername;
	
	@Value("${WX.custom.send}")
	private String wxCustomSend;
	
	// 菜单管理
	@Value("${WX.menu.mgr.createUrl}")
	private String wxMenuMgrCreateUrl;
	@Value("${WX.menu.mgr.getUrl}")
	private String wxMenuMgrGetUrl;
	@Value("${WX.menu.mgr.deleteUrl}")
	private String wxMenuMgrDeleteUrl;
	@Value("${WX.menu.mgr.menuInfoUrl}")
	private String wxMenuMgrMenuInfoUrl;
	
	// 个性化菜单管理
	@Value("${WX.menu.mgr.conditional.addUrl}")
	private String wxMenuMgrConditionalAddUrl;
	@Value("${WX.menu.mgr.conditional.tryMatchUrl}")
	private String wxMenuMgrConditionalTryMatchUrl;
	@Value("${WX.menu.mgr.conditional.deleteUrl}")
	private String wxMenuMgrConditionalDeleteUrl;
	
	// 标签管理
	@Value("${WX.tags.mgr.createUrl}")
	private String wxTagsMgrCreateUrl;
	@Value("${WX.tags.mgr.getUrl}")
	private String wxTagsMgrGetUrl;
	@Value("${WX.tags.mgr.updateUrl}")
	private String wxTagsMgrUpdateUrl;
	@Value("${WX.tags.mgr.deleteUrl}")
	private String wxTagsMgrDeleteUrl;
	@Value("${WX.tags.mgr.getUsersUrl}")
	private String wxTagsMgrGetUsersUrl;
	@Value("${WX.tags.mgr.batchTaggingUrl}")
	private String wxTagsMgrBatchTaggingUrl;
	@Value("${WX.tags.mgr.batchUntaggingUrl}")
	private String wxTagsMgrBatchUntaggingUrl;
	@Value("${WX.tags.mgr.getIdListUrl}")
	private String wxTagsMgrGetIdListUrl;
	
	// 生成临时和长期二维码
	@Value("${WX.qrcode.ticketUrl}")
	private String wxQrCodeTicketUrl;
	@Value("${WX.qrcode.showUrl}")
	private String wxQrCodeShowUrl;
	// JS-SDK
	@Value("${WX.jssdk.ticketUrl}")
	private String wxJsSdkTicketUrl;

	// 微信卡券
	@Value("${WX.card.mgr.getUrl}")
	private String wxCardMgrGetUrl;
	@Value("${WX.card.mgr.getCardListUrl}")
	private String wxCardMgrGetCardListUrl;

	@Value("${WX.card.getTicketUrl}")
	private String wxCardGetTicketUrl;
	@Value("${WX.card.code.decryptUrl}")
	private String wxCardCodeDecryptUrl;
	@Value("${WX.card.code.getUrl}")
	private String wxCardCodeGetUrl;
	
	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

	@RequestMapping(value = "/api/wx/card/code/get", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public WxCardCodeGetRespApiPOJO wxCardCodeGet(@RequestBody WxCardCodeGetReqApiPOJO wxCardCodeGetReqApiPOJO
														 , @RequestParam(value="authorizerAppId", required = false) String authorizerAppId
														/*, @RequestBody String requestBody*/
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/

		WxCardCodeGetRespApiPOJO ret = new WxCardCodeGetRespApiPOJO();

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/

//			logger.info("requestBody: {}", requestBody);

			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxCardCodeGetUrl = wxCardCodeGetUrl
						.replace("TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
//				WxCardCodeGetReqApiPOJO wxCardCodeGetReqApiPOJO1 = JsonUtils.convertToJavaBean(requestBody, WxCardCodeGetReqApiPOJO.class);
				String requestBody = JsonUtils.convertToJson(wxCardCodeGetReqApiPOJO);

				String result = HttpClientUtil.postHttpsJson(myWxCardCodeGetUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxCardCodeGetRespApiPOJO wxCardCodeGetRespApiPOJO = JsonUtils.convertToJavaBean(result, WxCardCodeGetRespApiPOJO.class);
				ret = wxCardCodeGetRespApiPOJO;
			} else {
				logger.error("accessToken is must no null, authorizerAppId: {}", authorizerAppId);
			}

		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		return ret;
	}

	@RequestMapping(value = "/api/wx/card/code/decrypt", method = {RequestMethod.POST})
	@ResponseBody
	public WxCardCodeDecryptRespApiPOJO wxCardCodeDecryptApi(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
												  @RequestParam(value="authorizerAppId", required = false) String authorizerAppId,
												  @RequestBody String requestBody
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/

		WxCardCodeDecryptRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/

			logger.info("requestBody: {}", requestBody);

			WxCardCodeDecryptReqApiPOJO wxCardCodeDecryptReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxCardCodeDecryptReqApiPOJO.class);
			ret = this.wxCardCodeDecrypt(authorizerAppId, wxCardCodeDecryptReqApiPOJO);
		} catch (Exception e) {
			logger.error("wxCardCodeDecryptApi error:", e);
		}

		return ret;
	}

	public WxCardCodeDecryptRespApiPOJO wxCardCodeDecrypt(String authorizerAppId,
														  WxCardCodeDecryptReqApiPOJO wxCardCodeDecryptReqApiPOJO) throws Exception {
		WxCardCodeDecryptRespApiPOJO ret = null;

		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/

			logger.info("wxCardCodeDecryptReqApiPOJO: {}", wxCardCodeDecryptReqApiPOJO);

			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxCardCodeDecryptUrl = wxCardCodeDecryptUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				wxCardCodeDecryptReqApiPOJO.setEncryptCode(URLDecoder.decode(wxCardCodeDecryptReqApiPOJO.getEncryptCode(), "UTF-8"));
				String requestBody = JsonUtils.convertToJson(wxCardCodeDecryptReqApiPOJO);

				String result = HttpClientUtil.postHttpsJson(myWxCardCodeDecryptUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxCardCodeDecryptRespApiPOJO wxCardCodeDecryptRespApiPOJO = JsonUtils.convertToJavaBean(result, WxCardCodeDecryptRespApiPOJO.class);
				ret = wxCardCodeDecryptRespApiPOJO;
			} else {
				logger.error("accessToken is must no null, authorizerAppId: {}", authorizerAppId);
			}

		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		return ret;
	}

	public WxCardMgrGetCardListRespApiPOJO getWxCardList(String authorizerAppId, String openId, String cardId) throws Exception {
		WxCardMgrGetCardListRespApiPOJO ret = new WxCardMgrGetCardListRespApiPOJO();
		logger.info("authorizerAppId: {}, openId: {}, cardId: {}", authorizerAppId, openId, cardId);
		String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
		if (StringUtils.isNotBlank(authorizerAccessToken)) {
			String myWxCardMgrGetCardListUrl = wxCardMgrGetCardListUrl
					.replace("TOKEN", authorizerAccessToken);
			WxCardMgrGetCardListReqApiPOJO wxCardMgrGetCardListReqApiPOJO = new WxCardMgrGetCardListReqApiPOJO();
			wxCardMgrGetCardListReqApiPOJO.setOpenId(openId);
			wxCardMgrGetCardListReqApiPOJO.setCardId(cardId);
			String requestBody = JsonUtils.convertToJson(wxCardMgrGetCardListReqApiPOJO);
			String result = HttpClientUtil.postHttpsJson(myWxCardMgrGetCardListUrl, requestBody);
			result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
			logger.debug("result: " + result);
			WxCardMgrGetCardListRespApiPOJO wxCardMgrGetCardListRespApiPOJO = JsonUtils.convertToJavaBean(result, WxCardMgrGetCardListRespApiPOJO.class);
			ret = wxCardMgrGetCardListRespApiPOJO;
		} else {
			logger.error("accessToken must be no null, authorizerAppId: {}", authorizerAppId);
		}

		return ret;
	}

	public Map getWxCardDetail(String authorizerAppId, String cardId) throws Exception {
		Map ret = new HashMap();

		String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
		if (StringUtils.isNotBlank(authorizerAccessToken)) {
			String myWxCardMgrGetUrl = wxCardMgrGetUrl
					.replace("TOKEN", authorizerAccessToken);
			Map cardRequestMap = new HashMap();
			cardRequestMap.put("card_id", cardId);
			String requestBody = JsonUtils.convertToJson(cardRequestMap);
			String result = HttpClientUtil.postHttpsJson(myWxCardMgrGetUrl, requestBody);
			result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
			logger.debug("result: " + result);
			Map wxJsSdkTicketRespApiPOJO = JsonUtils.convertToJavaBean(result, Map.class);
			ret = wxJsSdkTicketRespApiPOJO;
		} else {
			logger.error("accessToken must be no null, authorizerAppId: {}", authorizerAppId);
		}

		return ret;
	}

	@RequestMapping(value = "/api/wx/custom/send/wxcard", method = {RequestMethod.GET})
	@ResponseBody
	public Map customSendWxCard(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestParam(value="authorizerAppId", required = false) String authorizerAppId
			, @RequestParam(value="openId", required = true) String openId
			, @RequestParam(value="cardId", required = true) String cardId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		String url = request.getRequestURL() + "";
		HttpSession session = request.getSession();
		try {
            String userType = UserUtil.getCurrentUser().getUserType();
            long userId = UserUtil.getCurrentUserId();
            String curAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
            if (CommonConstant.DWYZ_AUTHORIZER_APP_ID.equals(curAppId)
                && CommonConstant.DWYZ_USER_ID == userId) {

                if (StringUtils.isBlank(authorizerAppId)) {
                    authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
                }
                String result = this.sendCustomMsgWxCard(openId, cardId, authorizerAppId);
                ret.put("result", result);
                ret.put("success", true);
            } else {
                ret.put("success", false);
                ret.put("errMsg", "发送WX card失败, userId: " + userId + ", curAppId: " + curAppId + ", userName: " + UserUtil.getCurrentUsername());
            }
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		return ret;
	}
	
	@RequestMapping(value = "/api/wxpay/notify", method = {RequestMethod.POST}, produces= {MediaType.TEXT_XML_VALUE, MediaType.ALL_VALUE})
	@ResponseBody
	public String wxPaynotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("wxpay notify start...");
		String ret = new String();
		Map resp = new HashMap();
		try {
	        InputStream inStream = request.getInputStream();
	        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while ((len = inStream.read(buffer)) != -1) {
	            outSteam.write(buffer, 0, len);
	        }
	        outSteam.flush();
	        logger.info("微信支付付款成功");
	        String result = new String(outSteam.toByteArray(), "UTF-8");
	        logger.info("微信支付成功通知: {}", result);

	        Map<String, String> resultMap = WXPayUtil.xmlToMap(result);

            wpResultNotifyService.insert(result);

			try {
				WpOrderPOJO wpOrderPOJO = new WpOrderPOJO();
				String respAppId = resultMap.get("appid");
				wpOrderPOJO.setRespAppId(respAppId);
				String respMchId = resultMap.get("mch_id");
				wpOrderPOJO.setRespMchId(respMchId);
				String outTradeNo = resultMap.get("out_trade_no");
				wpOrderPOJO.setOutTradeNo(outTradeNo);
				String respReturnCode = resultMap.get("return_code");
				wpOrderPOJO.setRespReturnCode(respReturnCode);
				String respResultCode = resultMap.get("result_code");
				wpOrderPOJO.setRespResultCode(respResultCode);
				String respBankType = resultMap.get("bank_type");
				wpOrderPOJO.setRespBankType(respBankType);
				String respCashFee = resultMap.get("cash_fee");
				wpOrderPOJO.setRespCashFee(respCashFee);
				String respTotalFee = resultMap.get("total_fee");
				wpOrderPOJO.setRespTotalFee(respTotalFee);
				String respFeeType = resultMap.get("fee_type");
				wpOrderPOJO.setRespFeeType(respFeeType);
				String respTradeType = resultMap.get("trade_type");
				wpOrderPOJO.setRespTradeType(respTradeType);
				String respIsSubscribe = resultMap.get("is_subscribe");
				wpOrderPOJO.setRespIsSubscribe(respIsSubscribe);
				String respTimeEnd = resultMap.get("time_end");
				wpOrderPOJO.setRespTimeEnd(respTimeEnd);
				String respTransactionId = resultMap.get("transaction_id");
				wpOrderPOJO.setRespTransactionId(respTransactionId);
				wpOrderService.updateByOutTradeNo(wpOrderPOJO);
				
				// send wxpay card by using customer api
				try {
					wpOrderPOJO = wpOrderService.findByOutTradeNo(outTradeNo);
					EcOrderPOJO ecOrderPOJO = null;
					if (wpOrderPOJO != null) {
						ecOrderPOJO = ecOrderService.findById(wpOrderPOJO.getEcOrderId());
					}
					if (ecOrderPOJO != null) {
						// update ecOrder payResult for SUCCESS
						ecOrderPOJO.setPayResult(CommonConstant.WXPAY_ORDER_SUCCESS);
						ecOrderService.updatePayResult(ecOrderPOJO);

						String authorizerAppId = ecOrderPOJO.getEcProductPOJO().getAuthorizerAppId();
						String openId = ecOrderPOJO.getOpenId();
						String cardId = ecOrderPOJO.getEcProductPOJO().getWxCardId();
						if (StringUtils.isBlank(authorizerAppId)) {
							authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
						}
						int quantity = ecOrderPOJO.getQuantity();
						logger.info("openId: {} 共购买{}件商品(卡券), authorizerAppId: {}, cardId: {}" +
										", ecOrderId: {}, outTradeNo: {}"
								, openId, quantity, authorizerAppId, cardId, ecOrderPOJO.getOrderId(), outTradeNo);
						for (int i = 0; i < quantity; i++) {
							String result1 = this.sendCustomMsgWxCard(openId, cardId, authorizerAppId);
						}
						// create wx card init data
						// only test openid insert data
						/// TODO will remove after test success
						if ("ovyahuBNJLdRAS8ta6vFIsk_uo2U".equals(openId)
								|| "ovyahuAY2iNDmTeoetRFtehv_knU".equals(openId)) {
							for (int i = 0; i < quantity; i++) {
								EcWxCardPOJO ecWxCardPOJO = new EcWxCardPOJO();
								ecWxCardPOJO.setAuthorizerAppId(authorizerAppId);
								ecWxCardPOJO.setOpenId(openId);
								ecWxCardPOJO.setUserId(ecOrderPOJO.getUserId());
								ecWxCardPOJO.setEcProductId(ecOrderPOJO.getProductId());
								ecWxCardPOJO.setEcOrderId(ecOrderPOJO.getOrderId());
								ecWxCardPOJO.setWpOrderId(wpOrderPOJO.getWpOrderId());
								ecWxCardPOJO.setCardAcquireFlag(CommonConstant.WX_CARD_UNACQUIRED);
								ecWxCardPOJO.setCardStatusWx(CommonConstant.WX_CARD_STATUS_NORMAL);
								ecWxCardPOJO.setCardId(cardId);
								String description = ";init:unacquired," + DateUtil.toStr(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
								ecWxCardPOJO.setDescription(description);
								ecWxCardService.insert(ecWxCardPOJO);
							}
						}
					}
				} catch (Exception e) {
					logger.error("Send wx pay card exception: ", e);
				}
			} catch (Exception e) {
				logger.error("wpOrderService insert exception: ", e);
			}

	        outSteam.close();
	        inStream.close();
			resp.put("return_code", "SUCCESS");
			resp.put("return_msg", "OK");
			ret = WXPayUtil.mapToXml(resp);
		} catch (Exception e) {
			logger.error("wxpay notify exception: ", e);
		}
		return ret;
	}

	@RequestMapping(value = "/web/wxpay/unifiedorder", method = {RequestMethod.GET})
	public ModelAndView wxPayUnifiedOrder(@RequestParam(value="myFee") Integer myFee,
			@RequestParam(value="authorizerAppId", required = false) String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		String queryString = request.getQueryString();
		String url = request.getRequestURL() + "";
		if (StringUtils.isNotBlank(queryString)) {
			queryString = queryString.split("#")[0];
			url += "?" + queryString;
		}
		HttpSession session = request.getSession();
		WxJsSdkConfigRespApiPOJO wxJsSdkConfigRespApiPOJO = new WxJsSdkConfigRespApiPOJO();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String appId = authorizerAppId;
			String jsSdkTicket = getWxJsSdkTicket(authorizerAppId);
			Long timestamp = System.currentTimeMillis() / 1000;
			String nonceStr = RandomStringUtils.randomAlphanumeric(6);
			//注意这里参数名必须全部小写，且必须有序
	        String string1 = "jsapi_ticket=" + jsSdkTicket +
	                  "&noncestr=" + nonceStr +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;

			String signature = "";
	        try {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());

		        logger.info("string1: {}, signature: {}", string1, signature);
	        } catch (NoSuchAlgorithmException e) {
	            logger.error("MessageDigest exception: ", e);
	        } catch (UnsupportedEncodingException e) {
	            logger.error("MessageDigest exception: ", e);
	        }
			List<String> jsApiList = Arrays.asList(StringUtils.split(messageSource.getMessage("WX.jssdk.jsApiList", null, null), ","));
			
			
			wxJsSdkConfigRespApiPOJO.setAppId(appId);
			wxJsSdkConfigRespApiPOJO.setNonceStr(nonceStr);
			wxJsSdkConfigRespApiPOJO.setTimestamp(timestamp);
			wxJsSdkConfigRespApiPOJO.setSignature(signature);
			wxJsSdkConfigRespApiPOJO.setJsApiList(jsApiList);
			
			wxJsSdkConfigRespApiPOJO.setTicket(jsSdkTicket);
			wxJsSdkConfigRespApiPOJO.setUrl(url);
			
			logger.info("wxJsSdkConfigRespApiPOJO: " + wxJsSdkConfigRespApiPOJO);
			
			
			// 调用wx unified order api获取prepay_id
//			Map unifiedOrderReqMap = new HashMap();
			WxPayUnifiedOrderReqApiPOJO wxPayUnifiedOrderReqApiPOJO = new WxPayUnifiedOrderReqApiPOJO();
			String mchId = "1425213902";
			String body = "deweiyizhan-paytest";
			String outTradeNo = wpOrderService.getNextOutTradeNo();
			String totalFee = myFee + "";
			String spbillCreateIp = "112.29.173.76";
//			String notifyUrl = HttpRequestUtil.getBase(request) + "/api/wxpay/notify";
			String notifyUrl = ConfigurationUtil.getPropertiesConfig().getString("WXPAY.notifyUrl", "http://www.deweiyizhan.com/api/wxpay/notify");
			String tradeType = "JSAPI";
			timestamp = System.currentTimeMillis() / 1000;
			nonceStr = RandomStringUtils.randomAlphanumeric(6);
			wxPayUnifiedOrderReqApiPOJO.setAppId(appId);
			wxPayUnifiedOrderReqApiPOJO.setMchId(mchId);
			wxPayUnifiedOrderReqApiPOJO.setNonceStr(nonceStr);
			wxPayUnifiedOrderReqApiPOJO.setDeviceInfo("WEB");
			wxPayUnifiedOrderReqApiPOJO.setBody(body);
			wxPayUnifiedOrderReqApiPOJO.setAttach("attachtest");
			wxPayUnifiedOrderReqApiPOJO.setOutTradeNo(outTradeNo);
			wxPayUnifiedOrderReqApiPOJO.setTotalFee(totalFee);
			wxPayUnifiedOrderReqApiPOJO.setSpbillCreateIp(spbillCreateIp);
			wxPayUnifiedOrderReqApiPOJO.setNotifyUrl(notifyUrl);
			wxPayUnifiedOrderReqApiPOJO.setTradeType(tradeType);
			
			String openId = (String) session.getAttribute(CommonConstant.PROXY_OPEN_ID);
			wxPayUnifiedOrderReqApiPOJO.setOpenId(openId);
			
			wxPayUnifiedOrderReqApiPOJO.setSignType(WXPayConstants.MD5);

//			unifiedOrderReqMap.put("appid", appId);
//			unifiedOrderReqMap.put("mch_id", mchId);
//			unifiedOrderReqMap.put("nonce_str", nonceStr);
//			unifiedOrderReqMap.put("body", body);
//			unifiedOrderReqMap.put("out_trade_no", outTradeNo);
//			unifiedOrderReqMap.put("total_fee", totalFee);
//			unifiedOrderReqMap.put("spbill_create_ip", spbillCreateIp);
//			unifiedOrderReqMap.put("notify_url", notifyUrl);
//			unifiedOrderReqMap.put("trade_type", "JSAPI");
			Map unifiedOrderRespMap = wxPayService.unifiedOrder(wxPayUnifiedOrderReqApiPOJO);
			try {
				WpOrderPOJO wpOrderPOJO = new WpOrderPOJO();
				org.apache.commons.beanutils.BeanUtils.copyProperties(wpOrderPOJO, wxPayUnifiedOrderReqApiPOJO);
				wpOrderPOJO.setCreateDateTime(new Date());
				wpOrderService.insert(wpOrderPOJO);
			} catch (Exception e) {
				logger.error("wpOrderService insert exception: ", e);
			}
			
			// orderquery
			WxPayOrderQueryReqApiPOJO wxPayOrderQueryReqApiPOJO = new WxPayOrderQueryReqApiPOJO();
			wxPayOrderQueryReqApiPOJO.setAppId(appId);
			wxPayOrderQueryReqApiPOJO.setMchId(mchId);
			wxPayOrderQueryReqApiPOJO.setOutTradeNo(outTradeNo);
			wxPayOrderQueryReqApiPOJO.setNonceStr(nonceStr + 1);
			wxPayOrderQueryReqApiPOJO.setSignType(WXPayConstants.MD5);
			Map orderQueryRespMap = wxPayService.orderQuery(wxPayOrderQueryReqApiPOJO);
			// end orderquery
			
			Map<String, String> jsPayMap = new HashMap<String, String>();
			jsPayMap.put("appId", appId);     //公众号名称，由商户传入     
			jsPayMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");        //时间戳，自1970年以来的秒数     
			jsPayMap.put("nonceStr", nonceStr + "1"); //随机串     
			jsPayMap.put("package", "prepay_id=" + unifiedOrderRespMap.get("prepay_id"));     
			jsPayMap.put("signType", WXPayConstants.MD5);        //微信签名方式：
			jsPayMap = wxPayService.appendSign(jsPayMap);
			jsPayMap.put("prepayId", unifiedOrderRespMap.get("prepay_id") + "");  
			jsPayMap.put("packageUo", "prepay_id=" + unifiedOrderRespMap.get("prepay_id")); 
			
			ret.addObject("orderQueryRespMap", orderQueryRespMap);
			ret.addObject("unifiedOrderRespMap", unifiedOrderRespMap);
			ret.addObject("jsPayMap", jsPayMap);
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
		ret.setViewName("/page/weixin/wx_pay_unified_order");
		return ret;
	}

	@RequestMapping(value = "/web/wxpay/fee", method = {RequestMethod.GET})
	public ModelAndView wxPayFee(
			@RequestParam(value="authorizerAppId", required = false) String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		String queryString = request.getQueryString();
		String url = request.getRequestURL() + "";
		if (StringUtils.isNotBlank(queryString)) {
			queryString = queryString.split("#")[0];
			url += "?" + queryString;
		}
		HttpSession session = request.getSession();
		WxJsSdkConfigRespApiPOJO wxJsSdkConfigRespApiPOJO = new WxJsSdkConfigRespApiPOJO();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String appId = authorizerAppId;
			String jsSdkTicket = getWxJsSdkTicket(authorizerAppId);
			Long timestamp = System.currentTimeMillis() / 1000;
			String nonceStr = RandomStringUtils.randomAlphanumeric(6);
			//注意这里参数名必须全部小写，且必须有序
	        String string1 = "jsapi_ticket=" + jsSdkTicket +
	                  "&noncestr=" + nonceStr +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;

			String signature = "";
	        try {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());

		        logger.info("string1: {}, signature: {}", string1, signature);
	        } catch (NoSuchAlgorithmException e) {
	            logger.error("MessageDigest exception: ", e);
	        } catch (UnsupportedEncodingException e) {
	            logger.error("MessageDigest exception: ", e);
	        }
			List<String> jsApiList = Arrays.asList(StringUtils.split(messageSource.getMessage("WX.jssdk.jsApiList", null, null), ","));
			
			
			wxJsSdkConfigRespApiPOJO.setAppId(appId);
			wxJsSdkConfigRespApiPOJO.setNonceStr(nonceStr);
			wxJsSdkConfigRespApiPOJO.setTimestamp(timestamp);
			wxJsSdkConfigRespApiPOJO.setSignature(signature);
			wxJsSdkConfigRespApiPOJO.setJsApiList(jsApiList);
			
			wxJsSdkConfigRespApiPOJO.setTicket(jsSdkTicket);
			wxJsSdkConfigRespApiPOJO.setUrl(url);
			
			logger.info("wxJsSdkConfigRespApiPOJO: " + wxJsSdkConfigRespApiPOJO);
			
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
		ret.setViewName("/page/weixin/wx_pay_fee");
		return ret;
	}
	
	@RequestMapping(value = "/web/test/{jspFileName}", method = {RequestMethod.GET})
	public ModelAndView testJsSdk(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@PathVariable(value="jspFileName") String jspFileName,
			@RequestParam(value="authorizerAppId", required = false) String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		String queryString = request.getQueryString();
		String url = request.getRequestURL() + "";
		if (StringUtils.isNotBlank(queryString)) {
			queryString = queryString.split("#")[0];
			url += "?" + queryString;
		}
		HttpSession session = request.getSession();
		WxJsSdkConfigRespApiPOJO wxJsSdkConfigRespApiPOJO = new WxJsSdkConfigRespApiPOJO();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String appId = authorizerAppId;
			String jsSdkTicket = getWxJsSdkTicket(authorizerAppId);
			Long timestamp = System.currentTimeMillis() / 1000;
			String nonceStr = RandomStringUtils.randomAlphanumeric(6);
			//注意这里参数名必须全部小写，且必须有序
	        String string1 = "jsapi_ticket=" + jsSdkTicket +
	                  "&noncestr=" + nonceStr +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;
	        logger.info(string1);

			String signature = "";
	        try {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());
	        } catch (NoSuchAlgorithmException e) {
	            logger.error("MessageDigest exception: ", e);
	        } catch (UnsupportedEncodingException e) {
	            logger.error("MessageDigest exception: ", e);
	        }
			List<String> jsApiList = Arrays.asList(StringUtils.split(messageSource.getMessage("WX.jssdk.jsApiList", null, null), ","));
			
			
			wxJsSdkConfigRespApiPOJO.setAppId(appId);
			wxJsSdkConfigRespApiPOJO.setNonceStr(nonceStr);
			wxJsSdkConfigRespApiPOJO.setTimestamp(timestamp);
			wxJsSdkConfigRespApiPOJO.setSignature(signature);
			wxJsSdkConfigRespApiPOJO.setJsApiList(jsApiList);
			
			wxJsSdkConfigRespApiPOJO.setTicket(jsSdkTicket);
			wxJsSdkConfigRespApiPOJO.setUrl(url);
			
			logger.info("wxJsSdkConfigRespApiPOJO: " + wxJsSdkConfigRespApiPOJO);
			
		} catch (Exception e) {
			logger.error("insert error.", e);
		}

		ret.addObject("wxJsSdkConfigRespApiPOJO", wxJsSdkConfigRespApiPOJO);
		ret.setViewName("/page/test/" + jspFileName);
		return ret;
	}


	public String getWxCardSdkTicket(String authorizerAppId) throws Exception {
		String ret = "";
		try {
			String key = CommonConstant.WX_CARD_TICKET + "_" + authorizerAppId;
			String value = CacheUtil.getInstance().get(key);
			if (StringUtils.isNotBlank(value)) {
				return value;
			}

			WxJsSdkTicketRespApiPOJO wxJsSdkTicketRespApiPOJO = getWxCardTicketRespApiPOJO(authorizerAppId);
			int timeToLiveSenconds = wxJsSdkTicketRespApiPOJO.getExpiresIn() - 30 * 60;
			if (timeToLiveSenconds <= 0) {
				timeToLiveSenconds = wxJsSdkTicketRespApiPOJO.getExpiresIn() / 2;
			}
			CacheUtil.getInstance().put(key, wxJsSdkTicketRespApiPOJO.getTicket(), timeToLiveSenconds);
			ret = wxJsSdkTicketRespApiPOJO.getTicket();
		} catch (Exception e) {
			logger.error("Can't getWxJsSdkTicket. ", e);
		}

		return ret;
	}

	/**
	 * Only call weixin api, has call times limit, must cache it
	 * @param authorizerAppId
	 * @return
	 * @throws Exception
	 */
	private WxJsSdkTicketRespApiPOJO getWxCardTicketRespApiPOJO(String authorizerAppId) throws Exception {
		WxJsSdkTicketRespApiPOJO ret = null;

		String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
		if (StringUtils.isNotBlank(authorizerAccessToken)) {
			String myWxJsSdkTicketUrl = wxCardGetTicketUrl
					.replace("ACCESS_TOKEN", authorizerAccessToken);

			String result = HttpClientUtil.get(myWxJsSdkTicketUrl);
			result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
			logger.debug("result: " + result);
			WxJsSdkTicketRespApiPOJO wxJsSdkTicketRespApiPOJO = JsonUtils.convertToJavaBean(result, WxJsSdkTicketRespApiPOJO.class);
			ret = wxJsSdkTicketRespApiPOJO;
		} else {
			logger.error("accessToken must be no null, authorizerAppId: {}", authorizerAppId);
		}

		return ret;
	}
	
	public String getWxJsSdkTicket(String authorizerAppId) throws Exception {
		String ret = "";
		try {
			String key = CommonConstant.WX_JS_SDK_TICKET + "_" + authorizerAppId;
			String value = CacheUtil.getInstance().get(key);
			if (StringUtils.isNotBlank(value)) {
				return value;
			}
			
			WxJsSdkTicketRespApiPOJO wxJsSdkTicketRespApiPOJO = getWxJsSdkTicketRespApiPOJO(authorizerAppId);
			int timeToLiveSenconds = wxJsSdkTicketRespApiPOJO.getExpiresIn() - 30 * 60;
			if (timeToLiveSenconds <= 0) {
				timeToLiveSenconds = wxJsSdkTicketRespApiPOJO.getExpiresIn() / 2;
			}
			CacheUtil.getInstance().put(key, wxJsSdkTicketRespApiPOJO.getTicket(), timeToLiveSenconds);
			ret = wxJsSdkTicketRespApiPOJO.getTicket();
		} catch (Exception e) {
			logger.error("Can't getWxJsSdkTicket. ", e);
		}
		
		return ret;
	}

	/**
	 * Only call weixin api, has call times limit, must cache it
	 * @param authorizerAppId
	 * @return
	 * @throws Exception
	 */
	private WxJsSdkTicketRespApiPOJO getWxJsSdkTicketRespApiPOJO(String authorizerAppId) throws Exception {
		WxJsSdkTicketRespApiPOJO ret = null;
		
		String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
		if (StringUtils.isNotBlank(authorizerAccessToken)) {
			String myWxJsSdkTicketUrl = wxJsSdkTicketUrl
					.replace("ACCESS_TOKEN", authorizerAccessToken);
			
			String result = HttpClientUtil.get(myWxJsSdkTicketUrl);
			result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
			logger.debug("result: " + result);
			WxJsSdkTicketRespApiPOJO wxJsSdkTicketRespApiPOJO = JsonUtils.convertToJavaBean(result, WxJsSdkTicketRespApiPOJO.class);
			ret = wxJsSdkTicketRespApiPOJO;
		} else {
			logger.error("accessToken must be no null, authorizerAppId: {}", authorizerAppId);
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/wx/jssdk/ticket", method = {RequestMethod.GET})
	@ResponseBody
	public WxJsSdkTicketRespApiPOJO jssdkTicket(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestParam(value="authorizerAppId", required = false) String authorizerAppId,
			@RequestBody String requestBody
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		WxJsSdkTicketRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxJsSdkTicketUrl = wxJsSdkTicketUrl
						.replace("TOKEN", authorizerAccessToken);
				
				String result = HttpClientUtil.get(myWxJsSdkTicketUrl);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxJsSdkTicketRespApiPOJO wxJsSdkTicketRespApiPOJO = JsonUtils.convertToJavaBean(result, WxJsSdkTicketRespApiPOJO.class);
				ret = wxJsSdkTicketRespApiPOJO;
			} else {
				logger.error("accessToken is must no null, authorizerAppId: {}", authorizerAppId);
			}
			
		} catch (Exception e) {
			logger.error("insert error.", e);
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/wx/qrcode/ticket", method = {RequestMethod.POST})
	@ResponseBody
	public WxQrCodeTicketRespApiPOJO qrcodeTicket(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestParam(value="authorizerAppId", required = false) String authorizerAppId,
			@RequestBody String requestBody
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		WxQrCodeTicketRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = CommonConstant.DWYZ_AUTHORIZER_APP_ID;
//				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxQrCodeTicketUrl = wxQrCodeTicketUrl
						.replace("TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxQrCodeTicketReqApiPOJO wxQrCodeTicketReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxQrCodeTicketReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxQrCodeTicketReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxQrCodeTicketUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxQrCodeTicketRespApiPOJO wxQrCodeTicketRespApiPOJO = JsonUtils.convertToJavaBean(result, WxQrCodeTicketRespApiPOJO.class);
				ret = wxQrCodeTicketRespApiPOJO;
			} else {
				logger.error("accessToken is must no null, authorizerAppId: {}", authorizerAppId);
			}
			
		} catch (Exception e) {
			logger.error("insert error.", e);
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/wx/qrcode/show", method = {RequestMethod.GET})
	public Void qrcodeShow(@RequestParam(value="ticket") String ticket
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		WxUserInfoApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			/*if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}*/
			
			if (StringUtils.isBlank(ticket)) {
				throw new NullPointerException("ticket must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			
			String myWxQrCodeShowUrl = wxQrCodeShowUrl.replace("TICKET", ticket);
			myRedirectStrategy.sendRedirect(request, response, myWxQrCodeShowUrl);
			
//			String result = HttpClientUtil.get(myWxQrCodeShowUrl);
//
//			result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
//			logger.debug("result: " + result);
//			WxUserInfoApiPOJO wxUserInfoApiPOJO = JsonUtils.convertToJavaBean(result, WxUserInfoApiPOJO.class);
//			
//			ret = wxUserInfoApiPOJO;
			return null;
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return null;
	}
	
	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/user/info/batchget", method = {RequestMethod.POST})
	@ResponseBody
	public WxUserInfoListBatchGetRespApiPOJO userInfoBatchGet(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		WxUserInfoListBatchGetRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myUserInfoUidBatchGetUrl = userInfoUidBatchGetUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxUserInfoListBatchGetReqApiPOJO wxUserInfoListBatchGetReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxUserInfoListBatchGetReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxUserInfoListBatchGetReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myUserInfoUidBatchGetUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxUserInfoListBatchGetRespApiPOJO wxUserInfoListBatchGetRespApiPOJO = JsonUtils.convertToJavaBean(result, WxUserInfoListBatchGetRespApiPOJO.class);
				ret = wxUserInfoListBatchGetRespApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/user/info", method = {RequestMethod.GET})
	@ResponseBody
	public WxUserInfoApiPOJO userInfo(@PathVariable(value="authorizerAppId") String authorizerAppId
			, @RequestParam(value="openId") String openId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		WxUserInfoApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			/*if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}*/
			
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myUserInfoUidUrl = userInfoUidUrl.replace("ACCESS_TOKEN", authorizerAccessToken)
						.replace("OPENID", openId);
				String result = HttpClientUtil.get(myUserInfoUidUrl);

				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxUserInfoApiPOJO wxUserInfoApiPOJO = JsonUtils.convertToJavaBean(result, WxUserInfoApiPOJO.class);
				
				ret = wxUserInfoApiPOJO;
				
				/*ret.addObject("msg", result + "\n" + wxMenuMgrRespApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/user/get", method = {RequestMethod.GET})
	@ResponseBody
	public WxUserGetRespApiPOJO userGet(@PathVariable(value="authorizerAppId") String authorizerAppId
			, @RequestParam(value="nextOpenId") String nextOpenId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		WxUserGetRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			/*if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}*/
			
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myUserGetUrl = userGetUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken)
						.replace("NEXT_OPENID", nextOpenId);
				String result = HttpClientUtil.get(myUserGetUrl);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxUserGetRespApiPOJO wxUserGetRespApiPOJO = JsonUtils.convertToJavaBean(result, WxUserGetRespApiPOJO.class);
				
				ret = wxUserGetRespApiPOJO;
				
				/*ret.addObject("msg", result + "\n" + wxMenuMgrRespApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/tags/getidlist", method = {RequestMethod.POST})
	@ResponseBody
	public WxTagsMgrGetIdListRespApiPOJO tagsMgrGetIdList(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		WxTagsMgrGetIdListRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxTagsMgrGetIdListUrl = wxTagsMgrGetIdListUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxTagsMgrGetIdListReqApiPOJO wxTagsMgrGetIdListReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxTagsMgrGetIdListReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxTagsMgrGetIdListReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxTagsMgrGetIdListUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxTagsMgrGetIdListRespApiPOJO wxTagsMgrGetIdListRespApiPOJO = JsonUtils.convertToJavaBean(result, WxTagsMgrGetIdListRespApiPOJO.class);
				ret = wxTagsMgrGetIdListRespApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/tags/batchuntagging", method = {RequestMethod.POST})
	@ResponseBody
	public BaseWxApiPOJO tagsMgrBatchUnTagging(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		BaseWxApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxTagsMgrBatchUntaggingUrl = wxTagsMgrBatchUntaggingUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxTagsMgrBatchTaggingReqApiPOJO wxTagsMgrBatchTaggingReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxTagsMgrBatchTaggingReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxTagsMgrBatchTaggingReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxTagsMgrBatchUntaggingUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(result, BaseWxApiPOJO.class);
				ret = baseWxApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/tags/batchtagging", method = {RequestMethod.POST})
	@ResponseBody
	public BaseWxApiPOJO tagsMgrBatchTagging(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		BaseWxApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxTagsMgrBatchTaggingUrl = wxTagsMgrBatchTaggingUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxTagsMgrBatchTaggingReqApiPOJO wxTagsMgrBatchTaggingReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxTagsMgrBatchTaggingReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxTagsMgrBatchTaggingReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxTagsMgrBatchTaggingUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(result, BaseWxApiPOJO.class);
				ret = baseWxApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/tags/getUsers", method = {RequestMethod.POST})
	@ResponseBody
	public WxTagsMgrUserRespApiPOJO tagsMgrGetUsers(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		WxTagsMgrUserRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxTagsMgrGetUsersUrl = wxTagsMgrGetUsersUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxTagsMgrUserReqApiPOJO wxTagsMgrUserReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxTagsMgrUserReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxTagsMgrUserReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxTagsMgrGetUsersUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxTagsMgrUserRespApiPOJO wxTagsMgrUserRespApiPOJO = JsonUtils.convertToJavaBean(result, WxTagsMgrUserRespApiPOJO.class);
				ret = wxTagsMgrUserRespApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/tags/delete", method = {RequestMethod.POST})
	@ResponseBody
	public BaseWxApiPOJO tagsMgrDelete(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		BaseWxApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxTagsMgrDeleteUrl = wxTagsMgrDeleteUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxTagsMgrTagDeleteReqApiPOJO wxTagsMgrTagDeleteReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxTagsMgrTagDeleteReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxTagsMgrTagDeleteReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxTagsMgrDeleteUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(result, BaseWxApiPOJO.class);
				ret = baseWxApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/tags/update", method = {RequestMethod.POST})
	@ResponseBody
	public BaseWxApiPOJO tagsMgrUpdate(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		BaseWxApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxTagsMgrUpdateUrl = wxTagsMgrUpdateUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxTagsMgrUpateReqApiPOJO wxTagsMgrUpateReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxTagsMgrUpateReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxTagsMgrUpateReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxTagsMgrUpdateUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(result, BaseWxApiPOJO.class);
				ret = baseWxApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/tags/get", method = {RequestMethod.GET})
	@ResponseBody
	public WxTagsMgrTagsRespApiPOJO tagsMgrGet(@PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		WxTagsMgrTagsRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			/*if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}*/
			
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxTagsMgrGetUrl = wxTagsMgrGetUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);
				String result = HttpClientUtil.get(myWxTagsMgrGetUrl);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxTagsMgrTagsRespApiPOJO wxTagsMgrTagsRespApiPOJO = JsonUtils.convertToJavaBean(result, WxTagsMgrTagsRespApiPOJO.class);
				
				ret = wxTagsMgrTagsRespApiPOJO;
				
				/*ret.addObject("msg", result + "\n" + wxMenuMgrRespApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/wx/third/{authorizerAppId}/tags/create", method = {RequestMethod.POST})
	@ResponseBody
	public WxTagsMgrRespApiPOJO tagsMgrCreate(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		WxTagsMgrRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxTagsMgrCreateUrl = wxTagsMgrCreateUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxTagsMgrReqApiPOJO wxTagsMgrReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxTagsMgrReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxTagsMgrReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxTagsMgrCreateUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxTagsMgrRespApiPOJO wxTagsMgrRespApiPOJO = JsonUtils.convertToJavaBean(result, WxTagsMgrRespApiPOJO.class);
				ret = wxTagsMgrRespApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/third/{authorizerAppId}/menu/conditional/delete", method = {RequestMethod.POST})
	@ResponseBody
	public BaseWxApiPOJO menuMgrConditionalDelete(/*WxMenuMgrReqApiPOJO wxMenuMgrReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView ret = new ModelAndView();
		BaseWxApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxMenuMgrConditionalDeleteUrl = wxMenuMgrConditionalDeleteUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);
				// test request POJO<->requestBody
				WxMenuMgrMenuCondDeleteReqApiPOJO wxMenuMgrMenuCondDeleteReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxMenuMgrMenuCondDeleteReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxMenuMgrMenuCondDeleteReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxMenuMgrConditionalDeleteUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(result, BaseWxApiPOJO.class);
				ret = baseWxApiPOJO;
//				ret.addObject("msg", result + "\n" + baseWxApiPOJO);
//				ret.setViewName("/page/test_info");
			} else {
//				ret.addObject("msg", "没有找到 authorizer access token");
//				ret.setViewName("/page/test_info");
			}
			
		} catch (Exception e) {
//			ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
//			ret.setViewName("/page/test_info");
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/third/{authorizerAppId}/menu/conditional/trymatch", method = {RequestMethod.POST})
	@ResponseBody
	public WxMenuMgrRespApiPOJO menuMgrConditionalTryMatch(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView ret = new ModelAndView();
		
		WxMenuMgrRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxMenuMgrConditionalTryMatchUrl = wxMenuMgrConditionalTryMatchUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);
				// test request POJO<->requestBody
				WxMenuMgrTryMatchReqApiPOJO wxMenuMgrTryMatchReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxMenuMgrTryMatchReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxMenuMgrTryMatchReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxMenuMgrConditionalTryMatchUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxMenuMgrRespApiPOJO wxMenuMgrRespApiPOJO = JsonUtils.convertToJavaBean(result, WxMenuMgrRespApiPOJO.class);
				
				ret = wxMenuMgrRespApiPOJO;
//				ret.addObject("msg", result + "\n" + wxMenuMgrRespApiPOJO);
//				ret.setViewName("/page/test_info");
			} else {
//				ret.addObject("msg", "没有找到 authorizer access token");
//				ret.setViewName("/page/test_info");
			}
			
		} catch (Exception e) {
//			ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
//			ret.setViewName("/page/test_info");
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/third/{authorizerAppId}/menu/conditional/add", method = {RequestMethod.POST})
	@ResponseBody
	public WxMenuMgrMenuCondRespApiPOJO menuMgrConditionalAdd(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView ret = new ModelAndView();
		
		WxMenuMgrMenuCondRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxMenuMgrConditionalAddUrl = wxMenuMgrConditionalAddUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);
				
				// test request POJO<->requestBody
				WxMenuMgrMenuCondReqApiPOJO wxMenuMgrMenuCondReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxMenuMgrMenuCondReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxMenuMgrMenuCondReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxMenuMgrConditionalAddUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxMenuMgrMenuCondRespApiPOJO wxMenuMgrMenuCondRespApiPOJO = JsonUtils.convertToJavaBean(result, WxMenuMgrMenuCondRespApiPOJO.class);
				ret = wxMenuMgrMenuCondRespApiPOJO;
				/*ret.addObject("msg", result + "\n" + wxMenuMgrMenuCondRespApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/third/{authorizerAppId}/menu/create", method = {RequestMethod.POST})
	@ResponseBody
	public BaseWxApiPOJO menuMgrCreate(/*WxMenuMgrCreateReqApiPOJO wxMenuMgrCreateReqApiPOJO*/
			@RequestBody String requestBody
			, @PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		
		BaseWxApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			logger.info("requestBody: {}", requestBody);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxMenuMgrCreateUrl = wxMenuMgrCreateUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);

				// test request POJO<->requestBody
				WxMenuMgrReqApiPOJO wxMenuMgrReqApiPOJO = JsonUtils.convertToJavaBean(requestBody, WxMenuMgrReqApiPOJO.class);
				requestBody = JsonUtils.convertToJson(wxMenuMgrReqApiPOJO);
				
				String result = HttpClientUtil.postHttpsJson(myWxMenuMgrCreateUrl, requestBody);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(result, BaseWxApiPOJO.class);
				ret = baseWxApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs + "<br/>" + e);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/third/{authorizerAppId}/menu/delete", method = {RequestMethod.GET})
	@ResponseBody
	public BaseWxApiPOJO menuMgrDelete(@PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView ret = new ModelAndView();

		BaseWxApiPOJO ret = null;
		
		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			if (StringUtils.isBlank(authorizerAppId)) {
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			/*if (!CommonConstant.DWYZ_AUTHORIZER_APP_ID.equalsIgnoreCase(authorizerAppId)) {
				throw new IllegalArgumentException("authorizerAppId must not be " + authorizerAppId);
			}*/
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxMenuMgrDeleteUrl = wxMenuMgrDeleteUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);
				String result = HttpClientUtil.get(myWxMenuMgrDeleteUrl);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(result, BaseWxApiPOJO.class);
				
				ret = baseWxApiPOJO;
				/*ret.addObject("msg", result + "\n" + baseWxApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/third/{authorizerAppId}/menu/get", method = {RequestMethod.GET})
	@ResponseBody
	public WxMenuMgrRespApiPOJO menuMgrGet(@PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView ret = new ModelAndView();*/
		WxMenuMgrRespApiPOJO ret = null;

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			/*if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}*/
			
			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxMenuMgrGetUrl = wxMenuMgrGetUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);
				String result = HttpClientUtil.get(myWxMenuMgrGetUrl);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxMenuMgrRespApiPOJO wxMenuMgrRespApiPOJO = JsonUtils.convertToJavaBean(result, WxMenuMgrRespApiPOJO.class);
				
				ret = wxMenuMgrRespApiPOJO;
				
				/*ret.addObject("msg", result + "\n" + wxMenuMgrRespApiPOJO);
				ret.setViewName("/page/test_info");*/
			} else {
				/*ret.addObject("msg", "没有找到 authorizer access token");
				ret.setViewName("/page/test_info");*/
			}
			
		} catch (Exception e) {
			/*ret.addObject("msg", uri + "?" + qs);
			ret.setViewName("/page/test_info");*/
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/third/{authorizerAppId}/menu/menuinfo", method = {RequestMethod.GET})
	@ResponseBody
	public DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO> menuMgrMenuInfo(@PathVariable(value="authorizerAppId") String authorizerAppId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO> ret = new DataTablesPOJO<WxMenuMgrMenuInfoRespApiPOJO>();

		String uri = request.getRequestURI();
		String qs = request.getQueryString();
		HttpSession session = request.getSession();
		try {
			/*if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}*/
			

			if (StringUtils.isBlank(authorizerAppId)) {
				throw new NullPointerException("authorizerAppId must not be null");
//				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				String myWxMenuMgrMenuInfoUrl = wxMenuMgrMenuInfoUrl
						.replace("ACCESS_TOKEN", authorizerAccessToken);
				String result = HttpClientUtil.get(myWxMenuMgrMenuInfoUrl);
				result = new String(result.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
				logger.debug("result: " + result);
				WxMenuMgrMenuInfoRespApiPOJO wxMenuMgrMenuInfoRespApiPOJO = JsonUtils.convertToJavaBean(result, WxMenuMgrMenuInfoRespApiPOJO.class);
				
				ret.setSuccess(true);
				List<WxMenuMgrMenuInfoRespApiPOJO> data = new ArrayList<WxMenuMgrMenuInfoRespApiPOJO>();
				data.add(wxMenuMgrMenuInfoRespApiPOJO);
				ret.setData(data);
				ret.setRecordsTotal(data.size());
				
//				ret.addObject("success", true);
//				ret.addObject("description", "SUCCESS");
//				ret.addObject("data", wxMenuMgrMenuRespApiPOJO);
//				
////				ret.addObject("msg", result + "\n" + wxMenuMgrMenuRespApiPOJO);
//				ret.setViewName("/page/test_info");
			} else {
//				ret.addObject("msg", "没有找到 authorizer access token");
//				ret.setViewName("/page/test_info");
			}
			
		} catch (Exception e) {
//			ret.addObject("msg", uri + "?" + qs + "<br/> " + e);
//			ret.setViewName("/page/test_info");
			logger.error("insert error.", e);
			//throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/weixin/wxprize", method = {RequestMethod.GET})
	public ModelAndView wxPrize(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			Long userId = UserUtil.getCurrentUserId();
			logger.info("begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("uri: " + uri + ", qs: " + qs);
			ret.setViewName("/page/weixin/wx_prize");
		} catch (Exception e) {
			logger.error("error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/weixin/wxscore", method = {RequestMethod.GET})
	public ModelAndView wxScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			Long userId = UserUtil.getCurrentUserId();
			logger.info("begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("uri: " + uri + ", qs: " + qs);
			ret.setViewName("/page/weixin/wx_score");
		} catch (Exception e) {
			logger.error("error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/weixin/wxcard", method = {RequestMethod.GET})
	public ModelAndView wxCard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			Long userId = UserUtil.getCurrentUserId();
			logger.info("begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("uri: " + uri + ", qs: " + qs);
			ret.setViewName("/page/weixin/wx_card");
		} catch (Exception e) {
			logger.error("error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/weixin/wxactivitys", method = {RequestMethod.GET})
	public ModelAndView wxActivitys(@RequestParam(value="wxIndexCode") String wxIndexCode
			, @RequestParam(value="openId") String openId
			, @RequestParam(value="unionId") String unionId
			, @RequestParam(value="typeCode") Integer typeCode
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			Long userId = UserUtil.getCurrentUserId();
			logger.info("begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("uri: " + uri + ", qs: " + qs);
			ActivitySearchPOJO activitySearchPOJO = new ActivitySearchPOJO();
			activitySearchPOJO.setOpenId(openId);
			activitySearchPOJO.setUnionId(unionId);
			activitySearchPOJO.setWxIndexCode(wxIndexCode);
			activitySearchPOJO.setTypeCode(typeCode);
			List<ActivityPOJO> activityPOJOs4WxPerson = activityService.findActivitys4WxPerson(activitySearchPOJO);
			
			UserPOJO userPOJO = userService.findUserByIndexCode(wxIndexCode);
			activitySearchPOJO = new ActivitySearchPOJO();
			activitySearchPOJO.setUserId(userPOJO.getUserId());
			activitySearchPOJO.setTypeCode(typeCode);
			List<ActivityPOJO> activityPOJOs = activityService.findActives(activitySearchPOJO);
			
			ret.addObject("activityPOJOs4WxPerson", activityPOJOs4WxPerson);
			ret.addObject("activityPOJOs", activityPOJOs);
			ret.setViewName("/page/weixin/wx_activitys");
		} catch (Exception e) {
			logger.error("wxLinkUserCenter error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/usercenter/{wxIndexCode}/person", method = {RequestMethod.GET})
	public ModelAndView wxPersonUserCenter(@PathVariable(value="wxIndexCode") String wxIndexCode, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			logger.info("wxLinkUserCenter begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("wxLinkUserCenter uri: " + uri + ", qs: " + qs);
			
			UserUtil.getCurrentUserId();
			WxUserInfoApiPOJO wxUserInfoApiPOJO = (WxUserInfoApiPOJO) session.getAttribute(CommonConstant.WX_USER_INFO_API_POJO);
			
			ret.addObject(CommonConstant.WX_USER_INFO_API_POJO, wxUserInfoApiPOJO);
//			ret.addObject("wxActivitys4ApplyUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxactivitys" + "?wxIndexCode=" + wxIndexCode
//					+ "&openId=" + session.getAttribute(CommonConstant.OPEN_ID)
//					+ "&unionId=" + session.getAttribute(CommonConstant.UNION_ID)
//					+ "&typeCode=" + 1);
//			ret.addObject("wxActivitys4SurveyUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxactivitys" + "?wxIndexCode=" + wxIndexCode
//					+ "&openId=" + session.getAttribute(CommonConstant.OPEN_ID)
//					+ "&unionId=" + session.getAttribute(CommonConstant.UNION_ID)
//					+ "&typeCode=" + 2);

			ret.addObject("wxScoreUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxscore");
//			ret.addObject("wxPrizeUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxprize");
			ret.addObject("wxCardUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxcard");
			ret.setViewName("/page/weixin/wx_person_user_center");
		} catch (Exception e) {
			logger.error("wxLinkUserCenter error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/third/authorizer/qrcode", method = {RequestMethod.GET})
	public ModelAndView wxQrcode(@RequestParam(value="authorizerAppId", required=false) String authorizerAppId
			, @RequestParam(value="errorCode", required=false) String errorCode
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
			wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(authorizerAppId);
			WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = new WxAuthorizerInfoPOJO();
			List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
			if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
				wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(0);
				
				if (StringUtils.isBlank(wxAuthorizerInfoPOJO.getQrcodeFilePath())) {
					// 生成qrcodeFilePath
					String toDir = messageSource.getMessage("files.directory", null, null);
					String toFilePath = "wx/authorizer/qrcode/" + wxAuthorizerInfoPOJO.getAuthorizerAppId() + ".jpg";
					toFilePath = toFilePath.replace("/", File.separator);
					HtmlConvertedPOJO htmlConvertedPOJO = FileUtil.url2File(wxAuthorizerInfoPOJO.getQrcodeUrl(), toDir, toFilePath);
					wxAuthorizerInfoPOJO.setQrcodeFilePath(htmlConvertedPOJO.getHtmlPath());
					
					WxAuthorizerInfoPOJO temp = new WxAuthorizerInfoPOJO();
					temp.setWxAuthorizerInfoId(wxAuthorizerInfoPOJO.getWxAuthorizerInfoId());
					temp.setQrcodeFilePath(htmlConvertedPOJO.getHtmlPath());
					
					wxAuthorizerInfoService.update(temp);
				}
				
				ret.addObject("wxAuthorizerInfoPOJO", wxAuthorizerInfoPOJO);
				String documentTitle = wxAuthorizerInfoPOJO.getNickName();
				ret.addObject("documentTitle", documentTitle);
				ret.addObject("errorCode", errorCode);
				ret.setViewName("/page/weixin/authorizer_qrcode");
				
			}
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}

	/**
	 * paramters: unionId, authorizerAppId, openId, proxyAuthorizerAppId, proxyOpenId
	 * @param wxPersonUserSearchPOJO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/wx/oauth2/third/web/subscribe", method = {RequestMethod.POST})
	@ResponseBody
	public StatusPOJO subscribe(WxPersonUserSearchPOJO wxPersonUserSearchPOJO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
//			String unionId = wxPersonUserSearchPOJO.getUnionId();
			String authorizerAppId = wxPersonUserSearchPOJO.getAuthorizerAppId();
			String openId = wxPersonUserSearchPOJO.getOpenId();
			String proxyAuthorizerAppId = wxPersonUserSearchPOJO.getProxyAuthorizerAppId();
			String proxyOpenId = wxPersonUserSearchPOJO.getProxyOpenId();

			HttpSession session = request.getSession();
			/*if (StringUtils.isBlank(unionId)) {
				unionId = (String) session.getAttribute(CommonConstant.UNION_ID);
			}*/
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			if (StringUtils.isBlank(openId)) {
				openId = (String) session.getAttribute(CommonConstant.OPEN_ID);
			}
			
			
			/*RelWxPuOpenPOJO relWxPuOpenPOJO = relWxPuOpenService.findWithPu(unionId, authorizerAppId);*/
			WxPersonUserSearchPOJO tmPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
			List<WxPersonUserPOJO> wxPersonUserPOJOs = null;
			if (StringUtils.isNotBlank(openId)) {
				tmPersonUserSearchPOJO.setOpenId(openId);
				wxPersonUserPOJOs = wxPersonUserService.finds(tmPersonUserSearchPOJO);
			}
			
			
			if (StringUtils.isBlank(openId) || CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
				ret.setSuccess(false);
				ret.setDesc("没有关注");
				return ret;
			}
			WxPersonUserPOJO wxPersonUserPOJO = wxPersonUserPOJOs.get(0);	//获取第一个 微信个人用户信息
			
			/*WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
			wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(authorizerAppId);
			List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenService.finds(wxAuthorizerRefreshTokenSearchPOJO);
			WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = new WxAuthorizerRefreshTokenPOJO();
			if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
				wxAuthorizerRefreshTokenPOJO = wxAuthorizerRefreshTokenPOJOs.get(0);
			}*/
			WxUserInfoApiPOJO wxUserInfoApiPOJO = this.getWxUserInfoApi(authorizerAppId, wxPersonUserPOJO.getOpenId());
			if (wxUserInfoApiPOJO != null && wxUserInfoApiPOJO.getSubscribe() == CommonConstant.WX_SUBSCRIBE) {
				ret.setSuccess(true);
				ret.setDesc("已经关注：" + wxUserInfoApiPOJO.getOpenId());
			} else {
				ret.setSuccess(false);
				ret.setDesc("没有关注");
			}
		} catch (Exception e) {
			logger.error("insert error: {}", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
			throw e;
		}
		
		return ret;
	}
	
	public Boolean getSubscribeFlag(String authorizerAppId, String openId) {
		Boolean ret = false;
		try {
			WxUserInfoApiPOJO wxUserInfoApiPOJO = this.getWxUserInfoApi(authorizerAppId, openId);
			if (wxUserInfoApiPOJO != null && wxUserInfoApiPOJO.getSubscribe() == CommonConstant.WX_SUBSCRIBE) {
				ret = true;
			}
		} catch (Exception e) {
			logger.error("getSubscribeFlag exception: ", e);
		}
		return ret;
	}
	
	public WxUserInfoApiPOJO getWxUserInfoApi(String authorizerAppId, String openId) throws Exception {
		WxUserInfoApiPOJO ret = null;
		try {
			WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
			wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(authorizerAppId);
			List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenService.finds(wxAuthorizerRefreshTokenSearchPOJO);
			WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = new WxAuthorizerRefreshTokenPOJO();
			if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
				wxAuthorizerRefreshTokenPOJO = wxAuthorizerRefreshTokenPOJOs.get(0);
			}
			ret = this.getWxUserInfoApi(wxAuthorizerRefreshTokenPOJO.getAuthorizerAccessToken(), openId, null);
		} catch (Exception e) {
			logger.error("getWxUserInfoApi exception: ", e);
		}
		return ret;
	}
	
	private WxUserInfoApiPOJO getWxUserInfoApi(String authorizerAccessToken, String openId, String thirdWebAccessToken) throws Exception {
		String userInfo = "";
		WxUserInfoApiPOJO wxUserInfoApiPOJO = null;
		String nickname = "";
		String country = "";
		String province = "";
		String city = "";
		
		if (StringUtils.isBlank(openId)) {
			return null;
		}
		try {
			if (StringUtils.isNotBlank(authorizerAccessToken)) {
				int count = 0;
				do {
					try {
						String myUserInfoUidUrl = userInfoUidUrl.replace("ACCESS_TOKEN", authorizerAccessToken)
								.replace("OPENID", openId);
						userInfo = HttpClientUtil.get(myUserInfoUidUrl);
						wxUserInfoApiPOJO = JsonUtils.convertToJavaBean(userInfo, WxUserInfoApiPOJO.class);
						if (wxUserInfoApiPOJO.getSubscribe() == CommonConstant.WX_SUBSCRIBE) {
							nickname = wxUserInfoApiPOJO.getNickname();
							country = wxUserInfoApiPOJO.getCountry();
							province = wxUserInfoApiPOJO.getProvince();
							city = wxUserInfoApiPOJO.getCity();
							wxUserInfoApiPOJO.setNickname(new String(nickname.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
							wxUserInfoApiPOJO.setCountry(new String(country.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
							wxUserInfoApiPOJO.setProvince(new String(province.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
							wxUserInfoApiPOJO.setCity(new String(city.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
						}
					} catch (Exception e) {
						logger.error("myUserInfoUidUrl exception: {}", e);
					} finally {
						count++;
						if (wxUserInfoApiPOJO == null) {
							Thread.sleep(1000);
						}
					}
				} while (wxUserInfoApiPOJO == null && count < 2);
				
			}
			
			if ((wxUserInfoApiPOJO == null || wxUserInfoApiPOJO.getSubscribe() != CommonConstant.WX_SUBSCRIBE)
					&& StringUtils.isNotBlank(thirdWebAccessToken)) {
				// get user info without subscribe
				logger.info("Get user info without subscribe");
				String profileUrl = myProfileUrl.replace("ACCESS_TOKEN", thirdWebAccessToken)
									.replace("OPENID", openId);
				userInfo = HttpClientUtil.get(profileUrl);
				WxUserApiPOJO wxUserPOJO = JsonUtils.convertToJavaBean(userInfo, WxUserApiPOJO.class);
				nickname = wxUserPOJO.getNickname();
				country = wxUserPOJO.getCountry();
				province = wxUserPOJO.getProvince();
				city = wxUserPOJO.getCity();
				wxUserPOJO.setNickname(new String(nickname.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
				wxUserPOJO.setCountry(new String(country.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
				wxUserPOJO.setProvince(new String(province.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
				wxUserPOJO.setCity(new String(city.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
				if (wxUserInfoApiPOJO == null) {
					wxUserInfoApiPOJO = new WxUserInfoApiPOJO();
				}
				BeanUtils.copyProperties(wxUserPOJO, wxUserInfoApiPOJO);
			}
		} catch (Exception e) {
			logger.error("myUserInfoUidUrl exception: {}", e);
		}
		
		logger.info("wxUserInfoApiPOJO: {}", wxUserInfoApiPOJO);
		return wxUserInfoApiPOJO;
	}
	
	// 微信个人用户web页面登录的redirect url, 获取用户的信息(OPEN_ID)
	// 1. 如果是代理, 则authorizerUserNameVice必存在, 
	// 2. loginVice和openIdVice只会存在一个
	// 3. @20171206, 以后都用DWYZ代理
	@RequestMapping(value = "/web/wx/oauth2/third/web/authCode"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView wxWebAuthCode(@RequestParam(value="code", required=false) String code
			, @RequestParam(value="state", required=false) String state
			, @RequestParam(value="appid", required=false) String appid
			, @RequestParam(value="loginVice", required=false) String loginVice
			, @RequestParam(value="openIdVice", required=false) String openIdVice
			, @RequestParam(value="authorizerUserNameVice", required=false) String authorizerUserNameVice
			, @RequestParam(value="typeCode", required=false) String typeCode
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			logger.info("login begin...");
			logger.info("code: {}, state: {}, appid: {}, loginVice: {}, openIdVide: {}, authorizerUserNameVice: {}, typeCode: {}"
					, code, state, appid, loginVice, openIdVice, authorizerUserNameVice, typeCode);
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login uri: " + uri + ", qs: " + qs);
			if (StringUtils.isBlank(code)) {
				logger.info("code must not be null");
				throw new NullPointerException("code must not be null");
			}
			if (StringUtils.isBlank(authorizerUserNameVice)) {
				logger.error("authorizerUserNameVice must not be null");
				throw new NullPointerException("authorizerUserNameVice must not be null");
			}
			
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
			
			String myWxThirdWebAccessTokenUrl = wxThirdWebAccessTokenUrl
					.replace("COMPONENT_APPID", wxThirdClientId)
					.replace("APPID", appid)
					.replace("CODE", code)
					.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken);
			String result = HttpClientUtil.get(myWxThirdWebAccessTokenUrl);
			WxOauth2TokenApiPOJO wxOauth2TokenPOJO = JsonUtils.convertToJavaBean(result, WxOauth2TokenApiPOJO.class);
			// get user info with subscribe
			logger.info("Get user info with subscribe");
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(appid);

			// get user info unionID
			WxUserInfoApiPOJO wxUserInfoApiPOJO = this.getWxUserInfoApi(authorizerAccessToken, 
					wxOauth2TokenPOJO.getOpenId(), wxOauth2TokenPOJO.getAccessToken());
			
			// 通过代理取得的openId
			String proxyOpenId = wxUserInfoApiPOJO.getOpenId();
			// use unionId instead of proxyOpenId to login
			String unionId = wxUserInfoApiPOJO.getUnionId();
			
			logger.info("wxUserInfoApiPOJO: {}", wxUserInfoApiPOJO);
			
			// insert Wx person user into DB
//			UserPOJO userPOJO1 = userService.findUserByName(proxyOpenId);
			UserPOJO userPOJO1 = userService.findUserByName(unionId);
			UserPOJO userPOJO = new UserPOJO();
			if (userPOJO1 == null) {
				// use unionId instead of proxyOpenId
				/*userPOJO.setUsername(proxyOpenId);
				userPOJO.setPassword(proxyOpenId);*/
				userPOJO.setUsername(unionId);
				String password = unionId + unionId.substring(0, 2);
				userPOJO.setPassword(password);
				userPOJO.setUserType(MyUser.PERSON);
				userPOJO.setNickname(wxUserInfoApiPOJO.getNickname());
				userService.insert(userPOJO);
			} else {
				BeanUtils.copyProperties(userPOJO1, userPOJO);
			}
			
			WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = new WxAuthorizerInfoPOJO();
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO =  new WxAuthorizerInfoSearchPOJO();
			// Deal appid is proxy id case
			String authorizerAppIdVice = "";
			String indexCode = "";
			if (StringUtils.isNotBlank(authorizerUserNameVice)) {
				wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
				wxAuthorizerInfoSearchPOJO.setUserName(authorizerUserNameVice);
				List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
				if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
					if (wxAuthorizerInfoPOJOs.size() > 1) {
						logger.error("存在多个WxAuthorizerInfoPOJO, Authorizer UserName: {}", authorizerUserNameVice);
					}
					wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(0);
					authorizerAppIdVice = wxAuthorizerInfoPOJO.getAuthorizerAppId();

					// get indexCode and set into session
					wxAuthorizerInfoSearchPOJO =  new WxAuthorizerInfoSearchPOJO();
					wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(authorizerAppIdVice);
					List<RelWxIndexMapPOJO> relWxIndexMapPOJOs = relWxIndexMapService.findsByAuthorizerInfo(wxAuthorizerInfoSearchPOJO);
					if (!CollectionUtils.isEmpty(relWxIndexMapPOJOs)) {
						RelWxIndexMapPOJO relWxIndexMapPOJO = relWxIndexMapPOJOs.get(0);
						indexCode = relWxIndexMapPOJO.getWxIndexCode();
						session.setAttribute(CommonConstant.INDEX_CODE, indexCode);
					}
				}
			}
			
			// Deal wxPersonUser
			WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
//				wxPersonUserSearchPOJO.setUnionId(wxUserInfoApiPOJO.getUnionId());
//			wxPersonUserSearchPOJO.setOpenId(wxUserInfoApiPOJO.getOpenId());
//			wxPersonUserSearchPOJO.setUserId(userPOJO.getUserId());
			wxPersonUserSearchPOJO.setProxyOpenId(proxyOpenId);
			wxPersonUserSearchPOJO.setAuthorizerAppId(authorizerAppIdVice);
			List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
			WxPersonUserPOJO wxPersonUserPOJO = new WxPersonUserPOJO();
			if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
				BeanUtils.copyProperties(wxUserInfoApiPOJO, wxPersonUserPOJO);
				
				List<String> tagidList = wxUserInfoApiPOJO.getTagidList();
				String tagidListStr = "";
				if (!CollectionUtils.isEmpty(tagidList)) {
					tagidListStr = CollectionUtilx.nullSafeToString(tagidList);
				}
				wxPersonUserPOJO.setTagidList(tagidListStr);
				
				// 通过代理取得的微信个人用户信息
				wxPersonUserPOJO.setUserId(userPOJO.getUserId());
				wxPersonUserPOJO.setAuthorizerAppId(authorizerAppIdVice);
				wxPersonUserPOJO.setOpenId(null);
				wxPersonUserPOJO.setProxyOpenId(proxyOpenId);
				wxPersonUserPOJO.setProxyAuthorizerAppId(appid);
				wxPersonUserService.insert(wxPersonUserPOJO);
			} else {
				wxPersonUserPOJO = wxPersonUserPOJOs.get(0);
			}
			
			
			// @10102016 如果openIdVice不是空, 绑定用户
			if (StringUtils.isNotBlank(openIdVice)) {
				
//				userPOJO1 = userService.findUserByName(openIdVice);
//				userPOJO = new UserPOJO();
//				if (userPOJO1 == null) {
//					userPOJO.setUsername(openIdVice);
//					userPOJO.setPassword(openIdVice);
//					userPOJO.setUserType(MyUser.PERSON);
//					userPOJO.setNickname(wxUserInfoApiPOJO.getNickname());
//					userService.insert(userPOJO);
//				} else {
//					BeanUtils.copyProperties(userPOJO1, userPOJO);
//				}

				WxPersonUserPOJO temp = new WxPersonUserPOJO();
				temp.setWxPersonUserId(wxPersonUserPOJO.getWxPersonUserId());
				temp.setOpenId(openIdVice);
				temp.setMemberFlag(CommonConstant.MEMBER_FLAG);
				wxPersonUserService.update(temp);
				// fix openId is null issue
				wxPersonUserPOJO.setOpenId(openIdVice);
				wxPersonUserPOJO.setMemberFlag(CommonConstant.MEMBER_FLAG);
				
//				String msg = "proxyOpenId: " + proxyOpenId + ", nickname: " + wxUserInfoApiPOJO.getNickname()
//						+ "\n" + "wxUserInfoApiPOJO: \n" + wxUserInfoApiPOJO + "\n"
//						/*+ "MyUserInfoUid: \n" + myUserInfoUid*/;
//				msg += ", openIdVice: " + openIdVice + "\n <br/>";
//				msg += wxPersonUserPOJO + "\n <br/>";
//				ret.addObject("msg", msg);
//				
//				session.setAttribute("msg", msg);
//
//				ret.addObject("addVip", "ADD_VIP");
//				ret.addObject("msg", "加入会员成功");
				
//				ret.setViewName("/page/oauth2_success");
			} // openIdVice end
			
			if (StringUtils.isNotBlank(loginVice)) {
				// 根据authorizerUserNameVice 和 proxyOpenId 获取user
//				wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
//				wxPersonUserSearchPOJO.setWxAuthorizerUserName(authorizerUserNameVice);
//				wxPersonUserSearchPOJO.setProxyOpenId(proxyOpenId);
//				
//				wxPersonUserPOJOs = wxPersonUserService.findFulls(wxPersonUserSearchPOJO);
//				if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
//					logger.error("根据authorizerUserNameVice: {} 和 proxyOpenId: {} 获取user 失败"
//								, authorizerUserNameVice, wxUserInfoApiPOJO.getOpenId());
//					String msg = "您还不是该公众号的会员，请关注该公众号并加入会员" ;
//					ret.addObject("msg", msg);
//					
//					session.setAttribute("msg", msg);
//					myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) 
//							+ "/web/wx/oauth2/third/authorizer/qrcode?authorizerAppId=" + authorizerAppIdVice
//							+ "&errorCode=" + "NOTVIP");
//					return null;
//				}
				/*wxPersonUserPOJO = wxPersonUserPOJOs.get(0);
				openIdVice = wxPersonUserPOJO.getOpenId();
				userPOJO1 = userService.findUserByName(openIdVice);
				userPOJO = new UserPOJO();
				if (userPOJO1 == null) {
					userPOJO.setUsername(openIdVice);
					userPOJO.setPassword(openIdVice);
					userPOJO.setUserType(MyUser.PERSON);
					userPOJO.setNickname(wxUserInfoApiPOJO.getNickname());
					userService.insert(userPOJO);
				} else {
					BeanUtils.copyProperties(userPOJO1, userPOJO);
				}*/
				
				/*wxPersonUserPOJO = new WxPersonUserPOJO();
				BeanUtils.copyProperties(wxUserInfoApiPOJO, wxPersonUserPOJO);
				wxPersonUserPOJO.setOpenId(openIdVice);
				wxPersonUserPOJO.setProxyOpenId(wxUserInfoApiPOJO.getOpenId());
				wxPersonUserPOJO.setProxyAuthorizerAppId(appid);
				wxPersonUserPOJO.setUserId(userPOJO.getUserId());*/
				
				/*wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
//					wxPersonUserSearchPOJO.setUnionId(wxUserInfoApiPOJO.getUnionId());
				wxPersonUserSearchPOJO.setUserId(userPOJO.getUserId());
				wxPersonUserSearchPOJO.setOpenId(openIdVice);
				wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
				wxPersonUserPOJO = new WxPersonUserPOJO();
				if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
					BeanUtils.copyProperties(wxUserInfoApiPOJO, wxPersonUserPOJO);
					
					List<String> tagidList = wxUserInfoApiPOJO.getTagidList();
					String tagidListStr = "";
					if (!CollectionUtils.isEmpty(tagidList)) {
						tagidListStr = CollectionUtilx.nullSafeToString(tagidList);
					}
					wxPersonUserPOJO.setTagidList(tagidListStr);

					wxPersonUserPOJO.setOpenId(openIdVice);
					wxPersonUserPOJO.setProxyOpenId(wxUserInfoApiPOJO.getOpenId());
					wxPersonUserPOJO.setProxyAuthorizerAppId(appid);
					wxPersonUserPOJO.setUserId(userPOJO.getUserId());
					wxPersonUserPOJO.setAuthorizerAppId(authorizerAppIdVice);
					wxPersonUserService.insert(wxPersonUserPOJO);
				} else {
					wxPersonUserPOJO = wxPersonUserPOJOs.get(0);
					// 可能存在proxy openId/authorizerAppId 为空, 如果为空, 则更新
					if (StringUtils.isBlank(wxPersonUserPOJO.getProxyOpenId())
							|| StringUtils.isBlank(wxPersonUserPOJO.getProxyAuthorizerAppId())) {
						wxPersonUserPOJO.setProxyOpenId(wxUserInfoApiPOJO.getOpenId());
						wxPersonUserPOJO.setProxyAuthorizerAppId(appid);
						wxPersonUserService.update(wxPersonUserPOJO);
					}
				}*/
			}	// loginVice不是空, end
			

			MyUser myUser = userService.createPrincipalByName(userPOJO.getUsername(), session);
			
			// 微信用户信息放入session
			session.setAttribute(CommonConstant.WX_USER_INFO_API_POJO, wxUserInfoApiPOJO);
			session.setAttribute(CommonConstant.OPEN_ID, wxPersonUserPOJO.getOpenId());
			session.setAttribute(CommonConstant.UNION_ID, wxPersonUserPOJO.getUnionId());
			session.setAttribute(CommonConstant.AUTHORIZER_APP_ID, wxPersonUserPOJO.getAuthorizerAppId());

			session.setAttribute(CommonConstant.CURRENT_WX_PERSON_USER, wxPersonUserPOJO);
			
			session.setAttribute(CommonConstant.PROXY_OPEN_ID, proxyOpenId);
			session.setAttribute(CommonConstant.PROXY_AUTHORIZER_APP_ID, appid);
			

			// maybe duplicate code
			session.setAttribute("username", myUser.getUsername());
			session.setAttribute("userType", myUser.getUserType());
			session.setAttribute("myUser", myUser);
//			String openId = (String) session.getAttribute(CommonConstant.OPEN_ID);
//			String unionId = (String) session.getAttribute(CommonConstant.UNION_ID);
			
			logger.info("wxWebAuthCode, Session attributes: {}", HttpRequestUtil.getSessionAttributeNameValue(session));
			String ecommerceUrl1 = (String) session.getAttribute(CommonConstant.ECOMMERCE_URL_1);
			if (StringUtils.isNotBlank(ecommerceUrl1)) {
				CacheUtil.getInstance().put(proxyOpenId + "," + appid, ecommerceUrl1, 5 * 60);
			}
			
			String openId =  wxPersonUserPOJO.getOpenId();
			if (StringUtils.isNotBlank(proxyOpenId)) {
				myUser.setProxyOpenId(proxyOpenId);
			}
			if (StringUtils.isNotBlank(openId)) {
				myUser.setOpenId(openId);
			}
			if (StringUtils.isNotBlank(unionId)) {
				myUser.setUnionId(unionId);
			}

			SavedRequest savedRequest = HttpRequestUtil.getRequest(request, response);
			String url = "";
			if (savedRequest != null) {
				url  = savedRequest.getRedirectUrl();
			} else {
				if (MyUser.ADMIN.equals(myUser.getUserType())) {
					url = UserController.URL_ADMIN;
				} else if (MyUser.MEDIA.equalsIgnoreCase(myUser.getUserType())) {
					url = UserController.URL_INDEX;
				} else if (MyUser.ENTERPRISE.equalsIgnoreCase(myUser.getUserType())) {
					url = UserController.URL_INDEX;
				} else {
//					url = "/web/wx/usercenter/"  + indexCode + "/person";
					url = "/page/wx_auto_close.jsp";
				}
			}
			
			if (StringUtils.isNotBlank(openIdVice)) {
//				url = "/web/wx/usercenter/"  + indexCode + "/person";
				url = "/page/wx_auto_close.jsp";
			}
			
			if (StringUtils.isNotBlank(typeCode) && typeCode.equalsIgnoreCase(CommonConstant.BIND_MEMBER_TYPE_LOTTERY_VOICE)) {
				url = "/page/wx_auto_close.jsp";

				String content = "恭喜您加入会员成功, 可以开始活动了!🎉";
				String authorizerAppId = wxAuthorizerInfoPOJO.getAuthorizerAppId();
				KfMsgSendThread kfMsgSendThread = new KfMsgSendThread(openId, content, authorizerAppId, this);
				kfMsgSendThread.start();
			} else if (StringUtils.isNotBlank(openIdVice)) {
				String detail = "请点击👉"
						+ "<a href=\"" + HttpRequestUtil.getBase(request)
								+ "/web/wx/usercenter/"  + indexCode + "/person"
						+ "\">会员中心</a>";
				String content = "恭喜您加入会员成功!🎉";
				content += "" + detail;
				String authorizerAppId = wxAuthorizerInfoPOJO.getAuthorizerAppId();
				KfMsgSendThread kfMsgSendThread = new KfMsgSendThread(openId, content, authorizerAppId, this);
				kfMsgSendThread.start();
			}

			
			//有一个设置session的代码, 所有最后再跳转
			myRedirectStrategy.sendRedirect(request, response, url);

//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("deal person user login/bind/proxyOpenId error.", e);
			throw e;
		}
		
		return null;
//		return ret;
	}

	@RequestMapping(value = "/web/wx/oauth2/third/personUser/login")
	public ModelAndView wxThirdPersonUserLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			String wxThirdPersonUserLoginUrl = "";
			if (wxAuthorizerInfoPOJO != null) {
				wxWebLoginUrl = wxThirdWebAuthorizeUrl
				.replace("COMPONENT_APPID", wxThirdClientId)
				.replace("APPID", wxAuthorizerInfoPOJO.getAuthorizerAppId())
				.replace("REDIRECT_URI", wxThirdWebRedirectUrl)
				.replace("SCOPE", scope)
				.replace("STATE", RandomStringUtils.randomAlphabetic(6))
				;
				/*wxEncodeUrl = wxWebLoginUrl;
				wxWebLoginUrl = myRedirectStrategy.encodeQueryParam(wxWebLoginUrl);
				wxEncodeUrl = myRedirectStrategy.encodeUrl(response, wxEncodeUrl);*/
				
				wxThirdPersonUserLoginUrl = wxWebLoginUrl;
				wxThirdPersonUserLoginUrl = myRedirectStrategy.encodeQueryParam(wxThirdPersonUserLoginUrl);
			}
			
			/*ret.addObject("wxWebLoginUrl", wxWebLoginUrl);
			ret.addObject("wxEncodeUrl", wxEncodeUrl);*/
			
			ret.addObject("wxThirdPersonUserLoginUrl", wxThirdPersonUserLoginUrl);
			
			ret.setViewName("/wx_third_person_user_login");
		} catch (Exception e) {
			logger.error("wx_third_person_user_login error.", e);
			throw e;
		}
		
		return ret;
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
			@RequestParam(value="userId", required=false) Long userId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			
			String msg = "";
			Boolean success = true;
			
			//Long userId = UserUtil.getCurrentUserId();
			logger.info("authorizerInfo begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("authorizerInfo uri: " + uri + ", qs: " + qs);
			
			logger.info("componentAppId: {}, authorizerAppId: {}, userId: {}", componentAppId, authorizerAppId, userId);
			
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
			wxThirdAuthorizerInfo = new String(wxThirdAuthorizerInfo.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);

			logger.debug("wxThirdAuthorizerInfo: " + wxThirdAuthorizerInfo);
			
			WxAuthorizerInfoApiPOJO wxAuthorizerInfoApiPOJO = JsonUtils.convertToJavaBean(wxThirdAuthorizerInfo, WxAuthorizerInfoApiPOJO.class);
			
			// 保存授权者信息到数据库
			com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO2 = new com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO();
			String nickName = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getNickName();
//			wxAuthorizerInfoPOJO2.setNickName(new String(nickName.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
			wxAuthorizerInfoPOJO2.setNickName(nickName);
			String headImg = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getHeadImg();
			wxAuthorizerInfoPOJO2.setHeadImg(headImg);
			Integer serviceTypeInfo = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getServiceTypeInfoPOJO().getId();
			wxAuthorizerInfoPOJO2.setServiceTypeInfo(serviceTypeInfo);
			Integer verifyTypeInfo = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getVerifyTypeInfoPOJO().getId();
			wxAuthorizerInfoPOJO2.setVerifyTypeInfo(verifyTypeInfo);
			String userName = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getUserName();
			wxAuthorizerInfoPOJO2.setUserName(userName);
			String alias = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getAlias();
			wxAuthorizerInfoPOJO2.setAlias(alias);
			String qrcodeUrl = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getQrcodeUrl();
			wxAuthorizerInfoPOJO2.setQrcodeUrl(qrcodeUrl);
			String businessInfo = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenStore()
					 			+ "," + wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenScan()
					 			+ "," + wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenPay()
					 			+ "," + wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenCard()
					 			+ "," + wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getBusinessInfoPOJO().getOpenShake();
			wxAuthorizerInfoPOJO2.setBusinessInfo(businessInfo);
			Integer idc = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getIdc();
			wxAuthorizerInfoPOJO2.setIdc(idc);
			String principalName = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getPrincipalName();
			wxAuthorizerInfoPOJO2.setPrincipalName(principalName);
			String signature = wxAuthorizerInfoApiPOJO.getAuthorizerInfoPOJO().getSignature();
			wxAuthorizerInfoPOJO2.setSignature(signature);
			
			wxAuthorizerInfoPOJO2.setAuthorizerAppId(authorizerAppId);
			
			String authorizerRefreshToken = wxAuthorizerInfoApiPOJO.getAuthorizationInfo4AuthzerPOJO().getAuthorizerRefreshToken();
			wxAuthorizerInfoPOJO2.setAuthorizerRefreshToken(authorizerRefreshToken);
			
			String funcInfo = "";
			List<FuncInfoApiPOJO> funcInfoPOJOs = wxAuthorizerInfoApiPOJO.getAuthorizationInfo4AuthzerPOJO().getFuncInfoPOJOList();
			List<Integer> ids = new ArrayList<Integer>();
			if (!CollectionUtils.isEmpty(funcInfoPOJOs)) {
				for (int i = 0; i < funcInfoPOJOs.size(); i++) {
					ids.add(funcInfoPOJOs.get(i).getFuncscopeCategoryPOJO().getId());
				}
				funcInfo = StringUtils.join(ids, ",");
			}
			wxAuthorizerInfoPOJO2.setFuncInfo(funcInfo);
			wxAuthorizerInfoPOJO2.setCreateDateTime(new Date());
			wxAuthorizerInfoPOJO2.setUserId(userId);
			
			msg = wxThirdAuthorizerInfo + "";
			
			// if exist authorizerAppId, then update
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
			wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(authorizerAppId);
			List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
			if (CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {	// insert
				wxAuthorizerInfoService.insert(wxAuthorizerInfoPOJO2);
				msg += ", 添加成功 ";
			} else {	// update
				if (wxAuthorizerInfoPOJOs.size() > 1) {
					logger.error("存在多个相同的公众号， 请检查数据库和代码。wxAuthorizerInfoPOJOs size: {}", wxAuthorizerInfoPOJOs.size());
				}
				
				// 01/11/2017, 如果此公众号已经被使用, 则不能进行再次绑定
//				msg = "此公众号已经被使用, 您不能进行二次绑定.";
//				success = false;
				WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO3 = wxAuthorizerInfoPOJOs.get(0);
				wxAuthorizerInfoPOJO2.setWxAuthorizerInfoId(wxAuthorizerInfoPOJO3.getWxAuthorizerInfoId());
				wxAuthorizerInfoService.update(wxAuthorizerInfoPOJO2);
				msg += ", 更新成功 ";
			}
			
			ret.addObject("success", success);
			ret.addObject("msg", msg);
			ret.setViewName("/page/oauth2_success");
			/*ret.setViewName("/web/unified/usercenter");*/
		} catch (Exception e) {
			logger.error("authorizerInfo error.", e);
			throw e;
		}
		
//		return null;
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/third/forgetPassword"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView forgetPassword(@RequestParam(value="username", required=true) String username
			, @RequestParam(value="password", required=true) String password
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("authCode begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("authCode uri: " + uri + ", qs: " + qs);

			HttpSession session = request.getSession();
			UserPOJO userPOJO = userService.findUserByName(username);
			
			if (userPOJO == null) {
				myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/login.jsp?errorMsg=usernamenotfound");
				return null;
			} else {
				session.setAttribute("userId", userPOJO.getUserId());
				session.setAttribute("newPassword", password);
			}
			
			Map redirectReqParamMap = new HashMap();
			redirectReqParamMap.put("commonParam", CommonConstant.FORGET_PASSWORD);
			String wxComLoginUrl = WxUtil.getWxComLoginUrl(redirectReqParamMap);
			myRedirectStrategy.sendRedirect(request, response, wxComLoginUrl);
			
		} catch (Exception e) {
			logger.error("authCode error.", e);
			throw e;
		}
		
		return null;
//		return ret;
	}
	
	// 公众号授权给第三方平台时的redirect url, 获取授权公众号的token, 用来为下一步获取授权公众号的信息
	@RequestMapping(value = "/web/wx/oauth2/third/authCode"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView thirdAuthCode(@RequestParam(value="auth_code", required=false) String code
			, @RequestParam(value="commonParam", required=false) String commonParam
			, @RequestParam(value="userId", required=false) Long userId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("authCode begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("authCode uri: " + uri + ", qs: " + qs);

			HttpSession session = request.getSession();
			
			logger.info("Param userId: {}, Session userId: {}", userId, session.getAttribute("userId"));
			
			if (userId == null) {
				userId = (Long) session.getAttribute("userId");
			}
			String newPassword = (String) session.getAttribute("newPassword");
			
			logger.info("Session userId: {}, Session newPassword: {}", userId, newPassword);
			
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
				// 保存授权者refresh token信息进数据库
				WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = new WxAuthorizerRefreshTokenPOJO();
				wxAuthorizerRefreshTokenPOJO.setAuthorizerAccessToken(authorizerAccessToken);
				wxAuthorizerRefreshTokenPOJO.setAuthorizerAppId(authorizerAppId);
				wxAuthorizerRefreshTokenPOJO.setAuthorizerRefreshToken(authorizerRefreshToken);
				wxAuthorizerRefreshTokenPOJO.setComponentAppId(wxThirdClientId);
				wxAuthorizerRefreshTokenPOJO.setExpiresIn(expiresIn);
				wxAuthorizerRefreshTokenPOJO.setCreateDateTime(new Date());
				wxAuthorizerRefreshTokenService.insert(wxAuthorizerRefreshTokenPOJO);
				if (StringUtils.isBlank(commonParam)) {
					// 显示获取授权者信息
					myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/web/wx/oauth2/third/authorizerInfo"
							+ "?componentAppId=" + wxThirdClientId + "&authorizerAppId=" + wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getAuthorizerAppId()
							+ "&userId=" + userId);
					return null;
				} else if (CommonConstant.FORGET_PASSWORD.equalsIgnoreCase(commonParam)) {
					// 写新密码
					WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
					wxAuthorizerInfoSearchPOJO.setAuthorizerAppId(authorizerAppId);
					List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
					if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
						if (wxAuthorizerInfoPOJOs.size() > 1) {
							logger.error("同一个authorizerId: {} 不应该获得多个WxAuthorizerInfoPOJO, size: {}", authorizerAppId, wxAuthorizerInfoPOJOs.size());
						}
						WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(0);
						if (userId != null && newPassword != null) {
							if (userId.longValue() == wxAuthorizerInfoPOJO.getUserId()) {
								// update password
								UserPOJO userPOJO = new UserPOJO();
								userPOJO.setUserId(userId);
								userPOJO.setPassword(newPassword);
								userService.updatePassword(userPOJO);
								// 跳转到登录页面
								myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/login.jsp");
								return null;
							}
						}
					}
				} else if (CommonConstant.REG_ENTERPRISE_USER.equalsIgnoreCase(commonParam)) {
					UserPOJO userPOJO = (UserPOJO) session.getAttribute("regUserPOJO");
					int result = userService.insert(userPOJO);
					MyUser myUser = userService.createPrincipalByName(userPOJO.getUsername(), session);
					
					logger.info("userPOJO userId: {}, myUser userId: {}", userPOJO.getUserId(), myUser.getUserId());
					
					userId = userPOJO.getUserId();
					// 可以用session
					session.setAttribute("userId", userId);
					try {
						// 创建微信首页超链接
						RelWxIndexMapPOJO relWxIndexMapPOJO = new RelWxIndexMapPOJO();
						relWxIndexMapPOJO.setUserId(userPOJO.getUserId());
						String wxIndexCode = userPOJO.getWxIndexCode();
						if (StringUtils.isBlank(wxIndexCode)) {
							wxIndexCode = RandomStringUtils.randomAlphabetic(6);
						}
						relWxIndexMapPOJO.setWxIndexCode(wxIndexCode);
						relWxIndexMapService.insert(relWxIndexMapPOJO);
						
						// 创建文件夹, 用于保存文件/图片/等等
						// 上传文件的代码才需要, 这儿是多余的代码
						/*String fileDirPath = messageSource.getMessage("files.directory", null, null);
						File fileDir = new File(fileDirPath);
						FileUtils.forceMkdir(fileDir);
						if (!fileDirPath.endsWith(File.separator)) {
							fileDirPath += File.separator;
						}
						fileDir = new File(fileDirPath + relWxIndexMapPOJO.getWxIndexCode());
						FileUtils.forceMkdir(fileDir);*/
					} catch (Exception e) {
						logger.error("创建微信首页链接/文件夹失败, userId: {}, wxIndexCode: {}, Exception: {}"
								, userPOJO.getUserId(), userPOJO.getWxIndexCode(), e);
					}
				}
				// 显示获取授权者信息
				myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/web/wx/oauth2/third/authorizerInfo"
						+ "?componentAppId=" + wxThirdClientId + "&authorizerAppId=" + wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getAuthorizerAppId()
						+ "&userId=" + userId);
			
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
	// 返回公众号授权给第三方开放平台的URL
	@RequestMapping(value = "/api/wx/oauth2/comLogin")
	@ResponseBody
	public ModelAndView wxComLogin4Api(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login success begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login success uri: " + uri + ", qs: " + qs);
			
			String wxComLoginUrl = WxUtil.getWxComLoginUrl();
			ret.addObject("wxComLoginUrl", wxComLoginUrl);
//			ret.setViewName("/page/weixin/com_login");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}
	
	// 返回带有公众号授权给第三方开放平台的URL
	@RequestMapping(value = "/web/wx/oauth2/comLogin")
	public ModelAndView wxComLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			logger.info("login success begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("login success uri: " + uri + ", qs: " + qs);
			

			WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchPOJO = new WxComAccessTokenSearchApiPOJO();
			List<WxComAccessTokenApiPOJO> wxComAccessTokenPOJOs = wxComAccessTokenService.finds(wxComAccessTokenSearchPOJO);
			WxComAccessTokenApiPOJO wxComAccessTokenPOJO = null;
			if (!CollectionUtils.isEmpty(wxComAccessTokenPOJOs)) {
				wxComAccessTokenPOJO = wxComAccessTokenPOJOs.get(0);
			}
			
			if (wxComAccessTokenPOJO == null) {
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
				wxComAccessTokenPOJO = JsonUtils.convertToJavaBean(wxComAccessTokenStr, WxComAccessTokenApiPOJO.class);
				wxComAccessTokenPOJO.setCreateDateTime(new Date());
				
				wxComAccessTokenService.insert(wxComAccessTokenPOJO);
			}
			
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
	
	// 得到个人用户登录, 获取个人用户信息和授权确认URL
	private String getWxThirdPersonUserLoginUrl(String fromUserName, String toUserName) {
		String ret = "";
		String extraParam = "&openIdVice=" + fromUserName
				+ "&authorizerUserNameVice=" + toUserName;

		String wxWebLoginUrl = wxThirdWebAuthorizeUrl
		.replace("COMPONENT_APPID", wxThirdClientId)
		.replace("APPID", CommonConstant.PROXY_AUTHORIZER_APP_ID_VALUE)
		.replace("REDIRECT_URI", wxThirdWebRedirectUrl.contains("?") ? 
			wxThirdWebRedirectUrl + extraParam
			: wxThirdWebRedirectUrl + "?abc_event=1" + extraParam)
		.replace("SCOPE", scope)
		.replace("STATE", RandomStringUtils.randomAlphabetic(6))
		;
		
		String wxThirdPersonUserLoginUrl = wxWebLoginUrl;
		wxThirdPersonUserLoginUrl = myRedirectStrategy.encodeQueryParam(wxThirdPersonUserLoginUrl);
		wxThirdPersonUserLoginUrl = wxThirdPersonUserLoginUrl.replace("&openIdVice=", "%26openIdVice%3D")
								.replace("&authorizerUserNameVice=", "%26authorizerUserNameVice%3D");
		ret = wxThirdPersonUserLoginUrl;
		return ret;
	}
	// 得到个人用户登录, 获取个人用户信息和授权确认URL
	private String getWxThirdPersonUserLoginUrl(String fromUserName, String toUserName, String typeCode) {
		String ret = "";
		String extraParam = "&openIdVice=" + fromUserName
				+ "&authorizerUserNameVice=" + toUserName
				+ "&typeCode=" + typeCode;

		String wxWebLoginUrl = wxThirdWebAuthorizeUrl
		.replace("COMPONENT_APPID", wxThirdClientId)
		.replace("APPID", CommonConstant.PROXY_AUTHORIZER_APP_ID_VALUE)
		.replace("REDIRECT_URI", wxThirdWebRedirectUrl.contains("?") ? 
			wxThirdWebRedirectUrl + extraParam
			: wxThirdWebRedirectUrl + "?abc_event=1" + extraParam)
		.replace("SCOPE", scope)
		.replace("STATE", RandomStringUtils.randomAlphabetic(6))
		;
		
		String wxThirdPersonUserLoginUrl = wxWebLoginUrl;
		wxThirdPersonUserLoginUrl = myRedirectStrategy.encodeQueryParam(wxThirdPersonUserLoginUrl);
		wxThirdPersonUserLoginUrl = wxThirdPersonUserLoginUrl.replace("&openIdVice=", "%26openIdVice%3D")
								.replace("&authorizerUserNameVice=", "%26authorizerUserNameVice%3D")
								.replace("&typeCode=", "%26typeCode%3D");
		ret = wxThirdPersonUserLoginUrl;
		return ret;
	}
	
	// 获取签到成功的回复信息
	private String getWxMsgSignInContent(String eventKey, String authorizerAppId, WxPersonUserPOJO wxPersonUserPOJO) throws Exception {
		String ret = "";

		PointRecordSearchPOJO pointRecordSearchPOJO = new PointRecordSearchPOJO();
		pointRecordSearchPOJO.setPointReason(eventKey);
		Date curDate = new Date();
		Date dateOfDayMin = DateUtils.truncate(curDate, Calendar.DATE);
		
		String ymd = DateUtil.toStr(curDate, "yyyy-MM-dd");
		Date dateOfDayMax = DateUtil.toDate(ymd + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
		pointRecordSearchPOJO.setStartDateTime(dateOfDayMin);
		pointRecordSearchPOJO.setEndDateTime(dateOfDayMax);
		List<PointRecordPOJO> pointRecordPOJOs = pointRecordService.finds(pointRecordSearchPOJO);
		String content;
		if (CollectionUtils.isEmpty(pointRecordPOJOs)) {
			// 开始签到
			PointEventSearchPOJO pointEventSearchPOJO = new PointEventSearchPOJO();
			pointEventSearchPOJO.setEventName(eventKey);
			List<PointEventPOJO> pointEventPOJOs = pointEventService.finds(pointEventSearchPOJO);
			if (!CollectionUtils.isEmpty(pointEventPOJOs)) {
				PointEventPOJO pointEventPOJO = pointEventPOJOs.get(0);
				
				// insert pointRecord 
				PointRecordPOJO pointRecordPOJO = new PointRecordPOJO();
				pointRecordPOJO.setAuthorizerAppId(authorizerAppId);
				pointRecordPOJO.setOpenId(wxPersonUserPOJO.getOpenId());
				pointRecordPOJO.setPointNum(pointEventPOJO.getPointNumPer());
				pointRecordPOJO.setPointReason(eventKey);
				pointRecordPOJO.setUserId(wxPersonUserPOJO.getUserId());
				pointRecordService.insert(pointRecordPOJO , wxPersonUserPOJO.getUserId());
				
				// insert pointSummary
				
				PointSummaryPOJO pointSummaryPOJO = new PointSummaryPOJO();
				PointSummarySearchPOJO pointSummarySearchPOJO = new PointSummarySearchPOJO();
				pointSummarySearchPOJO.setUserId(wxPersonUserPOJO.getUserId());
				List<PointSummaryPOJO> pointSummaryPOJOs = pointSummaryService.finds(pointSummarySearchPOJO);
				if (!CollectionUtils.isEmpty(pointSummaryPOJOs)) {
					pointSummaryPOJO = pointSummaryPOJOs.get(0);
					pointSummaryPOJO.setPointTotal(pointSummaryPOJO.getPointTotal() + pointRecordPOJO.getPointNum());
//					pointSummaryPOJO.setPointUsed(pointSummaryPOJO.getPointUsed());
					pointSummaryPOJO.setPointRemainder(pointSummaryPOJO.getPointRemainder() + pointRecordPOJO.getPointNum());
					pointSummaryService.update(pointSummaryPOJO);
				} else {
					pointSummaryPOJO.setUserId(wxPersonUserPOJO.getUserId());
					pointSummaryPOJO.setPointTotal(pointRecordPOJO.getPointNum());
					pointSummaryPOJO.setPointUsed(0);
					pointSummaryPOJO.setPointRemainder(pointRecordPOJO.getPointNum());
					int insert = pointSummaryService.insert(pointSummaryPOJO, wxPersonUserPOJO.getUserId());
				}
				
			}
			content = "签到成功, " + wxPersonUserPOJO.getNickname();
		} else {	
			// 已经签到
			content = "已经签到, " + wxPersonUserPOJO.getNickname();
		}
		
		ret = content;
		return ret;
	}
	
	// 返回加入会员的回复信息
	private String getWxMsgJoinMemberContent(WxPersonUserPOJO wxPersonUserPOJO, String authorizerAppId) throws Exception {
		String ret = "";
		String content = "欢迎您，"
				+ wxPersonUserPOJO.getNickname()
				+ "  1.加入会员请回复001  2.重新加入请回复002  3.退出会员请回复003";
		String msgTypeTemp = CommonConstant.MSG_TYPE_SYSTEM;
		Long userId = wxPersonUserPOJO.getUserId();

		WxRespMsgSearchPOJO tempSearch = new WxRespMsgSearchPOJO();
		tempSearch.setAuthorizerAppId(authorizerAppId);
		tempSearch.setMsgType(msgTypeTemp);
		tempSearch.setUserId(userId);
		List<WxRespMsgPOJO> wxRespMsgPOJOs = wxRespMsgService.finds(tempSearch);
		if (!CollectionUtils.isEmpty(wxRespMsgPOJOs)) {
			for (WxRespMsgPOJO wxRespMsgPOJO : wxRespMsgPOJOs) {
				content = content.replace(wxRespMsgPOJO.getMsgSend(), wxRespMsgPOJO.getMsgReceive());
			}
		}
		ret = content;
		return ret;
	}
	private Boolean isMember(WxPersonUserPOJO wxPersonUserPOJO) {
		Boolean ret = false;
		// 如果WxPersonUserPOJO是空,或者不是会员则回复加入会员
		Boolean isMember = false;
		if ( wxPersonUserPOJO != null 
				&& wxPersonUserPOJO.getMemberFlag() != null && CommonConstant.MEMBER_FLAG == wxPersonUserPOJO.getMemberFlag()
				&& StringUtils.isNotBlank(wxPersonUserPOJO.getOpenId()) ) {
			isMember = true;
		}
		/*if (wxPersonUserPOJO == null) {
			isMember = false;
		} else {
			if (wxPersonUserPOJO.getMemberFlag() == null || CommonConstant.MEMBER_FLAG != wxPersonUserPOJO.getMemberFlag()) {
				isMember = false;
			} else {
				isMember = true;
			}
		}*/
		
		ret = isMember;
		return ret;
	}
	// CLICK会员中心的回复信息
	private String getWxMsgMemberCenterContent(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO, WxPersonUserPOJO wxPersonUserPOJO, String fromUserName, String toUserName, String wxIndexCode, HttpServletRequest request) {
		String ret = "";
		HttpSession session = request.getSession();
		// 如果WxPersonUserPOJO是空,或者不是会员则回复加入会员
		Boolean isMember = this.isMember(wxPersonUserPOJO);

		String authorizerNickName = "";
		if (wxAuthorizerInfoPOJO != null) {
			authorizerNickName = wxAuthorizerInfoPOJO.getNickName();
		}
		// 不是会员, 返回加入会员链接
		if (!isMember) {
			String wxThirdPersonUserLoginUrl = this.getWxThirdPersonUserLoginUrl(fromUserName, toUserName);
			String key = RandomStringUtils.randomAlphabetic(8);
			CacheUtil.getInstance().put(key, wxThirdPersonUserLoginUrl, 15);
			String content = "";
			content += /*"您好，欢迎来到" + authorizerNickName +*/ "抱歉! 您还不是会员, 请点击👉";
			content += "<a href=\"" + HttpRequestUtil.getBase(request) + "/web/unified/t/" + key
					+ "\">加入会员</a>";
			
			ret = content;
//			return ret;
		} else {	// 2. // 如果是会员, 则回复会员中心和重写加入会员链接
//			String wxThirdPersonUserLoginUrl = "";
//			wxThirdPersonUserLoginUrl = this.getWxThirdPersonUserLoginUrl(fromUserName, toUserName);

			// 用户中心
			String memberCenterUrl = "";
			memberCenterUrl += HttpRequestUtil.getBase(request) + "/web/wx/usercenter/" + wxIndexCode + "/person";
			
			String memberCenter = "";
			memberCenter += "<a href=\"" + memberCenterUrl
					+ "\">会员中心</a>";
			
			String content = "";
			content += "您好," + wxPersonUserPOJO.getNickname()
					+ ", 欢迎来到" + authorizerNickName + ", 请点击👉";
			content += memberCenter;
//			content += "/";
//			content += "<a href=\"" + wxThirdPersonUserLoginUrl
//					+ "\">重新加入</a>";
//			content += "\n注意：请不要将该链接转发给任何人，否则会出现安全隐患；该链接的有效时间为30秒。";
			
			ret = content;
//			return ret;
		}
		
		
		return ret;
	}
	
	private String dealText001(List<WxPersonUserPOJO> wxPersonUserPOJOs, String fromUserName, String toUserName, WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO) throws Exception {

		// 001-注册会员, 002-重新加入会员, 003-退出会员
		// 查询是否有wx_person_user
		// 1. 如果没有wx_person_user, then 回复带有参数openIdVice的登录连接
		
		Boolean needRegisterMember = false;
		WxPersonUserPOJO wxPersonUserPOJO = null;
		if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
			needRegisterMember = true;
		} else {
			wxPersonUserPOJO  = wxPersonUserPOJOs.get(0);
			if (wxPersonUserPOJO.getMemberFlag() == null || CommonConstant.MEMBER_FLAG != wxPersonUserPOJO.getMemberFlag()) {
				needRegisterMember = true;
			} else {
				needRegisterMember = false;
			}
		}
		
		if (needRegisterMember) {
			
			String wxThirdPersonUserLoginUrl = this.getWxThirdPersonUserLoginUrl(fromUserName, toUserName);
			
			String content = "";
			content += "您好，现在开始加入会员，请点击";
			content += "<a href=\"" + wxThirdPersonUserLoginUrl
					+ "\">加入会员</a>";
			content += "\n注意：请不要将该链接转发给任何人，否则会出现安全隐患；该链接的有效时间为30秒。";
			wxMsgEventRespTextApiPOJO.setContent(content);
			String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//			String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
			return replyMsg;
		} else {	// 2. 如果有返回已经注册
			// 去除个人用户的标签
			String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
					+ "/api/wx/third/" + wxPersonUserPOJO.getAuthorizerAppId() + "/tags/batchuntagging";
			// test request POJO<->requestBody
			WxTagsMgrBatchTaggingReqApiPOJO wxTagsMgrBatchTaggingReqApiPOJO = new WxTagsMgrBatchTaggingReqApiPOJO();
			final int TAG_ID = 101;
			List<String> openIdList = new ArrayList<String>();
			openIdList.add(wxPersonUserPOJO.getOpenId());
			wxTagsMgrBatchTaggingReqApiPOJO.setTagId(TAG_ID);
			wxTagsMgrBatchTaggingReqApiPOJO.setOpenIdList(openIdList);
			String requestBody = JsonUtils.convertToJson(wxTagsMgrBatchTaggingReqApiPOJO);
			String resp = HttpClientUtil.postHttpsJson(url, requestBody);
			BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(resp, BaseWxApiPOJO.class);
			logger.info("batchuntagging, openId: {}, baseWxApiPOJO: {}", wxPersonUserPOJO.getOpenId(), baseWxApiPOJO);
			
			
			String content = "您好, " + wxPersonUserPOJO.getNickname()
					+ ", 已经是会员，不需要再次加入";
			wxMsgEventRespTextApiPOJO.setContent(content);
			String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//			String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
			return replyMsg;
		}
	}
	
	private String dealText002(List<WxPersonUserPOJO> wxPersonUserPOJOs, WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO, String fromUserName, String toUserName) {

		// 查询是否有wx_person_user
		// 1. 如果没有wx_person_user, then 回复带有参数openIdVice的登录连接
		
		Boolean isMember = false;
		WxPersonUserPOJO wxPersonUserPOJO = null;
		if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
			isMember = false;
		} else {
			wxPersonUserPOJO  = wxPersonUserPOJOs.get(0);
			if (wxPersonUserPOJO .getMemberFlag() == null || CommonConstant.MEMBER_FLAG != wxPersonUserPOJO.getMemberFlag()) {
				isMember = false;
			} else {
				isMember = true;
			}
		}
		
		if (!isMember) {
			String content = "";
			content += "您不是会员，不能重新加入会员。";
//			content += wxThirdPersonUserLoginUrl;
			wxMsgEventRespTextApiPOJO.setContent(content);
			String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//			String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
			return replyMsg;
		} else {	// 2. 如果有返回已经注册
			
//			String wxWebLoginUrl = "";
			String wxThirdPersonUserLoginUrl = "";
			wxThirdPersonUserLoginUrl = this.getWxThirdPersonUserLoginUrl(fromUserName, toUserName);
			
			String content = "" /*"获取的事件：" + XmlUtils.convertToXml(wxMsgEventRecvApiPOJO) + "\n<br/>"*/;
			content += "您好," + wxPersonUserPOJO.getNickname()
					+ ", 现在开始重新加入会员，请点击";
			content += "<a href=\"" + wxThirdPersonUserLoginUrl
					+ "\">重新加入</a>";
			content += "\n注意：请不要将该链接转发给任何人，否则会出现安全隐患；该链接的有效时间为30秒。";
			
			wxMsgEventRespTextApiPOJO.setContent(content);
			String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//			String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
			return replyMsg;
		}
	}
	
	private String dealText003(List<WxPersonUserPOJO> wxPersonUserPOJOs, WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO) throws Exception {

		// 查询是否有wx_person_user
		// 1. 如果没有wx_person_user, then 回复带有参数openIdVice的登录连接

		Boolean isMember = false;
		WxPersonUserPOJO wxPersonUserPOJO = null;
		if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
			isMember = false;
		} else {
			wxPersonUserPOJO = wxPersonUserPOJOs.get(0);
			if (wxPersonUserPOJO .getMemberFlag() == null || CommonConstant.MEMBER_FLAG != wxPersonUserPOJO.getMemberFlag()) {
				isMember = false;
			} else {
				isMember = true;
			}
		}
		
		if (!isMember) {
			
			String content = "" /*"获取的事件：" + XmlUtils.convertToXml(wxMsgEventRecvApiPOJO) + "\n<br/>"*/;
			content += "您还不是会员，不需要退出会员。";
			wxMsgEventRespTextApiPOJO.setContent(content);
			String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//			String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
			return replyMsg;
		} else {	// 2. 如果有返回已经注册
			for (WxPersonUserPOJO tempOrgi : wxPersonUserPOJOs) {
//				Long id = tempOrgi.getWxPersonUserId();
				
				WxPersonUserPOJO temp = new WxPersonUserPOJO();
				temp.setWxPersonUserId(tempOrgi.getWxPersonUserId());
				temp.setMemberFlag(CommonConstant.MEMBER_FLAG_NOT);
				
				int retUpd = wxPersonUserService.update(temp);
			}
			
			// 去除个人用户的标签
			String url = /*HttpRequestUtil.getBase(request)*/"http://127.0.0.1"
					+ "/api/wx/third/" + wxPersonUserPOJO.getAuthorizerAppId() + "/tags/batchuntagging";
			// test request POJO<->requestBody
			WxTagsMgrBatchTaggingReqApiPOJO wxTagsMgrBatchTaggingReqApiPOJO = new WxTagsMgrBatchTaggingReqApiPOJO();
			final int TAG_ID = 101;
			List<String> openIdList = new ArrayList<String>();
			openIdList.add(wxPersonUserPOJO.getOpenId());
			wxTagsMgrBatchTaggingReqApiPOJO.setTagId(TAG_ID);
			wxTagsMgrBatchTaggingReqApiPOJO.setOpenIdList(openIdList);
			String requestBody = JsonUtils.convertToJson(wxTagsMgrBatchTaggingReqApiPOJO);
			String resp = HttpClientUtil.postHttpsJson(url, requestBody);
			BaseWxApiPOJO baseWxApiPOJO = JsonUtils.convertToJavaBean(resp, BaseWxApiPOJO.class);
			logger.info("batchuntagging, openId: {}, baseWxApiPOJO: {}", wxPersonUserPOJO.getOpenId(), baseWxApiPOJO);
			
			
			String content = "您好，" + wxPersonUserPOJO.getNickname()
					+ "，您已成功退出会员";
			wxMsgEventRespTextApiPOJO.setContent(content);
			String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//			String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
			return replyMsg;
		}
	}
	
	private String dealTextMemberCenter(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO, WxPersonUserPOJO wxPersonUserPOJO, String fromUserName, String toUserName, String wxIndexCode, HttpServletRequest request
			, WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO) {

//		wxMsgEventRecvApiPOJO
		/// deal text event
//		logger.info("wxMsgEventRecvApiPOJO: {}", wxMsgEventRecvApiPOJO);
		
		String content = "";
		
		content = this.getWxMsgMemberCenterContent(wxAuthorizerInfoPOJO, wxPersonUserPOJO, fromUserName, toUserName, wxIndexCode, request);
		
		wxMsgEventRespTextApiPOJO.setContent(content);
		String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//		String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
		return replyMsg;
		///
		
	}
	
	private String dealTextSignIn(String authorizerAppId, WxPersonUserPOJO wxPersonUserPOJO, WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO) throws Exception {

//		wxMsgEventRecvApiPOJO
		/// deal text event
//		logger.info("wxMsgEventRecvApiPOJO: {}", wxMsgEventRecvApiPOJO);

		String content = "";
		
		content = this.getWxMsgSignInContent(CommonConstant.WX_MSG_SIGN_IN, authorizerAppId, wxPersonUserPOJO);
		
		wxMsgEventRespTextApiPOJO.setContent(content);
		String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//		String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
		return replyMsg;
	}

	// voice lottry
	private String dealTextLottery(WxRespMsgPOJO wxRespMsgPOJO, Long userId, HttpServletRequest request) throws Exception {
		String ret = "success";

		List<String> awardNamesNot = new ArrayList<String>();
		String notName = messageSource.getMessage("lottery.award.notname", null, null);
		if (StringUtils.isNotBlank(notName)) {
			String[] notNames = StringUtils.split(notName, ",");
			if (null != notNames) {
				for (int i = 0; i < notNames.length; i++) {
					awardNamesNot.add(notNames[i]);
				}
			} else {
				awardNamesNot.add("未中奖");
			}
		} else {
			awardNamesNot.add("未中奖");
		}
		
		Long interactiveId = NumberUtils.toLong(wxRespMsgPOJO.getMsgSend());
		
		InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);

		String detail = "查看请点击👉"
				+ "<a href=\"" + HttpRequestUtil.getBase(request)
						+ "/web/unified/interactive2Detail/lotteryvoice?interactiveId=" 
				+ interactiveId
				+ "\">活动详情</a>";
		String contact = ""
				+ "<a href=\"" + HttpRequestUtil.getBase(request)
						+ "/page/unified/activity_detail.jsp"
						+ "?activityId=" + interactivePOJO.getActivityId()
						+ "&hidContent=1&a=1"
				+ interactiveId
				+ "\">领奖方式</a>";

		AwardSearchPOJO awardSearchPOJO = new AwardSearchPOJO();
		awardSearchPOJO.setBalanceGt0Flag(true);
		awardSearchPOJO.setInteractiveId(interactiveId);
		List<AwardPOJO> awardPOJOs = awardService.finds(awardSearchPOJO);
		// 获取奖品的剩余量
		int awardRemindCount = 0;
		if (CollectionUtils.isNotEmpty(awardPOJOs)) {
			for (int i = 0; i < awardPOJOs.size(); i++) {
				AwardPOJO awardPOJO = awardPOJOs.get(i);
				if (!awardNamesNot.contains(awardPOJO.getName())) {
					awardRemindCount += awardPOJO.getBalance();
				}
			}
		}
		if (awardRemindCount <= 0) {
			ret = "网友实在太疯狂！😂福利已被一抢而空！";
			ret += detail;
			return ret;
		}
		
		AwardRecordSearchPOJO awardRecordSearchPOJO = new AwardRecordSearchPOJO();
		awardRecordSearchPOJO.setInteractiveId(interactiveId);
		awardRecordSearchPOJO.setUserId(userId);
		awardRecordSearchPOJO.setAwardNamesNot(awardNamesNot);
		
		List<AwardRecordPOJO> awardRecordPOJOs = awardRecordService.finds(awardRecordSearchPOJO);
		int awardCount = awardRecordService.getCount(awardRecordSearchPOJO);
		if (awardCount > 0) {
			ret = "恭喜您已经中奖啦！请把机会留给别人吧, 领取奖品请点击👉";
			ret += "" + contact;
			ret += "," + detail;
//			ret = "欢迎您参加[" + interactivePOJO.getName()
//					+ "]抽奖活动, 您的抽奖结果是: " + awardRecordPOJOs.get(0).getAwardPOJO().getName() + "! 您还有" + remindCount
//							+ "次抽奖机会！活动详情请点击"
//							+ "<a href=\"" + "http://www.deweiyizhan.com/web/unified/interactive2Detail/lotteryvoice?interactiveId=" 
//							+ interactiveId
//							+ "\">查看活动</a>";
			return ret;
		}
		
		
		// to call lottery api
		// /api/unified/lottery/{interactiveId}/happy?userId={userId}
		String url = "http://127.0.0.1" + "/api/unified/lottery/" + interactiveId + "/happy"
						+ "?userId=" + userId;
		String res = HttpClientUtil.get(url);
		Map lotterymap = JsonUtils.convertToJavaBean(res, Map.class);
		Boolean success = (Boolean) (lotterymap.get("success"));
//		AwardPOJO awardPOJO = (AwardPOJO) (lotterymap.get("awardPOJO"));

		Map awardMap = (Map) (lotterymap.get("awardPOJO"));
		String awardName = (String) awardMap.get("name");
		
		Boolean isHappy = (Boolean) (lotterymap.get("isHappy"));
		String result1 = (String) (lotterymap.get("result"));
		/*ret.put("success", true);
		ret.put("awardPOJO", awardPOJO);
		ret.put("isHappy", false);
		ret.put("result", "活动未开始");*/
		
		if (StringUtils.isBlank(awardName)) {
			awardName = "未中奖";
		}
		
		awardRecordSearchPOJO = new AwardRecordSearchPOJO();
		awardRecordSearchPOJO.setInteractiveId(interactiveId);
		awardRecordSearchPOJO.setUserId(userId);
		
//		List<AwardRecordPOJO> awardRecordPOJOs = awardRecordService.finds(awardRecordSearchPOJO);
		int count = awardRecordService.getCount(awardRecordSearchPOJO);
		
		Integer awardNumberPer = interactivePOJO.getAwardNumberPer();
		if (awardNumberPer == null) {
			awardNumberPer = 1;
		}
		int remindCount = awardNumberPer;
		if (count - awardNumberPer <= 0) {
			remindCount = awardNumberPer - count;
		} else {
			remindCount = 0;
		}
		
		// 没有中奖
		if (awardNamesNot.contains(awardName)) {
			ret = "啊欧，很遗憾！您没有获得奖品！请继续加油！";
			/*if (count >= 10 && count < 20) {
				ret = "啊欧，很遗憾！没有获得奖品！您已经参与了" + count
						+ "次了，请继续加油，坚持就是胜利！";
			} else if (count >= 20 && count < 30) {
				ret = "啊欧，很遗憾！没有获得奖品！您已经参与了" + count
						+ "次了，请继续加油，坚持就是胜利！";
			} else if (count > 30) {
				ret = "虽然您抽了" + count
						+ "次，但是还是要很遗憾的告诉您，没有中奖！您的运气实在是......！！！";
			} else if (count > 40) {
				ret = "很抱歉，还是没有中奖！我只是默默的看着你，我不说话！";
			}*/
			ret += detail;
			return ret;
		} else {	//抽中奖品
			ret = "哇塞~几率大神降临了！恭喜您中奖啦！请点击👉";
			ret += contact;
//			ret += detail;
			return ret;
		}

//		String content = "";
//		
//		content = "欢迎您参加[" + interactivePOJO.getName()
//				+ "]抽奖活动, 您的抽奖结果是: " + awardName + "! 您还有" + remindCount
//						+ "次抽奖机会！活动详情请点击"
//						+ "<a href=\"" + "http://www.deweiyizhan.com/web/unified/interactive2Detail/lotteryvoice?interactiveId=" 
//						+ interactiveId
//						+ "\">查看活动</a>";
//		
//		ret = content;
//		return ret;
	}
	
	// lotter
	private String dealTextLottery(WxRespMsgPOJO wxRespMsgPOJO, Long userId, WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO) throws Exception {

		// to call lottery api
		// /api/unified/lottery/{interactiveId}/happy?userId={userId}
		Long interactiveId = NumberUtils.toLong(wxRespMsgPOJO.getMsgSend());
		String url = "http://127.0.0.1" + "/api/unified/lottery/" + interactiveId + "/happy"
						+ "?userId=" + userId;
		String res = HttpClientUtil.get(url);
		Map lotterymap = JsonUtils.convertToJavaBean(res, Map.class);
		Boolean success = (Boolean) (lotterymap.get("success"));
//		AwardPOJO awardPOJO = (AwardPOJO) (lotterymap.get("awardPOJO"));

		Map awardMap = (Map) (lotterymap.get("awardPOJO"));
		String awardName = (String) awardMap.get("name");
		
		Boolean isHappy = (Boolean) (lotterymap.get("isHappy"));
		String result1 = (String) (lotterymap.get("result"));
		/*ret.put("success", true);
		ret.put("awardPOJO", awardPOJO);
		ret.put("isHappy", false);
		ret.put("result", "活动未开始");*/
		
		if (StringUtils.isBlank(awardName)) {
			awardName = "未中奖";
		}
		InteractivePOJO interactivePOJO = interactiveService.findById(interactiveId);

		
		AwardRecordSearchPOJO awardRecordSearchPOJO = new AwardRecordSearchPOJO();
		awardRecordSearchPOJO.setInteractiveId(interactiveId);
		awardRecordSearchPOJO.setUserId(userId);
//		List<AwardRecordPOJO> awardRecordPOJOs = awardRecordService.finds(awardRecordSearchPOJO);
		int count = awardRecordService.getCount(awardRecordSearchPOJO);
		
		Integer awardNumberPer = interactivePOJO.getAwardNumberPer();
		if (awardNumberPer == null) {
			awardNumberPer = 1;
		}
		int remindCount = awardNumberPer;
		if (count - awardNumberPer <= 0) {
			remindCount = awardNumberPer - count;
		} else {
			remindCount = 0;
		}

		String content = "";
		
		content = "欢迎您参加[" + interactivePOJO.getName()
				+ "]抽奖活动, 您的抽奖结果是: " + awardName + "! 您还有" + remindCount
						+ "次抽奖机会！活动详情请点击"
						+ "<a href=\"" + "http://www.deweiyizhan.com/web/unified/interactive2Detail?interactiveId=" 
						+ interactiveId
						+ "\">查看活动</a>";
		
		wxMsgEventRespTextApiPOJO.setContent(content);
		String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//		String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
		return replyMsg;
	}
	
	private String dealTextMsg(String result, String fromUserName, String toUserName
						, WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO
						, String authorizerAppId, Long userId, List<String> controlCodes
						, List<WxPersonUserPOJO> wxPersonUserPOJOs, HttpServletRequest request
						, String wxIndexCode) throws Exception {
		String ret = "success";

		WxMsgEventRecvApiPOJO wxMsgEventRecvApiPOJO = XmlUtils.convertToJavaBean(result, WxMsgEventRecvApiPOJO.class);
		if (result.contains("TESTCOMPONENT_MSG_TYPE_TEXT")) {
			logger.info("Text: TESTCOMPONENT_MSG_TYPE_TEXT, autoTest");
			if (wxAutoTestUsername.equals(toUserName)) {
				
				wxMsgEventRespTextApiPOJO.setContent(wxMsgEventRecvApiPOJO.getContent() + "_callback");
				String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//				String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
				return replyMsg;
			}
		} else if (result.contains("QUERY_AUTH_CODE:")) {
			logger.info("Text: QUERY_AUTH_CODE:, autoTest");
			if (wxAutoTestUsername.equals(toUserName)) {
				KfInfoInterfaceThread kfInfoInterfaceThread = new KfInfoInterfaceThread(wxMsgEventRecvApiPOJO);
				kfInfoInterfaceThread.start();
				return "success";
			}
		}
		
		// deal resp msg @03/28/2017
		String respMsgType = "";	// 0-客户自定义, 1-系统定义
		
		String contentRecv = wxMsgEventRecvApiPOJO.getContent();
		WxRespMsgSearchPOJO wxRespMsgSearchPOJO = new WxRespMsgSearchPOJO();
		wxRespMsgSearchPOJO.setAuthorizerAppId(authorizerAppId);
		wxRespMsgSearchPOJO.setMsgReceive(contentRecv);
		List<WxRespMsgPOJO> wxRespMsgPOJOs = wxRespMsgService.finds(wxRespMsgSearchPOJO);
		if (!CollectionUtils.isEmpty(wxRespMsgPOJOs)) {
			if (wxRespMsgPOJOs.size() > 1) {
				logger.error("同一个公众号和接收的关键字返回了{}条记录, 请查询代码.", wxRespMsgPOJOs.size());
			}
			WxRespMsgPOJO wxRespMsgPOJO = wxRespMsgPOJOs.get(0);
			
			respMsgType = wxRespMsgPOJO.getMsgType();
			
			if (CommonConstant.MSG_TYPE_CUSTOMER.equalsIgnoreCase(respMsgType)) {

				String content = "";
				content = wxRespMsgPOJO.getMsgSend();
				wxMsgEventRespTextApiPOJO.setContent(content);
				String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//				String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
				return replyMsg;
			} else if (CommonConstant.MSG_TYPE_SYSTEM.equalsIgnoreCase(respMsgType) && !result.contains(CommonConstant.HFJT_USER_NAME)) {
				contentRecv = wxRespMsgPOJO.getMsgSend();
			} else if (CommonConstant.MSG_TYPE_LOTTERY.equalsIgnoreCase(respMsgType) && !result.contains(CommonConstant.HFJT_USER_NAME)) {
				return dealTextLottery(wxRespMsgPOJO, userId, wxMsgEventRespTextApiPOJO);
			}
			
		}
		// end deal resp msg
		// HFJT只处理定制关键字
		if (result.contains(CommonConstant.HFJT_USER_NAME)) {
			return "success";
		}

//		WxPersonUserPOJO wxPersonUserPOJO = null;
//		if (!CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
//			wxPersonUserPOJO = wxPersonUserPOJOs.get(0);
//		}
//		String requestBody = "";
		// 检测VIEW， 获取from/to，注册其他的公众号的用户
		// 得味驿站是服务号， 直接发送【注册】2个字
		// 主OPEN_ID用合肥交通广播，得味驿站的为副公众号
		/// remove text system msg
//		if (
//				("注册".equalsIgnoreCase(contentRecv) 
//						|| "001".equalsIgnoreCase(contentRecv)
//				) && controlCodes.contains("001")
//				/*&& CommonConstant.DWYZ_USER_NAME.equals(toUserName)*/) {
//			return dealText001(wxPersonUserPOJOs, fromUserName, toUserName, wxMsgEventRespTextApiPOJO);
//		}	// end 注册/001
//		
//		if ("002".equalsIgnoreCase(contentRecv) && controlCodes.contains("002")
//				/*&& CommonConstant.DWYZ_USER_NAME.equals(toUserName)*/) {
//			return dealText002(wxPersonUserPOJOs, wxMsgEventRespTextApiPOJO, fromUserName, toUserName);
//		}	// end 002
//		
//		if ("003".equalsIgnoreCase(contentRecv) && controlCodes.contains("003") 
//				/*&& CommonConstant.DWYZ_USER_NAME.equals(toUserName)*/) {
//			return dealText003(wxPersonUserPOJOs, wxMsgEventRespTextApiPOJO);
//		}	// end 003
//		
//		// 会员中心_DWYZ
//		if (CommonConstant.WX_MSG_MEMBER_CENTER.equalsIgnoreCase(contentRecv)
//				&& CommonConstant.DWYZ_USER_NAME.equals(toUserName)) {
//			return dealTextMemberCenter(wxPersonUserPOJO, fromUserName, toUserName, wxIndexCode, request, wxMsgEventRespTextApiPOJO);
//		}
//		// 签到_DWYZ
//		if (CommonConstant.WX_MSG_SIGN_IN.equalsIgnoreCase(contentRecv)
//				&& CommonConstant.DWYZ_USER_NAME.equals(toUserName)) {
//			return dealTextSignIn(authorizerAppId, wxPersonUserPOJO, wxMsgEventRespTextApiPOJO);
//		}	// end test "签到"
		
		return ret;
	}

	// voice lottery
	private String dealVoiceLottery(String result
						, WxPersonUserPOJO wxPersonUserPOJO
						, WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO
						, String wxIndexCode
						, HttpServletRequest request) throws Exception {
		String ret = "success";
		
		String msgType = XmlUtils.getNodeString(result, "/xml/MsgType");
		String toUserName = XmlUtils.getNodeString(result, "/xml/ToUserName");
		String fromUserName = XmlUtils.getNodeString(result, "/xml/FromUserName");
		String recognition = XmlUtils.getNodeString(result, "/xml/Recognition");
		String mediaId = XmlUtils.getNodeString(result, "/xml/MediaId");
		String format = XmlUtils.getNodeString(result, "/xml/Format");
		String msgId = XmlUtils.getNodeString(result, "/xml/MsgId");
		String createTime = XmlUtils.getNodeString(result, "/xml/CreateTime");


		String authorizerAppId = wxAuthorizerInfoPOJO.getAuthorizerAppId();
		Long userId = wxAuthorizerInfoPOJO.getUserId();
		
		InteractiveSearchPOJO interactiveSearchPOJO = new InteractiveSearchPOJO();
		interactiveSearchPOJO.setUserId(userId);
		interactiveSearchPOJO.setStartDateTime(new Date());
		interactiveSearchPOJO.setEndDateTime(new Date());
		interactiveSearchPOJO.setInteractiveType(CommonConstant.INTERACTIVE_LOTTERY_VOICE);
		List<InteractivePOJO> interactivePOJOs = interactiveService.findFulls(interactiveSearchPOJO);
		
		InteractivePOJO interactivePOJO = null;
		WxRespMsgPOJO wxRespMsgPOJO = null;
		if (!CollectionUtils.isEmpty(interactivePOJOs)) {
			if (interactivePOJOs.size() > 1) {
				logger.error("同一个公众号/用户当前时间返回了{}条记录抽奖活动, 请查询代码.", interactivePOJOs.size());
			}
			// 获取语音抽奖关键字
			for (int i = 0; i < interactivePOJOs.size(); i++) {
				interactivePOJO = interactivePOJOs.get(i);
				wxRespMsgPOJO = interactivePOJO.getWxRespMsgPOJO();
				if (CommonConstant.MSG_TYPE_LOTTERY_VOICE.equals(wxRespMsgPOJO.getMsgType())) {
					break;
				}
			}
		}
		if (interactivePOJO == null) {
			return ret;
		}
		
		if (!this.isMember(wxPersonUserPOJO)) {
			String authorizerNickName = "";
			if (wxAuthorizerInfoPOJO != null) {
				authorizerNickName = wxAuthorizerInfoPOJO.getNickName();
			}
			String typeCode = CommonConstant.BIND_MEMBER_TYPE_LOTTERY_VOICE;
			String wxThirdPersonUserLoginUrl = this.getWxThirdPersonUserLoginUrl(fromUserName, toUserName, typeCode);
			String key = RandomStringUtils.randomAlphabetic(8);
			CacheUtil.getInstance().put(key, wxThirdPersonUserLoginUrl, 15);
			String content = "";
			content += "您好，欢迎来到" + authorizerNickName + ", 参加[" + interactivePOJO.getName()
					+ "]活动需要会员, 请点击👉";
			content += "<a href=\"" + HttpRequestUtil.getBase(request) + "/web/unified/t/" + key
					+ "\">加入会员</a>";
			
			ret = content;
			return ret;
//			return this.getWxMsgMemberCenterContent(wxAuthorizerInfoPOJO, wxPersonUserPOJO, fromUserName, toUserName, wxIndexCode, request);
		}

		
		

		if (wxRespMsgPOJO == null) {
			return ret;
		}
		String msgReceive = wxRespMsgPOJO.getMsgReceive();
		if (recognition.contains(msgReceive)) {
			ret = this.dealTextLottery(wxRespMsgPOJO, wxPersonUserPOJO.getUserId(), request);
		} else {
			ret = "啊欧，口令好像不对耶！提醒您：一定要用普通话说出口令哦！我们的口令是\"" + msgReceive + "\", 请大声说出来!";
		}
		return ret;
	}

	// deal voice msg
	private String dealVoiceMsg(String result
						, WxPersonUserPOJO wxPersonUserPOJO
						, WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO
						, String wxIndexCode
						, HttpServletRequest request) throws Exception {
		String ret = "success";
		ret = dealVoiceLottery(result, wxPersonUserPOJO, wxAuthorizerInfoPOJO, wxIndexCode, request);
		if (StringUtils.isBlank(ret)) {
			ret = "success";
		}
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/{authorizerAppId}/msgEventRecieve", method=RequestMethod.POST)
	@ResponseBody
	public String msgEventRecieve(@RequestBody String requestBody, @PathVariable(value="authorizerAppId") String authorizerAppId, 
			@RequestParam(value="signature", required = false) String signature,
			@RequestParam(value="timestamp", required = false) String timestamp,
			@RequestParam(value="nonce", required = false) String nonce,
			@RequestParam(value="openid", required = false) String openid,
			@RequestParam(value="encrypt_type", required = false) String encryptType,
			@RequestParam(value="msg_signature", required = false) String msgSignature,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession();
			logger.info("msgEventRecieve, Session attributes: {}", HttpRequestUtil.getSessionAttributeNameValue(session));
			
			logger.info("msgEventRecieve begin...");
			logger.info("Request params, signature: {}, timestamp: {}, nonce: {}, openid: {}, encrypt_type: {}, msg_signature: {}",
					signature, timestamp, nonce, openid, encryptType, msgSignature);
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("msgEventRecieve uri: " + uri + ", qs: " + qs);
			logger.info("requestBody: " + requestBody);
			
			String token = messageSource.getMessage("WX.third.msgVerifyToken", null, null);
			String encodingAesKey = messageSource.getMessage("WX.third.msgEncKey", null, null);
			String appId = messageSource.getMessage("WX.third.clientId", null, null);
			logger.info("token: {}, encodingAesKey: {}, appId: {}", token, encodingAesKey, appId);
			
			
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String result = pc.decryptMsg(msgSignature, timestamp, nonce, requestBody);
			logger.info("Paintext: {}", result);
			
			if (StringUtils.isBlank(result)) {
				logger.error("msgEventRecieve result is null, please check it.");
				return "success";
			}
			
			// 处理HFJT定制关键字
			/*if (result.contains(CommonConstant.HFJT_USER_NAME)) {
				return "success";
			}*/
			
			// 全网发布监测开始
			// for username is autoTest and contains "text" and content is "TESTCOMPONENT_MSG_TYPE_TEXT"
			if (StringUtils.isNotBlank(result)) {
				String msgType = XmlUtils.getNodeString(result, "/xml/MsgType");
				String toUserName = XmlUtils.getNodeString(result, "/xml/ToUserName");
				String fromUserName = XmlUtils.getNodeString(result, "/xml/FromUserName");
				String createTime = XmlUtils.getNodeString(result, "/xml/CreateTime");
				String event = XmlUtils.getNodeString(result, "/xml/Event");

				// 保存所有的msg event进DB
				WxMsgEventLogPOJO wxMsgEventLogPOJO = new WxMsgEventLogPOJO();
				wxMsgEventLogPOJO.setAuthorizerAppId(authorizerAppId);
				wxMsgEventLogPOJO.setSignature(signature);
				wxMsgEventLogPOJO.setTimestamp(timestamp);
				wxMsgEventLogPOJO.setNonce(nonce);
				wxMsgEventLogPOJO.setOpenId(openid);
				wxMsgEventLogPOJO.setEncryptType(encryptType);
				wxMsgEventLogPOJO.setMsgSignature(msgSignature);
				wxMsgEventLogPOJO.setToUserName(toUserName);
				wxMsgEventLogPOJO.setFromUserName(fromUserName);
				wxMsgEventLogPOJO.setCreateTime(createTime);
				wxMsgEventLogPOJO.setMsgType(msgType);
				wxMsgEventLogPOJO.setEvent(event);
				wxMsgEventLogPOJO.setRawData(result);
				wxMsgEventLogPOJO.setCreateDateTime(new Date());
				wxMsgEventLogPOJO.setLastModifiedDateTime(new Date());

				wxMsgEventLogService.insert(wxMsgEventLogPOJO);
				
				// 获取公众号的信息, 确定是哪个公众号收到信息
				WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
				wxAuthorizerInfoSearchPOJO.setUserName(toUserName);
				List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
				WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO = null;
				
				RelWxIndexMapPOJO relWxIndexMapPOJO = new RelWxIndexMapPOJO();
				String wxIndexCode = "";
				
				// 获取设置的公众号的控制code,(001,002, etc,.)
				List<String> controlCodes = new ArrayList<String>();
				if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
					// get first one
					wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJOs.get(0);
					String controlCode = wxAuthorizerInfoPOJO.getControlCode();
					if (StringUtils.isNotBlank(controlCode)) {
						String[] controlCodeArr = StringUtils.split(controlCode, ",");
						if (null != controlCodeArr) {
							for (int i = 0; i < controlCodeArr.length; i++) {
								controlCodes.add(controlCodeArr[i]);
							}
						}
					}
					
					// 获取公众号的wxIndexCode
					RelWxIndexMapSearchPOJO relWxIndexMapSearchPOJO = new RelWxIndexMapSearchPOJO();
					relWxIndexMapSearchPOJO.setUserId(wxAuthorizerInfoPOJO.getUserId());
					List<RelWxIndexMapPOJO> relWxIndexMapPOJOs = relWxIndexMapService.finds(relWxIndexMapSearchPOJO );
					if (!CollectionUtils.isEmpty(relWxIndexMapPOJOs)) {
						relWxIndexMapPOJO = relWxIndexMapPOJOs.get(0);
						wxIndexCode = relWxIndexMapPOJO.getWxIndexCode();
					}
				}
				
				// 获取微信个人用户的信息, 获取发送信息的微信个人用户
				WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
				wxPersonUserSearchPOJO.setOpenId(fromUserName);
				wxPersonUserSearchPOJO.setWxAuthorizerUserName(toUserName);
				List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.findFulls(wxPersonUserSearchPOJO);
				WxPersonUserPOJO wxPersonUserPOJO = null;
				if (!CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
					wxPersonUserPOJO = wxPersonUserPOJOs.get(0);
				}
				
				Long userId = null;
				if (wxPersonUserPOJO != null) {
					userId = wxPersonUserPOJO.getUserId();
				}
				
				// 公共的发送返回信息代码
				WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO = new WxMsgEventRespTextApiPOJO();
				wxMsgEventRespTextApiPOJO.setToUserName(fromUserName);
				wxMsgEventRespTextApiPOJO.setFromUserName(toUserName);
				wxMsgEventRespTextApiPOJO.setCreateTime(new Date().getTime() + "");
				wxMsgEventRespTextApiPOJO.setMsgType("text");
				
				if ("text".equalsIgnoreCase(msgType)) {
					String respMsg = this.dealTextMsg(result, fromUserName, toUserName
							, wxMsgEventRespTextApiPOJO, authorizerAppId, userId
							, controlCodes, wxPersonUserPOJOs, request, wxIndexCode);
					logger.info("Text msg response: {}", respMsg);
					if (StringUtils.isBlank(respMsg) || "success".equalsIgnoreCase(respMsg)) {
						return "success";
					} else {
						String encryptMsg = pc.encryptMsg(respMsg, timestamp, nonce);
						return encryptMsg;
					}
				} /**text end**/ else if ("event".equalsIgnoreCase(msgType)) {
					// HFJT只处理定制关键字
					if (result.contains(CommonConstant.HFJT_USER_NAME)) {
						return "success";
					}

					WxMsgEventRecvEventApiPOJO wxMsgEventRecvEventApiPOJO = XmlUtils.convertToJavaBean(result, WxMsgEventRecvEventApiPOJO.class);
					logger.info("接收到的事件: {}", wxMsgEventRecvEventApiPOJO);

					
					if (wxAutoTestUsername.equals(toUserName)) {
						wxMsgEventRespTextApiPOJO.setContent(wxMsgEventRecvEventApiPOJO.getEvent() + "from_callback");
						String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
						String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
						return encryptMsg;
					}
					/// deal CLICK event, and is DWYZ公众号
					if ("CLICK".equalsIgnoreCase(wxMsgEventRecvEventApiPOJO.getEvent())
							/*&& CommonConstant.DWYZ_USER_NAME.equalsIgnoreCase(toUserName)*/) {
						String eventKey = wxMsgEventRecvEventApiPOJO.getEventKey();
						logger.info("wxMsgEventRecvEventApiPOJO: {}", wxMsgEventRecvEventApiPOJO);

						String content = "";
						
						if (CommonConstant.WX_MSG_SIGN_IN.equalsIgnoreCase(eventKey)) {	// 点击签到
							content = this.getWxMsgSignInContent(CommonConstant.WX_MSG_SIGN_IN, authorizerAppId, wxPersonUserPOJO);
						} else if (CommonConstant.WX_MSG_JOIN_MEMBER.equalsIgnoreCase(eventKey)) {	// 点击 加入会员
							content = this.getWxMsgJoinMemberContent(wxPersonUserPOJO, authorizerAppId);
						} else if (CommonConstant.WX_MSG_MEMBER_CENTER.equalsIgnoreCase(eventKey)) {	// 点击 会员中心
							content = this.getWxMsgMemberCenterContent(wxAuthorizerInfoPOJO, wxPersonUserPOJO, fromUserName, toUserName, wxIndexCode, request);
						} else {	// CLICK 不是 "签到"
							content = eventKey;
						}
						
						wxMsgEventRespTextApiPOJO.setContent(content);
						String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
						String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
						return encryptMsg;
					} /*else if ("subscribe".equalsIgnoreCase(wxMsgEventRecvEventApiPOJO.getEvent())) {
						logger.info("subscribe事件, 看看是否为购买url, 如果是, 发送客服通知.");
						String ecommerceUrl1 = CacheUtil.getInstance().get(openid + "," + authorizerAppId);
						String content = "<a href='" + ecommerceUrl1 + "'>购买链接</a>";
						this.sendCustomMsgText(openid, content, authorizerAppId);
					}*/ else if ("user_get_card".equalsIgnoreCase(wxMsgEventRecvEventApiPOJO.getEvent())) {
						UpdateEcWxCard4MsgEventReceiveThread updateEcWxCard4MsgEventReceiveThread = new UpdateEcWxCard4MsgEventReceiveThread(result, authorizerAppId, openid);
						updateEcWxCard4MsgEventReceiveThread.start();
					} else {
						logger.info("除了CLICK/user_get_card, 其他的event先不做处理");
					}
					///
					
					// to text
//					if (true/*CommonConstant.DWYZ_USER_NAME.equals(toUserName)*/) {
//						logger.info("发生事件：{}", wxMsgEventRecvEventApiPOJO.getEventKey());
//						
//						// 查询是否有wx_person_user
//						// 1. 如果没有wx_person_user, then 回复带有参数openIdVice的登录连接
//						if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
////							String wxWebLoginUrl = "";
//							String wxThirdPersonUserLoginUrl = "";
//							wxThirdPersonUserLoginUrl = this.getWxThirdPersonUserLoginUrl(fromUserName, toUserName);
//							
//							String content = "获取的事件：" + XmlUtils.convertToXml(wxMsgEventRecvEventApiPOJO) + "\n<br/>";
//							content += wxThirdPersonUserLoginUrl;
//							content = "test event response";
//							if ("CLICK".equalsIgnoreCase(wxMsgEventRecvEventApiPOJO.getEvent())) {
//								content = wxMsgEventRecvEventApiPOJO.getEventKey();
//								
//								wxMsgEventRespTextApiPOJO.setContent(content);
//								String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//								String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
//								return encryptMsg;
//							} else {
//								logger.info("其他的event先不做处理");
//							}
//						} else {	// 2. 如果有返回已经注册
//							String content = "已经注册, " + XmlUtils.convertToXml(wxMsgEventRecvEventApiPOJO);
//							wxMsgEventRespTextApiPOJO.setContent(content);
//							String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
//							String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
//							return encryptMsg;
//						}
//					}

				} /*event end*/ else if ("voice".equalsIgnoreCase(msgType)) {
					String content = "";
					
					content = this.dealVoiceMsg(result, wxPersonUserPOJO, wxAuthorizerInfoPOJO, wxIndexCode, request);
					if (StringUtils.isNotBlank(content) && !"success".equals(content)) {
						wxMsgEventRespTextApiPOJO.setContent(content);
						String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
						String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
						return encryptMsg;
					}
				} /*voice end*/
			}	// 全网发布监测结束
			
			
		} catch (Exception e) {
			logger.error("error: ", e);
//			throw e;
		}
		
		return "success";
	}

	class UpdateEcWxCard4MsgEventReceiveThread extends Thread {
		private String result;
		private String authorizerAppId;
		private String openId;
		public UpdateEcWxCard4MsgEventReceiveThread(String result, String authorizerAddId, String openId) {
			this.result = result;
			this.authorizerAppId = authorizerAddId;
			this.openId = openId;
		}
		@Override
		public void run() {
			try {
				String cardId = XmlUtils.getNodeString(result, "/xml/CardId");
				String userCardCode = XmlUtils.getNodeString(result, "/xml/UserCardCode");
				logger.info("接收到user_get_card, xmlwxcardupdate start update ecWxCard, authorizerAppId: {}, openId: {}, cardId: {}, userCardCode: {} "
						, authorizerAppId, openId, cardId, userCardCode);
				if (StringUtils.isBlank(cardId) || StringUtils.isBlank(userCardCode)) {
					logger.error("接收到user_get_card, xmlwxcardupdate update ecWxCard error, cardId/userCardCode is null, authorizerAppId: {}, openId: {}, cardId: {}, userCardCode: {}, result: {}"
							, authorizerAppId, openId, cardId, userCardCode, result);
					return;
				}
				// 根据authorizerAppId, openId, cardId, cardAcquireFlag获取ACQURING的记录, 然后update第一条记录.
				// 应该只有一条记录, 如果有2条可能发生了错误, 打印log以备分析
				EcWxCardSearchPOJO ecWxCardSearchPOJO = new EcWxCardSearchPOJO();
				ecWxCardSearchPOJO.setAuthorizerAppId(authorizerAppId);
				ecWxCardSearchPOJO.setOpenId(openId);
				ecWxCardSearchPOJO.setCardId(cardId);
				ecWxCardSearchPOJO.setCardAcquireFlag(CommonConstant.WX_CARD_ACQUIRING);
				List<EcWxCardPOJO> ecWxCardPOJOs = ecWxCardService.finds(ecWxCardSearchPOJO);
				if (CollectionUtils.isEmpty(ecWxCardPOJOs)) {
					logger.error("ecWxCardPOJOs must not be null, must check it, ecWxCardSearchPOJO: {}", JSON.toJSONString(ecWxCardSearchPOJO));
				} else {
					// get one and should be only one
					int size = ecWxCardPOJOs.size();
					if (size > 1) {
						logger.error("ecWxCardPOJOs size: {} > 1, should one and only one, must check it.");
					}
					EcWxCardPOJO ecWxCardPOJO = ecWxCardPOJOs.get(0);
					EcWxCardPOJO ecWxCardPOJO4UpdateJs2Xml = new EcWxCardPOJO();
					ecWxCardPOJO4UpdateJs2Xml.setEcWxCardId(ecWxCardPOJO.getEcWxCardId());
					ecWxCardPOJO4UpdateJs2Xml.setCardCode(userCardCode);
					ecWxCardPOJO4UpdateJs2Xml.setResultCode("SUCCESS");
					ecWxCardPOJO4UpdateJs2Xml.setCardAcquireFlag(CommonConstant.WX_CARD_ACQUIRED);
					ecWxCardService.update(ecWxCardPOJO4UpdateJs2Xml);
					// append decription
					String description = ";xmlwxcardupdate success," + DateUtil.toStr(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
					ecWxCardService.appendDescription(ecWxCardPOJO.getEcWxCardId(), description);
				}

				logger.info("接收到user_get_card, finish update ecWxCard, authorizerAppId: {}, openId: {}, cardId: {}, userCardCode: {}, xmlwxcardupdate"
							, authorizerAppId, openId, cardId, userCardCode);
			} catch (Exception e) {
				logger.error("UpdateEcWxCard4MsgEventReceiveThread exception: ", e);
			}
		}
	}
	
	class KfInfoInterfaceThread extends Thread {
		private WxMsgEventRecvApiPOJO wxMsgEventRecvApiPOJO;
		
		public KfInfoInterfaceThread(WxMsgEventRecvApiPOJO wxMsgEventRecvApiPOJO) {
			this.wxMsgEventRecvApiPOJO = wxMsgEventRecvApiPOJO;
		}

		@Override
		public void run() {
			try {
				logger.info("用code客服发送接口线程开始。。。");
//				Thread.sleep(1 * 1000l);	// 1s
				String code = wxMsgEventRecvApiPOJO.getContent().replace("QUERY_AUTH_CODE:", "");
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
					String authorizerAccessToken = wxAuthorizerAccessTokenPOJO.getAuthorizationInfoPOJO().getAuthorizerAccessToken();
					
					WxCustomSendReqApiPOJO wxCustomSendReqApiPOJO = new WxCustomSendReqApiPOJO();
					WxCustomSendReqTextApiPOJO wxCustomSendReqTextApiPOJO = new WxCustomSendReqTextApiPOJO();
					wxCustomSendReqTextApiPOJO.setContent(code + "_from_api");
					wxCustomSendReqApiPOJO.setTouser(wxMsgEventRecvApiPOJO.getFromUserName());
					wxCustomSendReqApiPOJO.setMsgtype("text");
					wxCustomSendReqApiPOJO.setWxCustomSendReqTextApiPOJO(wxCustomSendReqTextApiPOJO);
					String wxCustomSendResp = HttpClientUtil.postHttpsJson(wxCustomSend.replace("ACCESS_TOKEN", authorizerAccessToken), 
							JsonUtils.convertToJson(wxCustomSendReqApiPOJO));
					logger.info("用code客服发送接口线程结束。。。，wxCustomSendResp: {}", wxCustomSendResp);
				}
			} catch (Exception e) {
				logger.error("KfInfoInterfaceThread: {}", e);
			}
		}

		public WxMsgEventRecvApiPOJO getWxMsgEventRecvApiPOJO() {
			return wxMsgEventRecvApiPOJO;
		}

		public void setWxMsgEventRecvApiPOJO(WxMsgEventRecvApiPOJO wxMsgEventRecvApiPOJO) {
			this.wxMsgEventRecvApiPOJO = wxMsgEventRecvApiPOJO;
		}
		
	}
	
	class KfMsgSendThread extends Thread {
		private String openId;
		private String content;
		private String authorizerAppId;
		private Oauth2Controller that;
		
		public KfMsgSendThread(String openId, String content, String authorizerAppId, Oauth2Controller that) {
			this.openId = openId;
			this.content = content;
			this.authorizerAppId = authorizerAppId;
			this.that = that;
		}

		@Override
		public void run() {
			try {
				logger.info("客服发送文本线程开始。。。");
				String wxCustomSendResp = that.sendCustomMsgText(openId, content, authorizerAppId);
				logger.info("客服发送接口线程结束。。。，wxCustomSendResp: {}", wxCustomSendResp);
			} catch (Exception e) {
				logger.error("KfInfoInterfaceThread: {}", e);
			}
		}	// run end

	}
	
	public String sendCustomMsgText(String openId, String content, String authorizerAppId) throws Exception {
		String ret = "";
		try {

			logger.info("openId: {}, content: {}, authorizerAppId: {}", openId, content, authorizerAppId);
			if (StringUtils.isBlank(openId) || StringUtils.isBlank(content) || StringUtils.isBlank(authorizerAppId)) {
				return ret;
			}
			WxCustomSendReqApiPOJO wxCustomSendReqApiPOJO = new WxCustomSendReqApiPOJO();
			WxCustomSendReqTextApiPOJO wxCustomSendReqTextApiPOJO = new WxCustomSendReqTextApiPOJO();
			wxCustomSendReqTextApiPOJO.setContent(content);
			wxCustomSendReqApiPOJO.setTouser(openId);
			wxCustomSendReqApiPOJO.setMsgtype("text");
			wxCustomSendReqApiPOJO.setWxCustomSendReqTextApiPOJO(wxCustomSendReqTextApiPOJO);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);

			if (StringUtils.isBlank(authorizerAccessToken)) {
				logger.info("客服发送接口失败，wxAuthorizerAccessTokenPOJOs is null");
				return ret;
			}
			String wxCustomSendResp = HttpClientUtil.postHttpsJson(wxCustomSend.replace("ACCESS_TOKEN", authorizerAccessToken), 
					JsonUtils.convertToJson(wxCustomSendReqApiPOJO));
			logger.info("客服发送接口，wxCustomSendResp: {}", wxCustomSendResp);
			ret = wxCustomSendResp;
		} catch (Exception e) {
			logger.error("Send custom msg text exception: {}", e);
		}
		return ret;
	}

	public String sendCustomMsgWxCard(String openId, String cardId, String authorizerAppId) throws Exception {
		String ret = "";
		try {

			logger.info("openId: {}, cardId: {}, authorizerAppId: {}", openId, cardId, authorizerAppId);
			if (StringUtils.isBlank(openId) || StringUtils.isBlank(cardId) || StringUtils.isBlank(authorizerAppId)) {
				return ret;
			}
			WxCustomSendReqApiPOJO wxCustomSendReqApiPOJO = new WxCustomSendReqApiPOJO();
			WxCustomSendReqWxCardApiPOJO wxCustomSendReqWxCardApiPOJO = new WxCustomSendReqWxCardApiPOJO();
			wxCustomSendReqWxCardApiPOJO.setCardId(cardId);;
			wxCustomSendReqApiPOJO.setTouser(openId);
			wxCustomSendReqApiPOJO.setMsgtype("wxcard");
			wxCustomSendReqApiPOJO.setWxCustomSendReqWxCardApiPOJO(wxCustomSendReqWxCardApiPOJO);
			
			String authorizerAccessToken = wxAuthorizerRefreshTokenService.findTokenByAuthorizerAppId(authorizerAppId);

			if (StringUtils.isBlank(authorizerAccessToken)) {
				logger.info("客服发送接口失败，wxAuthorizerAccessTokenPOJOs is null");
				return ret;
			}
			String wxCustomSendResp = HttpClientUtil.postHttpsJson(wxCustomSend.replace("ACCESS_TOKEN", authorizerAccessToken), 
					JsonUtils.convertToJson(wxCustomSendReqApiPOJO));
			logger.info("客服发送接口结果，wxCustomSendResp: {}", wxCustomSendResp);
			ret = wxCustomSendResp;
		} catch (Exception e) {
			logger.error("Send custom msg wxcard exception: {}", e);
		}
		return ret;
	}

	public void refreshWxComAccessToken() {
		try {
			// add comAccessToken into DB every 1 hour
			WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchApiPOJO = new WxComAccessTokenSearchApiPOJO();
			wxComAccessTokenSearchApiPOJO.setPaginationFlage(true);
			List<WxComAccessTokenApiPOJO> wxComAccessTokenApiPOJOs = wxComAccessTokenService.finds(wxComAccessTokenSearchApiPOJO);
			String componentAccessToken = "";
			Integer expireIn = 2 * 60 * 60;	// 7200 seconds
			Date createDateTime = null;
			if (!CollectionUtils.isEmpty(wxComAccessTokenApiPOJOs)) {
				WxComAccessTokenApiPOJO wxComAccessTokenApiPOJO = wxComAccessTokenApiPOJOs.get(0);
				componentAccessToken = wxComAccessTokenApiPOJO.getComponentAccessToken();
				createDateTime = wxComAccessTokenApiPOJO.getCreateDateTime();
				expireIn = wxComAccessTokenApiPOJO.getExpiresIn();
			}
			Date curDate = new Date();
			Long duration = 1 * 60 * 60 * 1000L;
			if (duration > (expireIn - 20 * 60) * 1000L) {
				duration = (expireIn - 20 * 60) * 1000L;
			}

			if (curDate.getTime() - createDateTime.getTime() >= duration) {	// 1 hours
				WxComAccessTokenReqApiPOJO wxComAccessTokenReqApiPOJO = new WxComAccessTokenReqApiPOJO();
				wxComAccessTokenReqApiPOJO.setComponentAppId(wxThirdClientId);
				wxComAccessTokenReqApiPOJO.setComponentAppSecret(wxThirdSecret);

				WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchApiPOJO = new WxComVerifyTicketSearchApiPOJO();
				wxComVerifyTicketSearchApiPOJO.setPaginationFlage(true);
				List<WxComVerifyTicketApiPOJO> wxComVerifyTicketApiPOJOs = wxComVerifyTicketService.finds(wxComVerifyTicketSearchApiPOJO);
				String componentVerifyTicket = "";
				if (!CollectionUtils.isEmpty(wxComVerifyTicketApiPOJOs)) {
					componentVerifyTicket = wxComVerifyTicketApiPOJOs.get(0).getComponentVerifyTicket();
				}
				wxComAccessTokenReqApiPOJO.setComponentVerifyTicket(componentVerifyTicket);
				String wxComAccessTokenStr = HttpClientUtil.postHttpsJson(wxThirdAccessTokenUrl, JsonUtils.convertToJson(wxComAccessTokenReqApiPOJO));
				WxComAccessTokenApiPOJO wxComAccessTokenApiPOJO = JsonUtils.convertToJavaBean(wxComAccessTokenStr, WxComAccessTokenApiPOJO.class);
				wxComAccessTokenApiPOJO.setCreateDateTime(new Date());

				wxComAccessTokenService.insert(wxComAccessTokenApiPOJO);
			}
		} catch (Exception e) {
			logger.error("refreshWxComAccessToken exception: ", e);
		}
	}

	public void refreshWxAuthorizerToken() {
		try {
			// get wxComAccessToken
			WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchApiPOJO = new WxComAccessTokenSearchApiPOJO();
			wxComAccessTokenSearchApiPOJO.setPaginationFlage(true);
			List<WxComAccessTokenApiPOJO> wxComAccessTokenApiPOJOs = wxComAccessTokenService.finds(wxComAccessTokenSearchApiPOJO);
			String componentAccessToken = "";
			Integer expireIn = 2 * 60 * 60;
			Date createDateTime = null;
			if (!CollectionUtils.isEmpty(wxComAccessTokenApiPOJOs)) {
				// 当前仅仅一个第三方账号
				WxComAccessTokenApiPOJO wxComAccessTokenApiPOJO = wxComAccessTokenApiPOJOs.get(0);
				componentAccessToken = wxComAccessTokenApiPOJO.getComponentAccessToken();
//				createDateTime = wxComAccessTokenApiPOJO.getCreateDateTime();
//				expireIn = wxComAccessTokenApiPOJO.getExpiresIn();
			}

			Date curDate = new Date();
			// Refresh authorizer access token every 1 hour
			WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
			wxAuthorizerRefreshTokenSearchPOJO.setPaginationFlage(false);
			// 1. get distinct all authorizerAppId, 不知道为什么wxAuthorizerInfoPOJOs里面的appid和wxAuthorizerRefreshTokenPOJOs4AppId里面的不一样
//			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
//			wxAuthorizerInfoSearchPOJO.setPaginationFlage(false);
//			List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
			List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs4AppId = wxAuthorizerRefreshTokenService.findAuthorizerAppIds(wxAuthorizerRefreshTokenSearchPOJO);
			if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs4AppId)) {
				for (WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO4AppId : wxAuthorizerRefreshTokenPOJOs4AppId) {
					try {
						String authorizerAppId = wxAuthorizerRefreshTokenPOJO4AppId.getAuthorizerAppId();
						wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(authorizerAppId);
						wxAuthorizerRefreshTokenSearchPOJO.setPaginationFlage(true);// 只需要获取最新的一条记录
						// get authorizer refresh token to refresh
						List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenService.finds(wxAuthorizerRefreshTokenSearchPOJO);
						if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
							WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = wxAuthorizerRefreshTokenPOJOs.get(0);
							createDateTime = wxAuthorizerRefreshTokenPOJO.getCreateDateTime();
							expireIn = wxAuthorizerRefreshTokenPOJO.getExpiresIn();
							Long duration = 1 * 60 * 60 * 1000L;
							if (duration > (expireIn - 20 * 60) * 1000L) {
								duration = (expireIn - 20 * 60) * 1000L;
							}

							if (curDate.getTime() - createDateTime.getTime() >= duration) {	// 1 hours
								WxAuthorizerRefreshTokenReqApiPOJO wxAuthorizerRefreshTokenReqApiPOJO = new WxAuthorizerRefreshTokenReqApiPOJO();
								wxAuthorizerRefreshTokenReqApiPOJO.setComponentAppId(wxAuthorizerRefreshTokenPOJO.getComponentAppId());
								wxAuthorizerRefreshTokenReqApiPOJO.setAuthorizerAppId(wxAuthorizerRefreshTokenPOJO.getAuthorizerAppId());
								wxAuthorizerRefreshTokenReqApiPOJO.setAuthorizerRefreshToken(wxAuthorizerRefreshTokenPOJO.getAuthorizerRefreshToken());
								String wxAuthorizerRefreshTokenStr = HttpClientUtil.postHttpsJson(wxThirdAuthorizerRefreshTokenUrl.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken)
										, JsonUtils.convertToJson(wxAuthorizerRefreshTokenReqApiPOJO));
								WxAuthorizerRefreshTokenApiPOJO wxAuthorizerRefreshTokenApiPOJO = JsonUtils.convertToJavaBean(wxAuthorizerRefreshTokenStr, WxAuthorizerRefreshTokenApiPOJO.class);
								BeanUtils.copyProperties(wxAuthorizerRefreshTokenApiPOJO, wxAuthorizerRefreshTokenPOJO);
								wxAuthorizerRefreshTokenPOJO.setCreateDateTime(new Date());
								wxAuthorizerRefreshTokenService.insert(wxAuthorizerRefreshTokenPOJO);
							}
						}
					} catch (Exception e) {
						logger.error("wxAuthorizer refresh token exception: ", e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("refreshWxAuthorizerToken exception: ", e);
		}
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
			
			WxComVerifyTicketEncryptApiPOJO wxComVerifyTicketEncryptApiPOJO = XmlUtils.convertToJavaBean(requestBody, WxComVerifyTicketEncryptApiPOJO.class);
			String encrypt = wxComVerifyTicketEncryptApiPOJO.getEncrypt();
			logger.info("encrypt: {}", encrypt);
			String format = "<xml><ToUserName><![CDATA[%1$s]]></ToUserName><Encrypt><![CDATA[%2$s]]></Encrypt></xml>";
			String fromXML = String.format(format, appId, encrypt);
			
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String result = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
			logger.info("Paintext: {}", result);
			
			// ComponentVerifyTicket(Event)
			if (!StringUtils.isBlank(result) && result.contains("ComponentVerifyTicket")) {

				WxComVerifyTicketApiPOJO wxComVerifyTicketApiPOJO = XmlUtils.convertToJavaBean(result, WxComVerifyTicketApiPOJO.class);
				logger.info("wxComVerifyTicketApiPOJO: {}", wxComVerifyTicketApiPOJO);
				wxComVerifyTicketService.insert(wxComVerifyTicketApiPOJO);
				
//				// add comAccessToken into DB every 1 hour
//				WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchApiPOJO = new WxComAccessTokenSearchApiPOJO();
//				wxComAccessTokenSearchApiPOJO.setPaginationFlage(true);
//				List<WxComAccessTokenApiPOJO> wxComAccessTokenApiPOJOs = wxComAccessTokenService.finds(wxComAccessTokenSearchApiPOJO);
//				String componentAccessToken = "";
//				Date createDateTime = new Date();
//				if (!CollectionUtils.isEmpty(wxComAccessTokenApiPOJOs)) {
//					componentAccessToken = wxComAccessTokenApiPOJOs.get(0).getComponentAccessToken();
//					createDateTime = wxComAccessTokenApiPOJOs.get(0).getCreateDateTime();
//				}
//				Date curDate = new Date();
//
//				if (curDate.getTime() - createDateTime.getTime() >= 1 * 60 * 60 * 1000L) {	// 1 hours
//					WxComAccessTokenReqApiPOJO wxComAccessTokenReqApiPOJO = new WxComAccessTokenReqApiPOJO();
//					wxComAccessTokenReqApiPOJO.setComponentAppId(wxThirdClientId);
//					wxComAccessTokenReqApiPOJO.setComponentAppSecret(wxThirdSecret);
//
//					WxComVerifyTicketSearchApiPOJO wxComVerifyTicketSearchApiPOJO = new WxComVerifyTicketSearchApiPOJO();
//					List<WxComVerifyTicketApiPOJO> wxComVerifyTicketApiPOJOs = wxComVerifyTicketService.finds(wxComVerifyTicketSearchApiPOJO);
//					String componentVerifyTicket = "";
//					if (!CollectionUtils.isEmpty(wxComVerifyTicketApiPOJOs)) {
//						componentVerifyTicket = wxComVerifyTicketApiPOJOs.get(0).getComponentVerifyTicket();
//					}
//					wxComAccessTokenReqApiPOJO.setComponentVerifyTicket(componentVerifyTicket);
//					String wxComAccessTokenStr = HttpClientUtil.postHttpsJson(wxThirdAccessTokenUrl, JsonUtils.convertToJson(wxComAccessTokenReqApiPOJO));
//					WxComAccessTokenApiPOJO wxComAccessTokenApiPOJO = JsonUtils.convertToJavaBean(wxComAccessTokenStr, WxComAccessTokenApiPOJO.class);
//					wxComAccessTokenApiPOJO.setCreateDateTime(new Date());
//
//					wxComAccessTokenService.insert(wxComAccessTokenApiPOJO);
//				}
				
//				// Refresh authorizer access token every 1 hour
//				WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
//				// 1. get distinct all authorizerAppId
//				List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs4AppId = wxAuthorizerRefreshTokenService.findAuthorizerAppIds(wxAuthorizerRefreshTokenSearchPOJO);
//				if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs4AppId)) {
//					for (WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO4AppId : wxAuthorizerRefreshTokenPOJOs4AppId) {
//						String authorizerAppId = wxAuthorizerRefreshTokenPOJO4AppId.getAuthorizerAppId();
//						wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(authorizerAppId);
//						// get authorizer refresh token to refresh
//						List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenService.finds(wxAuthorizerRefreshTokenSearchPOJO);
//						if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
//							WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = wxAuthorizerRefreshTokenPOJOs.get(0);
//							createDateTime = wxAuthorizerRefreshTokenPOJO.getCreateDateTime();
//							if (curDate.getTime() - createDateTime.getTime() >= 1 * 60 * 60 * 1000L) {	// 1 hours
//								WxAuthorizerRefreshTokenReqApiPOJO wxAuthorizerRefreshTokenReqApiPOJO = new WxAuthorizerRefreshTokenReqApiPOJO();
//								wxAuthorizerRefreshTokenReqApiPOJO.setComponentAppId(wxAuthorizerRefreshTokenPOJO.getComponentAppId());
//								wxAuthorizerRefreshTokenReqApiPOJO.setAuthorizerAppId(wxAuthorizerRefreshTokenPOJO.getAuthorizerAppId());
//								wxAuthorizerRefreshTokenReqApiPOJO.setAuthorizerRefreshToken(wxAuthorizerRefreshTokenPOJO.getAuthorizerRefreshToken());
//								String wxAuthorizerRefreshTokenStr = HttpClientUtil.postHttpsJson(wxThirdAuthorizerRefreshTokenUrl.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken)
//										, JsonUtils.convertToJson(wxAuthorizerRefreshTokenReqApiPOJO));
//								WxAuthorizerRefreshTokenApiPOJO wxAuthorizerRefreshTokenApiPOJO = JsonUtils.convertToJavaBean(wxAuthorizerRefreshTokenStr, WxAuthorizerRefreshTokenApiPOJO.class);
//								BeanUtils.copyProperties(wxAuthorizerRefreshTokenApiPOJO, wxAuthorizerRefreshTokenPOJO);
//								wxAuthorizerRefreshTokenPOJO.setCreateDateTime(new Date());
//								wxAuthorizerRefreshTokenService.insert(wxAuthorizerRefreshTokenPOJO);
//
//							}
//						}
//					}
//				}
				
				return "success";
			}	// end ComponentVerifyTicket(Event)
			
			// authorized
			if (!StringUtils.isBlank(result) && result.contains("authorized")) {
				WxAuthEventRecvAuthorizedApiPOJO wxAuthEventRecvAuthorizedApiPOJO = XmlUtils.convertToJavaBean(result, WxAuthEventRecvAuthorizedApiPOJO.class);
				logger.info("WxAuthEventRecvAuthorizedApiPOJO: {}", wxAuthEventRecvAuthorizedApiPOJO);
				WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO = new WxMsgEventRespTextApiPOJO();
				wxMsgEventRespTextApiPOJO.setToUserName(wxAutoTestUsername);
				wxMsgEventRespTextApiPOJO.setFromUserName(wxAuthEventRecvAuthorizedApiPOJO.getAppId());
				wxMsgEventRespTextApiPOJO.setCreateTime(new Date().getTime() + "");
				wxMsgEventRespTextApiPOJO.setMsgType("text");
				wxMsgEventRespTextApiPOJO.setContent(wxAuthEventRecvAuthorizedApiPOJO.getInfoType() + "from_callback");
				String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
				String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
//				return encryptMsg;
			}
			
			// unauthorized
			if (!StringUtils.isBlank(result) && result.contains("unauthorized")) {
				WxAuthEventRecvAuthorizedApiPOJO wxAuthEventRecvAuthorizedApiPOJO = XmlUtils.convertToJavaBean(result, WxAuthEventRecvAuthorizedApiPOJO.class);
				logger.info("WxAuthEventRecvAuthorizedApiPOJO: {}", wxAuthEventRecvAuthorizedApiPOJO);
				WxMsgEventRespTextApiPOJO wxMsgEventRespTextApiPOJO = new WxMsgEventRespTextApiPOJO();
				wxMsgEventRespTextApiPOJO.setToUserName(wxAutoTestUsername);
				wxMsgEventRespTextApiPOJO.setFromUserName(wxAuthEventRecvAuthorizedApiPOJO.getAppId());
				wxMsgEventRespTextApiPOJO.setCreateTime(new Date().getTime() + "");
				wxMsgEventRespTextApiPOJO.setMsgType("text");
				wxMsgEventRespTextApiPOJO.setContent(wxAuthEventRecvAuthorizedApiPOJO.getInfoType() + "from_callback");
				String replyMsg = XmlUtils.convertToXml(wxMsgEventRespTextApiPOJO);
				String encryptMsg = pc.encryptMsg(replyMsg, timestamp, nonce);
//				return encryptMsg;
			}
			
			
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
				WxOauth2TokenApiPOJO wxOauth2TokenPOJO = JsonUtils.convertToJavaBean(result, WxOauth2TokenApiPOJO.class);
				// get user info
				String profileUrl = myProfileUrl.replace("ACCESS_TOKEN", wxOauth2TokenPOJO.getAccessToken())
									.replace("OPENID", wxOauth2TokenPOJO.getOpenId());
				String userInfo = HttpClientUtil.get(profileUrl);
				WxUserApiPOJO wxUserPOJO = JsonUtils.convertToJavaBean(userInfo, WxUserApiPOJO.class);
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
				
				myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/web/wx/oauth/success");
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

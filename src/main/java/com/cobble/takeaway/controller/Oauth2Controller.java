package com.cobble.takeaway.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.oauth2.MyRedirectStrategy;
import com.cobble.takeaway.oauth2.WxOauth2TokenApiPOJO;
import com.cobble.takeaway.oauth2.WxUserApiPOJO;
import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.Apply2SearchPOJO;
import com.cobble.takeaway.pojo.HtmlConvertedPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.weixin.RelWxPuOpenPOJO;
import com.cobble.takeaway.pojo.weixin.RelWxPuOpenSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerRefreshTokenPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerRefreshTokenSearchPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserPOJO;
import com.cobble.takeaway.pojo.weixin.WxPersonUserSearchPOJO;
import com.cobble.takeaway.pojo.weixin.api.FuncInfoApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerAccessTokenApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerAccessTokenReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerInfoApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerInfoReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerRefreshTokenApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxAuthorizerRefreshTokenReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComAccessTokenSearchApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketEncryptApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxComVerifyTicketSearchApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxPreAuthCodeApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxPreAuthCodeReqApiPOJO;
import com.cobble.takeaway.pojo.weixin.api.WxUserInfoApiPOJO;
import com.cobble.takeaway.service.ActivityService;
import com.cobble.takeaway.service.RelWxPuOpenService;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.service.WxAuthorizerAccessTokenService;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.service.WxAuthorizerRefreshTokenService;
import com.cobble.takeaway.service.WxComAccessTokenService;
import com.cobble.takeaway.service.WxComVerifyTicketService;
import com.cobble.takeaway.service.WxPersonUserService;
import com.cobble.takeaway.spring.security.MyUser;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.FileUtil;
import com.cobble.takeaway.util.HttpClientUtil;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;
import com.cobble.takeaway.util.XmlUtils;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@Controller
public class Oauth2Controller extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(Oauth2Controller.class);
	
	public final static int WX_SUBSCRIBE = 1;
	
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
	@Autowired
	private RelWxPuOpenService relWxPuOpenService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActivityService activityService;
	
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

	@RequestMapping(value = "/web/weixin/wxprize", method = {RequestMethod.GET})
	public ModelAndView wxPrize(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			Long userId = UserUtil.getCurrentUser().getUserId();
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
			Long userId = UserUtil.getCurrentUser().getUserId();
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
	
	@RequestMapping(value = "/web/weixin/wxactivitys", method = {RequestMethod.GET})
	public ModelAndView wxActivitys(@RequestParam(value="wxIndexCode") String wxIndexCode
			, @RequestParam(value="openId") String openId
			, @RequestParam(value="unionId") String unionId
			, @RequestParam(value="typeCode") Integer typeCode
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			Long userId = UserUtil.getCurrentUser().getUserId();
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
			
			UserUtil.getCurrentUser().getUserId();
			WxUserInfoApiPOJO wxUserInfoApiPOJO = (WxUserInfoApiPOJO) session.getAttribute(CommonConstant.WX_USER_INFO_API_POJO);
			
			ret.addObject(CommonConstant.WX_USER_INFO_API_POJO, wxUserInfoApiPOJO);
			ret.addObject("wxActivitys4ApplyUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxactivitys" + "?wxIndexCode=" + wxIndexCode
					+ "&openId=" + session.getAttribute(CommonConstant.OPEN_ID)
					+ "&unionId=" + session.getAttribute(CommonConstant.UNION_ID)
					+ "&typeCode=" + 1);
			ret.addObject("wxActivitys4SurveyUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxactivitys" + "?wxIndexCode=" + wxIndexCode
					+ "&openId=" + session.getAttribute(CommonConstant.OPEN_ID)
					+ "&unionId=" + session.getAttribute(CommonConstant.UNION_ID)
					+ "&typeCode=" + 2);

			ret.addObject("wxScoreUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxscore");
			ret.addObject("wxPrizeUrl", HttpRequestUtil.getBase(request) + "/web/weixin/wxprize");
			ret.setViewName("/page/weixin/wx_person_user_center");
		} catch (Exception e) {
			logger.error("wxLinkUserCenter error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/third/authorizer/qrcode", method = {RequestMethod.GET})
	public ModelAndView wxQrcode(@RequestParam(value="authorizerAppId", required=false) String authorizerAppId
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
				ret.setViewName("/page/weixin/authorizer_qrcode");
				
			}
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/wx/oauth2/third/web/authorizer", method = {RequestMethod.POST})
	@ResponseBody
	public WxAuthorizerInfoPOJO authorizer(WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception {
		WxAuthorizerInfoPOJO ret = new WxAuthorizerInfoPOJO();
		try {
			List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = wxAuthorizerInfoService.finds(wxAuthorizerInfoSearchPOJO);
			if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
				ret = wxAuthorizerInfoPOJOs.get(0);
				
				if (StringUtils.isBlank(ret.getQrcodeFilePath())) {
					// 生成qrcodeFilePath
					String toDir = messageSource.getMessage("files.directory", null, null);
					String toFilePath = "wx/authorizer/qrcode/" + ret.getAuthorizerAppId() + ".jpg";
					toFilePath = toFilePath.replace("/", File.separator);
					HtmlConvertedPOJO htmlConvertedPOJO = FileUtil.url2File(ret.getQrcodeUrl(), toDir, toFilePath);
					ret.setQrcodeFilePath(htmlConvertedPOJO.getHtmlPath());
					
					WxAuthorizerInfoPOJO temp = new WxAuthorizerInfoPOJO();
					temp.setWxAuthorizerInfoId(ret.getWxAuthorizerInfoId());
					temp.setQrcodeFilePath(htmlConvertedPOJO.getHtmlPath());
					
					wxAuthorizerInfoService.update(temp);
					
				}
				
			}
			
		} catch (Exception e) {
			logger.error("insert error.", e);
			throw e;
		}
		
		return ret;
	} 
	

	@RequestMapping(value = "/api/wx/oauth2/third/web/subscribe", method = {RequestMethod.POST})
	@ResponseBody
	/**
	 * paramters: unionId and authorizerAppId
	 * @param wxPersonUserPOJO
	 * @return
	 * @throws Exception
	 */
	public StatusPOJO subscribe(RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			// 根据unionId and authorizerAppId, 获取是否存在openId
			String unionId = relWxPuOpenSearchPOJO.getUnionId();
			String authorizerAppId = relWxPuOpenSearchPOJO.getAuthorizerAppId();

			HttpSession session = request.getSession();
			if (StringUtils.isBlank(unionId)) {
				unionId = (String) session.getAttribute(CommonConstant.UNION_ID);
			}
			if (StringUtils.isBlank(authorizerAppId)) {
				authorizerAppId = (String) session.getAttribute(CommonConstant.AUTHORIZER_APP_ID);
			}
			
			
			RelWxPuOpenPOJO relWxPuOpenPOJO = relWxPuOpenService.findWithPu(unionId, authorizerAppId);
			
			if (relWxPuOpenPOJO == null) {
				ret.setSuccess(false);
				ret.setDesc("没有关注：");
				return ret;
			}
			
			WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
			wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(relWxPuOpenPOJO.getAuthorizerAppId());
			List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenService.finds(wxAuthorizerRefreshTokenSearchPOJO);
			WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = new WxAuthorizerRefreshTokenPOJO();
			if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
				wxAuthorizerRefreshTokenPOJO = wxAuthorizerRefreshTokenPOJOs.get(0);
			}
			WxUserInfoApiPOJO wxUserInfoApiPOJO = this.getWxUserInfoApi(wxAuthorizerRefreshTokenPOJO.getAuthorizerAccessToken(), relWxPuOpenPOJO.getOpenId(), null);
			if (wxUserInfoApiPOJO != null && wxUserInfoApiPOJO.getSubscribe() == WX_SUBSCRIBE) {
				ret.setSuccess(true);
				ret.setDesc("已经关注：" + wxUserInfoApiPOJO.getOpenId());
			} else {
				ret.setSuccess(false);
				ret.setDesc("没有关注：");
			}
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			ret.setDesc(e.getMessage());
			throw e;
		}
		
		return ret;
	} 
	
	private WxUserInfoApiPOJO getWxUserInfoApi(String authorizerAccessToken, String openId, String thirdWebAccessToken) throws Exception {
		String userInfo = "";
		WxUserInfoApiPOJO wxUserInfoApiPOJO = new WxUserInfoApiPOJO();
		String nickname = "";
		String country = "";
		String province = "";
		String city = "";
		if (StringUtils.isNotBlank(authorizerAccessToken)) {
			try {
				String myUserInfoUidUrl = userInfoUidUrl.replace("ACCESS_TOKEN", authorizerAccessToken)
						.replace("OPENID", openId);
				userInfo = HttpClientUtil.get(myUserInfoUidUrl);
				wxUserInfoApiPOJO = JsonUtils.convertToJavaBean(userInfo, WxUserInfoApiPOJO.class);
				if (wxUserInfoApiPOJO.getSubscribe() == WX_SUBSCRIBE) {
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
			}
		}
		
		if (wxUserInfoApiPOJO.getSubscribe() != WX_SUBSCRIBE && StringUtils.isNotBlank(thirdWebAccessToken)) {
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
			
			BeanUtils.copyProperties(wxUserPOJO, wxUserInfoApiPOJO);
		}
		
		logger.info("wxUserInfoApiPOJO: {}", wxUserInfoApiPOJO);
		return wxUserInfoApiPOJO;
	}
	
	@RequestMapping(value = "/web/wx/oauth2/third/web/authCode"/*, produces = {MediaType.APPLICATION_JSON_VALUE}*/)
	public ModelAndView wxWebAuthCode(@RequestParam(value="code", required=false) String code
			, @RequestParam(value="state", required=false) String state
			, @RequestParam(value="appid", required=false) String appid
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			HttpSession session = request.getSession();
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
				WxOauth2TokenApiPOJO wxOauth2TokenPOJO = JsonUtils.convertToJavaBean(result, WxOauth2TokenApiPOJO.class);
				// get user info with subscribe
				logger.info("Get user info with subscribe");
				WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
				wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(appid);
				List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenService.finds(wxAuthorizerRefreshTokenSearchPOJO);
				WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = new WxAuthorizerRefreshTokenPOJO();
				if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
					wxAuthorizerRefreshTokenPOJO = wxAuthorizerRefreshTokenPOJOs.get(0);
				}
				
				WxUserInfoApiPOJO wxUserInfoApiPOJO = this.getWxUserInfoApi(wxAuthorizerRefreshTokenPOJO.getAuthorizerAccessToken(), 
						wxOauth2TokenPOJO.getOpenId(), wxOauth2TokenPOJO.getAccessToken());
				// 微信用户信息放入session
				session.setAttribute(CommonConstant.WX_USER_INFO_API_POJO, wxUserInfoApiPOJO);
				session.setAttribute(CommonConstant.OPEN_ID, wxUserInfoApiPOJO.getOpenId());
				session.setAttribute(CommonConstant.UNION_ID, wxUserInfoApiPOJO.getUnionId());
				session.setAttribute(CommonConstant.AUTHORIZER_APP_ID, appid);
				
				logger.info("wxUserInfoApiPOJO: {}", wxUserInfoApiPOJO);
				// get user info unionID
				/*String myUserInfoUidUrl = userInfoUidUrl.replace("ACCESS_TOKEN", wxOauth2TokenPOJO.getAccessToken())
											.replace("OPENID", wxOauth2TokenPOJO.getOpenId());
				String myUserInfoUid = HttpClientUtil.get(myUserInfoUidUrl);*/
				
				// insert Wx person user into DB
				UserPOJO userPOJO1 = userService.findUserByName(wxUserInfoApiPOJO.getUnionId());
				UserPOJO userPOJO = new UserPOJO();
				if (userPOJO1 == null) {
					userPOJO.setUsername(wxUserInfoApiPOJO.getUnionId());
					userPOJO.setPassword(wxUserInfoApiPOJO.getUnionId());
					userPOJO.setUserType(MyUser.PERSON);
					userPOJO.setNickname(wxUserInfoApiPOJO.getNickname());
					userService.insert(userPOJO);
				} else {
					BeanUtils.copyProperties(userPOJO1, userPOJO);
				}
				
				WxPersonUserSearchPOJO wxPersonUserSearchPOJO = new WxPersonUserSearchPOJO();
				wxPersonUserSearchPOJO.setUnionId(wxUserInfoApiPOJO.getUnionId());
				wxPersonUserSearchPOJO.setUserId(userPOJO.getUserId());
				List<WxPersonUserPOJO> wxPersonUserPOJOs = wxPersonUserService.finds(wxPersonUserSearchPOJO);
				WxPersonUserPOJO wxPersonUserPOJO = new WxPersonUserPOJO();
				if (CollectionUtils.isEmpty(wxPersonUserPOJOs)) {
					BeanUtils.copyProperties(wxUserInfoApiPOJO, wxPersonUserPOJO);
					/*List<String> tagidList = wxUserPOJO.getTagidList();
					String tagidListStr = "";
					if (!CollectionUtils.isEmpty(tagidList)) {
						tagidListStr = CollectionUtilx.nullSafeToString(tagidList);
					}
					wxPersonUserPOJO.setTagidList(tagidListStr);*/
					wxPersonUserPOJO.setUserId(userPOJO.getUserId());
					wxPersonUserService.insert(wxPersonUserPOJO);
				} else {
					wxPersonUserPOJO = wxPersonUserPOJOs.get(0);
				}
				
				RelWxPuOpenSearchPOJO relWxPuOpenSearchPOJO = new RelWxPuOpenSearchPOJO();
				relWxPuOpenSearchPOJO.setOpenId(wxUserInfoApiPOJO.getOpenId());
				relWxPuOpenSearchPOJO.setWxPersonUserId(wxPersonUserPOJO.getWxPersonUserId());
				List<RelWxPuOpenPOJO> relWxPuOpenPOJOs = relWxPuOpenService.finds(relWxPuOpenSearchPOJO);
				if (CollectionUtils.isEmpty(relWxPuOpenPOJOs)) {
					RelWxPuOpenPOJO relWxPuOpenPOJO = new RelWxPuOpenPOJO();
					relWxPuOpenPOJO.setOpenId(wxUserInfoApiPOJO.getOpenId());
					relWxPuOpenPOJO.setWxPersonUserId(wxPersonUserPOJO.getWxPersonUserId());
					relWxPuOpenPOJO.setAuthorizerAppId(appid);
					relWxPuOpenPOJO.setCreateDateTime(new Date());
					relWxPuOpenService.insert(relWxPuOpenPOJO);
				}
				
				/*ret.setViewName("/page/person/reg_success");*/
				
				String msg = "openid: " + wxOauth2TokenPOJO.getOpenId() + ", nickname: " + wxUserInfoApiPOJO.getNickname()
						+ "\n" + "Token: \n" + result + "\n" + "wxUserInfoApiPOJO: \n" + wxUserInfoApiPOJO + "\n"
						/*+ "MyUserInfoUid: \n" + myUserInfoUid*/;
				/*ret.addObject("msg", msg);
				
				session.setAttribute("msg", msg);*/
				

				/*ret.setViewName("redirect:/web/wxAutoLogin");
				session.setAttribute("regUserPOJO", userPOJO);*/
				
				///////
				
				MyUser myUser = userService.findMyUserByName(userPOJO.getUsername());

				String openId = (String) session.getAttribute("openId");
				String unionId = (String) session.getAttribute("unionId");
				if (StringUtils.isNotBlank(openId)) {
					myUser.setOpenId(openId);
				}
				if (StringUtils.isNotBlank(unionId)) {
					myUser.setUnionId(unionId);
				}
				//List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS");
				Collection<GrantedAuthority> authorities = myUser.getAuthorities();
				
				UsernamePasswordAuthenticationToken anAnthentication = new UsernamePasswordAuthenticationToken(myUser, userPOJO.getPassword(), authorities);
				SecurityContextHolder.getContext().setAuthentication(anAnthentication);
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				logger.info("principal instanceof MyUser: " + (principal instanceof MyUser));
				if (principal instanceof MyUser) {
					myUser = (MyUser) principal;
					session.setAttribute("username", myUser.getUsername());
					session.setAttribute("userType", myUser.getUserType());
					session.setAttribute("myUser", myUser);
				}
				
				SavedRequest savedRequest = HttpRequestUtil.getRequest(request, response);
				String url = "";
				/*DefaultSavedRequest defaultSavedRequest = null;
				if (savedRequest instanceof DefaultSavedRequest) {
					defaultSavedRequest = (DefaultSavedRequest) savedRequest;
				}*/
				if (savedRequest != null) {
					url  = savedRequest.getRedirectUrl();
				} else {
					if (MyUser.ADMIN.equals(myUser.getUserType())) {
						url = UserController.URL_ADMIN;
					} else {
						url = UserController.URL_INDEX;
					}
				}
				
				myRedirectStrategy.sendRedirect(request, response, url);
				//////
				
//				myRedirectStrategy.sendRedirect(request, response, HttpRequestUtil.getBase(request) + "/web/wx/oauth2/success");
			} else {
				logger.info("code isnot null");
				throw new NullPointerException("code isnot null");
			}
			
//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return null;
		/*return ret;*/
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
			wxAuthorizerInfoPOJO2.setNickName(new String(nickName.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8));
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
				// 报错授权者refresh token信息进数据库
				WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = new WxAuthorizerRefreshTokenPOJO();
				wxAuthorizerRefreshTokenPOJO.setAuthorizerAccessToken(authorizerAccessToken);
				wxAuthorizerRefreshTokenPOJO.setAuthorizerAppId(authorizerAppId);
				wxAuthorizerRefreshTokenPOJO.setAuthorizerRefreshToken(authorizerRefreshToken);
				wxAuthorizerRefreshTokenPOJO.setComponentAppId(wxThirdClientId);
				wxAuthorizerRefreshTokenPOJO.setExpiresIn(expiresIn);
				wxAuthorizerRefreshTokenPOJO.setCreateDateTime(new Date());
				wxAuthorizerRefreshTokenService.insert(wxAuthorizerRefreshTokenPOJO);
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
	
	@RequestMapping(value = "/web/wx/{authorizerAppId}/msgEventRecieve", method=RequestMethod.POST)
	@ResponseBody
	public String msgEventRecieve(@RequestBody String requestBody, @PathVariable(value="authorizerAppId") String authorizerAppId, 
			@RequestParam(value="signature", required = false) String signature,
			@RequestParam(value="timestamp", required = false) String timestamp,
			@RequestParam(value="nonce", required = false) String nonce,
			@RequestParam(value="encrypt_type", required = false) String encryptType,
			@RequestParam(value="msg_signature", required = false) String msgSignature,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			logger.info("msgEventRecieve begin...");
			logger.info("Request params, signature: {}, timestamp: {}, nonce: {}, encrypt_type: {}, msg_signature: {}",
					signature, timestamp, nonce, encryptType, msgSignature);
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
			
			/*WxComVerifyTicketEncryptApiPOJO wxComVerifyTicketEncryptPOJO = XmlUtils.convertToJavaBean(requestBody, WxComVerifyTicketEncryptApiPOJO.class);
			String encrypt = wxComVerifyTicketEncryptPOJO.getEncrypt();
			logger.info("encrypt: {}", encrypt);
			String format = "<xml><ToUserName><![CDATA[%1$s]]></ToUserName><Encrypt><![CDATA[%2$s]]></Encrypt></xml>";
			String fromXML = String.format(format, appId, encrypt);
			
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String result = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
			logger.info("Paintext: {}", result);
			
			WxComVerifyTicketApiPOJO wxComVerifyTicketPOJO = XmlUtils.convertToJavaBean(result, WxComVerifyTicketApiPOJO.class);
			logger.info("wxComVerifyTicketPOJO: {}", wxComVerifyTicketPOJO);
			wxComVerifyTicketService.insert(wxComVerifyTicketPOJO);*/
			
		} catch (Exception e) {
			logger.error("error: ", e);
//			throw e;
		}
		
		return "success";
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
			
			// add comAccessToken into DB every 1 hour
			WxComAccessTokenSearchApiPOJO wxComAccessTokenSearchPOJO = new WxComAccessTokenSearchApiPOJO();
			List<WxComAccessTokenApiPOJO> wxComAccessTokenPOJOs = wxComAccessTokenService.finds(wxComAccessTokenSearchPOJO);
			String componentAccessToken = "";
			Date createDateTime = new Date();
			if (!CollectionUtils.isEmpty(wxComAccessTokenPOJOs)) {
				componentAccessToken = wxComAccessTokenPOJOs.get(0).getComponentAccessToken();
				createDateTime = wxComAccessTokenPOJOs.get(0).getCreateDateTime();
			}
			Date curDate = new Date();
			
			if (curDate.getTime() - createDateTime.getTime() >= 1 * 60 * 60 * 1000L) {	// 1 hours
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
			}
			
			// Refresh authorizer access token every 1 hour
			WxAuthorizerRefreshTokenSearchPOJO wxAuthorizerRefreshTokenSearchPOJO = new WxAuthorizerRefreshTokenSearchPOJO();
			// 1. get distinct all authorizerAppId
			List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs4AppId = wxAuthorizerRefreshTokenService.findAuthorizerAppIds(wxAuthorizerRefreshTokenSearchPOJO);
			if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs4AppId)) {
				for (WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO4AppId : wxAuthorizerRefreshTokenPOJOs4AppId) {
					String authorizerAppId = wxAuthorizerRefreshTokenPOJO4AppId.getAuthorizerAppId();
					wxAuthorizerRefreshTokenSearchPOJO.setAuthorizerAppId(authorizerAppId);
					// get authorizer refresh token to refresh
					List<WxAuthorizerRefreshTokenPOJO> wxAuthorizerRefreshTokenPOJOs = wxAuthorizerRefreshTokenService.finds(wxAuthorizerRefreshTokenSearchPOJO);
					if (!CollectionUtils.isEmpty(wxAuthorizerRefreshTokenPOJOs)) {
						WxAuthorizerRefreshTokenPOJO wxAuthorizerRefreshTokenPOJO = wxAuthorizerRefreshTokenPOJOs.get(0);
						createDateTime = wxAuthorizerRefreshTokenPOJO.getCreateDateTime();
						if (curDate.getTime() - createDateTime.getTime() >= 1 * 60 * 60 * 1000L) {	// 1 hours
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
				}
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

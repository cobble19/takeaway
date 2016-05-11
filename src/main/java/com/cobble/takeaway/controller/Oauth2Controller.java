package com.cobble.takeaway.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.oauth2.MyRedirectStrategy;
import com.cobble.takeaway.oauth2.WxOauth2TokenPOJO;
import com.cobble.takeaway.oauth2.WxUserPOJO;
import com.cobble.takeaway.util.HttpClientUtil;
import com.cobble.takeaway.util.HttpRequestUtil;
import com.cobble.takeaway.util.JsonUtils;

@Controller
public class Oauth2Controller extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(Oauth2Controller.class);
	
	@Autowired
	private MessageSource messageSource;
	
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
	

	@RequestMapping(value = "/web/wx/authEventRecieve", method=RequestMethod.POST)
	public String authEventRecieve(String requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			logger.info("authEventRecieve begin...");
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			logger.info("authEventRecieve uri: " + uri + ", qs: " + qs);
			logger.info("requestBody: " + requestBody);
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			logger.info("From request: " + sb.toString());
			
//			ret.setViewName("/page/oauth2_success");
		} catch (Exception e) {
			logger.error("error: ", e);
			throw e;
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

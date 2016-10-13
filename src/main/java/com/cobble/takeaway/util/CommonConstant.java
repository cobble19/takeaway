package com.cobble.takeaway.util;

public interface CommonConstant {
	String FILES_DIRECTORY = "files.directory";
	String CHECK_SESSION_URLS_NEEDLOGIN = "check.session.urls.needlogin";
	String CHECK_SESSION_URLS_NONEEDLOGIN = "check.session.urls.noneedlogin";
	
	String WX_THIRD_CLIENTID = "WX.third.clientId";
	String WX_THIRD_SECRET = "WX.third.secret";
	// 登录授权的发起页域名
	String WX_THIRD_SEND_DOMAIN = "WX.third.sendDomain";
	// 授权事件接收URL
	String WX_THIRD_AUTH_EVENT_RECIEVE = "WX.third.authEventRecieve";
	
	// 公众号消息校验Token
	String WX_THIRD_MSG_VERIFY_TOKEN = "WX.third.msgVerifyToken";
	// 公众号消息加解密Key
	String WX_THIRD_MSG_ENC_KEY = "WX.third.msgEncKey";
	// 公众号消息与事件接收URL
	String WX_THIRD_MSG_EVENT_RECIEVE = "WX.third.msgEventRecieve";
	// 网页开发域名
	String WX_THIRD_DEV_DOMAIN = "WX.third.devDomain";
	
	
	// For session
	String AUTHORIZER_APP_ID = "authorizerAppId";
	String UNION_ID = "unionId";
	String OPEN_ID = "openId";
	String WX_USER_INFO_API_POJO = "wxUserInfoApiPOJO";
	String INDEX_CODE = "indexCode";
	
	String CURRENT_WX_PERSON_USER = "currentWxPersonUser";
	String PROXY_OPEN_ID = "proxyOpenId";
	String PROXY_AUTHORIZER_APP_ID = "proxyAuthorizerAppId";
	
	// 权限
	String NUMBER_GUESS = "3001";
	
	// other
	String FORGET_PASSWORD = "forgetPassword";
	String LOGIN_VICE = "loginVice";
	
	// Oauth2Controller
	public final static int WX_SUBSCRIBE = 1;

	// 检测VIEW， 获取from/to，注册其他的公众号的用户
	// 主OPEN_ID用合肥交通广播，得味驿站的为副公众号
	// 合肥交通广播
	public final static String HFJT_USER_NAME = "gh_b7b6c7fa2db9";
	public final static String HFJT_AUTHORIZER_APP_ID = "wx483bd8288ebe84b4";
	public final static Long HFJT_USER_ID = 22L;
	// 得味驿站
	public final static String DWYZ_USER_NAME = "gh_31205fc6892e";
	public final static String DWYZ_AUTHORIZER_APP_ID = "wxe0037de41e16f816";
	public final static Long DWYZ_USER_ID = 16L;
	
}

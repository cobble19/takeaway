package com.cobble.takeaway.util;

public interface CommonConstant {
	String WX_MSG_SIGN_IN = "签到_DWYZ";
	String WX_MSG_JOIN_MEMBER = "加入会员_DWYZ";
	String WX_MSG_MEMBER_CENTER = "会员中心_DWYZ";
	
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
	
	// COM
	String COMMON_PARAM = "commonParam";
	
	String FORGET_PASSWORD = "forgetPassword";
	String REG_ENTERPRISE_USER = "regEnterpriseUser";
	// person user
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
	// 代理公众号
	public final static String PROXY_USER_NAME_VALUE = DWYZ_USER_NAME;
	public final static String PROXY_AUTHORIZER_APP_ID_VALUE = DWYZ_AUTHORIZER_APP_ID;
	public final static Long PROXY_USER_ID_VALUE = DWYZ_USER_ID;
	
	// 微信菜单button level
	public final static int WX_MENU_LEVEL_1 = 1;
	public final static int WX_MENU_LEVEL_2 = 2;
	
	// 微信网页授权
	//service_type_info: 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
	public final static int WX_SERVICE_TYPE_SUBSCRIBE = 0;
	public final static int WX_SERVICE_TYPE_SUBSCRIBE_UPGRADE = 1;
	public final static int WX_SERVICE_TYPE_SERVICE = 2;
	// verify_type_info: 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，
	// 4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
	public final static int WX_VERIFY_TYPE_NOT = -1;
	public final static int WX_VERIFY_TYPE_WEIXIN = 0;
	public final static int WX_VERIFY_TYPE_WEIBO = 1;
	public final static int WX_VERIFY_TYPE_TENCENTWEIBO = 2;
	public final static int WX_VERIFY_TYPE_NOTNAME = 3;
	public final static int WX_VERIFY_TYPE_NOTNAME_WEIBO = 4;
	public final static int WX_VERIFY_TYPE_NOTNAME_TENCENTWEIBO = 5;
	
	// 会员标志
	public final static int MEMBER_FLAG = 1;
	public final static int MEMBER_FLAG_NOT = 0;
	
	// 有效标志
	public final static int ENABLE_FLAG = 1;
	public final static int DISABLE_FLAG = 0;
	
	// 0-系统定义, 1-客户自定义, 2-抽奖, 3-语音抽奖
	public final static String MSG_TYPE_SYSTEM = "0";
	public final static String MSG_TYPE_CUSTOMER = "1";
	public final static String MSG_TYPE_LOTTERY = "2";
	public final static String MSG_TYPE_LOTTERY_VOICE = "3";
	
	// 抽奖活动类型
	public final static String INTERACTIVE_NORMAL = "NORMAL";
	public final static String INTERACTIVE_LOTTERY = "INTERACTIVE_LOTTERY";
	public final static String INTERACTIVE_LOTTERY_VOICE = "INTERACTIVE_LOTTERY_VOICE";
	
	// 加入会员绑定;类型
	public final static String BIND_MEMBER_TYPE_LOTTERY_VOICE = "BIND_MEMBER_TYPE_LOTTERY_VOICE";
	// qrcode, 二维码, 
	// 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，
	// QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
	public final static String WX_QR_ACTION_QR_SCENE = "QR_SCENE";
	public final static String WX_QR_ACTION_QR_STR_SCENE = "QR_STR_SCENE";
	public final static String WX_QR_ACTION_QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
	public final static String WX_QR_ACTION_QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
	
	
	
}

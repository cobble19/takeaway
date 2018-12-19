<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:if test="${not empty documentTitle}">
    	<title>${documentTitle}</title>
    </c:if>
    <c:if test="${empty documentTitle}">
    	<title>得味驿站</title>
    </c:if>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- jQuery Validation -->
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/jquery.validate.min.js"></script>
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/additional-methods.min.js"></script>
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/jquery.validate.message.zh_cn.js"></script>
    <style>
		form label.error {
			color: #f00; font-size:12px;
		}
	</style>
	<!-- jQuery datetime -->
    <script src="<cmn:base/>/js/thirdpart/jquery-datetimepicker-2.4.3/jquery.datetimepicker.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-datetimepicker-2.4.3/jquery.datetimepicker.css">

    <!-- jQuery loading -->
    <script src="<cmn:base/>/js/thirdpart/jquery-loading/jquery.loading.js"></script>
    <link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-loading/jquery.loading.css">

    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- UEditor -->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/lang/zh-cn/zh-cn.js"></script>

    <!-- Date.format('Y-m-d H:i:s'); -->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/date.format.js"></script>

	<!-- JQuery-weui -->
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/weui/weui.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/weui/jquery-weui.css">
    <script src="<cmn:base/>/js/thirdpart/weui/jquery-weui.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/city-picker.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/swiper.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/zepto.js"></script>

    <!-- customer -->
    <!-- 公共的函数 -->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/common.js"></script>
    <!--<link href="<cmn:base/>/css/common.css" rel="stylesheet"> -->

    <link href="<cmn:base/>/css/common.css" rel="stylesheet">
    <link href="<cmn:base/>/css/dwsy.css" rel="stylesheet" media="print, projection, screen">

    <!-- 微信JS-SDK -->
    <script src="<cmn:base/>/js/thirdpart/weixin/jweixin-1.2.0.js"></script>
    
	<input id="basePath" type="hidden" value='<cmn:base/>'>
	<c:set var="basePath"><cmn:base/></c:set>
	<input id="userId" type="hidden" value='${sessionScope.myUser.userId}'>
	<input id="username" type="hidden" value='${sessionScope.myUser.username}'>

    <input id="proxyOpenId" type="hidden" value='${sessionScope.proxyOpenId}'>
	<input id="unionId" type="hidden" value='${sessionScope.unionId}'>
	<input id="openId" type="hidden" value='${sessionScope.openId}'>
	<input id="authorizerAppId" type="hidden" value='${sessionScope.authorizerAppId}'>
    <input id="proxyAuthorizerAppId" type="hidden" value='${sessionScope.proxyAuthorizerAppId}'>
	<input id="indexCode" type="hidden" value='${sessionScope.indexCode}'>
	







    

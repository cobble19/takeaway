﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
    <!-- jQuery UI -->
    <script src="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.min.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.structure.min.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.theme.min.css">
	<!-- jQuery datetime -->
    <script src="<cmn:base/>/js/thirdpart/jquery-datetimepicker-2.4.3/jquery.datetimepicker.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-datetimepicker-2.4.3/jquery.datetimepicker.css">

    <!-- jQuery loading -->
    <script src="<cmn:base/>/js/thirdpart/jquery-loading/jquery.loading.js"></script>
    <link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-loading/jquery.loading.css">
	
    
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!--[if lte IE 6]>
	<script type="text/javascript" src="<cmn:base/>/bootstrap/js/bootstrap-ie.js"></script>
	<![endif]-->
	<!-- Just for debugging purposes. Don't actually copy this line! -->
	<!--[if lt IE 9]>
	<script src="<cmn:base/>/bootstrap/js/ie8-responsive-file-warning.js"></script>
	<![endif]-->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<!-- UEditor -->    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/lang/zh-cn/zh-cn.js"></script>
    
<%-- <script src="<cmn:base/>/js/jquery-1.2.4b.js" type="text/javascript"></script> --%>
	<!-- conflict with jQuery-ui -->
    <!--<script src="<cmn:base/>/js/ui.core.js" type="text/javascript"></script>
	<script src="<cmn:base/>/js/ui.tabs.js" type="text/javascript"></script>-->
	<!-- <script type="text/javascript" src="http://api.go2map.com/maps/js/api_v2.5.1.js"></script> -->
	<!-- <script src="js/jquery-1.2.4b.js" type="text/javascript"></script> -->
	
	
	<!-- <script src="js/ui.core.js" type="text/javascript"></script>
	<script src="js/ui.tabs.js" type="text/javascript"></script> -->

	<!-- DataTables -->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/media/js/jquery.dataTables.min.js"></script>
    <link href="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/media/css/jquery.dataTables.min.css" rel="stylesheet">
    <!-- DataTables integration Bootstrap -->
    <script src="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/integration/bootstrap/3/dataTables.bootstrap.css">
	<!-- Date.format('Y-m-d H:i:s'); -->
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/date.format.js"></script>
	
	<%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/jquery-zeroclipboard/jquery.zeroclipboard.js"></script> --%>
	
	<%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/jquery-clipboard/jquery.clipboard.js"></script> --%>
	
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/clipboard/clipboard.min.js"></script>
	
	<%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/zeroclipboard/ZeroClipboard.min.js"></script> --%>
	
	<!-- JQuery-weui -->
	<%-- <link rel="stylesheet" href="<cmn:base/>/js/thirdpart/weui/weui.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/weui/jquery-weui.css">
    <script src="<cmn:base/>/js/thirdpart/weui/jquery-weui.js"></script> --%>
    <%-- <script src="<cmn:base/>/js/thirdpart/weui/city-picker.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/swiper.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/zepto.js"></script> --%>
    
    <!-- Mustache -->
    <script src="<cmn:base/>/js/thirdpart/mustache/mustache.js"></script>
	<!-- 微信JS-SDK -->
    <script src="<cmn:base/>/js/thirdpart/weixin/jweixin-1.2.0.js"></script>
    
    <!-- customer -->
    <!-- 公共的函数 -->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/common.js"></script>
    <link href="<cmn:base/>/css/common.css" rel="stylesheet">
	<link href="<cmn:base/>/css/dwsy.css" rel="stylesheet" media="print, projection, screen">
    <!-- <link href="css/dwsy.css" rel="stylesheet" media="print, projection, screen"> -->
    <!--<link href="<cmn:base/>/css/wm.css" rel="stylesheet">-->
    
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
	
	
    <%-- <%  
	    String path = request.getContextPath();  
	    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";  
	%> --%>  
 	<%-- <base href="${basePath}"/>  --%>
 	
 	<%-- <cmn:basex/> --%>
 	
    

    

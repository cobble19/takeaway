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
    
	<input id="basePath" type="hidden" value='<cmn:base/>'>
	<c:set var="basePath"><cmn:base/></c:set>
	<input id="userId" type="hidden" value='${sessionScope.myUser.userId}'>
	<input id="username" type="hidden" value='${sessionScope.myUser.username}'>
	
	<input id="unionId" type="hidden" value='${sessionScope.unionId}'>
	<input id="openId" type="hidden" value='${sessionScope.openId}'>
	<input id="authorizerAppId" type="hidden" value='${sessionScope.authorizerAppId}'>
	







    

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    <%@include file="../common/head.jsp" %>
    
    <%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/authorizer_qrcode.js"></script> --%>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
            <div class="col-md-6 col-md-offset-3 col-xs-12" style=" margin-top:20px; margin-bottom:50px;">
			        <h4>
			        	<c:if test="${errorCode eq 'NOVIP'}">第一步.</c:if>请长按识别二维码或微信扫描二维码关注公众号
				        <c:if test="${errorCode eq 'NOVIP'}">
				        	<br/>第二步.进入公众号请加入会员
				        </c:if>
			        </h4>
            </div>      
  			<div class="col-md-6 col-md-offset-3 col-xs-12">
  				<div class="thumbnail">
			      <img id="qrcodeImg" src="<cmn:base/>/${wxAuthorizerInfoPOJO.qrcodeFilePath}" alt="">
			    </div>
  			</div>
  		</div>
	</div>
  </body>
</html>

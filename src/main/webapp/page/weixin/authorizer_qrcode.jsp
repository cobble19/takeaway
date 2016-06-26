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
  	<div class="container-fluid">
  		<div class="row">
  			<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2">
  				<div class="thumbnail">
			      <div class="caption">
			        <h3>请通过长按识别二维码或微信扫描二维码关注公众号</h3>
			        <p></p>
			      </div>
			      <img id="qrcodeImg" src="<cmn:base/>/${wxAuthorizerInfoPOJO.qrcodeFilePath}" alt="">
			      </div>
  			</div>
  		</div>
  		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>
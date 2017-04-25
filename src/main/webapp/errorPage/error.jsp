<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/page/common/head.jsp" %>
    
    <meta http-equiv="refresh" content="5; URL=<cmn:base/>/login.jsp">
    
  </head>
  <body style="padding:0px; background-color:#f1f1f1;">
  	
  	<div class="container">
  		<div class="row">
  			<div class="col-md-12">
		  		<h2>系统发生错误 error.jsp</h2>
		  		<c:out value="${request.exception}"></c:out>
		  		<c:out value="${ex}"></c:out>
  			</div>
  			<div class="col-md-12">
		  		<a class="btn btn-link" href="<cmn:base/>/login.jsp">如果5秒不自动跳转，请点击链接，手动跳转到登陆界面</a>
  			</div>
  		</div>
  	</div>	<!-- container -->
  	
  
  </body>
</html>


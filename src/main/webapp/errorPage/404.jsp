<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/page/common/head.jsp" %>
    
    <%-- <meta http-equiv="refresh" content="5; URL=<cmn:base/>/login.jsp"> --%>
    
  </head>
  <body style="padding:0px; background-color:#f1f1f1;">
  	
  	<div class="container">
  		<div class="row">
  			<div class="col-md-12" style="text-align: center; margin-top: 20px;">
		  		<%-- <a class="btn btn-warning" href="<cmn:base/>/index">首页</a> --%>
		  		<%-- <c:if test="${not empty sessionScope.myUser.userId}">
		  			<a class="btn btn-warning" href="<cmn:base/>/web/unified/usercenter">管理页面</a>
		  		</c:if>
		  		<c:if test="${empty sessionScope.myUser.userId}">
		  			<a class="btn btn-warning" href="<cmn:base/>/login.jsp">登录</a>
		  		</c:if> --%>
  			</div>
  			<br/>
  			<div class="col-md-12" style="text-align: center; margin-top: 20px;">
		  		<h2><b><!-- <span style="color: red;" class="glyphicon glyphicon-remove"></span>
		  		<span style="color: red;" class="glyphicon glyphicon-remove-circle"></span> -->
		  		<span style="color: red;" class="glyphicon glyphicon-ban-circle"></span>   404</b> 页面不存在</h2>
		  		<c:out value="${request.exception}"></c:out>
		  		<c:out value="${ex}"></c:out>
  			</div>
  		</div>
  	</div>	<!-- container -->
  	
  
  </body>
</html>


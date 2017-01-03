<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<%@include file="page/common/head.jsp"%>
    
	<%-- <input id="basePath" type="hidden" value=''>
	<input id="userId" type="hidden" value='${sessionScope.myUser.userId}'>
	<input id="username" type="hidden" value='${sessionScope.myUser.username}'> --%>
  </head>
  <body>
  	<div>
  		<security:authentication property="principal.username" var="username"/>
  		<h1>恭喜您:<c:out value="${username}"></c:out>,授权成功 !</h1>
  		<br/>
  		<a id="toIndex" class="btn btn-link" href="<cmn:base/>/web/unified/usercenter">如果5秒不自动跳转，请点击链接，手动跳转到管理中心</a>
		
		<%-- <p>Message: <c:out value="${msg}"></c:out></p> 
		<p><a href="${globalLogoutUrl}">Sign Out</a></p> --%>
	</div>
	<script type="text/javascript">
		$(function() {
			window.setTimeout(function() {
				window.location.href = $('#basePath').val() + "/web/unified/usercenter";
			}, 5000);
		});
	</script>
	
  </body>
</html>
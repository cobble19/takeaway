<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="page/common/head.jsp" %>
  </head>
  <body>
  	<div class="container">
  		<h1>Enterprise</h1>
		<p><security:authentication property="principal.username" var="username"/> <c:out value="${username}"></c:out> Reg success</p> 
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>
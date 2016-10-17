<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<%-- <input id="basePath" type="hidden" value=''>
	<input id="userId" type="hidden" value='${sessionScope.myUser.userId}'>
	<input id="username" type="hidden" value='${sessionScope.myUser.username}'> --%>
  </head>
  <body>
  	<div>
  		<security:authentication property="principal.username" var="username"/>
  		<h1>恭喜您:<c:out value="${username}"></c:out>,用户 !</h1>
  		<br/>
		
		<p>Message: <c:out value="${msg}"></c:out></p> 
	</div>
	<script type="text/javascript">
		//window.close();
		/* location.href='../close.html'; */
	</script>
  </body>
</html>
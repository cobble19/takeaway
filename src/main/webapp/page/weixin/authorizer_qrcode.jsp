<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
  </head>
  <body>
  	<div class="container-fluid">
  		<div class="row">
  			<h2 class="col-sm-offset-3 col-md-offset-2">请关注公众号 <c:out value="${wxAuthorizerInfoPOJO.nickname}"></c:out></h2>
  			<p>
  				<img alt="" src="${wxAuthorizerInfoPOJO.qrcodeUrl}">
  				
  			</p>
  		</div>
  		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>
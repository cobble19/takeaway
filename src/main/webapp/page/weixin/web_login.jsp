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
  			<h2 class="col-sm-offset-3 col-md-offset-2">个人用户微信登陆</h2>
  			<p>
  				<a href="${wxWebLoginUrl}" class="btn btn-default">登陆</a>
  				<a href="${wxEncodeUrl}" class="btn btn-default">EncodeUrl</a>
  				<a href="${wxUrl}" class="btn btn-default">Url</a>
  				
  			</p>
  		</div>
  		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>
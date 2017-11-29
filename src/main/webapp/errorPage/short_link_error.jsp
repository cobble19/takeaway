<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/page/common/head.jsp" %>
    
  </head>
  <body style="padding:0px; background-color:#f1f1f1;">
  	
  	<div class="container">
  		<div class="row">
  			<div class="col-md-12">
		  		<h2>链接已经失效</h2>
		  		<%
		  			String uri = request.getRequestURI();
		  		%>
		  		<%-- <c:out value="<%=uri%>"/> --%>
  			</div>
  		</div>
  	</div>	<!-- container -->
  	
  
  </body>
</html>


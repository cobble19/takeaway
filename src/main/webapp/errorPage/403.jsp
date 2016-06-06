<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/page//common/head.jsp" %>
    
    <%-- <meta http-equiv="refresh" content="1; URL=<cmn:base/>/web/wx/oauth2/third/personUser/login"> --%>
    
  </head>
  <body style="padding:0px; background-color:#f1f1f1;">
  	
  	<div class="container">
  		<div class="row">
  			<div class="col-md-12">
		  		<h2>状态码:403, 正在登陆</h2>
		  		<%-- <c:out value="${request.exception}"></c:out>
		  		<c:out value="${ex}"></c:out> --%>
  			</div>
  			<div class="col-md-12">
		  		<%-- <a class="btn btn-link" href="<cmn:base/>/login.jsp">如果5秒不自动跳转，请点击链接，手动跳转到登陆界面</a> --%>
		  		<a id="toIndex" class="btn btn-link" href="<cmn:base/>/web/wx/oauth2/third/personUser/login">如果1秒不自动跳转，请点击链接，手动跳转到登陆界面</a>
  			</div>
  		</div>
  	</div>	<!-- container -->
  	<script>
		$(function() {
			/* var count = 5;
			$('#interval').text(5); */
			/* var interval = window.setInterval(function() {
				$('#interval').text(--count);
				if (count <= 0) {
					clearInterval(interval);
				}
			}, 1000); */
			/* $('#toIndex').click(function(e) {
				e.preventDefault();
				$('#loginForm').submit();
			}); */
			
			console.log('auto submit');
			window.setTimeout(function() {
				/* $('#toIndex').trigger("click"); */
				window.location.href=$('#toIndex').attr('href');
			}, 500);
		})
	</script>
  
  </body>
</html>


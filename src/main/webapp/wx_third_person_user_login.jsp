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
  		<%-- <h1>注册成功</h1>
		<p><security:authentication property="principal.username" var="username"/> <c:out value="${username}"></c:out> 注册成功，<span id="interval"></span>秒后跳转到首页</p> --%>
		<div style="display: none;">
			<a id="toIndex" class="btn btn-link" href="${wxThirdPersonUserLoginUrl}">微信个人用户通过第三方登陆/授权</a>
			
		</div>
		
		<!-- <footer><hr><p>&copy; 版权所有</p></footer> -->
	</div>
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
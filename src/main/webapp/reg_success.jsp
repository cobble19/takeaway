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
  		<h1>注册成功</h1>
		<p><security:authentication property="principal.username" var="username"/> <c:out value="${username}"></c:out> Reg success</p> 
		<div style="display: none;">
			<form action="<cmn:base/>/j_spring_security_check" id="loginForm" method="post">
				<table>
					<tbody>
	                    <tr style="border-bottom:#2f69c9 solid 1px; ">
	                        <td style="font-weight:bold; padding-bottom:10px; margin-bottom:10px;"><small >帐号登录</small>
	                        </td>
	                    </tr>
						<tr>
							<td class="login-label form-lable hidden-xs">用户名:</td>
							<td>
								<input class="login-input form-control" id="name" name="j_username" value="${j_username}" type="text" placeholder="登陆账号"/>
							</td>
						</tr>
						<tr>
							<td class="login-label form-lable hidden-xs">密&nbsp;&nbsp;码:</td>
							<td>
								<input class="login-input form-control" id="password" name="j_password" value="${j_password}" type="password" placeholder="密码"/>
							</td>
						</tr>
	                    <tr>
	                        <td><a href="#"><p class="smaller">忘记登录密码？</p></a></td>
	                        <td align="right" class="hidden-xs"><a href="register.jsp"><p class="smaller">免费注册</p></a></td> 
	                    </tr>
	                    <tr class="visible-xs">
	                        <td><a href="register.jsp"><p class="smaller">免费注册</p></a></td> 
	                    </tr>
						<tr>
							<td class="hidden-xs">
							</td>
							<td>
								<input class="login-btn btn btn-primary" id="loginBtn" title="登陆" value="登陆" type="submit"/>
								
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
	<script>
		$(function() {
			console.log('auto submit');
			$('#loginForm').submit();
		})
	</script>
  </body>
</html>
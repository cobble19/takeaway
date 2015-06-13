<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="page/common/head.jsp" %>
    <link href="<cmn:base/>/css/login.css" rel="stylesheet">
</head>



<body>
	<security:authentication property="principal.username" var="username"/>
	
	<div class="login-container">
	<%-- <form action="<%=request.getContextPath() %>/login!execute" id="loginForm" method="post"> --%>
	<form action="<cmn:base/>/j_spring_security_check" id="loginForm" method="post">
			<table>
				<tbody>
					<tr>
						<td class="login-label form-lable">用户名:</td>
						<td>
							<input class="login-input form-control" id="name" name="j_username" type="text" placeholder="登陆账号"/>
						</td>
					</tr>
					<tr>
						<td class="login-label form-lable">密码:</td>
						<td>
							<input class="login-input form-control" id="password" name="j_password" type="password" placeholder="密码"/>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<input class="login-btn btn btn-primary" id="loginBtn" title="登陆" value="登陆" type="submit"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>

</html>


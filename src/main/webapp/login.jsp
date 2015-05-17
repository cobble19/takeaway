<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>得味驿站</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
	<%-- <link href="<cmn:base/>/css/dwsy.css" rel="stylesheet" media="print, projection, screen">
    <link href="css/dwsy.css" rel="stylesheet" media="print, projection, screen"> --%>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    <link href="<cmn:base/>/css/common.css" rel="stylesheet">
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
						<td class="login-label">用户名:</td>
						<td>
							<input class="login-input" id="name" name="j_username" type="text" placeholder="登陆账号"/>
						</td>
					</tr>
					<tr>
						<td class="login-label">密码:</td>
						<td>
							<input class="login-input" id="password" name="j_password" type="password" placeholder="密码"/>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<input class="login-btn" id="loginBtn" title="登陆" value="登陆" type="submit"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>

</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="page/common/head.jsp" %>
    <link href="<cmn:base/>/css/login.css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/login.js"></script>
</head>



<body>
	<security:authentication property="principal.username" var="username"/>
	<%@include file="reg_login.jsp"%>
	<div class="login-container container-fluid">
    <div class="row" style="margin-bottom:20px;">
    <div class="col-md-7">
			<div class="row" style="margin-top:40%;">
				<div class="qima col-md-2 hidden-xs"></div>
				<div class="sywz1 col-md-4 hidden-xs"></div>
				<div class="sywz2 col-md-4 hidden-xs"></div>
			</div>        
    </div>
    <div class="col-md-5">
    <div class="row">
    <div class="col-md-12 hidden-xs" style="margin-top:40%;"></div>
	<%-- <form action="<%=request.getContextPath() %>/login!execute" id="loginForm" method="post"> --%>
    <div class="col-md-12">
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
							<input class="login-input form-control" id="name" name="j_username" type="text" placeholder="登陆账号"/>
						</td>
					</tr>
					<tr>
						<td class="login-label form-lable hidden-xs">密&nbsp;&nbsp;码:</td>
						<td>
							<input class="login-input form-control" id="password" name="j_password" type="password" placeholder="密码"/>
						</td>
					</tr>
                    <tr>
                        <td><a href="forget_password.jsp"><p class="smaller">忘记登录密码？</p></a></td>
                        <td align="right" class="hidden-xs"><a href="register.jsp"><p class="smaller">免费注册</p></a></td>
                    </tr>
                    <tr class="visible-xs">
                        <!--<td><a href="register.jsp"><p class="smaller">免费注册</p></a></td>--> 
                    </tr>
					<tr>
						<td class="hidden-xs">
						</td>
						<td>
							<input class="login-btn btn btn-primary" id="loginBtn" title="登陆" value="登陆" type="button"/>
						</td>
					</tr>
					<tr>
						<td class="hidden-xs">
						</td>
						<td>
							<span id="errorMsg" style="color: red;"></span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
        </div>
        </div>
    </div>
    </div>
	</div>
    <%@include file="bottom.jsp"%>
</body>

</html>


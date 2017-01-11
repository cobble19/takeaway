<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="page/common/head.jsp" %>
    <link href="<cmn:base/>/css/login.css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/forget_password.js"></script>
</head>



<body style="padding-top: 100px;">
	<security:authentication property="principal.username" var="username"/>
	<%@include file="reg_login.jsp"%>
	<div class="container-fluid">
    <div  class="row" style=" height:4px; background-color:#44b549;"></div>
        <div  class="row" style=" height:80px; padding-top:20px; border-bottom:1px solid #CCC; margin:0px 10px;">
            <a class="h2" style="height:40px; width:120px; line-height:40px; display:block; border-bottom:2px solid #F00; color:#44b549;">用户注册</a>
        </div>
        <div class="row" style="margin:0px 10px;">
            <div class="col-md-12">
               <div class="row" style="margin:20px 0px 50px 0px;">
                   <strong style="color:#F00;">1. 填写需要重置密码的账户</strong>&nbsp;&nbsp;&nbsp;&nbsp;
                   <strong style="color:#ccc;">2. 重新授权公众号验证身份</strong>&nbsp;&nbsp;&nbsp;&nbsp;
                   <strong style="color:#ccc;">2. 填写账户新密码</strong>
               </div>
            </div>
        </div>
    <div class="row">
    <div class="col-md-6 col-md-offset-3">
    <div class="row">
	<!-- <form action="<%=request.getContextPath() %>/login!execute" id="loginForm" method="post"> -->
    <div class="col-md-12">
	<form action="<cmn:base/>/web/wx/oauth2/third/forgetPassword" id="loginForm" method="post">
			<table>
				<tbody> 
					<tr>
						<td class="login-label form-lable">用户名:</td>
						<td>
							<input class="login-input form-control" id="username" name="username" type="text" placeholder="登陆账号"/>
						</td>
					</tr>      
					<tr>
						<td class="login-label form-lable">新密码:</td>
						<td>
							<input class="login-input form-control" id="password" name="password" type="password" placeholder="新密码"/>
						</td>
					</tr>
                    <tr class="visible-xs">
                        <!--<td><a href="register.jsp"><p class="smaller">免费注册</p></a></td>--> 
                    </tr>
					<tr>
						<td class="hidden-xs">
						</td>
						<td>
							<input class="login-btn btn btn-primary" id="loginBtn" title="下一步" value="下一步" type="button"/>
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


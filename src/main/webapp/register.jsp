<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="page/common/head.jsp"%>
<link href="<cmn:base/>/css/login.css" rel="stylesheet">
<script type="text/javascript">
$(function() {
	/* $('#loginBtnDefault').trigger('click');
	$('#goRegBtn').click(function() {
		$('#loginModal').modal('hide');
		$('#regBtnDefault').trigger('click');
	});
	$('#goLoginBtn').click(function() {
		$('#myModal').modal('hide');
		$('#loginBtnDefault').trigger('click');
	}); */
	/* $('#myModal').modal('show'); */
});
</script>
<script src="<cmn:base/>/js/register.js"></script>
</head>

<body style="padding-top: 100px;">
	<%@include file="reg_login.jsp"%>
	<div class="container-fluid">
        <div  class="row" style=" height:4px; background-color:#44b549;"></div>
        <div  class="row" style=" height:80px; padding-top:20px; border-bottom:1px solid #CCC; margin:0px 10px;">
            <a class="h2" style="height:40px; width:120px; line-height:40px; display:block; border-bottom:2px solid #F00; color:#44b549;">用户注册</a>
        </div>
        <div class="row" style="margin:0px 10px;">
            <div class="col-md-12">
               <div class="row" style="margin:20px 0px 50px 0px;">
                   <strong id="step1" style="color:#F00;">1. 填写用户注册信息</strong>&nbsp;&nbsp;&nbsp;&nbsp;
                   <strong id="step2" style="color:#ccc;">2. 授权公众号信息</strong>
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
																
	    						<div id="enterprise">
							  		<form id="regForm" class="form-horizontal" role="form" action='<cmn:base/>/web/user/enterprise/reg' method="post">
							 			<div class="form-group">
							 				<label class="control-label col-sm-3 col-md-3" for="username">用户名:</label>
							 				<div class="col-sm-9 col-md-7">
							 					<input class="form-control" id="username" name="username" required="required" minlength="1" value="" placeholder="请输入用户名">
							 					<span id="usernameError" style="color: red;"></span>
							 				</div>
							 			</div>
										<div class="form-group">
											<label class="control-label col-sm-3 col-md-3" for="nickname">昵&nbsp;&nbsp;称:</label>
											<div class="col-sm-9 col-md-7">
												<input class="form-control" id="nickname" name="nickname" required="required" value="" placeholder="请输入昵称">
											</div>
										</div>
							 			<div class="form-group">
							 				<label class="control-label col-sm-3 col-md-3" for="password">密&nbsp;&nbsp;码:</label>
							 				<div class="col-sm-9 col-md-7">
							 					<input class="form-control" type="password" id="password" name="password" required="required" value="" placeholder="请输入密码">
							 				</div>
							 			</div>
							 			<div class="form-group">
							 				<label class="control-label col-sm-3 col-md-3" for="email">Email:</label>
							 				<div class="col-sm-9 col-md-7">
							 					<input class="form-control" type="email" id="email" name="email" required="required" value="" placeholder="请输入email">
							 				</div>
							 			</div>
							 			<!-- <div class="form-group">
							 				<label class="control-label col-sm-3 col-md-3" for="wxIndexCode">唯一识别号:</label>
							 				<div class="col-sm-9 col-md-7">
							 					<input class="form-control" type="text" id="wxIndexCode" name="wxIndexCode" required="required" value="" placeholder="请输入唯一识别号, 英文和数字">
							 				</div>
							 			</div> -->
							 			<div class="form-group">
								 			<div class="col-sm-offset-3 col-sm-9 col-md-offset-3 col-md-7">
								  				<button type="button" class="btn btn-danger btn-block" id="registerBtn">下一步</button>
                                                <!--<span style="height:24px; line-height:24px; text-align:right;"><a href="login.jsp"><p class="smaller">已有帐号，点击登陆</p></a></span>-->
								 			</div>
							 			</div>
							  		</form>
								</div>
                <!-- -->
            </div>
            <div class="col-md-12">
				<div id="wxComLoginDiv">
					<iframe src="" id="wxComLoginIframe" width="100%" height="800"></iframe>
				</div>
            </div>
        </div>
</div>
   <%@include file="bottom.jsp"%>
</body>
</html>


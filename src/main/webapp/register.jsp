<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="page/common/head.jsp"%>
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

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
	    		<!-- 注册 -->
				<div class="row">
		  			<h2 class="col-sm-offset-3 col-md-offset-2">个人用户注册</h2>
		  		</div>
		  		<form id="regForm" class="form-horizontal" role="form" action='<cmn:base/>/web/user/person/reg' method="post">
		 			<div class="form-group">
		 				<label class="control-label col-sm-3 col-md-2" for="username">用户名:</label>
		 				<div class="col-sm-9 col-md-6">
		 					<input class="form-control" id="username" name="username" required="required" minlength="1" value="" placeholder="请输入用户名">
		 				</div>
		 			</div>
					<div class="form-group">
						<label class="control-label col-sm-3 col-md-2" for="nickname">昵称:</label>
						<div class="col-sm-9 col-md-6">
							<input class="form-control" id="nickname" name="nickname" required="required" value="" placeholder="请输入昵称">
						</div>
					</div>
		 			<div class="form-group">
		 				<label class="control-label col-sm-3 col-md-2" for="password">密码:</label>
		 				<div class="col-sm-9 col-md-6">
		 					<input class="form-control" type="password" id="password" name="password" required="required" value="" placeholder="请输入密码">
		 				</div>
		 			</div>
		 			<div class="form-group">
		 				<label class="control-label col-sm-3 col-md-2" for="email">Email:</label>
		 				<div class="col-sm-9 col-md-6">
		 					<input class="form-control" type="email" id="email" name="email" required="required" value="" placeholder="请输入email">
		 				</div>
		 			</div>
		 			<div class="form-group">
			 			<div class="col-md-offset-2 col-sm-9 col-md-6">
			  				<button type="submit" class="btn btn-default" id="registerBtn">注册</button>
  							<span><a href="login.jsp"><small>若有账号，请点击登陆</small></a></span>
			 			</div>
		 			</div>
		  		</form>
			</div>
			<div class="col-md-6">
				<div class="row">
		  			<h2 class="col-sm-offset-3 col-md-offset-2">商家用户注册</h2>
		  		</div>
		  		<form id="regForm" class="form-horizontal" role="form" action='<cmn:base/>/web/user/enterprise/reg' method="post">
		 			<div class="form-group">
		 				<label class="control-label col-sm-3 col-md-2" for="username">用户名:</label>
		 				<div class="col-sm-9 col-md-6">
		 					<input class="form-control" id="username" name="username" required="required" minlength="1" value="" placeholder="请输入用户名">
		 				</div>
		 			</div>
					<div class="form-group">
						<label class="control-label col-sm-3 col-md-2" for="nickname">昵称:</label>
						<div class="col-sm-9 col-md-6">
							<input class="form-control" id="nickname" name="nickname" required="required" value="" placeholder="请输入昵称">
						</div>
					</div>
		 			<div class="form-group">
		 				<label class="control-label col-sm-3 col-md-2" for="password">密码:</label>
		 				<div class="col-sm-9 col-md-6">
		 					<input class="form-control" type="password" id="password" name="password" required="required" value="" placeholder="请输入密码">
		 				</div>
		 			</div>
		 			<div class="form-group">
		 				<label class="control-label col-sm-3 col-md-2" for="email">Email:</label>
		 				<div class="col-sm-9 col-md-6">
		 					<input class="form-control" type="email" id="email" name="email" required="required" value="" placeholder="请输入email">
		 				</div>
		 			</div>
		 			<div class="form-group">
			 			<div class="col-md-offset-2 col-sm-9 col-md-6">
			  				<button type="submit" class="btn btn-default" id="registerBtn">注册</button>
  							<span><a href="login.jsp"><small>若有账号，请点击登陆</small></a></span>
			 			</div>
		 			</div>
		  		</form>
			</div>
		</div>
			
</div>
</body>
</html>


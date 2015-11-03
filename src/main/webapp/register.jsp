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

<body>
	<div class="container">
        <div class="row" style="border-bottom:#afe2a5 1px solid; padding-bottom:25px;">
            <div class="col-md-2 logo hidden-xs"></div>
            <div class="col-md-4 hidden-xs" style="height:72px;"><p style="font-size:24px; line-height:72px;">用户注册</p></div>
        </div>
        <div class="row" style="background:#fbfbfb; border:#d9e6d9 1px solid; margin-top:1px;">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <!-- -->
                <div class="modal-body">
	    				<div role="tabpanel">
	    					<ul class="nav nav-tabs nav-justified" role="tablist" id="myTab">
	    						<li role="presentation" class="active">
	    							<a href="#person" aria-controls="person" role="tab" data-toggle="tab" id="personReg">注册个人账户</a>
	    						</li>
	    						<li role="presentation" class="">
	    							<a href="#enterprise" aria-controls="enterprise" role="tab" data-toggle="tab" id="enterpriseReg">注册商家账户</a>
	    						</li>
	    					</ul>
	    					
	    					<div class="tab-content" style="border-left:#e8e8e8 solid 1px; border-right:#e8e8e8 solid 1px; border-bottom:#e8e8e8 solid 1px;">
	    						<div role="tabpanel" class="tab-pane active" id="person">
									<div class="row">
							  			<h2 class="col-sm-offset-2 col-md-offset-1" style="margin-bottom:15px;"><small style="font-weight:bold;">个人用户注册</small></h2>
							  		</div>
							  		<form id="regForm" class="form-horizontal" role="form" action='<cmn:base/>/web/user/person/reg' method="post">
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
							 			<div class="form-group">
								 			<div class="col-sm-offset-3 col-sm-9 col-md-offset-3 col-md-7">
								  				<button type="submit" class="btn btn-default reg-btn" id="registerBtn">注册</button>
                                                <span style="height:24px; line-height:24px; text-align:right;"><a href="login.jsp"><p class="smaller">已有帐号，点击登陆</p></a></span>
								 			</div>
							 			</div>
							  		</form>
								</div>
								
	    						<div role="tabpanel" class="tab-pane" id="enterprise">
									<div class="row">
							  			<h2 class="col-sm-offset-2 col-md-offset-1" style="margin-bottom:15px;"><small style="font-weight:bold;">商家用户注册</small></h2>
							  		</div>
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
							 			<div class="form-group">
								 			<div class="col-sm-offset-3 col-sm-9 col-md-offset-3 col-md-7">
								  				<button type="submit" class="btn btn-default" id="registerBtn">注册</button>
                                                <span style="height:24px; line-height:24px; text-align:right;"><a href="login.jsp"><p class="smaller">已有帐号，点击登陆</p></a></span>
								 			</div>
							 			</div>
							  		</form>
								</div>
							</div>
	    				</div>
	    			</div>
                <!-- -->
            </div>
            <div class="col-md-3"></div>
        </div>
		<!--<div class="row">
			<div class="col-md-6">
	    		
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
		</div>-->	
        		
</div>
</body>
</html>


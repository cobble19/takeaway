<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript">
    $('#regForm').validate();
    	$('#regForm').submit(function(e) {
    		console.log('adb');
    		return false;
    	});
    </script>
  </head>
  <body>
  	<div class="container-fluid">
  		<div class="row">
  			<h2 class="col-sm-offset-3 col-md-offset-2">企业用户注册</h2>
  		</div>
  		<form id="regForm" class="form-horizontal" role="form" action='<cmn:base/>/web/user/enterprise/reg' method="post">
 			<div class="form-group">
 				<label class="control-label col-sm-3 col-md-2" for="username">用户名:</label>
 				<div class="col-sm-9 col-md-6">
 					<input class="form-control" id="username" name="username" required="required" value="" placeholder="请输入用户名">
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
 					<input class="form-control" type="password" id="password" name="password" value="" placeholder="请输入密码">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label col-sm-3 col-md-2" for="email">E-Mail:</label>
 				<div class="col-sm-9 col-md-6">
 					<input class="form-control" type="email" id="email" name="email" value="" placeholder="请输入email">
 				</div>
 			</div>
 			<div class="form-group">
	 			<div class="col-sm-offset-3 col-sm-9 col-md-offset-2 col-md-4">
	  				<button type="submit" class="btn btn-default" id="registerBtn">注册</button>
	 			</div>
 			</div>
  		</form>
  		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>
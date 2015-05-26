<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>餐厅列表</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- jQuery Validation -->
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/jquery.validate.min.js"></script>
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/additional-methods.min.js"></script>
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/jquery.validate.message.zh_cn.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>
  <body>
  	<div class="container-fluid">
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
	 			<div class="col-sm-offset-3 col-sm-9 col-md-offset-2 col-md-4">
	  				<button type="submit" class="btn btn-default" id="registerBtn">注册</button>
	 			</div>
 			</div>
  		</form>
  		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
	
    <script type="text/javascript">
    	$(function() {
           $('#regForm').validate();
        	$('#regForm').submit(function(e) {
        		console.log('reg form submit');

                var valid = $('#regForm').valid();
                console.log('valid: ' + valid);
                if (!valid) {
                	return false;
                }
        	});
    	})
    </script>
  </body>
</html>
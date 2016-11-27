<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_match_rule_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加微信菜单match rule</h2>
  		</div>
  		<form id="wxMenuMgrMatchRuleForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/wxMenuMgrMatchRule/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="groupId">标签ID:</label>
 				<div class="">
 					<input class="form-control" id="groupId" name="groupId" required="required" placeholder="请输入标签ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="sex">性别:</label>
 				<div class="">
 					<input class="form-control" id="sex" name="sex" placeholder="请输入性别 1-男 2-女 ">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="country">国家:</label>
 				<div class="">
 					<input class="form-control" id="country" name="country" placeholder="请输入国家">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="province">省:</label>
 				<div class="">
 					<input class="form-control" id="province" name="province" placeholder="请输入省">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="city">市:</label>
 				<div class="">
 					<input class="form-control" id="city" name="city" placeholder="请输入市">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="clientPlatformType">客户端版本:</label>
 				<div class="">
 					<input class="form-control" id="clientPlatformType" name="clientPlatformType" placeholder="请输入客户端版本 IOS(1), Android(2),Others(3)">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="language">语言:</label>
 				<div class="">
 					<input class="form-control" id="language" name="language" placeholder="请输入语言 1、简体中文 zh_CN ">
 				</div>
 			</div>
 			<div class="form-group">
	 			<div class="">
	  				<button type="button" class="btn btn-default" id="addBtn">创建</button>
	 			</div>
 			</div>
  		</form>
  		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>
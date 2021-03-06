<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_person_user_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加微信个人用户</h2>
  		</div>
  		<form id="wxPersonUserForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/wxPersonUser/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" required="required" placeholder="请输入用户ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="openId">OPEN ID:</label>
 				<div class="">
 					<input class="form-control" id="openId" name="openId" placeholder="请输入OPEN ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="authorizerAppId">授权者APP ID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppId" name="authorizerAppId" placeholder="请输入授权者APP ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="proxyOpenId">代理OPEN ID:</label>
 				<div class="">
 					<input class="form-control" id="proxyOpenId" name="proxyOpenId" placeholder="请输入代理OPEN ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="proxyAuthorizerAppId">代理授权者APP ID:</label>
 				<div class="">
 					<input class="form-control" id="proxyAuthorizerAppId" name="proxyAuthorizerAppId" placeholder="请输入代理授权者APP ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="nickname">昵称:</label>
 				<div class="">
 					<input class="form-control" id="nickname" name="nickname" placeholder="请输入昵称">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="sex">性别:</label>
 				<div class="">
 					<input class="form-control" id="sex" name="sex" placeholder="请输入性别">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="language">语言:</label>
 				<div class="">
 					<input class="form-control" id="language" name="language" placeholder="请输入语言">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="city">城市:</label>
 				<div class="">
 					<input class="form-control" id="city" name="city" placeholder="请输入城市">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="province">省:</label>
 				<div class="">
 					<input class="form-control" id="province" name="province" placeholder="请输入省">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="country">国家:</label>
 				<div class="">
 					<input class="form-control" id="country" name="country" placeholder="请输入国家">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="subscribe">关注:</label>
 				<div class="">
 					<input class="form-control" id="subscribe" name="subscribe" placeholder="请输入关注">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="subscribeTime">关注时间:</label>
 				<div class="">
 					<input class="form-control" id="subscribeTime" name="subscribeTime" placeholder="请输入关注时间">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="privileges">权限:</label>
 				<div class="">
 					<input class="form-control" id="privileges" name="privileges" placeholder="请输入权限">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="unionId">Union ID:</label>
 				<div class="">
 					<input class="form-control" id="unionId" name="unionId" placeholder="请输入Union ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="remark">备注:</label>
 				<div class="">
 					<input class="form-control" id="remark" name="remark" placeholder="请输入备注">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="groupId">GROUP ID:</label>
 				<div class="">
 					<input class="form-control" id="groupId" name="groupId" placeholder="请输入GROUP ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="tagidList">TAG IDs:</label>
 				<div class="">
 					<input class="form-control" id="tagidList" name="tagidList" placeholder="请输入TAG IDs">
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
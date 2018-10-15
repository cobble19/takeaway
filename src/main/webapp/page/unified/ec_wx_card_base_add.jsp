<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_wx_card_base_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加用户已经拥有的卡券在jssdk获取卡券功能之前(不要使用)</h2>
  		</div>
  		<form id="ecWxCardBaseForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/ecWxCardBase/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" placeholder="请输入用户ID">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="authorizerAppId">authorizerAppId:</label>
				<div class="">
					<input class="form-control" id="authorizerAppId" name="authorizerAppId" placeholder="请输入authorizerAppId">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="openId">OpenID:</label>
 				<div class="">
 					<input class="form-control" id="openId" name="openId" placeholder="请输入OpenID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="cardId">卡券ID:</label>
 				<div class="">
 					<input class="form-control" id="cardId" name="cardId" placeholder="请输入卡券ID">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="cardBaseResult">用户以前的卡券数据:</label>
				<div class="">
					<input class="form-control" id="cardBaseResult" name="cardBaseResult" placeholder="请输入用户以前的卡券数据">
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
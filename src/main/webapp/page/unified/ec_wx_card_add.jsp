<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_wx_card_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加卡券(不要使用)</h2>
  		</div>
  		<form id="ecWxCardForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/ecWxCard/add' method="post">
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
 				<label class="control-label" for="wpOrderId">微信支付订单ID:</label>
 				<div class="">
 					<input class="form-control" id="wpOrderId" name="wpOrderId" placeholder="请输入微信支付订单ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="ecOrderId">商品订单ID:</label>
 				<div class="">
 					<input class="form-control" id="ecOrderId" name="ecOrderId" placeholder="请输入商品订单ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="ecProductId">商品ID:</label>
 				<div class="">
 					<input class="form-control" id="ecProductId" name="ecProductId" placeholder="请输入商品ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="cardId">卡券ID:</label>
 				<div class="">
 					<input class="form-control" id="cardId" name="cardId" placeholder="请输入卡券ID">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="cardCode">卡券code码:</label>
				<div class="">
					<input class="form-control" id="cardCode" name="cardCode" placeholder="请输入卡券code码">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="rawData">原始数据:</label>
				<div class="">
					<input class="form-control" id="rawData" name="rawData" placeholder="请输入原始数据">
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
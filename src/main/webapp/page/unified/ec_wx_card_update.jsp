<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_wx_card_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改订单(不要使用)</h2>
  		</div>
  		<form id="ecWxCardForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/ecWxCard/add' method="post">
	  		<input type="hidden" id="ecWxCardId" name="ecWxCardId" value="${ecWxCardPOJO.ecWxCardId}"/>

			<div class="form-group">
				<label class="control-label" for="userId">用户ID:</label>
				<div class="">
					<input class="form-control" id="userId" name="userId" value="${ecWxCardPOJO.userId}" placeholder="请输入用户ID">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="authorizerAppId">authorizerAppId:</label>
				<div class="">
					<input class="form-control" id="authorizerAppId" name="authorizerAppId" value="${ecWxCardPOJO.authorizerAppId}" placeholder="请输入authorizerAppId">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="openId">OpenID:</label>
				<div class="">
					<input class="form-control" id="openId" name="openId" value="${ecWxCardPOJO.openId}" placeholder="请输入OpenID">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="wpOrderId">微信支付订单ID:</label>
				<div class="">
					<input class="form-control" id="wpOrderId" name="wpOrderId" value="${ecWxCardPOJO.wpOrderId}" placeholder="请输入微信支付订单ID">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="ecOrderId">商品订单ID:</label>
				<div class="">
					<input class="form-control" id="ecOrderId" name="ecOrderId" value="${ecWxCardPOJO.ecOrderId}" placeholder="请输入商品订单ID">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="ecProductId">商品ID:</label>
				<div class="">
					<input class="form-control" id="ecProductId" name="ecProductId" value="${ecWxCardPOJO.ecProductId}" placeholder="请输入商品ID">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="cardId">卡券ID:</label>
				<div class="">
					<input class="form-control" id="cardId" name="cardId" value="${ecWxCardPOJO.cardId}" placeholder="请输入卡券ID">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="cardCode">卡券code码:</label>
				<div class="">
					<input class="form-control" id="cardCode" name="cardCode" value="${ecWxCardPOJO.cardCode}" placeholder="请输入卡券code码">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="rawData">原始数据:</label>
				<div class="">
					<input class="form-control" id="rawData" name="rawData" value="${ecWxCardPOJO.rawData}" placeholder="请输入原始数据">
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
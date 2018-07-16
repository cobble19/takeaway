<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_cart_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改购物车</h2>
  		</div>
  		<form id="ecCartForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/ecCart/add' method="post">
	  		<input type="hidden" id="ecCartId" name="ecCartId" value="${ecCartPOJO.ecCartId}"/>
	  		
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" value="${ecCartPOJO.userId}" placeholder="请输入用户ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="openId">OpenID:</label>
 				<div class="">
 					<input class="form-control" id="openId" name="openId" value="${ecCartPOJO.openId}" placeholder="请输入OpenID">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="authorizerAppId">OpenID:</label>
				<div class="">
					<input class="form-control" id="authorizerAppId" name="authorizerAppId" value="${ecCartPOJO.authorizerAppId}" placeholder="请输入authorizerAppId">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="productId">商品ID:</label>
 				<div class="">
 					<input class="form-control" id="productId" name="productId" value="${ecCartPOJO.productId}" placeholder="请输入商品ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="productName">商品名称:</label>
 				<div class="">
 					<input class="form-control" id="productName" name="productName" value="${ecCartPOJO.productName}" placeholder="请输入商品名称">
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
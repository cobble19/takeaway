<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_order_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加订单</h2>
  		</div>
  		<form id="ecOrderForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/ecOrder/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" placeholder="请输入用户ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="openId">OpenID:</label>
 				<div class="">
 					<input class="form-control" id="openId" name="openId" placeholder="请输入OpenID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="productId">商品ID:</label>
 				<div class="">
 					<input class="form-control" id="productId" name="productId" placeholder="请输入商品ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="unitPrice">单价(元):</label>
 				<div class="">
 					<input class="form-control" id="unitPrice" name="unitPrice" placeholder="请输入单价(元)">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="quantity">商品个数:</label>
 				<div class="">
 					<input class="form-control" id="quantity" name="quantity" placeholder="请输入商品个数">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="discountRate">折扣率:</label>
 				<div class="">
 					<input class="form-control" id="discountRate" name="discountRate" placeholder="请输入折扣率">
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
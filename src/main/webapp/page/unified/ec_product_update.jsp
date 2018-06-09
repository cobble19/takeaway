<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_product_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改商品</h2>
  		</div>
  		<form id="ecProductForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/ecProduct/add' method="post">
	  		<input type="hidden" id="productId" name="productId" value="${ecProductPOJO.productId}"/>
	  		<div class="form-group">
 				<label class="control-label" for="userId">拥有者ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" value="${ecProductPOJO.userId}" placeholder="请输入拥有者ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="authorizerAppId">APPID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppId" name="authorizerAppId" value="${ecProductPOJO.authorizerAppId}" placeholder="请输入APPID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="productName">商品名称:</label>
 				<div class="">
 					<input class="form-control" id="productName" name="productName" value="${ecProductPOJO.productName}" placeholder="请输入商品名称">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="imgUrl">图片:</label>
				<div class="">
					<input class="form-control" id="imgUrl" name="imgUrl" value="${ecProductPOJO.imgUrl}" readonly="readonly" required="required" placeholder="请上传图片">
					<input class="" id="pic" name="pic" type="file">
					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="buyAbout">购买须知:</label>
 				<div class="">
 					<script id="editor" type="text/plain" name="buyAbout"></script>
 					<input type="hidden" class="form-control" id="buyAbout" value="${e:forHtml(ecProductPOJO.buyAbout)}" placeholder="请输入购买须知">
 					<%-- <input class="form-control" id="buyAbout" name="buyAbout" value="${ecProductPOJO.buyAbout}" placeholder="请输入购买须知"> --%>
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="quantityTotal">商品总量:</label>
 				<div class="">
 					<input class="form-control" id="quantityTotal" name="quantityTotal" value="${ecProductPOJO.quantityTotal}" placeholder="请输入商品总量">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="quantityStock">商品库存:</label>
 				<div class="">
 					<input class="form-control" id="quantityStock" name="quantityStock" value="${ecProductPOJO.quantityStock}" placeholder="请输入商品库存">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="unitPrice">单价(分):</label>
 				<div class="">
 					<input class="form-control" id="unitPrice" name="unitPrice" value="${ecProductPOJO.unitPrice}" placeholder="请输入单价(分)">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="limitNumEveryone">每人限额:</label>
				<div class="">
					<input class="form-control" id="limitNumEveryone" name="limitNumEveryone" value="${ecProductPOJO.limitNumEveryone}" placeholder="每人限额">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="limitNumDay">每天限额:</label>
				<div class="">
					<input class="form-control" id="limitNumDay" name="limitNumDay" value="${ecProductPOJO.limitNumDay}" placeholder="每天限额">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="wxCardId">微信卡券ID:</label>
 				<div class="">
 					<input class="form-control" id="wxCardId" name="wxCardId" value="${ecProductPOJO.wxCardId}" placeholder="请输入微信卡券ID">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="wxCardStock">微信卡券库存:</label>
				<div class="">
					<input class="form-control" id="wxCardStock" name="wxCardStock" value="${ecProductPOJO.wxCardStock}" placeholder="请输入微信卡券库存">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="needSubscribe">是否需要关注公众号:</label>
 				<div class="">
 					<input class="form-control" id="needSubscribe" name="needSubscribe" value="${ecProductPOJO.needSubscribe}" placeholder="请确定是否需要关注公众号, 0-无需关注, 1-需要关注">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="activeFlag">是否显示for active:</label>
				<div class="">
					<input class="form-control" id="activeFlag" name="activeFlag" value="${ecProductPOJO.activeFlag}" placeholder="请确定是否显示, 0-不显示, 1-显示">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="startDateTime">活动开始时间:</label>
				<input type="hidden" id="startDateTimeX" value="${ecProductPOJO.startDateTime}">
				<div class="">
					<input class="form-control" id="startDateTime" name="startDateTime" value="" required="required" placeholder="请输入开始时间">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="endDateTime">活动结束时间:</label>
				<input type="hidden" id="endDateTimeX" value="${ecProductPOJO.endDateTime}">
				<div class="">
					<input class="form-control" id="endDateTime" name="endDateTime" value="" required="required" placeholder="请输入结束时间">
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_product_add.js?v=20190130"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加商品</h2>
  		</div>
  		<form id="ecProductForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/ecProduct/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="userId">拥有者ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" placeholder="请输入拥有者ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="authorizerAppId">APPID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppId" name="authorizerAppId" placeholder="请输入APPID">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="homeUrl">商品官方网站:</label>
				<div class="">
					<input class="form-control" id="homeUrl" name="homeUrl" placeholder="请输入商品官方网站">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="productName">商品名称:</label>
 				<div class="">
 					<input class="form-control" id="productName" name="productName" placeholder="请输入商品名称">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="marketingSubject">营销主体:</label>
				<div class="">
					<input class="form-control" id="marketingSubject" name="marketingSubject" placeholder="请输入营销主体（目前的值有得味驿站、合肥交广）">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="categoryType">分类:</label>
				<div class="">
					<input class="form-control" id="categoryType" name="categoryType" placeholder="请输入分类（目前的值有美食，娱乐）">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="imgUrl">图片:</label>
				<div class="">
					<input class="form-control" id="imgUrl" name="imgUrl" readonly="readonly" required="required" placeholder="请上传图片">
					<input class="" id="pic" name="pic" type="file">
					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="img2Url">图片2:</label>
				<div class="">
					<input class="form-control" id="img2Url" name="img2Url" readonly="readonly" required="required" placeholder="请上传图片2">
					<input class="" id="pic2" name="pic2" type="file">
					<input class="btn btn-info" id="upload2Btn" name="upload2Btn" type="button" value="上传2">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="buyAbout">购买须知:</label>
 				<div class="">
 					<script id="editor" type="text/plain" name="buyAbout"></script>
 					<!-- <input class="form-control" id="buyAbout" name="buyAbout" placeholder="请输入购买须知"> -->
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="quantityTotal">商品总量:</label>
 				<div class="">
 					<input class="form-control" id="quantityTotal" name="quantityTotal" placeholder="请输入商品总量">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="quantityStock">商品库存:</label>
 				<div class="">
 					<input class="form-control" id="quantityStock" name="quantityStock" placeholder="请输入商品库存">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="unitPrice">单价(分):</label>
 				<div class="">
 					<input class="form-control" id="unitPrice" name="unitPrice" placeholder="请输入单价(分)">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="limitNumEveryone">每人限额:</label>
				<div class="">
					<input class="form-control" id="limitNumEveryone" name="limitNumEveryone" placeholder="每人限额">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="limitNumDay">每天总限额:</label>
				<div class="">
					<input class="form-control" id="limitNumDay" name="limitNumDay" placeholder="每天总限额">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="wxCardId">微信卡券ID:</label>
 				<div class="">
 					<input class="form-control" id="wxCardId" name="wxCardId" placeholder="请输入微信卡券ID">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="wxCardStock">微信卡券库存:</label>
				<div class="">
					<input class="form-control" id="wxCardStock" name="wxCardStock" placeholder="请输入微信卡券库存">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="needSubscribe">是否需要关注公众号:</label>
 				<div class="">
 					<input class="form-control" id="needSubscribe" name="needSubscribe" placeholder="请确定是否需要关注公众号, 0-无需关注, 1-需要关注">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="activeFlag">是否显示for active:</label>
				<div class="">
					<input class="form-control" id="activeFlag" name="activeFlag" placeholder="请确定是否显示, 0-不显示, 1-显示">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="unitPriceOrigin">商品原价:</label>
				<div class="">
					<input class="form-control" id="unitPriceOrigin" name="unitPriceOrigin" placeholder="请确定商品原价">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="openDateTimeSeg">营业时间区间:</label>
				<div class="">
					<input class="form-control" id="openDateTimeSeg" name="openDateTimeSeg" placeholder="请确定营业时间区间">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="bizAddress">营业地址:</label>
				<div class="">
					<input class="form-control" id="bizAddress" name="bizAddress" placeholder="请确定营业地址, 多个地址请用逗号(,)分隔">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="bizTelephone">商家电话:</label>
				<div class="">
					<input class="form-control" id="bizTelephone" name="bizTelephone" placeholder="请确定商家电话, 多个电话请用逗号(,)分隔">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="startDateTime">活动开始时间:</label>
				<div class="">
					<input class="form-control" id="startDateTime" name="startDateTime" required="required" placeholder="请输入开始时间">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="endDateTime">活动结束时间:</label>
				<div class="">
					<input class="form-control" id="endDateTime" name="endDateTime" required="required" placeholder="请输入结束时间">
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
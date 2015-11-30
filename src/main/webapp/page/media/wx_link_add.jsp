<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/wx_link.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加图片</h2>
  		</div>
  		<form id="wxLinkForm" class="form-horizontal" role="form" action='<cmn:base/>/web/media/wxLink/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="title">名称:</label>
 				<div class="">
 					<input class="form-control" id="title" name="title" minlength="2" required="required" placeholder="请输入图片主题">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="imgSrc">图片:</label>
 				<div class="">
 					<input class="" id="imgSrc" name="imgSrc" readonly required="required" placeholder="请上传图片">
 					<input class="" id="pic" name="pic" type="file">
 					<input class="" id="uploadBtn" name="uploadBtn" type="button" value="上传">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="linkUrl">链接:</label>
 				<div class="">
 					<input class="form-control" id="linkUrl" name="linkUrl" required="required" placeholder="请输入链接">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="display">是否显示:</label>
 				<div class="">
 					<input class="form-control" id="display" name="display" placeholder="请输入是否显示（0-不显示，1-显示）">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="orderNo">排序:</label>
 				<div class="">
 					<input class="form-control" id="orderNo" name="orderNo" required="required" placeholder="请输入排序序号">
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
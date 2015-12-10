<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/wx_link_single.js"></script>
    
    <style type="text/css">
    	fieldset.scheduler-border {
		    border: 1px groove #ddd !important;
		    padding: 0 1.4em 1.4em 1.4em !important;
		    margin: 0 0 1.5em 0 !important;
		    -webkit-box-shadow:  0px 0px 0px 0px #000;
		            box-shadow:  0px 0px 0px 0px #000;
		}
		
		legend.scheduler-border {
		    font-size: 1.2em !important;
		    font-weight: bold !important;
		    text-align: left !important;
		    
		    width:inherit; /* Or auto */
		    padding:0 10px; /* To give a bit of padding on the left and right */
		    border-bottom:none;
		}
    </style>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">单页面添加wx首页</h2>
  		</div>
  		
  		
  		<c:forEach begin="0" end="8" var="i">
	  		<!-- <div style="border: solid 1px #CCC; margin: 5px; padding: 20px;"> -->
	  		<div class="col-md-4 col-xs-12">
	  			<fieldset class="scheduler-border">
	  				<legend class="scheduler-border">Form ${i+1}</legend>
			  		<form id="wxLinkForm${i}" class="form-horizontal" role="form" action='<cmn:base/>/web/media/wxLink/add' method="post">
			 			<div class="form-group">
			 				<label class="control-label" for="title">名称:</label>
			 				<div class="">
			 					<input class="form-control" id="title" name="title" minlength="2" required="required" placeholder="请输入图片主题">
			 				</div>
			 			</div>
			 			<div class="form-group">
			 				<label class="control-label" for="imgSrc">图片:</label>
			 				<div class="">
			 					<input class="form-control" id="imgSrc" name="imgSrc" readonly="readonly" required="required" placeholder="请上传图片">
			 					<input class="" id="pic" name="pic" type="file">
			 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
			 				</div>
			 			</div>
			 			<div class="form-group">
			 				<label class="control-label" for="linkUrl">链接:</label>
			 				<div class="">
			 					<input class="form-control" id="linkUrl" name="linkUrl" required="required" placeholder="请输入链接">
			 				</div>
			 			</div>
			 			<!-- <div class="form-group">
			 				<label class="control-label" for="display">是否显示:</label>
			 				<div class="">
			 					<input class="form-control" id="display" name="display" placeholder="请输入是否显示（0-不显示，1-显示）">
			 				</div>
			 			</div>
			 			<div class="form-group">
			 				<label class="control-label" for="orderNo">排序:</label>
			 				<div class="">
			 					<input class="form-control" id="orderNo" name="orderNo" placeholder="请输入排序序号">
			 				</div>
			 			</div> -->
			 			<div class="form-group">
				 			<div class="">
				  				<button type="button" class="btn btn-default" id="addBtn">创建</button>
				 			</div>
			 			</div>
			  		</form>
	  			</fieldset>
	  		</div>
  		</c:forEach>
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>
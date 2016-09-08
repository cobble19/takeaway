<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/vote_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加投票信息</h2>
  		</div>
  		<form id="voteForm" class="form-horizontal" role="form" action='<cmn:base/>/web/media/vote/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="title">标题:</label>
 				<div class="">
 					<input class="form-control" id="title" name="title" minlength="2" required="required" placeholder="请输入本次互动主题">
 				</div>
 			</div>
 			<!-- <div class="form-group">
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
 			</div> -->
 			<div class="form-group">
 				<label class="control-label" for="content">介绍:</label>
 				<div style="" class="">
 					<textarea rows="5" cols="" id="content" name="content" placeholder="请输入内容" class="form-control"></textarea>
 					<!-- <input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
 					<!-- <script id="editor" type="text/plain" name="content"></script> -->
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="voteType">投票类型:</label>
 				<div class="">
 					<!-- <input class="form-control" id="userId" name="userId" minlength="2" required="required" placeholder="请选择本次奖品提供者"> -->
 					
 				</div>
 				<select id="voteType" name="voteType" autofocus="autofocus" class="form-control">
 						<option value="0">文字</option>
 						<option value="1">图片</option>
 						<option value="2" selected="selected">文字+图片</option>
 					</select>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="publishType">发布类型:</label>
 				<div class="">
 					<!-- <input class="form-control" id="userId" name="userId" minlength="2" required="required" placeholder="请选择本次奖品提供者"> -->
 					
 				</div>
 				<select id="publishType" name="publishType" autofocus="autofocus" class="form-control">
 						<option value="0" selected>未发布</option>
 						<option value="1">发布</option>
 					</select>
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
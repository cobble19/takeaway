<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">活动发布</h2>
  		</div>
  		<form id="activityForm" class="form-horizontal" role="form" action='<cmn:base/>/web/enterprise/activity/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="title">主题:</label>
 				<div class="">
 					<input class="form-control" id="title" name="title" minlength="2" required="required" placeholder="请输入本次活动主题">
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
 				<label class="control-label" for="content">活动介绍:</label>
 				<div style="" class="">
 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
 					<script id="editor" type="text/plain" name="content"></script>
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
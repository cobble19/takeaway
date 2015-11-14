<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/interactive_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改互动</h2>
  		</div>
  		<form id="interactiveForm" class="form-horizontal" role="form" action='<cmn:base/>/web/enterprise/interactive/add' method="post">
	  		<input type="hidden" id="interactiveId" name="interactiveId" value="<%=request.getParameter("interactiveId") %>"/>
 			<div class="form-group">
 				<label class="control-label" for="title">名称:</label>
 				<div class="">
 					<input class="form-control" id="name" name="name" minlength="2" required="required" placeholder="请输入本次互动主题">
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
 				<label class="control-label" for="title">活动规则:</label>
 				<div class="">
 					<input class="form-control" id="rule" name="rule" minlength="2" required="required" placeholder="请输入本次互动活动规则">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="title">奖品信息:</label>
 				<div class="">
 					<input class="form-control" id="prize" name="prize" minlength="2" required="required" placeholder="请输入本次互动活动奖品">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="userIdX">奖品提供者:</label>
 				<div class="">
 					<!-- <input class="form-control" id="userId" name="userId" minlength="2" required="required" placeholder="请选择本次奖品提供者"> -->
 					
 				</div>
 				<select id="userIdX" name="userId" autofocus="autofocus" class="form-control">
 						<option value="0" selected>--选择奖品提供者--</option>
 					</select>
 					<!-- <select class="form-control">
 						<option selected="selected" value="1">abc</option>
 					</select> -->
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="prizeEndDateTime">奖品截止时间:</label>
 				<div class="">
 					<input class="form-control" id="prizeEndDateTime" name="prizeEndDateTime" required="required" placeholder="请输入奖品截止时间">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="title">获奖人数:</label>
 				<div class="">
 					<input type="number" class="form-control" id="numOfWinner" name="numOfWinner" minlength="1" required="required" placeholder="请输入本次互动活动获奖人数">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="title">答案:</label>
 				<div class="">
 					<input class="form-control" id="answer" name="answer" minlength="1" required="required" placeholder="请输入本次互动答案">
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
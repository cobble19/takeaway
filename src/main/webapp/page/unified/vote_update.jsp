<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/vote_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加投票信息</h2>
  		</div>
  		<form id="voteForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/vote/add' method="post">
	  		<input type="hidden" id="voteId" name="voteId" value="<%=request.getParameter("voteId") %>"/>
 			<div class="form-group">
 				<label class="control-label" for="title">标题:</label>
 				<div class="">
 					<input class="form-control" id="title" name="title" minlength="2" required="required" placeholder="请输入本次互动主题">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="logoImg">图片:</label>
 				<div class="">
 					<input class="form-control" id="logoImg" name="logoImg" readonly="readonly" placeholder="请上传图片">
 					<input class="" id="pic" name="pic" type="file">
 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传图片">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="period">频率:</label>
 				<div class="">
 					<input class="form-control" id="period" name="period" placeholder="请输入投票周期(7-7天投一次票), 例如 1, 7">
 				</div>
 			</div><div class="form-group">
 				<label class="control-label" for="numOfPeriod">每次可投票个数:</label>
 				<div class="">
 					<input class="form-control" id="numOfPeriod" name="numOfPeriod" placeholder="请输入每周期投票个数(可给几个人投票), 例如1, 3名">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="activityId">活动ID:</label>
 				<div class="">
 					<input class="form-control" id="activityId" name="activityId" readonly="readonly" placeholder="请输入本次投票相关的活动ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="apply2AttrModelIds">显示属性模块ID:</label>
 				<div class="">
 					<input class="form-control" id="apply2AttrModelIds" name="apply2AttrModelIds" placeholder="请输入需显示属性模块IDS(,逗号分隔)">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="startDateTime">投票开始时间:</label>
 				<div class="">
 					<input class="form-control" id="startDateTime" name="startDateTime" required="required" placeholder="请输入开始时间">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="endDateTime">投票结束时间:</label>
 				<div class="">
 					<input class="form-control" id="endDateTime" name="endDateTime" required="required" placeholder="请输入结束时间">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="content">介绍:</label>
 				<div style="" class="">
 					<!-- <textarea rows="5" cols="" id="content" name="content" placeholder="请输入内容" class="form-control"></textarea> -->
 					<!-- <input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
 					<script id="editor" type="text/plain" name="content"></script>
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="voteType">投票类型:</label>
 				<div class="">
 					<!-- <input class="form-control" id="userId" name="userId" minlength="2" required="required" placeholder="请选择本次奖品提供者"> -->
 					
 				</div>
 				<select id="voteType" name="voteType" autofocus="autofocus" class="form-control">
 						<option value="0" selected>0</option>
 						<option value="1">1</option>
 						<option value="2">2</option>
 					</select>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="publishType">发布类型:</label>
 				<div class="">
 					<!-- <input class="form-control" id="userId" name="userId" minlength="2" required="required" placeholder="请选择本次奖品提供者"> -->
 					
 				</div>
 				<select id="publishType" name="publishType" autofocus="autofocus" class="form-control">
 						<option value="0" selected>0</option>
 						<option value="1">1</option>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity.js"></script> --%>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/activity_update.js"></script>
    
  </head>
  <body>
  
	<div class="container">
		<div id="editDiv">
			<form class="form-horizontal" id="activityForm" role="form" action='<cmn:base/>/web/enterprise/activity/add' method="post">
	  			<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
	  			<div class="form-group">
	 				<label class="control-label" for="typeCode">新增信息类型:</label>
	 				<div class="">
	 					<select class="form-control" id="typeCode" name="typeCode">
	   						<option value="" selected="selected">所有</option>
	   						<option value="1">报名申请</option>
	   						<option value="2">征集调查</option>
	   					</select>
	 					<!-- <input class="form-control" id="title" name="title" minlength="2" required="required" placeholder="请输入本次活动主题"> -->
	 				</div>
	 			</div>
	 			<div class="form-group">
	 				<label class="control-label" for="titleE">主题:</label>
	 				<div class="">
	 					<input class="form-control" id="titleE" name="title" minlength="2" required="required" placeholder="请输入本次活动主题">
	 				</div>
	 			</div>
	 			<!-- <div class="form-group">
	 				<label class="control-label" for="logoImg">图片:</label>
	 				<div class="">
	 					<input class="form-control" id="logoImg" name="logoImg" readonly="readonly" required="required" placeholder="请上传图片">
	 					<input class="" id="pic" name="pic" type="file">
	 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
	 				</div>
	 			</div> -->
	 			<div class="form-group">
	 				<label class="control-label" for="title">商家组织者:（本活动是否由商家组织，若是请选择商家，不是则选择自己）</label>
	 				<div class="">
	 					<!-- <input class="form-control" id="userId" name="userId" minlength="2" required="required" placeholder="请选择本次奖品提供者"> -->
	 					
	 				</div>
	 				<select id="userIdEnterpriseX" name="userIdEnterprise" autofocus="autofocus" class="form-control">
	 						<option value="-1" selected>--选择组织活动的商家--</option>
	 					</select>
	 					<!-- <select class="form-control">
	 						<option selected="selected" value="1">abc</option>
	 					</select> -->
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
	 				<label class="control-label" for="contentE">活动介绍:</label>
	 				<div style="" class="">
	 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
	 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
	 					<script id="editor" type="text/plain" name="content"></script>
	 				</div>
	 			</div>
	 			<div class="form-group">
		 			<div class="">
		  				<button type="button" class="btn btn-default" id="addBtn">提交</button>
		 			</div>
	 			</div>
	  		</form>
		</div>
  		
		<footer><br/><p>&copy; 版权所有</p></footer>
	</div> <!-- container -->
	
  </body>
</html>


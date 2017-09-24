<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/activity_registration_update.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<div id="editDiv">
			<form class="form-horizontal" id="activityRegistrationForm" role="form" action='<cmn:base/>/web/unified/activityregistration/add' method="post">
	  			<input type="hidden" id="activityRegistrationId" name="activityRegistrationId" value="<%=request.getParameter("activityRegistrationId") %>"/>
	 			<div class="form-group">
	 				<label class="control-label" for="titleE">主题:</label>
	 				<div class="">
	 					<input class="form-control" id="titleE" name="title" minlength="2" required="required" placeholder="请输入本次活动主题">
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
	 				<label class="control-label" for="needSubscribe">是否需要关注公众号并加入会员才能访问:</label>
	 				<div class="">
	 					<input class="form-control" id="needSubscribe" name="needSubscribe" placeholder="请输入是否开启检测(0-不开启,1-开启)">
	 				</div>
	 			</div>
	 			<div class="form-group">
	 				<label class="control-label" for="contentE">活动介绍:</label>
	 				<div style="" class="">
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


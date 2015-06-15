<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity.js"></script> --%>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity_update.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<%@include file="../../../reg_login.jsp" %>
		<div id="editDiv">
			<form class="form-horizontal" id="activityForm" role="form" action='<cmn:base/>/web/enterprise/activity/add' method="post">
	  			<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
	 			<div class="form-group">
	 				<label class="control-label" for="titleE">主题:</label>
	 				<div class="">
	 					<input class="form-control" id="titleE" name="title" placeholder="请输入本次活动主题">
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

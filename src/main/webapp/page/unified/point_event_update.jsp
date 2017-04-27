<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/point_event_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改积分事件</h2>
  		</div>
  		<form id="pointEventForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/pointEvent/add' method="post">
	  		<input type="hidden" id="pointEventId" name="pointEventId" value="<%=request.getParameter("pointEventId") %>"/>
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" value="${pointEventPOJO.userId}" placeholder="请输入用户ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="authorizerAppId">授权APP ID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppId" name="authorizerAppId" value="${pointEventPOJO.authorizerAppId}" placeholder="请输入授权APP ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="eventName">事件名称:</label>
 				<div class="">
 					<input class="form-control" id="eventName" name="eventName" value="${pointEventPOJO.eventName}" placeholder="请输入事件名称">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="pointNumPer">每次积分数:</label>
 				<div class="">
 					<input class="form-control" id="pointNumPer" name="pointNumPer" value="${pointEventPOJO.pointNumPer}" placeholder="请输入每次积分数">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="pointRate">获取积分频率:</label>
 				<div class="">
 					<input class="form-control" id="pointRate" name="pointRate" value="${pointEventPOJO.pointRate}" placeholder="请输入获取积分频率">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="createDateTime">创建日期:</label>
 				<div class="">
 					<input class="form-control" id="createDateTime" name="createDateTime" value="${pointEventPOJO.createDateTime}" placeholder="请输入创建日期">
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
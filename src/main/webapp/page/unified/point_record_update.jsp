<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/point_record_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改积分记录</h2>
  		</div>
  		<form id="pointRecordForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/pointRecord/add' method="post">
	  		<input type="hidden" id="pointRecordId" name="pointRecordId" value="<%=request.getParameter("pointRecordId") %>"/>
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" value="${pointRecordPOJO.userId}" placeholder="请输入用户ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="openId">OPEN ID:</label>
 				<div class="">
 					<input class="form-control" id="openId" name="openId" value="${pointRecordPOJO.openId}" required="required" placeholder="请输入OPEN ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="authorizerAppId">授权APP ID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppId" name="authorizerAppId" value="${pointRecordPOJO.authorizerAppId}" placeholder="请输入授权APP ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="pointNum">积分:</label>
 				<div class="">
 					<input class="form-control" id="pointNum" name="pointNum" value="${pointRecordPOJO.pointNum}" placeholder="请输入积分">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="pointReason">积分原因:</label>
 				<div class="">
 					<input class="form-control" id="pointReason" name="pointReason" value="${pointRecordPOJO.pointReason}" placeholder="请输入积分原因">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="reateDateTime">创建日期:</label>
 				<div class="">
 					<input class="form-control" id="createDateTime" name="createDateTime" value="${pointRecordPOJO.createDateTime}" placeholder="请输入创建日期">
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
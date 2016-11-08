<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/award_record_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改奖品记录</h2>
  		</div>
  		<form id="awardRecordForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/awardRecord/add' method="post">
	  		<input type="hidden" id="awardRecordId" name="awardRecordId" value="<%=request.getParameter("awardRecordId") %>"/>
 			<div class="form-group">
 				<label class="control-label" for="interactiveId">互动ID:</label>
 				<div class="">
 					<input class="form-control" id="interactiveId" name="interactiveId" value="${awardRecordPOJO.interactiveId}" required="required" placeholder="请输入互动ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="awardId">奖品ID:</label>
 				<div class="">
 					<input class="form-control" id="awardId" name="awardId" value="${awardRecordPOJO.awardId}" placeholder="请输入奖品ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" value="${awardRecordPOJO.userId}" placeholder="请输入用户ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="hitDateTime">获奖日期:</label>
 				<div class="">
 					<input class="form-control" id="hitDateTime" name="hitDateTime" value="${awardRecordPOJO.hitDateTime}" placeholder="请输入获奖日期">
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
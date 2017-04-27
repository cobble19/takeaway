<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/point_summary_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改积分总结记录</h2>
  		</div>
  		<form id="pointSummaryForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/pointSummary/add' method="post">
	  		<input type="hidden" id="pointSummaryId" name="pointSummaryId" value="<%=request.getParameter("pointSummaryId") %>"/>
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" value="${pointSummaryPOJO.userId}" placeholder="请输入用户ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="pointTotal">总积分:</label>
 				<div class="">
 					<input class="form-control" id="pointTotal" name="pointTotal" value="${pointSummaryPOJO.pointTotal}" placeholder="请输入总积分">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="pointUsed">已用积分:</label>
 				<div class="">
 					<input class="form-control" id="pointUsed" name="pointUsed" value="${pointSummaryPOJO.pointUsed}" placeholder="请输入已用积分">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="pointRemainder">剩余积分:</label>
 				<div class="">
 					<input class="form-control" id="pointRemainder" name="pointRemainder" value="${pointSummaryPOJO.pointRemainder}" placeholder="请输入剩余积分">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="createDateTime">创建日期:</label>
 				<div class="">
 					<input class="form-control" id="createDateTime" name="createDateTime" value="${pointSummaryPOJO.createDateTime}" placeholder="请输入创建日期">
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
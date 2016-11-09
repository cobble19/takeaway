<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/award_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加奖品</h2>
  		</div>
  		<form id="awardForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/award/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="interactiveId">互动ID:</label>
 				<div class="">
 					<input class="form-control" id="interactiveId" name="interactiveId" value="<%=request.getParameter("interactiveId") %>" required="required" placeholder="请输入互动ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="name">名称:</label>
 				<div class="">
 					<input class="form-control" id="name" name="name" placeholder="请输入名称">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="description">描述:</label>
 				<div class="">
 					<input class="form-control" id="description" name="description" placeholder="请输入描述">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="amount">奖品总量:</label>
 				<div class="">
 					<input class="form-control" id="amount" name="amount" placeholder="请输入奖品总量">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="balance">奖品余额:</label>
 				<div class="">
 					<input class="form-control" id="balance" name="balance" placeholder="请输入奖品余额">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="weight">权重:</label>
 				<div class="">
 					<input class="form-control" id="weight" name="weight" placeholder="请输入权重">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="orderNo">序号:</label>
 				<div class="">
 					<input class="form-control" id="orderNo" name="orderNo" placeholder="请输入序号">
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
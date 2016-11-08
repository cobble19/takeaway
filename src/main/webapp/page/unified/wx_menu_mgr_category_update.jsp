<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_category_update.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">修改微信菜单button</h2>
  		</div>
  		<form id="wxMenuMgrCategoryForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/wxMenuMgrCategory/add' method="post">
	  		<input type="hidden" id="wxMenuMgrCategoryId" name="wxMenuMgrCategoryId" value="<%=request.getParameter("wxMenuMgrCategoryId") %>"/>
 			<div class="form-group">
 				<label class="control-label" for="authorizerAppId">公众号APPID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppId" name="authorizerAppId" value="${wxMenuMgrCategoryPOJO.authorizerAppId}" required="required" placeholder="请输入公众号APPID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="name">名称:</label>
 				<div class="">
 					<input class="form-control" id="name" name="name" value="${wxMenuMgrCategoryPOJO.name}" placeholder="请输入名称">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="description">描述:</label>
 				<div class="">
 					<input class="form-control" id="description" name="description" value="${wxMenuMgrCategoryPOJO.description}" placeholder="请输入描述">
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
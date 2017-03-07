<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/rel_wx_index_map_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加微信微官网</h2>
  		</div>
  		<form id="relWxIndexMapForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/relWxIndexMap/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="userId">用户ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" value='${sessionScope.myUser.userId}' readonly="readonly" required="required" placeholder="请输入用户ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="wxIndexCode">微官网简码:</label>
 				<div class="">
 					<input class="form-control" id="wxIndexCode" name="wxIndexCode" placeholder="请输入微官网简码">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="wxTemplateId">微信模板ID:</label>
 				<div class="">
 					<input class="form-control" id="wxTemplateId" name="wxTemplateId" placeholder="请输入微信模板ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="wxStaticPage">静态页面URL:</label>
 				<div class="">
 					<input class="form-control" id="wxStaticPage" name="wxStaticPage" placeholder="请输入静态页面URL">
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
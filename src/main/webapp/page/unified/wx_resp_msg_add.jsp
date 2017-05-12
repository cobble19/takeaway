<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_resp_msg_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加回复信息</h2>
  		</div>
  		<form id="wxRespMsgForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/wxRespMsg/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="msgReceive">接受关键字:</label>
 				<div class="">
 					<input class="form-control" id="msgReceive" name="msgReceive" required="required" placeholder="请输入接受关键字">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="msgSend">回复关键字:</label>
 				<div id="msgSendWrap" class="">
 					<input class="form-control" id="msgSend" name="msgSend" required="required" placeholder="请输入回复关键字">
 				</div>
 			</div>
 			<div class="form-group" style="display: none;">
 				<label class="control-label" for="msgType">关键字类型:</label>
 				<!-- <div class="">
 					<input class="form-control" id="msgType" name="msgType" placeholder="请输入关键字类型">
 				</div> -->
 				<div class="">
 					<select class="form-control" id="msgType" name="msgType" required="required">
						<option value="1" selected="selected">自定义关键字</option>
						<option value="0">系统关键字</option>
						<option value="2">抽奖关键字</option>
					</select>
 				</div>
 			</div>
 			<div class="form-group" style="display: none;">
 				<label class="control-label" for="userId">规则所有者ID:</label>
 				<div class="">
 					<input class="form-control" id="userId" name="userId" value="${sessionScope.myUser.userId}" placeholder="请输入规则所有者ID">
 				</div>
 			</div>
 			<div class="form-group" style="display: none;">
 				<label class="control-label" for="authorizerAppId">公众号APPID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppId" name="authorizerAppId"value="${sessionScope.authorizerAppId}" placeholder="请输入公众号APPID">
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
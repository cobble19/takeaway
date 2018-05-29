<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_msg_event_log_add.js"></script>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">添加事件</h2>
  		</div>
  		<form id="wxMsgEventLogForm" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/wxMsgEventLog/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="authorizerAppId">APPID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppId" name="authorizerAppId" placeholder="请输入APPID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="signature">签名:</label>
 				<div class="">
 					<input class="form-control" id="signature" name="signature" placeholder="请输入签名">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="timestamp">时间戳:</label>
 				<div class="">
 					<input class="form-control" id="timestamp" name="timestamp" placeholder="请输入时间戳">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="nonce">nonce:</label>
 				<div class="">
 					<input class="form-control" id="nonce" name="nonce" placeholder="请输入nonce">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="openId">openId:</label>
 				<div class="">
 					<input class="form-control" id="openId" name="openId" placeholder="请输入openId">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="encryptType">加密类型:</label>
				<div class="">
					<input class="form-control" id="encryptType" name="encryptType" placeholder="加密类型">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="msgSignature">文本签名:</label>
 				<div class="">
 					<input class="form-control" id="msgSignature" name="msgSignature" placeholder="请输入文本签名">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="toUserName">微信公众号用户名:</label>
				<div class="">
					<input class="form-control" id="toUserName" name="toUserName" placeholder="请输入微信公众号用户名">
				</div>
			</div>
 			<div class="form-group">
 				<label class="control-label" for="fromUserName">信息发送者同openId:</label>
 				<div class="">
 					<input class="form-control" id="fromUserName" name="fromUserName" placeholder="信息发送者同openId">
 				</div>
 			</div>
			<div class="form-group">
				<label class="control-label" for="createTime">创建时间:</label>
				<div class="">
					<input class="form-control" id="createTime" name="createTime" placeholder="微信时间创建时间">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="msgType">信息类型:</label>
				<div class="">
					<input class="form-control" id="msgType" name="msgType" placeholder="信息类型">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="event">事件类型:</label>
				<div class="">
					<input class="form-control" id="event" name="event" placeholder="事件类型">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="rawData">原始数据:</label>
				<div class="">
					<input class="form-control" id="rawData" name="rawData" placeholder="原始数据">
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
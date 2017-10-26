<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
		<%@include file="../../page/common/head.jsp"%>
		
		<link href="<cmn:base/>/css/unified/activity_list.css" rel="stylesheet">
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/progress_dialog.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/update_password_single.js"></script>
	</head>
	
  <body style="padding-top: 100px;">
  	<%@include file="../../reg_login.jsp" %>
  	<div class="container-fluid">
        <div class="row" style=" height:4px; background-color:#44b549;"></div>
        <div class="row" style=" height:36px; background-color:#e7e8eb;"></div>
  		<div class="row" style="min-height:500px; border:1px solid #CCC;">
  			<div class="col-md-3 col-xs-4" style="padding:30px 0px 0px 0px; border-right:1px solid #e7e7eb; text-align:center;" id="sidebar">
                  <%@include file="menu_left.jsp" %>
            </div>
	     	<div class="col-md-9 col-xs-8" style="min-height:500px;">
	     		<div id="uc_content" style="padding-top:40px;">
        			
	     			<div id="update_password">
                        <form id="pwdForm" role="form" action='' method="post">
					  	<input type="hidden" id="userId" name="userId" value="${myUser.userId}"/>
                        <div class="panel panel-success">
						  <!-- Default panel contents -->
						  <div class="panel-heading"><p class="h5" style="font-weight:bold;">密码修改</p></div>
						  <!-- List group -->
						    <ul class="list-group">                        
							    <li class="list-group-item">
									<div class="form-inline">
						 				<label class="h5" for="passwordOld">旧密码:</label>
					 					<input type="password" class="form-control input-sm" id="passwordOld" name="passwordOld" minlength="2" required="required" placeholder="请输入旧密码">
						 			</div>
						 		</li>
							    <li class="list-group-item">
							    	<div class="form-inline">
						 				<label class="h5" for="password">新密码:</label>
						 				<input type="password" class="form-control input-sm" id="password" name="password" minlength="2" required="required" placeholder="请输入新密码">
						 			</div>
						 		</li>
						  </ul>
						  <div class="panel-footer" style="text-align:right;"><button type="button" class="btn btn-xs btn-default" id="pwdChg">修改密码</button></div>
						</div></form>
					</div>
                        
				<!-- content end -->
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
					
	     			
